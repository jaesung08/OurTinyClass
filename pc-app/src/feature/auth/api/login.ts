import { axios } from "@/lib/axios";

export const login = (memberId: string, password: string) => {
  const res = axios.post("/members/login", { memberId, password });
  return res;
};
