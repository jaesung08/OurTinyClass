import { LectureCard } from "../components/LectureCard";
import { Avatar, Input, Select, SelectItem, Button } from "@nextui-org/react";
import EditIcon from "@/assets/img/EditIcon";
import { useNavigate } from "react-router";

import { deleteLecture, getLecture, searchLecture } from "../api/lecture";
import { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { userState } from "@/atoms/user";
import { Lecture } from "../types";
import { LectureCategory, SearchLecturesOptions } from "../assets/textContent";

// TODO : 특강페이지 할일

// 검색기능

interface SearchBarProps {
  searchKeyword: string;
  setSearchKeyword: (searchKeyword: string) => void;
  searchType: string;
  setSearchType: (searchType: string) => void;
  lectuerSearch: () => void;
}

function SearchBar({
  searchKeyword,
  setSearchKeyword,
  searchType,
  setSearchType,
  lectuerSearch,
}: SearchBarProps) {
  return (
    <form className="w-full flex justify-between items-center">
      <Select
        className="ml-5 bg-white w-1/6 rounded-xl"
        size="sm"
        defaultSelectedKeys={[SearchLecturesOptions[0].name]}
        aria-label="게시글을 검색할 방법을 선택해주세요."
        value={searchType}
      >
        {SearchLecturesOptions.map((item) => (
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
        onClick={lectuerSearch}
      >
        검색
      </Button>
    </form>
  );
}

// TODO : 특강페이지 할일

export function SpecialLecture() {
  const [totalList, setTotalList] = useState<Lecture[]>([]);
  const userInfo = useRecoilState(userState);
  // TODO : Array type 으로 변경해야함
  const [lectureLists, setLectureLists] = useState<Lecture[]>([]);
  const [searchType, setSearchType] = useState<string>(
    SearchLecturesOptions[0].value
  );
  const [searchKeyword, setSearchKeyword] = useState<string>("");

  const navigator = useNavigate();
  // 특강 목록 조회
  useEffect(() => {
    const onLoading = async () => {
      try {
        const lectures = await getLecture();
        setLectureLists(lectures.data);
        setTotalList(lectures.data);
      } catch (error) {
        console.error(error);
      }
    };
    onLoading();
  }, []);

  // 특강 삭제
  const lectureDelete = async (lectureId: number): Promise<void> => {
    try {
      await deleteLecture(lectureId);
      const renderLectures = lectureLists?.filter((lecture) => {
        return lecture.id !== lectureId;
      });
      setLectureLists(renderLectures);
    } catch (error) {
      console.error(error);
    }
  };

  // 검색 기능
  const lectuerSearch = async () => {
    try {
      const responseData = await searchLecture(searchType, searchKeyword);
      setLectureLists(responseData.data);
    } catch (error) {
      console.error(error);
    }
  };

  // 필터 기능
  const filterCategory = async (categoryId: number) => {
    if (categoryId !== 8) {
      const renderList = lectureLists.filter((item: Lecture) => {
        return item.lectureCategoryType === categoryId;
      });
      setLectureLists(renderList);
    } else {
      // 로직이 생각안나서 일단은 전체 조회로 받아옴
      setLectureLists(totalList);
    }
  };

  return (
    <>
      <section className="w-lvw h-full">
        <div className="flex w-full justify-between py-5 h-1/6">
          <SearchBar
            searchType={searchType}
            searchKeyword={searchKeyword}
            setSearchKeyword={setSearchKeyword}
            setSearchType={setSearchType}
            lectuerSearch={lectuerSearch}
          />
          <div className="w-3/12 flex items-center justify-end gap-5 cursor-pointer mr-12">
            <Avatar src="https://i.pravatar.cc/150?u=a042581f4e29026024d" />
            <p>Hello {userInfo[0].memberId}</p>
          </div>
        </div>
        <div className="w-full h-2/6 px-12 py-5">
          <p className="text-lg">주요 특강 주제</p>
          <div className="w-full h-52 py-8 flex gap-x-32 gap-y-8 flex-wrap overflow-y-scroll scrollbar-hide">
            {/* todo : 추후 데이터 map 돌려서 작성 예정 */}
            {LectureCategory.map((category) => {
              return (
                <div
                  className="flex flex-col items-center gap-5 cursor-pointer"
                  key={category.id}
                  onClick={() => filterCategory(category.id)}
                >
                  <Avatar
                    src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                    className="w-20 h-20 text-large"
                  />
                  <p>{category.name}</p>
                </div>
              );
            })}
          </div>
        </div>
        <hr className="mx-12" />
        <p className="text-lg px-12 py-5">모집중인 특강 목록</p>
        <div className="w-full h-[450px] px-12 py-5 flex flex-wrap gap-10 overflow-y-scroll scrollbar-hide">
          {lectureLists.length !== 0 ? (
            lectureLists.map((lectures: Lecture, index: number) => {
              return (
                <LectureCard
                  lecture={lectures}
                  key={index}
                  deleteHandler={lectureDelete}
                />
              );
            })
          ) : (
            <div className="w-full h-full flex justify-center items-center">
              <p className="text-xl">등록된 강의가 존재하지 않습니다</p>
            </div>
          )}
        </div>
      </section>
      <button
        className="fixed right-10 bottom-10 bg-lime-500 p-3 rounded-full"
        aria-label="특강 등록"
        onClick={() => navigator("./create")}
      >
        <EditIcon />
      </button>
    </>
  );
}
