import { axios } from "@/lib/axios";

export const freeBoard = async () => {
  return await axios.get("/board", {
    params: {
      boardType: "notice",
    },
  });
};
