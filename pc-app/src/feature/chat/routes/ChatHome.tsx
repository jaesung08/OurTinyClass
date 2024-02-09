import {
  MainContainer,
  ChatContainer,
  MessageList,
  MessageInput,
  ConversationList,
  Conversation,
  ConversationHeader,
  Message,
} from "@chatscope/chat-ui-kit-react";
import "@chatscope/chat-ui-kit-styles/dist/default/styles.min.css";
import "../assets/customChatStyles.scss";
import { useRecoilState, useRecoilValue } from "recoil";
import { roomList } from "@/atoms/chatRoom";
import {  ChatRoom } from "../types";
import { useEffect, useState } from "react";
import { IMessage, useStompClient, useSubscription } from "react-stomp-hooks";
import { userState } from "@/atoms/user";

const ChatHome = () => {
  const [chatRoomList, setChatRoomList] = useRecoilState(roomList);
  const userInfo = useRecoilValue(userState)
  const stompClient = useStompClient();
  const makeRoomName = (room: ChatRoom) => {
    return room.roomMemberList.map((member) => member.name).join(", ");
  };
  const [currentRoom, setCurrentRoom] = useState(chatRoomList[0]);
  const [currentRoomId, setCurrentRoomId] = useState(0);
  
  useSubscription("/sub/room/1", (message) => recevHandler(message));
  
  function recevHandler (message:IMessage) {
    const messageBody = JSON.parse(message.body);
    const newChatRoomList = chatRoomList.map(room => {
      // roomId가 일치하는 경우, 해당 room의 chatList에 messageBody 추가
      if (room.roomId === messageBody.roomId) {
        return {
          ...room, // room 객체의 모든 프로퍼티를 복사
          chatList: [...room.chatList, messageBody], // chatList에 messageBody 추가
        };
      }
      // roomId가 일치하지 않는 경우, room 객체 변경 없이 반환
      return room;
    })
    setChatRoomList(newChatRoomList)
  }

  const onSendMessage = (innerHtml: string) => {
    if (stompClient) {
      stompClient.publish({
        destination: "/pub/message",
        body: JSON.stringify({
          roomId: 1,
          message: innerHtml,
          senderId: userInfo.memberId,
          isSystem: false
        }),
      })
    }
  };

  useEffect(() => {
    setCurrentRoom(chatRoomList[currentRoomId])
  }, [chatRoomList, currentRoomId])

  return (
    <div className="relative w-full h-full">
      <MainContainer>
        <ConversationList>
          { chatRoomList.map((room) => (
            <Conversation
              key={room.roomId}
              name={makeRoomName(room)}
              active={
                currentRoom && currentRoom.roomId === room.roomId ? true : false
              }
              onClick={() => setCurrentRoomId(room.roomId)}
            />
          ))}
        </ConversationList>
        <ChatContainer>
          <ConversationHeader>
            <ConversationHeader.Content
              userName={makeRoomName(currentRoom)}
              info="Active 10 mins ago"
            />
          </ConversationHeader>

          <MessageList>
            {currentRoom &&
              currentRoom.chatList.map((chat) => (
                <Message
                  model={{
                    message: chat.message,
                    sentTime: "15 mins ago",
                    sender: chat.senderName,
                    direction: chat.memberId === userInfo.memberId ? 'outgoing': 'incoming',
                    position: "single",
                  }}
                  key={chat.chatId}
                ></Message>
              ))}
          </MessageList>
          <MessageInput
            placeholder="Type message here"
            attachButton={false}
            onSend={onSendMessage}
          />
        </ChatContainer>
      </MainContainer>
    </div>
  );
};
export default ChatHome;
