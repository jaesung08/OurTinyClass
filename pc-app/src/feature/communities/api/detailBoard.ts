import { commonAxios } from "@/lib/commonAxios";

export const getDetail = async (article_id: string) => {
  return await commonAxios.get(`/community/articles/${article_id}`);
};

export const editDetail = async (
  title: string,
  content: string,
  article_id: string
) => {
  return await commonAxios.patch(`/community/articles/${article_id}`, {
    title,
    content,
  });
};
