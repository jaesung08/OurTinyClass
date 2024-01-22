import { axios } from "@/lib/axios";
import { BaseResponse } from "@/types/BaseResponse";

const getTodayAttendance = (): Promise<BaseResponse> => {
  return axios.get("/attendances/today-attendance/4", {
    params: {
      memberId: "ssafy123",
      password: "Jtjgml^^7",
      name: "초롱초롱 어피치",
      email: "ssafy1@ssafy.com",
      birthday: "2024-01-11",
      point: 0,
    },
  });
};

export { getTodayAttendance };
