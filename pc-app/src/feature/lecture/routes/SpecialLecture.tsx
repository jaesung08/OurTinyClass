import { LectureCard } from "../components/LectureCard";
import { Avatar, Input } from "@nextui-org/react";
import EditIcon from "@/assets/img/EditIcon";
import { useNavigate } from "react-router";
import { deleteLecture, getLecture } from "../api/createLecture";
import { useEffect, useState } from "react";
import { useRecoilState } from "recoil";
import { userState } from "@/atoms/user";
import { LectureType } from "../types";
import { LectureCategory } from "../assets/textContent";

export function SpecialLecture() {
  const userInfo = useRecoilState(userState);
  // TODO : Array type 으로 변경해야함
  const [lectureLists, setLectureLists] = useState<LectureType[]>();

  const navigator = useNavigate();
  // 특강 목록 조회
  useEffect(() => {
    const onLoading = async () => {
      try {
        const lectures = await getLecture();
        setLectureLists(lectures.data);
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
  return (
    <>
      <section className="w-lvw h-full">
        <div className="flex w-full justify-between py-5 h-1/6">
          <Input
            className="bg-white w-7/12 ml-12"
            type="text"
            size="sm"
            placeholder="제목을 입력하세요."
          />
          <div className="w-3/12 flex items-center justify-end gap-5 cursor-pointer mr-12">
            <Avatar src="https://i.pravatar.cc/150?u=a042581f4e29026024d" />
            <p>Hello {userInfo[0].memberId}</p>
          </div>
        </div>
        <div className="w-full h-2/6 px-12 py-5">
          <p className="text-lg">주요 특강 주제</p>
          <div className="w-full h-52 py-8 flex gap-x-32 gap-y-16 flex-wrap overflow-y-scroll scrollbar-hide">
            {/* todo : 추후 데이터 map 돌려서 작성 예정 */}
            {LectureCategory.map((category) => {
              return (
                <div
                  className="flex flex-col items-center gap-5 cursor-pointer"
                  key={category.id}
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
          {lectureLists &&
            lectureLists.map((lectures: LectureType, index: number) => {
              return (
                <LectureCard
                  lecture={lectures}
                  key={index}
                  deleteHandler={lectureDelete}
                />
              );
            })}
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
