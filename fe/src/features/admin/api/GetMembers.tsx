import { commonAxios } from "@/lib/commonAxios";

export const memberGet = () => {
  return commonAxios.get("/members");
};
