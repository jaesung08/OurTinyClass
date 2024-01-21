import { Button } from "@nextui-org/react";
import LoginForm from "../components/LoginForm";
import { login } from "../api/login";
import { useMutation } from "@tanstack/react-query";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import { useSetRecoilState } from "recoil";
import { userState } from "@/atoms/user";
import Cookies from "js-cookie";
import { useCallback } from "react";

export default function Login() {
  const navigate = useNavigate();
  const setUserState = useSetRecoilState(userState);
  const dummyCookie = "dummy-cookie"; // TODO 로그인 쿠키 테스트를 위한 임시 Cookie. 로그인 연동시 삭제해야함.
  const mutation = useMutation({
    mutationFn: (loginData: { id: string; password: string }) => {
      return login(loginData.id, loginData.password);
    },
    onSuccess: (res) => {
      // 로그인 성공 처리
      if (res.data) {
        // 정상 로그인
        setUserState(res.data);

        Swal.fire(res.message, "success").then(() => {
          navigate("/");
        });
      } else {
        Swal.fire(
          "정상적인 로그인에 실패하였습니다. 다시 시도해주세요.",
          "error"
        );
      }
    },
    onError: (error) => {
      // 로그인 실패 처리
      console.error("로그인 실패:", error);
      Swal.fire("error", "로그인에 실패하였습니다", "error");
    },
  });

  const onSubmit = (id: string, password: string) => {
    Cookies.set("token", dummyCookie); // TODO 로그인 쿠키 테스트를 위한 임시 Cookie. 로그인 연동시 삭제해야함.
    mutation.mutate({ id, password });
  };

  const goJoin = useCallback(() => {
    navigate("/auth/join");
  }, [navigate]);

  return (
    <div className="min-h-screen bg-yellow-100 flex flex-col justify-center py-12 sm:px-6 lg:px-8">
      <div className="sm:mx-auto sm:w-full">
        {/* <img
          alt="School Logo"
          className="mx-auto h-12 w-auto"
          src="/placeholder.svg"
        /> */}
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
          나만의 작은 교실에 오신 것을 환영합니다.
        </h2>
        <p className="mt-4 text-center text-sm text-gray-600">
          계정에 접근하기 위해 로그인을 해주세요.
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
                <span className="px-2 bg-white text-gray-500">또는</span>
              </div>
            </div>
            <Button
              className="w-full bg-green-500 hover:bg-green-700 text-white mt-7"
              type="submit"
              onClick={goJoin}
            >
              회원가입
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
}
