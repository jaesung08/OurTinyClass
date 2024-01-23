/* eslint-disable @typescript-eslint/no-explicit-any */
import Axios from "axios";

import { API_URL } from "@/config";
import Cookies from "js-cookie";
import { CODE } from "@/types/Code";

export const axios = Axios.create({
  baseURL: API_URL,
});

axios.interceptors.request.use(function (config): any {
  // eslint-disable-next-line valid-typeof
  if (typeof window === undefined) {
    return;
  }

  if (!Cookies.get("token")) {
    const controller = new AbortController();

    const cfg = {
      ...config,
      signal: controller.signal,
    };

    return cfg;
  }
  config.headers = Object.assign({}, config.headers, {
    "Content-Type": "application/json",
    Authorization: Cookies.get("token"),
  });
  return config;
});

let isTokenRefreshing = false;

axios.interceptors.response.use(
  (response) => {
    return response.data;
  },
  async (error) => {
    const {
      config,
      response: { status },
    } = error;
    //새로고침에 의해 저장된 accessToken이 없어진 경우
    if (status == CODE.HTTP_STATUS_CODE.UNAUTHORIZED) {
      // 요청상태저장
      const originalRequest = config;

      //token을 재발급 하는 동안 다른 요청이 발생할 경우 대기시킨다.

      if (!isTokenRefreshing) {
        isTokenRefreshing = true;
        // 에러가 발생했던 컴포넌트의 axios로 이동하고자 하는 경우
        // 반드시 return을 붙여야 한다.
        return await axios.post("/silent-refresh").then((response) => {
          const newAccessToken = response.data.Authorization;

          axios.defaults.headers.common["Authorization"] = newAccessToken;
          originalRequest.headers.Authorization = newAccessToken;

          isTokenRefreshing = false;

          return axios(originalRequest);
        });
      }
    } else if (status == CODE.HTTP_STATUS_CODE.FORBIDDEN) {
      alert(error.response.data.message);
    }
    return Promise.reject(error);
  }
);
