export interface Plan {
  id: number;
  date: string;
  scheduleList: Array<Schedule>;
}

export interface Schedule {
  isMentoring: boolean;
  addUserId: number;
  isRegular: boolean;
  title: string;
  lectureId: number;
}
