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

const ChatHome = () => {
  return (
    <div className="relative w-full h-full">
      <MainContainer>
        <ConversationList>
          <Conversation
            name="김싸피"
            unreadCnt={3}
            unreadDot={true}
            active={true}
          />
          <Conversation name="김싸피" unreadCnt={3} unreadDot={true} />
          <Conversation name="김싸피" unreadCnt={3} unreadDot={true} />
        </ConversationList>
        <ChatContainer>
          <ConversationHeader>
            <ConversationHeader.Content
              userName="Akane"
              info="Active 10 mins ago"
            />
          </ConversationHeader>

          <MessageList>
            <Message
              model={{
                message: "Hello my friend",
                sentTime: "15 mins ago",
                sender: "Emily",
                direction: "incoming",
                position: "single",
              }}
            ></Message>
            <Message
              model={{
                message: "Hello my friend",
                sentTime: "15 mins ago",
                direction: "outgoing",
                position: "single",
              }}
            />
            <Message
              model={{
                message: "Hello my friend",
                sentTime: "15 mins ago",
                sender: "Emily",
                direction: "incoming",
                position: "single",
              }}
            />
          </MessageList>
          <MessageInput placeholder="Type message here" />
        </ChatContainer>
      </MainContainer>
    </div>
  );
};
export default ChatHome;
