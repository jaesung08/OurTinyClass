import { commonAxios } from "@/lib/commonAxios";
import { CheckInResponse } from "..";

const checkIn = (): Promise<CheckInResponse> => {
  return commonAxios.post("/attendances/check-in", {
    memberId: "ssafy123",
    password: "Jtjgml^^7",
    name: "초롱초롱 어피치",
    email: "ssafy1@ssafy.com",
    birthday: "2024-01-11",
    point: 0,
  });
};

export { checkIn };
