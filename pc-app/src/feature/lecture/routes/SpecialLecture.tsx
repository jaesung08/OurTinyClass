import AppLayout from "@/components/Layout/AppLayout";
import { LectureCard } from "../components/LectureCard";
import { Avatar, Input } from "@nextui-org/react";
import EditIcon from "@/assets/img/EditIcon";

export function SpecialLecture() {
  return (
    <AppLayout>
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
            <p>Hello There!</p>
          </div>
        </div>
        <div className="w-full h-2/6 px-12 py-5">
          <p className="text-lg">주요 특강 주제</p>
          <div className="w-full my-12 flex gap-x-32 gap-y-10 flex-wrap">
            {/* todo : 추후 데이터 map 돌려서 작성 예정 */}
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
            <div className="flex flex-col items-center gap-5 cursor-pointer">
              <Avatar
                src="https://i.pravatar.cc/150?u=a04258114e29026708c"
                className="w-20 h-20 text-large"
              />
              <p>수학</p>
            </div>
          </div>
        </div>
        <hr className="mx-12" />
        <p className="text-lg px-12 py-5">모집중인 특강 목록</p>
        <div className="w-full h-[450px] px-12 py-5 flex flex-wrap gap-10 overflow-y-scroll scrollbar-hide">
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
          <LectureCard />
        </div>
      </section>
      <button
        className="fixed right-10 bottom-10 bg-lime-500 p-3 rounded-full"
        aria-label="특강 등록"
      >
        <EditIcon />
      </button>
    </AppLayout>
  );
}
