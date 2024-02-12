import { BaseResponse } from "./../../../types/BaseResponse";

export type ChatRoom = {
  roomId: number;
  roomMemberList: Array<{
    memberId: string;
    name: string;
    id: number;
    profileImage: string;
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
  message: string;
  isSystem: boolean;
  memberId: string;
};

export interface CreateChatRoomResponse extends BaseResponse {
  data: {
    roomId: number;
  };
}

export interface GetChatRoomListResponse extends BaseResponse {
  data: ChatRoom[];
}

export interface GetChatListResponse extends BaseResponse {
  data: Chat[];
}
