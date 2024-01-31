import { axios } from "@/lib/axios";

export const createBoard = (
  title: string,
  content: string,
  articleType: string
) => {
  return axios.post("/board", {
    title,
    content,
    articleType,
  });
};
