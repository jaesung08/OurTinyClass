import { axios } from "@/lib/axios";

export const createBoard = async (
  title: string,
  content: string,
  articleType: string
) => {
  return await axios.post("/board", {
    title,
    content,
    articleType,
  });
};
