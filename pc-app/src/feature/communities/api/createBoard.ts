import { commonAxios } from "@/lib/commonAxios";

export const createBoard = (
  title: string,
  content: string,
  articleType: string
) => {
  return commonAxios.post("/community/articles", {
    title,
    content,
    articleType,
  });
};
