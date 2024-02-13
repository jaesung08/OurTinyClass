import { CODE } from "@/types/Code";

export const LectureCategory = [
  {
    id: CODE.LECTURE_CATEGORY.ALL,
    name: "전체",
  },
  {
    id: CODE.LECTURE_CATEGORY.MATH,
    name: "수학",
  },
  {
    id: CODE.LECTURE_CATEGORY.SCIENCE,
    name: "과학",
  },
  {
    id: CODE.LECTURE_CATEGORY.ART,
    name: "미술",
  },
  {
    id: CODE.LECTURE_CATEGORY.HEALTH,
    name: "건강",
  },
  {
    id: CODE.LECTURE_CATEGORY.READING,
    name: "독서",
  },
  {
    id: CODE.LECTURE_CATEGORY.FUTURE,
    name: "상담",
  },
  {
    id: CODE.LECTURE_CATEGORY.COMMUNICATION,
    name: "소통",
  },
  {
    id: CODE.LECTURE_CATEGORY.ETC,
    name: "기타",
  },
];

export const ClassTime = [
  { name: "1교시(9:00 ~ 9:50)", value: 0 },
  { name: "2교시(10:00 ~ 10:50)", value: 1 },
  { name: "3교시(11:00 ~ 11:50)", value: 2 },
  { name: "4교시(13:00 ~ 13:50)", value: 3 },
  { name: "5교시(14:00 ~ 14:50)", value: 4 },
  { name: "6교시(15:00 ~ 15:50)", value: 5 },
];

export const SearchLecturesOptions = [
  { name: "제목", value: "title" },
  { name: "내용", value: "description" },
  { name: "요일", value: "dayOfWeek" },
  { name: "시간", value: "timeTable" },
];
