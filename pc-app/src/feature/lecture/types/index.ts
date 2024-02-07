export interface LectureType {
  id: number;
  approved: string | null;
  date: string | null;
  memberId: string | null;
  title: string | null;
  description: string | null;
  dayOfWeek: number | null;
  timeTable: number | null;
  lectureType: number | null;
  lectureCategory: number | null;
  member: {
    name: string | null;
    role: number | string | null;
  };
}

export interface LectureCardProps {
  lecture: LectureType;
  deleteHandler: (lectureId: number) => Promise<object>;
}
