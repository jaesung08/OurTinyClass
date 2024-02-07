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
  return commonAxios.post(`/lectures`, {
    memberId,
    title,
    description,
    dayOfWeek,
    timeTable,
    lectureType,
    lectureCategoryType,
    date: selectDate,
  });
};

export const getLecture = async () => {
  return commonAxios.get(`/lectures`);
};

export const deleteLecture = async (lectureId: number) => {
  return commonAxios.delete(`/lectures/${lectureId}`);
};

export const getEditLecture = async (lectureId: number) => {
  return commonAxios.get(`/lectures/${lectureId}`);
};

export const editLecture = async (
  memberId: string | number,
  title: string,
  description: string,
  dayOfWeek: number,
  timeTable: number,
  lectureType: number,
  lectureCategoryType: number,
  selectDate: Date,
  lectureId: number
) => {
  return commonAxios.patch(`/lectures/${lectureId}`, {
    memberId: 21,
    title,
    description,
    dayOfWeek,
    timeTable,
    lectureType,
    lectureCategoryType,
    date: selectDate,
  });
};
