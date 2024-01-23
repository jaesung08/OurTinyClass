import { axios } from "@/lib/axios";

const checkOut = () => {
  return axios.put("/attendances/check-out", {
    memberId: "ssafy123",
    password: "Jtjgml^^7",
    name: "초롱초롱 어피치",
    email: "ssafy1@ssafy.com",
    birthday: "2024-01-11",
    point: 0,
  });
};

export { checkOut };
