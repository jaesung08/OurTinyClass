import { axios } from "@/lib/axios";
import { BaseResponse } from "@/types/BaseResponse";

export const login = (
  memberId: string,
  password: string
): Promise<BaseResponse> => {
  return axios.post("/members/login", { memberId, password });
};
