import Logo from "@/assets/img/Logo.png";
import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  Link,
  Button,
} from "@nextui-org/react";
import Cookies from "js-cookie";
import { useEffect, useState } from "react";

export function LandingPage() {
  const [isLogined, setIsLogined] = useState<string | undefined>("");
  useEffect(() => {
    Cookies.get("accessToken")
      ? setIsLogined(Cookies.get("accessToken"))
      : undefined;
  }, [isLogined]);

  return (
    <section className="w-full h-lvh relative">
      <div className="w-full h-lvh relative bg-red-100 bg-cover bg-no-repeat bg-[url('./assets/img/landingback.png')]">
        <Navbar maxWidth="2xl">
          <NavbarBrand className="flex gap-7">
            <img className="w-20 h-20" src={Logo} alt="" />
            <p className="font-bold text-inherit">우리들의 작은 교실</p>
          </NavbarBrand>
          <NavbarContent justify="end">
            <NavbarItem className="hidden lg:flex">
              {isLogined ? (
                <Button
                  as={Link}
                  onClick={() => {
                    Cookies.remove("accessToken");
                    setIsLogined("");
                  }}
                  color="default"
                  variant="flat"
                >
                  로그아웃
                </Button>
              ) : (
                <Button
                  as={Link}
                  color="default"
                  href="/auth/login"
                  variant="flat"
                >
                  로그인
                </Button>
              )}
            </NavbarItem>
            <NavbarItem>
              <Button
                as={Link}
                color="primary"
                href="/auth/join"
                variant="flat"
              >
                회원가입
              </Button>
            </NavbarItem>
          </NavbarContent>
        </Navbar>
      </div>
      <Button
        className="w-3/12 min-h-20 transform -translate-x-1/2 -translate-y-1/2 absolute bottom-24 left-1/2 text-3xl"
        color="primary"
      >
        다운로드
      </Button>
    </section>
  );
}
