/* eslint-disable @typescript-eslint/no-explicit-any */
import Axios from "axios";

import { API_URL } from "@/config";

export const axios = Axios.create({
  baseURL: API_URL,
});

axios.interceptors.request.use(function (config): any {
  // eslint-disable-next-line valid-typeof
  if (typeof window === undefined) {
    return;
  }

  config.headers = Object.assign({}, config.headers, {
    "Content-Type": "application/json",
  });
  return config;
});
axios.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error: any) => {
    return Promise.reject(error);
  }
);
