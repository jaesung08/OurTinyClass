import {
  Input,
  Button,
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  Select,
  SelectItem,
} from "@nextui-org/react";
import dayjs from "dayjs";
import { DUMMY_DATA, SearchUserOptions, UserRole } from "../assets/textContent";
import { useEffect, useState } from "react";
import { SearchBarProps } from "../types";

function SearchBar({
  searchKeyword,
  setSearchKeyword,
  searchType,
  setSearchType,
}: SearchBarProps) {
  return (
    <form className="w-full flex justify-between items-center">
      <Select
        className="ml-5 bg-white w-1/6 rounded-xl"
        size="sm"
        defaultSelectedKeys={[SearchUserOptions[0].name]}
        aria-label="게시글을 검색할 방법을 선택해주세요."
        value={searchType}
      >
        {SearchUserOptions.map((item) => (
          <SelectItem
            key={item.name}
            value={item.value}
            onClick={() => setSearchType(item.value)}
          >
            {item.name}
          </SelectItem>
        ))}
      </Select>
      <Input
        className="bg-white w-4/6"
        type="text"
        placeholder="검색할 내용을 입력해주세요."
        size="sm"
        value={searchKeyword}
        onChange={(e) => setSearchKeyword(e.target.value)}
      />
      <Button
        className="w-1/12 text-white text-xl rounded-xl bg-lime-500 shadow"
        size="lg"
      >
        검색
      </Button>
    </form>
  );
}

const AdminUserBody = () => {
  const [searchType, setSearchType] = useState<string>(
    SearchUserOptions[0].value
  );
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [userName, setUserName] = useState<string>("");
  const [userEmail, setUserEmail] = useState<string>("");
  const [userRole, setUserRole] = useState<string>(UserRole[0].name);
  const [isEdit, setIsEdit] = useState<boolean>(false);

  const editHandler = (userName: string, userEmail: string) => {
    setUserName(userName);
    setUserEmail(userEmail);

    setIsEdit(true);
  };

  useEffect(() => {
    console.log(userRole);
  }, [userRole]);

  return (
    <section className="w-10/12 flex flex-col mx-auto py-10">
      <p className="my-5 text-2xl">유저목록</p>
      <SearchBar
        searchType={searchType}
        searchKeyword={searchKeyword}
        setSearchKeyword={setSearchKeyword}
        setSearchType={setSearchType}
      />
      <div className="flex gap-10">
        <Table
          aria-label="유저 관리 게시판"
          selectionMode="single"
          className={isEdit ? "mt-10 max-h-[30rem] w-6/12" : "mt-10 max-h-80"}
        >
          <TableHeader>
            <TableColumn key="id">ID</TableColumn>
            <TableColumn key="title">이름</TableColumn>
            <TableColumn key="name">이메일</TableColumn>
            <TableColumn key="createdAt">생일</TableColumn>
            <TableColumn key="hit">역할</TableColumn>
          </TableHeader>
          <TableBody items={DUMMY_DATA}>
            {(user) => (
              <TableRow
                key={user.id}
                onClick={() => editHandler(user.name, user.email)}
              >
                <TableCell>{user.id}</TableCell>
                <TableCell
                  className="cursor-pointer"
                  style={{
                    whiteSpace: "nowrap",
                    textOverflow: "ellipsis",
                    overflow: "hidden",
                    maxWidth: "25rem",
                  }}
                >
                  {user.name}
                </TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{dayjs(user.birthday).format("YY-MM-DD")}</TableCell>
                <TableCell>{user.role}</TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
        <div
          className={
            isEdit
              ? "flex flex-col justify-around mt-10 max-h-[30rem] w-6/12 bg-slate-50 p-5 rounded-lg"
              : "hidden"
          }
        >
          <Input type="email" value={userName} label="Email" />
          <Input type="text" value={userEmail} label="name" />
          <Select
            label="Select Role"
            className="max-w-full"
            defaultSelectedKeys={[userRole]}
            onChange={(e) => setUserRole(e.target.value)}
          >
            {UserRole.map((role) => (
              <SelectItem key={role.name} value={role.value}>
                {role.name}
              </SelectItem>
            ))}
          </Select>
          <Button
            color="success"
            className="w-full py-5"
            onClick={() => setIsEdit(false)}
          >
            수정
          </Button>
        </div>
      </div>
    </section>
  );
};
export default AdminUserBody;
