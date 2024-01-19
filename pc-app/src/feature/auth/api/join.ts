import { axios } from "@/lib/axios";

export const join = (
  memberId: string,
  password: string,
  name: string,
  email: string,
  birthday: string
) => {
  const res = axios.post("/members/join", {
    memberId,
    password,
    name,
    email,
    birthday,
  });
  return res;
};
