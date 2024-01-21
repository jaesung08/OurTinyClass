import { Button } from "@nextui-org/react";
import Cookies from "js-cookie";
import { useCallback } from "react";
import { useNavigate } from "react-router-dom";

function Landing() {
  const isLogined = Cookies.get("token") ? true : false;
  const navigate = useNavigate();
  const goLogin = useCallback(() => navigate("/auth/login"), [navigate]);

  const onClickLogoutBtn = useCallback(() => {
    Cookies.remove("token");
    localStorage.clear();
    goLogin();
  }, [goLogin]);

  return (
    <div>
      {isLogined ? (
        <Button onClick={onClickLogoutBtn}>로그아웃</Button>
      ) : (
        <a href="#/auth/login">로그인</a>
      )}
    </div>
  );
}

export default Landing;
