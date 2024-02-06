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
import { DUMMY_DATA, SearchUserOptions } from "../assets/textContent";
import { useEffect, useState } from "react";
import { SearchBarProps } from "../types";
import { commonAxios } from "@/lib/commonAxios";

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
        // onClick={onSubmit}
      >
        검색
      </Button>
    </form>
  );
}

const AdminUserBody = () => {
  // const [testData, setTestData] = useState(null);
  // useEffect(() => {
  //   commonAxios
  //     .get(
  //       `http://70.12.246.227:8080/api/admin/members/certification/student/1`
  //     )
  //     .then((res) => {
  //       console.log(res.data);
  //       setTestData(res.data);
  //     })
  //     .catch((err) => console.error(err));
  // }, []);

  const [searchType, setSearchType] = useState<string>(
    SearchUserOptions[0].value
  );
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [isEdit, setIsEdit] = useState(false);

  useEffect(() => {
    console.log(isEdit);
  }, [isEdit]);

  return (
    <section className="w-10/12 flex flex-col mx-auto py-10">
      {/* 이미지 처리 */}
      {/* {testData ? <img src={testData.quitConfirmationPaths[1]} alt="" /> : null} */}
      <p className="my-5 text-2xl">유저목록</p>
      <SearchBar
        searchType={searchType}
        searchKeyword={searchKeyword}
        setSearchKeyword={setSearchKeyword}
        setSearchType={setSearchType}
      />
      <Table
        aria-label="유저 관리 게시판"
        selectionMode="single"
        className={isEdit ? "mt-10 max-h-[30rem] w-6/12" : "mt-10 max-h-80"}
        // bottomContent={
        //   totalPage > 0 ? (
        //     <div className="flex w-full justify-center">
        //       <Pagination
        //         isCompact
        //         showControls
        //         showShadow
        //         color="success"
        //         page={currentPage}
        //         total={totalPage}
        //         onChange={(page: number) => setCurrentPage(page)}
        //       />
        //     </div>
        //   ) : null
        // }
      >
        <TableHeader>
          <TableColumn key="id">ID</TableColumn>
          <TableColumn key="title">이름</TableColumn>
          <TableColumn key="name">이메일</TableColumn>
          <TableColumn key="createdAt">생일</TableColumn>
          <TableColumn key="hit">역할</TableColumn>
        </TableHeader>
        <TableBody items={DUMMY_DATA}>
          {(board) => (
            <TableRow
              key={board.id}
              onClick={() => (isEdit ? setIsEdit(false) : setIsEdit(true))}
            >
              <TableCell>{board.id}</TableCell>
              <TableCell
                className="cursor-pointer"
                // 테이블 속성에서 너비 조정이 제대로 먹지않아서 inline 속성으로 style 적용함. 추후에 css 파일로 분리 예정
                style={{
                  whiteSpace: "nowrap",
                  textOverflow: "ellipsis",
                  overflow: "hidden",
                  maxWidth: "25rem",
                }}
              >
                {board.name}
              </TableCell>
              <TableCell>{board.email}</TableCell>
              <TableCell>{dayjs(board.birthday).format("YY-MM-DD")}</TableCell>
              <TableCell>{board.role}</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </section>
  );
};
export default AdminUserBody;
