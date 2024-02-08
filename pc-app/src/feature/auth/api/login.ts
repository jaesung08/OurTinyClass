import { commonAxios } from "@/lib/commonAxios";
import { LoginResponse } from "..";

export const login = (
  memberId: string,
  password: string
): Promise<LoginResponse> => {
  return commonAxios.post("/members/login", { memberId, password });
};

// kakaologin 후 kakao에서 accessToken을 발급 받고, 백엔드에 API를 호출할 때 사용하는 함수
export const kakaoLogin = (accessToken: string): Promise<LoginResponse> => {
  // TODO: 임의의 url과 data, responseType을 넣었으니 백엔드 구현에 맞게 수정할 것
  return commonAxios.post("/members/kakaoLogin", { accessToken });
};
