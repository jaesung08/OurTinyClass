export type ChatRoom = {
  roomId: number;
  roomMemberList: Array<{
    memberId: string;
    name: string;
    id: number;
  }>;
  lastChatId: number; // 프론트가 갖고 있는데, < 백엔드가 로그인할 때 알려줘야함.
  lastMessage: string;
  chatList: Chat[];
};

export type Chat = {
  senderId?: number;
  senderName: string;
  chatId?: number;
  roomId: number;
  content: string;
  isSystem: boolean;
};
