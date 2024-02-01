import Logo from "@/assets/img/Logo.png";
import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  Link,
  Button,
} from "@nextui-org/react";

export function LandingPage() {
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
              <Button as={Link} color="default" href="#" variant="flat">
                로그인
              </Button>
            </NavbarItem>
            <NavbarItem>
              <Button as={Link} color="primary" href="#" variant="flat">
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
