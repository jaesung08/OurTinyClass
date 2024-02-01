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
import { useEffect, useState } from "react";
import { cn } from "@nextui-org/react";
import { freeBoard, searchBoard } from "../api/freeBoard";
import dayjs from "dayjs";
import { useNavigate, useLocation } from "react-router-dom";
import { Board } from "../types/board";
import { ChevronIcon } from "@/assets/img/ChevronIcon";

const CATEGORIES = [
  {
    name: "제목",
    value: "title",
  },
  {
    name: "내용",
    value: "content",
  },
  {
    name: "제목 + 내용",
    value: "title_content",
  },
  {
    name: "작성자",
    value: "name",
  },
];
function FreeBoardBody() {
  const navigator = useNavigate();
  const navState = useLocation().state;
  // 페이지 네이션 처리 과정
  const renderItem = ({
    ref,
    key,
    value,
    isActive,
    onNext,
    onPrevious,
    setPage,
    className,
  }: PaginationItemRenderProps) => {
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
        onClick={() => {
          handlePageChange(value);
          setPage(currentPage);
        }}
      >
        {value}
      </button>
    );
  };
  const [boardList, setBoardList] = useState<Board[]>([]);
  const [searchKeyword, setSearchKeyword] = useState<string>("");
  const [searchType, setSearchType] = useState<string>(CATEGORIES[0].value);
  const [editPagination, setEditPagination] = useState<number>(1);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [isSearched, setIsSearched] = useState<boolean>(false);
  const [currentType, setCurrentType] = useState<string>("notice");

  // 페이지 렌더링시 게시글 전체 불러오기
  useEffect(() => {
    if (navState) {
      if (navState.title === "공지사항 🔊") {
        setCurrentType("notice");
      } else if (navState.title === "자유 게시판 ✨") {
        setCurrentType("free");
      } else if (navState.title === "고민 나눔 🧶") {
        setCurrentType("counseling");
      } else if (navState.title === "취미 공유 🎨") setCurrentType("hobby");
    }
  }, [navState]);

  useEffect(() => {
    const totalArticle = async () => {
      try {
        const articles = await freeBoard(currentType);
        setBoardList(articles.data.content);
        setIsSearched(false);
        setCurrentPage(1);
        setEditPagination(articles.data.totalPages - 1);
      } catch (error) {
        console.error(error);
      }
    };
    totalArticle();
  }, [currentType]);

  // 페이지 네이션 체크
  const handlePageChange = (page: number) => {
    setCurrentPage(page);
  };

  // 페이지네이션 함수
  useEffect(() => {
    const reRenderArticles = async () => {
      try {
        if (isSearched) {
          const newArticles = await searchBoard(
            currentType,
            searchType,
            searchKeyword,
            currentPage
          );
          setBoardList(newArticles.data.content);
        } else {
          const newArticles = await freeBoard(currentType, currentPage);
          setBoardList(newArticles.data.content);
        }
      } catch (error) {
        console.error(error);
      }
    };
    reRenderArticles();
  }, [currentPage, currentType, isSearched, searchType, searchKeyword]);

  // 게시글 검색기능
  const searchHandler = async () => {
    if (searchKeyword.trim()) {
      try {
        const searchLists = await searchBoard(
          currentType,
          searchType,
          searchKeyword
        );
        if (searchLists.data.content) {
          setEditPagination(searchLists.data.totalPages - 1);
          setCurrentPage(1);
          setIsSearched(true);
        }
      } catch (error) {
        console.error(error);
      }
    } else {
      alert("내용을 입력하세요");
    }
  };
  return (
    <section className="w-10/12 h-full border-l-3 relative">
      <Button
        className="fixed bottom-5 right-5 text-white text-xl rounded-full bg-lime-500 shadow"
        size="lg"
        onClick={() => {
          navigator("/communities/create");
        }}
      >
        ✏
      </Button>
      <div className="w-full bg-lime-500 flex items-center justify-center h-20">
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
              fillRule="evenodd"
              clipRule="evenodd"
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
      <div className="w-full bg-white h-[90%]">
        <div className="flex w-full p-5 items-center h-[8%]">
          {navState ? (
            <>
              <p className="text-2xl w-2/12 f">{navState.title}</p>
              <p className="w-10/12">{navState.content}</p>
            </>
          ) : (
            <>
              <p className="text-2xl w-2/12 f">고민 나눔</p>
              <p className="w-10/12">가슴속에 쌓아둔 이야기를 털어보세요</p>
            </>
          )}
        </div>
        <hr className="border-b-2" />
        <div className="w-full flex justify-center items-center h-[90%]">
          <div className="w-5/6 flex flex-col justify-between py-3 items-center h-[90%]">
            <div className="w-full h-[15%]">
              <form className="flex justify-between items-center">
                <Select
                  className="ml-5 bg-white w-1/6 rounded-xl"
                  size="sm"
                  defaultSelectedKeys={[CATEGORIES[0].name]}
                >
                  {CATEGORIES.map((item) => (
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
                  onClick={() => searchHandler()}
                >
                  검색
                </Button>
              </form>
            </div>

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
                {boardList.map((board: Board, index: number): JSX.Element => {
                  const date = dayjs(board.createdAt);
                  return (
                    <TableRow key={index} className="hover:bg-gray-200">
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
                        onClick={() =>
                          navigator("/communities/detail", { state: board.id })
                        }
                      >
                        {board.title}
                      </TableCell>
                      <TableCell>{board.name}</TableCell>
                      <TableCell>{date.format("YY-MM-DD")}</TableCell>
                      <TableCell>{board.hit}</TableCell>
                      <TableCell>222</TableCell>
                    </TableRow>
                  );
                })}
              </TableBody>
            </Table>
            <div className="flex items-center h-[10%]">
              <Pagination
                disableCursorAnimation
                showControls
                total={editPagination}
                initialPage={currentPage}
                page={currentPage}
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
