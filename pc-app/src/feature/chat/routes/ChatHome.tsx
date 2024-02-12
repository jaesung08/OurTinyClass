import {
  MainContainer,
  ChatContainer,
  MessageList,
  MessageInput,
  ConversationList,
  Conversation,
  ConversationHeader,
  Message,
  Sidebar,
} from "@chatscope/chat-ui-kit-react";
import "@chatscope/chat-ui-kit-styles/dist/default/styles.min.css";
import "../assets/customChatStyles.scss";
import { useRecoilState, useRecoilValue } from "recoil";
import { roomList } from "@/atoms/chatRoom";
import { Chat, ChatRoom } from "../types";
import { useEffect, useMemo, useState } from "react";
import { IMessage, useStompClient, useSubscription } from "react-stomp-hooks";
import { userState } from "@/atoms/user";
import { createRoom, getChatList, getChatRoomList } from "../api/chatRoom";
import {
  Button,
  Listbox,
  ListboxItem,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  Tooltip,
  User,
  useDisclosure,
} from "@nextui-org/react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCommentMedical } from "@fortawesome/free-solid-svg-icons";
import Swal from "sweetalert2";

const friendList = [
  {
    memberId: "abc",
    name: "박재선",
    profileImage: "https://i.pravatar.cc/150?u=a04258114e29026702d",
  },
  {
    memberId: "aaaa",
    name: "정서희",
    profileImage: "https://i.pravatar.cc/150?u=a04258114e29026702d",
  },
  {
    memberId: "abgggc",
    name: "장재성",
    profileImage: "https://i.pravatar.cc/150?u=a04258114e29026702d",
  },
  {
    memberId: "abasdgtec",
    name: "최근영",
    profileImage: "https://i.pravatar.cc/150?u=a04258114e29026702d",
  },
  {
    memberId: "abasdg2tec",
    name: "최창호",
    profileImage: "https://i.pravatar.cc/150?u=a04258114e29026702d",
  },
  {
    memberId: "abaasdg2tec",
    name: "전영빈",
    profileImage: "https://i.pravatar.cc/150?u=a04258114e29026702d",
  },
];

type CreateChatRoomModalProps = {
  isOpen: boolean;
  onOpenChange: (isOpen: boolean) => void;
  onSubmit: (memberId: string) => void;
  isLoading: boolean;
};

const CreateChatRoomModal = ({
  isOpen,
  onOpenChange,
  onSubmit,
  isLoading,
}: CreateChatRoomModalProps) => {
  const [selectedKeys, setSelectedKeys] = useState(new Set(["text"]));
  const selectedValue = useMemo(
    () => Array.from(selectedKeys).join(", "),
    [selectedKeys]
  );

  const onSelectItem = (key: string) => {
    setSelectedKeys(new Set([key]));
  };

  return (
    <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              대화상대 선택
            </ModalHeader>
            <ModalBody>
              <div className=" h-96 overflow-auto">
                <Listbox
                  aria-label="Single selection example"
                  variant="flat"
                  disallowEmptySelection
                  selectionMode="single"
                  selectedKeys={selectedKeys}
                >
                  {friendList.map((friend) => (
                    <ListboxItem
                      key={friend.memberId}
                      onPress={() => onSelectItem(friend.memberId)}
                    >
                      <User
                        name={friend.name}
                        avatarProps={{ src: friend.profileImage }}
                      />
                    </ListboxItem>
                  ))}
                </Listbox>
              </div>
            </ModalBody>
            <ModalFooter>
              <Button color="danger" variant="light" onPress={onClose}>
                취소
              </Button>
              <Button
                isLoading={isLoading}
                color="success"
                onPress={() => onSubmit(selectedValue)}
              >
                생성
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  );
};

const ChatHome = () => {
  const [chatRoomList, setChatRoomList] = useRecoilState(roomList);
  const { isOpen, onOpen, onOpenChange } = useDisclosure();
  const userInfo = useRecoilValue(userState);
  const stompClient = useStompClient();
  const makeRoomName = (room: ChatRoom) => {
    return room.roomMemberList.map((member) => member.name).join(", ");
  };
  const [currentRoom, setCurrentRoom] = useState(chatRoomList[0]);
  const [currentRoomId, setCurrentRoomId] = useState(0);
  const [chatList, setChatList] = useState<Chat[]>([]);
  const [isCreateChatRoomLoading, setIsCreateChatRoomLoading] = useState(false);

  useSubscription("/sub/room/1", (message) => recevHandler(message));

  function recevHandler(message: IMessage) {
    const messageBody = JSON.parse(message.body);
    if (currentRoomId == messageBody.roomId) {
      setChatList((chatList) => [...chatList, messageBody]);
    }
  }

  const onSendMessage = (innerHtml: string) => {
    if (stompClient) {
      stompClient.publish({
        destination: "/pub/message",
        body: JSON.stringify({
          roomId: 1,
          message: innerHtml,
          senderId: userInfo.memberId,
          isSystem: false,
        }),
      });
    }
  };

  const onCreateChatRoom = async (memberId: string) => {
    try {
      setIsCreateChatRoomLoading(true);
      const res = await createRoom(memberId);
      setCurrentRoomId(res.data.roomId);
    } catch (e) {
      Swal.fire(
        "채팅방 생성 실패",
        "채팅방 생성이 실패하였습니다. 다시 시도해주세요.",
        "error"
      );
    } finally {
      setIsCreateChatRoomLoading(false);
    }
  };

  useEffect(() => {
    setCurrentRoom(chatRoomList[currentRoomId]);
  }, [chatRoomList, currentRoomId]);

  useEffect(() => {
    const fetchChatList = async () => {
      try {
        const res = await getChatList(currentRoomId);
        setChatList(res.data);
      } catch (e) {
        console.error(e);
      }
    };
    fetchChatList();
  }, [currentRoomId]);

  useEffect(() => {
    const fetchChatRoomList = async () => {
      try {
        const res = await getChatRoomList();
        setChatRoomList(res.data);
      } catch (e) {
        console.error(e);
      }
    };
    fetchChatRoomList();
  }, [setChatRoomList]);

  return (
    <div className="relative w-full h-full">
      <MainContainer>
        <Sidebar position="left">
          <div className="flex justify-end p-2">
            <Tooltip color="success" content="채팅방 생성">
              <Button isIconOnly onPress={onOpen} className=" bg-lime-300">
                <FontAwesomeIcon size="2xl" icon={faCommentMedical} />
              </Button>
            </Tooltip>
          </div>

          <ConversationList>
            {chatRoomList.map((room) => (
              <Conversation
                key={room.roomId}
                name={makeRoomName(room)}
                active={
                  currentRoom && currentRoom.roomId === room.roomId
                    ? true
                    : false
                }
                onClick={() => setCurrentRoomId(room.roomId)}
              />
            ))}
          </ConversationList>
        </Sidebar>

        <ChatContainer>
          <ConversationHeader>
            <ConversationHeader.Content
              userName={makeRoomName(currentRoom)}
              info="Active 10 mins ago"
            />
          </ConversationHeader>

          <MessageList>
            {chatList.map((chat) => (
              <Message
                model={{
                  message: chat.message,
                  sentTime: "15 mins ago",
                  sender: chat.senderName,
                  direction:
                    chat.memberId === userInfo.memberId
                      ? "outgoing"
                      : "incoming",
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
      <CreateChatRoomModal
        isOpen={isOpen}
        onOpenChange={onOpenChange}
        isLoading={isCreateChatRoomLoading}
        onSubmit={onCreateChatRoom}
      />
    </div>
  );
};
export default ChatHome;
