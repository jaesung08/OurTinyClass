import CalendarIcon from "@/assets/img/CalendarIcon";
import ComunityIcon from "@/assets/img/ComunityIcon";
import BoardIcon from "@/assets/img/BoardIcon";
import { Button } from "@nextui-org/react";
import HomeIcon from "../../assets/img/HomeIcon";
import { useNavigate } from "react-router-dom";
import PersonIcon from "@/assets/img/PersonIcon";
import LogoutIcon from "@/assets/img/LogoutIcon";
import { useCallback } from "react";
import { logout } from "@/feature/auth/api/logout";
import Cookies from "js-cookie";

type AppLayoutProps = {
  children: React.ReactNode;
};

function SideBar() {
  const navigate = useNavigate();

  const goLogin = useCallback(() => navigate("/auth/login"), [navigate]);
  const goHome = () => {
    navigate("/");
  };

  const goComunity = () => {
    navigate("/communities");
  };

  const onClickLogoutBtn = useCallback(async () => {
    try {
      await logout();
    } catch (error) {
      console.log(error);
    } finally {
      Cookies.remove("accessToken");
      Cookies.remove("refreshToken");
      localStorage.clear();
      goLogin();
    }
  }, [goLogin]);

  return (
    <>
      <article className=" bg-lime-100 min-h-screen h-full flex flex-col items-center justify-between py-10 px-5 gap-5">
        <div className="flex flex-col items-center gap-5">
          <Button
            onClick={goHome}
            isIconOnly
            className="p-2 bg-lime-500"
            size="lg"
          >
            <HomeIcon />
          </Button>
          <Button
            isIconOnly
            className="p-2 bg-lime-500"
            onClick={goComunity}
            size="lg"
          >
            <BoardIcon />
          </Button>
          <Button isIconOnly className="p-2 bg-lime-500" size="lg">
            <ComunityIcon />
          </Button>
          <Button isIconOnly className="p-2 bg-lime-500" size="lg">
            <CalendarIcon />
          </Button>
        </div>
        <div className="flex flex-col items-center gap-5">
          <Button isIconOnly className="p-2 bg-lime-500" size="lg">
            <PersonIcon />
          </Button>
          <Button
            onClick={onClickLogoutBtn}
            isIconOnly
            className="p-2 bg-lime-500"
            size="lg"
          >
            <LogoutIcon />
          </Button>
        </div>
      </article>
    </>
  );
}

function AppLayout({ children }: AppLayoutProps) {
  return (
    <div className="flex">
      <SideBar />
      {children}
    </div>
  );
}

export default AppLayout;
