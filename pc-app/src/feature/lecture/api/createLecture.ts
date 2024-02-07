import { API_URL } from "@/config";
import { commonAxios } from "@/lib/commonAxios";

export const createLecture = async (
  memberId: number,
  title: string,
  description: string,
  dayOfWeek: number,
  timeTable: number,
  lectureType: number,
  lectureCategoryType: number,
  selectDate: Date
) => {
  return commonAxios.post(`${API_URL}/lectures`, {
    memberId,
    title,
    description,
    dayOfWeek,
    timeTable,
    lectureType,
    lectureCategoryType,
    selectDate,
  });
};
export const getLecture = async () => {
  return commonAxios.get(`${API_URL}/lectures`);
};
