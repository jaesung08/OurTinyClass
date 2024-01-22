import {
  Table,
  TableHeader,
  TableBody,
  TableColumn,
  TableRow,
  TableCell,
  Button,
  Select,
  SelectItem,
  Input,
  Pagination,
  PaginationItemType,
  PaginationItemRenderProps,
} from "@nextui-org/react";
import { ChevronIcon } from "@/assets/ChevronIcon";
import { useEffect, useState } from "react";
import { cn } from "@nextui-org/react";
import { axios } from "@/lib/axios";
import { API_URL } from "@/config";
import { freeBoard } from "../api/freeBoard";
import dayjs from "dayjs";

// import { ReactComponent as Search } from "../../../assets/images/Hamburger.svg";

function FreeBoardBody() {
  const categories = ["내용", "제목", "작성자"];
  const renderItem = ({
    ref,
    key,
    value,
    isActive,
    onNext,
    onPrevious,
    setPage,
    className,
  }: PaginationItemRenderProps<HTMLButtonElement>) => {
    if (value === PaginationItemType.NEXT) {
      return (
        <button
          key={key}
          className={cn(className, "bg-default-200/50 min-w-8 w-8 h-8")}
          onClick={onNext}
        >
          <ChevronIcon className="rotate-180" />
        </button>
      );
    }

    if (value === PaginationItemType.PREV) {
      return (
        <button
          key={key}
          className={cn(className, "bg-default-200/50 min-w-8 w-8 h-8")}
          onClick={onPrevious}
        >
          <ChevronIcon />
        </button>
      );
    }

    if (value === PaginationItemType.DOTS) {
      return (
        <button key={key} className={className}>
          ...
        </button>
      );
    }

    // cursor is the default item
    return (
      <button
        ref={ref}
        key={key}
        className={cn(
          className,
          isActive &&
            "text-white bg-gradient-to-br from-lime-400 to-lime-300 font-bold"
        )}
        onClick={() => setPage(value)}
      >
        {value}
      </button>
    );
  };
  const [renderBoard, setRenderBoard] = useState();
  const [boardList, setBoardList] = useState<object[]>([]);

  useEffect(() => {
    const searchArticle = async () => {
      try {
        const articles = await freeBoard();
        setBoardList(articles.data.content);
      } catch (error) {
        console.error(error);
      }
    };
    searchArticle();
  }, []);

  useEffect(() => {
    if (boardList) {
      const boards = boardList.map((board, index) => {
        const date = dayjs(board.createdAt);
        return (
          <TableRow key={index}>
            <TableCell>{index + 1}</TableCell>
            <TableCell
              style={{
                whiteSpace: "nowrap",
                textOverflow: "ellipsis",
                overflow: "hidden",
                maxWidth: "25rem",
              }}
            >
              {board.title}
            </TableCell>
            <TableCell>{board.name}</TableCell>
            <TableCell>{date.format("YY-MM-DD")}</TableCell>
            <TableCell>111</TableCell>
            <TableCell>222</TableCell>
          </TableRow>
        );
      });
      setRenderBoard(boards);
    }
  }, [boardList]);

  return (
    <section className="w-10/12 h-full border-l-3">
      <div
        className="w-full bg-lime-500 flex items-center justify-center"
        style={{ height: "10%" }}
      >
        <form className="group relative w-11/12 bg-lime-500">
          <svg
            width="20"
            height="20"
            fill="currentColor"
            className="absolute left-3 top-1/2 -mt-2.5 text-slate-400 pointer-events-none group-focus-within:text-blue-500"
            aria-hidden="true"
          >
            {" "}
            <path
              fill-rule="evenodd"
              clip-rule="evenodd"
              d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
            />
          </svg>
          <input
            className="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none w-full text-sm leading-6 text-slate-900 placeholder-slate-400 rounded-md py-2 pl-10 ring-1 ring-slate-200 shadow-sm h-2/5"
            type="text"
            aria-label="Filter projects"
            placeholder="Filter projects..."
          />
        </form>
      </div>
      <div className="w-full bg-white" style={{ height: "90%" }}>
        <div className="flex w-full p-5 items-center" style={{ height: "8%" }}>
          <p className="text-3xl w-2/12 f">고민 나눔</p>
          <p className="w-10/12">가슴속에 쌓아둔 이야기를 털어보세요</p>
        </div>
        <hr className="border-b-2" />
        <div
          className="w-full flex justify-center items-center"
          style={{ height: "90%" }}
        >
          <div
            className="w-5/6 flex flex-col justify-between py-3 items-center"
            style={{ height: "90%" }}
          >
            <div className="w-full" style={{ height: "15%" }}>
              <form className="flex justify-between items-center">
                <Select
                  label="카테고리"
                  className="ml-5 w-2/12 shadow-xl rounded-xl"
                >
                  {categories.map((item: string, index: number) => (
                    <SelectItem key={index} value={item}>
                      {item}
                    </SelectItem>
                  ))}
                </Select>
                <div className="flex w-8/12 flex-wrap md:flex-nowrap gap-4 shadow-xl rounded-xl">
                  <Input
                    type="text"
                    label="search"
                    placeholder="검색어를 입력하세요."
                  />
                </div>
                <Button
                  color="success"
                  className="w-1/12 text-white text-xl py-7 mr-5 shadow-xl rounded-xl"
                >
                  검색
                </Button>
              </form>
            </div>
            {/* {boardList ? boardList : null} */}

            <Table
              aria-label="Example static collection table"
              className="h-5/6"
            >
              <TableHeader>
                <TableColumn>번호</TableColumn>
                <TableColumn>제목</TableColumn>
                <TableColumn>글쓴이</TableColumn>
                <TableColumn>날짜</TableColumn>
                <TableColumn>조회수</TableColumn>
                <TableColumn>추천수</TableColumn>
              </TableHeader>
              <TableBody>
                {renderBoard ? renderBoard : "게시글이 없습니다."}
              </TableBody>
            </Table>
            <div className="flex items-center" style={{ height: "10%" }}>
              <Pagination
                disableCursorAnimation
                showControls
                total={10}
                initialPage={1}
                className="gap-2"
                radius="full"
                renderItem={renderItem}
                variant="light"
              />
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default FreeBoardBody;
