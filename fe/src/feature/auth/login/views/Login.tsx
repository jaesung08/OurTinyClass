/**
 * v0 by Vercel.
 * @see https://v0.dev/t/idSQHuphTh3
 */

import { Link } from "@nextui-org/react";
import LoginForm from "../components/LoginForm";
import { login } from "../api/login";
import { useMutation } from "react-query";

export default function Login() {
  const mutation = useMutation(
    (loginData: { id: string; password: string }) =>
      login(loginData.id, loginData.password),
    {
      onSuccess: (data) => {
        // 로그인 성공 처리
        console.log("로그인 성공:", data);
        // 여기에 로그인 성공 후의 로직을 추가할 수 있습니다.
      },
      onError: (error) => {
        // 로그인 실패 처리
        console.error("로그인 실패:", error);
        console.error("로그인에 실패했습니다.");
      },
    }
  );

  const onSubmit = (id: string, password: string) => {
    mutation.mutate({ id, password });
  };

  return (
    <div className="min-h-screen bg-yellow-100 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        {/* <img
          alt="School Logo"
          className="mx-auto h-12 w-auto"
          src="/placeholder.svg"
        /> */}
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
          Welcome to our School Portal
        </h2>
        <p className="mt-2 text-center text-sm text-gray-600">
          Please sign in to access your account
        </p>
      </div>
      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div className="bg-white py-8 px-4 shadow sm:rounded-lg sm:px-10">
          <LoginForm onSubmit={onSubmit} />
          <div className="mt-6">
            <div className="relative">
              <div className="absolute inset-0 flex items-center">
                <div className="w-full border-t border-gray-300" />
              </div>
              <div className="relative flex justify-center text-sm">
                <span className="px-2 bg-white text-gray-500">Or</span>
              </div>
            </div>
            <div className="mt-6 grid grid-cols-1 gap-3">
              <Link
                className="w-full text-center bg-green-500 hover:bg-green-700 text-white py-2 rounded"
                href="#"
              >
                Create new account
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
