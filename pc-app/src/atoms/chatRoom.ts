import { Chat, ChatRoom } from "@/feature/chat/types";
import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist(); // atom을 localStorage에 저장하기 위한 라이브러리

export const roomList = atom<Array<ChatRoom>>({
  key: "chatRoomList",
  default: [
    {
      roomId: 1,
      roomMemberList: [
        {
          memberId: "aa",
          name: "amily",
          id: 3,
        },
        {
          memberId: "asdf",
          name: "bloom",
          id: 4,
        },
      ],
      lastChatId: 1,
      lastMessage: "Hello",
      chatList: [],
    },
    {
      roomId: 2,
      roomMemberList: [
        {
          memberId: "aa",
          name: "amily",
          id: 3,
        },
        {
          memberId: "asdf",
          name: "amily",
          id: 4,
        },
      ],
      lastChatId: 1,
      lastMessage: "Hello aad",
      chatList: [] as Chat[],
    },
  ],
  effects_UNSTABLE: [persistAtom], // effects_UNSTABLE을 적어주어야 recoil-persist가 적용됨.
});
