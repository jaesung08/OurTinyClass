import { commonAxios } from "@/lib/commonAxios";

export const memberGet = () => {
  return commonAxios.get("http://70.12.247.27:8080/api/members");
};
