import AppLayout from "@/components/Layout/AppLayout";
import { Image, Button } from "@nextui-org/react";

const MyPage = () => {
  return (
    <AppLayout>
     <div className="bg-white w-full p-4">
      <div className="flex justify-between w-full min-h-10">
        <div className="flex  w-[33%] items-center min-h-10">
          <div className="w-1/2">
            <Image
              width={200}
              alt="NextUI hero Image"
              src="https://t1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/2sWY/image/3M3smqiU-iS_9xWn653lihVZGNA.jpg"
            />
          </div>
          <div className="w-1/2 items-center">
            <h2 className="text-xl font-semibold m-2 text-center">최창호</h2>
            <p className="text-sm text-gray-500 text-center">중학과정 3-2 C반</p>
            <div className="bg-[#d1f0d7] w-full m-4 rounded-lg text-center flex flex-row p-2">
              <div className="w-1/2">
                <p className="text-lg font-semibold ">포인트 </p>
              </div>
              <div className="w-2/6 text-right">
                  <p className="text-lg font-bold text-right"> 500P</p>
              </div>
            </div>
          </div>
        </div>
        <div className="flex flex-row items-center w-[33%] min-h-10">
          <div className="w-24 h-24 rounded-full border-8 border-green-400 flex items-center justify-center m-4">
            <span className="text-2xl font-semibold text-green-600">100%</span>
          </div>
          <div className="bg-[#e6f7e9] rounded-lg p-4 flex flex-col w-2/3">
            <div className="flex flex-row w-full justify-between">
              <p className="text-sm">총 수업일수: </p>
              <p className="text-sm text-right"> 20일</p>
            </div>
            <div className="flex flex-row w-full justify-between">
              <div className="flex flex-row items-center gap-1">
                <div className="w-2 h-2 bg-[#00c853] rounded-full"> </div>
                <p className="text-sm"> 정상 출석:</p>
              </div>
              <p className="text-sm"> 20일</p>
            </div>
            <div className="flex flex-row w-full justify-between">
              <div className="flex flex-row items-center gap-1">
                <div className="w-2 h-2 bg-[#ffea00] rounded-full"> </div>
                <p className="text-sm"> 지각/조퇴:</p>
              </div>
              <p className="text-sm"> 0일</p>
            </div>
            <div className="flex flex-row w-full justify-between">
              <div className="flex flex-row items-center gap-1">
                <div className="w-2 h-2 bg-[#ff1744] rounded-full"> </div>
                <p className="text-sm"> 결석:</p>
              </div>
              <p className="text-sm"> 0일</p>
            </div>
          </div>
        </div>
        <div className="flex flex-col justify-around items-center w-[25%] min-h-10">
          <div className="flex min-h-8 flex-col items-end w-full">
           <Button className="bg-[#b0bec5] rounded-lg  mb-2">
             <p className="text-sm text-black">프로필 수정</p>
           </Button>
           <Button className="bg-[#e6f7e9] rounded-lg ">
             <p className="text-sm text-black"> 내 시간표 보기</p>
           </Button>
          </div>
          <div className="flex w-full justify-end">
            <p className="text-sm">담임 선생님:</p>
            <p className="text-sm">최창호</p>
          </div>
        </div>
      </div>
      <div className="mt-3">
        <div className="bg-[#e6f7e9] rounded-lg p-4">
          <p className="font-bold mb-3 text-green-900"> 이번주 목표</p>
          <p className="text-sm">완료된 레슨을 복습 상하고 매일 한문항씩 프로젝트하기!</p>
        </div>
      </div>
      <div className="mt-4 grid grid-cols-2 gap-4">
        <div className="bg-[#e6f7e9] rounded-lg p-4">
          <h3 className="text-lg font-semibold mb-2 text-green-900">최근 프로젝트의 추적</h3>
          <div className="grid grid-cols-3 gap-4">
            <div className="flex flex-col items-center bg-green-400 rounded-xl p-2">
              <div className="w-full h-10 bg-white rounded-lg"> </div>
              <span className="text-sm mt-1">완료한 학기</span>
              <span className="font-semibold">3</span>
            </div>
            <div className="flex flex-col items-center bg-green-400 rounded-xl p-2">
              <div className="w-full h-10 bg-white rounded-lg"> </div>
              <span className="text-sm mt-1">총 학습시간</span>
              <span className="font-semibold">3</span>
            </div>
            <div className="flex flex-col items-center bg-green-400 rounded-xl p-2">
              <div className="w-full h-10 bg-white rounded-lg"> </div>
              <span className="text-sm mt-1">학습한 과목 수</span>
              <span className="font-semibold">3</span>
            </div>
          </div>
        </div>
        <div className="bg-[#e6f7e9] rounded-lg p-4">
          <h3 className="text-lg font-semibold mb-2 text-green-900">가장 좋아하는 과목!</h3>
          <div className="flex flex-col space-y-1">
            <span className="font-semibold ">1위 수학</span>
            <span className="font-semibold">2위 영어</span>
            <span className="font-semibold">3위 과학</span>
          </div>
        </div>
      </div>
      <div className="mt-4  gap-4 w-full">
        <div className="bg-[#e6f7e9] rounded-lg p-4 w-full">
          <h3 className="text-lg font-semibold text-green-900 mb-3"> 뱃지</h3>
          <div className="flex items-center mb-3">
            <Button className="bg-[#d1f0d7] text-green-600">도전과제</Button>
          </div>
          <div className="flex items-center">
            <Button className="bg-[#d1f0d7] text-green-600">경진대회</Button>
          </div>
        </div>
      </div>
      <div className="mt-4 grid grid-cols-2 gap-4">
        <div className="bg-[#e6f7e9] rounded-lg p-4">
          <h3 className="text-lg font-semibold text-green-900 mb-3">좋아요 누른 게시글</h3>
          <p className="text-sm">좋은 포스팅 글을 모으고</p>
          <p className="text-sm">일간리포트 스토리 더 잘할겁니다.</p>
          <p className="text-sm">일간리포트 공부 방법</p>
        </div>
        <div className="bg-[#e6f7e9] rounded-lg p-4">
          <h3 className="text-lg font-semibold text-green-900 mb-3">내가 쓴 글</h3>
          <p className="text-sm">뱃지</p>
        </div>
      </div>
    </div>
      
    </AppLayout>
  );
};

export default MyPage;
