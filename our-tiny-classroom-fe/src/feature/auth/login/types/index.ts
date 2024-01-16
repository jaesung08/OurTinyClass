import { baseResponse } from "@/types/apiResponse";

export interface LoginFormProps {
  onSubmit: (id: string, password: string) => void;
}

export interface LoginResponse extends baseResponse {
  data: {
    accessToken: string;
    nickname: string;
    name: string;
  };
}
