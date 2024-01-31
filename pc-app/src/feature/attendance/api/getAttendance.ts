import { axios } from "@/lib/axios";
import { getTodayAttendanceResponse } from "..";

const getTodayAttendance = (): Promise<getTodayAttendanceResponse> => {
  return axios.get("/attendances/today-attendance/ssafy123", {
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
