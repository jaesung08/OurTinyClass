import { axios } from "@/lib/axios";
import { BaseResponse } from "@/types/BaseResponse";

export const join = (
  memberId: string,
  password: string,
  name: string,
  email: string,
  birthday: string
): Promise<BaseResponse> => {
  return axios.post("/members/join", {
    memberId,
    password,
    name,
    email,
    birthday,
  });
};
