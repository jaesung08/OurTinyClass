import { ChatRoom } from "@/feature/chat/types";
import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist(); // atom을 localStorage에 저장하기 위한 라이브러리

export const roomList = atom<Array<ChatRoom>>({
  key: "chatRoomList",
  default: [],
  effects_UNSTABLE: [persistAtom], // effects_UNSTABLE을 적어주어야 recoil-persist가 적용됨.
});
