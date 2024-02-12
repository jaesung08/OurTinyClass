import { commonAxios } from "@/lib/commonAxios";
import {
  CreateChatRoomResponse,
  GetChatListResponse,
  GetChatRoomListResponse,
} from "../types";

// 채팅 개인방 생성
export const createRoom = (
  targetMemberId: string
): Promise<CreateChatRoomResponse> => {
  return commonAxios.post("/chatrooms", { targetMemberId });
};

// 채팅방 조회
export const getChatRoomList = (): Promise<GetChatRoomListResponse> => {
  return commonAxios.get("/chatrooms");
};

// 메세지 조회
export const getChatList = (
  chatRoomId: number
): Promise<GetChatListResponse> => {
  return commonAxios.get(`/chatrooms/${chatRoomId}`);
};
