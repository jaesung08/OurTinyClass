import { axios } from "@/lib/axios";

export const login = (id: string, password: string) => {
  const res = axios.post("/login", { id, password });
  return res;
};
