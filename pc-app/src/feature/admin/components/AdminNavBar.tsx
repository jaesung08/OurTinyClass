import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
  Link,
} from "@nextui-org/react";
import Logo from "../../../assets/img/logo.png";

const AdminNavBar = () => {
  return (
    <Navbar className="bg-lime-950">
      <NavbarBrand>
        <img src={Logo} className="w-20 h-20" alt="" />
        <p className="font-bold text-inherit text-white">우리들의 작은 교실</p>
      </NavbarBrand>
      <NavbarContent className="hidden sm:flex gap-4" justify="center">
        <NavbarItem>
          <p className="text-white cursor-pointer">유저 목록</p>
        </NavbarItem>
        <NavbarItem isActive>
          <p className="text-white cursor-pointer" aria-current="page">
            게시판 관리
          </p>
        </NavbarItem>
        <NavbarItem>
          <p className="text-white cursor-pointer">등업 관리</p>
        </NavbarItem>
      </NavbarContent>
    </Navbar>
  );
};

export default AdminNavBar;
