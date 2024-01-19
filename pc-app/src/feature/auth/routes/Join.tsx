/**
 * v0 by Vercel.
 * @see https://v0.dev/t/9nKwojc9Maz
 */

import { Link } from "@nextui-org/react";
import JoinForm from "../components/JoinForm";
import { useMutation } from "@tanstack/react-query";
import { join } from "../api/join";
import { Image } from "@nextui-org/react";
import DeskImage from "@/assets/img/DeskImage.png";
import Swal from "sweetalert2";
import { AxiosResponse } from "axios";

export default function Join() {
  const mutation = useMutation({
    mutationFn: (loginData: {
      memberId: string;
      password: string;
      name: string;
      email: string;
      birthday: string;
    }) => {
      return join(
        loginData.memberId,
        loginData.password,
        loginData.name,
        loginData.email,
        loginData.birthday
      );
    },

    onSuccess: (res) => {
      // 회원가입 성공 처리
      Swal.fire(res.message);
    },
    onError: (error: AxiosResponse) => {
      // 회원가입 실패 처리
      console.error("회원가입 실패:", error);
      console.error("회원가입에 실패했습니다.");
      alert("회원가입 실패");
    },
  });

  const onSubmit = (
    memberId: string,
    password: string,
    name: string,
    email: string,
    birthday: string
  ) => {
    mutation.mutate({ memberId, password, name, email, birthday });
  };

  return (
    <div key="1" className="flex h-screen bg-[#f4f7fc]">
      <div className="flex w-1/2 items-center justify-center bg-[#a7b5f1] p-12 text-white">
        <div className="max-w-sm">
          <Image
            width={400}
            alt="Workspace with laptop"
            src={DeskImage}
            className="mb-10"
          />
          <h2 className="text-3xl font-semibold">
            나작사에서 일상을 지켜나가세요.
          </h2>
          <p className="mt-4 text-sm">
            가상 교실, 학습 자료에 접근하고 선생님들과 채팅하세요.
          </p>
        </div>
      </div>
      <div className="flex w-1/2 items-center justify-center p-12 bg-white">
        <div className="w-full max-w-md">
          <JoinForm onSubmit={onSubmit} />
          <Link className="text-m text-blue-500 text-left" href="#/auth/login">
            이미 계정이 있으시다면?
          </Link>
        </div>
      </div>
    </div>
  );
}
