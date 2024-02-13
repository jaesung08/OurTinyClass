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
import { DUMMY_DATA, SearchArticleOptions } from "../assets/textContent";
import { useState } from "react";
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
        defaultSelectedKeys={[SearchArticleOptions[0].name]}
        aria-label="게시글을 검색할 방법을 선택해주세요."
        value={searchType}
      >
        {SearchArticleOptions.map((item) => (
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
    SearchArticleOptions[0].value
  );
  const [searchKeyword, setSearchKeyword] = useState<string>("");

  return (
    <section className="w-10/12 flex flex-col mx-auto py-10">
      <p className="my-5 text-2xl">게시글 목록</p>
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
          className="mt-10 max-h-80"
        >
          <TableHeader>
            <TableColumn key="id">ID</TableColumn>
            <TableColumn key="title">제목</TableColumn>
            <TableColumn key="name">작성자</TableColumn>
            <TableColumn key="createdAt">작성일</TableColumn>
            <TableColumn key="hit">조회수</TableColumn>
          </TableHeader>
          <TableBody items={DUMMY_DATA}>
            {(user) => (
              <TableRow key={user.id}>
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
      </div>
    </section>
  );
};
export default AdminUserBody;
