import { axios } from "@/lib/axios";

export const getDetail = async (article_id: string) => {
  return await axios.get(`/board/${article_id}`);
};

export const editDetail = async (
  title: string,
  content: string,
  article_id: string
) => {
  return await axios.post(`/board/${article_id}`, {
    title,
    content,
  });
};
