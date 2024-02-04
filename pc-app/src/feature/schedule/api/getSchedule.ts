import { commonAxios } from "@/lib/commonAxios";
import { BaseResponse } from "@/types/BaseResponse";
import { Schedule } from "..";

interface getWeekScheduleProps extends BaseResponse {
  data: {
    monday: string;
    scheduleList: Schedule[];
  };
}

export const getWeekSchedules = (
  memberId: string,
  scheduleStartDate: string
): Promise<getWeekScheduleProps> => {
  return commonAxios.get(`/api/schedule/${memberId}`, {
    params: {
      scheduleDate: scheduleStartDate, // 조회할 일주일중, 월요일의 날짜를 : YYYY-MM-DD 형식으로 보낸다.
    },
  });
};
