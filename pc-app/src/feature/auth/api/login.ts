import { axios } from "@/lib/axios";
import { LoginResponse } from "..";

export const login = (
  memberId: string,
  password: string
): Promise<LoginResponse> => {
  return axios.post("/members/login", { memberId, password });
};
