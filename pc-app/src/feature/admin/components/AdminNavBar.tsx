import {
  Navbar,
  NavbarBrand,
  NavbarContent,
  NavbarItem,
} from "@nextui-org/react";
import Logo from "../../../assets/img/logo.png";

interface AdminNavBarProps {
  setpage: (page: string) => void;
}

const AdminNavBar = ({ setpage }: AdminNavBarProps) => {
  return (
    <Navbar className="bg-lime-950">
      <NavbarBrand>
        <img src={Logo} className="w-20 h-20" alt="" />
        <p className="font-bold text-inherit text-white">우리들의 작은 교실</p>
      </NavbarBrand>
      <NavbarContent className="hidden sm:flex gap-4" justify="center">
        <NavbarItem onClick={() => setpage("user")}>
          <p className="text-white cursor-pointer">유저 목록</p>
        </NavbarItem>
        <NavbarItem onClick={() => setpage("article")}>
          <p className="text-white cursor-pointer" aria-current="page">
            게시판 관리
          </p>
        </NavbarItem>
        <NavbarItem onClick={() => setpage("accept")}>
          <p className="text-white cursor-pointer">등업 관리</p>
        </NavbarItem>
        <NavbarItem onClick={() => setpage("class")}>
          <p className="text-white cursor-pointer">반 배정</p>
        </NavbarItem>
      </NavbarContent>
    </Navbar>
  );
};

export default AdminNavBar;
