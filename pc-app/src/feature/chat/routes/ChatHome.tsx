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
import { useRecoilValue } from "recoil";
import { roomList } from "@/atoms/chatRoom";
import {  ChatRoom } from "../types";
import { useState } from "react";
import { IMessage, useStompClient, useSubscription } from "react-stomp-hooks";

const ChatHome = () => {
  const roomListInfo = useRecoilValue(roomList);
  const stompClient = useStompClient();

  const makeRoomName = (room: ChatRoom) => {
    return room.roomMemberList.map((member) => member.name).join(", ");
  };
  const [currentRoom, setCurrentRoom] = useState(roomListInfo[0]);
  
  useSubscription("/sub/channel/3", (message) => recevHandler(message));
  
  function recevHandler (message:IMessage) {
    const messageBody = JSON.parse(message.body);
    setCurrentRoom((_currentRoom) => ({
      ..._currentRoom,
      chatList: [...currentRoom.chatList, messageBody],
    }));
  }
  const onSendMessage = (innerHtml: string) => {
    if (stompClient) {
      stompClient.publish({
        destination: "/pub/message",
        body: JSON.stringify({
          roomId: 3,
          content: innerHtml,
        }),
      })
    }

  };

  return (
    <div className="relative w-full h-full">
      <MainContainer>
        <ConversationList>
          {roomListInfo.map((room) => (
            <Conversation
              key={room.roomId}
              name={makeRoomName(room)}
              active={
                currentRoom && currentRoom.roomId === room.roomId ? true : false
              }
              onClick={() => setCurrentRoom(room)}
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
                    message: chat.content,
                    sentTime: "15 mins ago",
                    sender: chat.senderName,
                    direction: "incoming",
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
