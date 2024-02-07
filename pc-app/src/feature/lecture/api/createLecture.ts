import { commonAxios } from "@/lib/commonAxios";

export const createLecture = async (
  memberId: number,
  title: string,
  description: string,
  dayOfWeek: number,
  timeTable: number,
  lectureType: number,
  lectureCategoryType: number,
  date: Date
) => {
  return commonAxios.post("http://70.12.247.27:8080/api/lectures", {
    memberId,
    title,
    description,
    dayOfWeek,
    timeTable,
    lectureType,
    lectureCategoryType,
    date,
  });
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
