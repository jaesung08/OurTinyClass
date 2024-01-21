import { axios } from "@/lib/axios";

export const logout = (memberId: string, password: string) => {
  return axios.post("/members/logout", { memberId, password });
};
