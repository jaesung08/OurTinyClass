import AppLayout from "@/components/Layout/AppLayout";
import { Image, Button } from "@nextui-org/react";

const MyPage = () => {
  return (
    <AppLayout>
      <section className="w-full overflow-y-scroll scrollbar-hide">
        <article className="w-full flex justify-around mt-10">
          <div className="w-2/6 flex gap-5">
            <Image
              width={200}
              alt="NextUI hero Image"
              src="https://t1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/2sWY/image/3M3smqiU-iS_9xWn653lihVZGNA.jpg"
            />
            <div className="flex flex-col justify-around">
              <p>김탈주</p>
              <p>4학년</p>
              <p>포인트 : 400p</p>
            </div>
          </div>
          <div className="w-2/6 flex gap-5 justify-end">
            <div className="flex flex-col justify-around w-3/6">
              <Button color="primary">우리반 친구보기</Button>
              <Button color="default">회원정보 수정</Button>
            </div>
            <div className="relative">
              <Image
                width={200}
                alt="NextUI hero Image"
                src="https://t1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/2sWY/image/3M3smqiU-iS_9xWn653lihVZGNA.jpg"
              />
              <Button
                color="default"
                className="absolute bottom-3 left-1/2 transform -translate-x-1/2 z-10"
              >
                상담신청
              </Button>
            </div>
          </div>
        </article>
        <article className="bg-lime-300 w-full min-h-96 pt-10 flex flex-col">
          <div>시간표</div>
          <div>둘째칸</div>
        </article>
        <article></article>
      </section>
    </AppLayout>
  );
};

export default MyPage;
