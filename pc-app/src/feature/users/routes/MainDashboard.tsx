import { Plan } from "@/feature/schedule";
import { getCurrentDayName } from "@/utils/DateFormattingHelpers";
import { Button, Card, CardFooter, CardHeader } from "@nextui-org/react";
import * as dayjs from "dayjs";
import { useState } from "react";
function MainDashBoard() {
  const todayDate = dayjs();
  const [userInfo, setUserInfo] = useState({
    name: "누네띠네",
  });

  const dummyNotices = [
    {
      id: 1,
      writer: "전용빈",
      title:
        "어제 발비, 발하에서 누웠던 것은 오랜만에 해서 그랬던 것입니다. 쿠크는 현역이에요",
    },
    {
      id: 2,
      writer: "전용빈",
      title:
        "어제 발비, 발하에서 누웠던 것은 오랜만에 해서 그랬던 것입니다. 쿠크는 현역이에요",
    },
    {
      id: 3,
      writer: "전용빈",
      title:
        "어제 발비, 발하에서 누웠던 것은 오랜만에 해서 그랬던 것입니다. 쿠크는 현역이에요",
    },
    {
      id: 4,
      writer: "전용빈",
      title:
        "어제 발비, 발하에서 누웠던 것은 오랜만에 해서 그랬던 것입니다. 쿠크는 현역이에요",
    },
    {
      id: 5,
      writer: "전용빈",
      title:
        "어제 발비, 발하에서 누웠던 것은 오랜만에 해서 그랬던 것입니다. 쿠크는 현역이에요",
    },
  ];

  const planList: Plan[] = [
    {
      id: 1,
      date: "2022-01-01",
      scheduleList: [
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 72,
          isRegular: true,
          title: "수학",
          lectureId: 2,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "HTML + CSS",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "독서",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 3,
        },
      ],
    },
    {
      id: 2,
      date: "2022-01-02",
      scheduleList: [
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 72,
          isRegular: true,
          title: "수학",
          lectureId: 2,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "HTML + CSS",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "독서",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 3,
        },
      ],
    },
    {
      id: 3,
      date: "2022-01-03",
      scheduleList: [
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 72,
          isRegular: true,
          title: "수학",
          lectureId: 2,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "HTML + CSS",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "독서",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 3,
        },
      ],
    },
    {
      id: 4,
      date: "2022-01-04",
      scheduleList: [
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 72,
          isRegular: true,
          title: "수학",
          lectureId: 2,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "HTML + CSS",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "독서",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 3,
        },
      ],
    },
    {
      id: 5,
      date: "2022-01-05",
      scheduleList: [
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 1,
        },
        {
          isMentoring: false,
          addUserId: 72,
          isRegular: true,
          title: "수학",
          lectureId: 2,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "HTML + CSS",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "독서",
          lectureId: 3,
        },
        {
          isMentoring: false,
          addUserId: 232,
          isRegular: false,
          title: "노래연습",
          lectureId: 3,
        },
      ],
    },
  ];

  const [planStartDate, setPlanStartDate] = useState(todayDate);
  const onClickChangePlanDate = (isBefore: boolean) => {
    if (isBefore) {
      setPlanStartDate(planStartDate.subtract(7, "day")); // 일주일 빼기
    } else {
      setPlanStartDate(planStartDate.add(7, "day")); // 일주일 더하기
    }
  };

  return (
    <div className="flex w-full">
      <div className="bg-white min-h-screen w-5/6 ">
        <header className="flex justify-between items-center mb-6 shadow w-full py-5 pl-24 pr-10">
          <h1 className="text-2xl font-bold">나만의 작은 코너</h1>
          <div className="flex space-x-2"></div>
        </header>
        <main className="flex-col gap-8 px-24 pb-8">
          <p className="text-2xl py-5">
            안녕하세요, <span className="font-semibold">{userInfo.name}</span>님
          </p>
          <section className="flex gap-6 h-38">
            <div className="bg-green-400 p-4 shadow flex flex-col gap-3 space-y-4 rounded w-4/12 h-full">
              <h2 className="text-lg ">출석체크 현황</h2>
              <div className="flex items-center space-x-2 justify-between w-full ">
                <div className="flex flex-col gap">
                  <div className="w-3 bg-blue-500 h-1 rounded" />
                  <span className="font-bold text-blue-500 text-lg mb-3">
                    {todayDate.format("MM.DD")}
                  </span>
                  <span className="text-blue-500 text-xs">
                    {getCurrentDayName(todayDate.day())?.fullKr ?? ""}
                  </span>
                </div>
                <div className="flex h-16 gap-4">
                  <div className="p-2 flex flex-col items-center bg-zinc-500 justify-evenly">
                    <span className="text-white h-8">08:58</span>
                    <span className="text-white text-xs">정상 출석</span>
                  </div>
                  <Button className="text-blue-500 bg-white h-full rounded-none w-8">
                    출석체크
                  </Button>
                </div>
              </div>
            </div>
            <div className="flex-1">
              <div className="bg-white p-4 rounded-lg shadow">
                <h2 className="text-lg font-semibold mb-4">공지사항 4</h2>
                <ul className="space-y-2">
                  {dummyNotices.slice(0, 3).map((notice) => (
                    <li id={notice.id.toString()} className="flex">
                      <span className="text-sm mr-4 text-green-500">
                        {notice.writer}
                      </span>
                      <span className="text-sm truncate w-4/5">
                        09:00 시작하는 일과를 위한 준비를 하여봅씨다람쥐
                        아라비아 어쩌구 저쩌구
                      </span>
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </section>
        </main>
        <div className="px-24">
          <div className="flex justify-between flex-col gap-5">
            <div className="flex justify-between">
              <h3 className="text-2xl">나의 계획</h3>
              <div className="flex gap-3">
                <span>
                  {`${planStartDate.format("MM월 DD일")} - ${planStartDate
                    .add(5, "D")
                    .format("MM월 DD일")}`}
                </span>
                <span
                  onClick={() => onClickChangePlanDate(true)}
                  className=" cursor-pointer"
                >
                  ^
                </span>
                <span
                  onClick={() => onClickChangePlanDate(false)}
                  className=" cursor-pointer"
                >
                  V
                </span>
              </div>
            </div>
            <div className="flex">
              <ul className="flex flex-col">
                <li className=" border h-8 w-10"></li>
                {Array.from({ length: 6 }, (_, i) => (
                  <li key={i} className="border h-8 w-10 text-center">
                    {i + 1}
                  </li>
                ))}
              </ul>
              <div className="flex">
                {planList.map((plan) => (
                  <ul className="flex flex-col ">
                    <li className="border h-8 w-40 text-center">
                      {`${dayjs(plan.date).format("YYYY-MM-DD")} (${
                        getCurrentDayName(dayjs(plan.date).day())?.shortKr
                      })`}
                    </li>
                    {plan.scheduleList.map((schedule, index) => (
                      <li key={index} className="border h-8 w-40 text-center">
                        {schedule.title}
                      </li>
                    ))}
                  </ul>
                ))}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="bg-green-50 w-1/4 p-10">
        <div>
          <h3 className="pb-3 text-large">진행중인 수업</h3>
          <Card className="p-4 rounded-lg">
            <CardHeader>
              <div className="flex items-center justify-between w-full gap-3">
                <h6 className="text-lg font-semibold">3학년 2반</h6>
              </div>
            </CardHeader>
            {/* TODO : 여기에 진행중인 수업의 썸네일 넣어야 한다. */}
            <CardFooter className="mt-4 flex flex-col gap-2">
              <div className="flex justify-between items-center w-full">
                <p>온라인 학생 수 : </p>
                <p>● 34/40</p>{" "}
                {/* TODO 여기에 ●은 아이콘으로 바꾸고, 34와 40은 현재 스케줄의 수업 관련 데이터로 바꿔야 한다.*/}
              </div>
              <div className="flex  items-center w-full">
                <p>
                  수업 종류 : <span> 정규 </span>
                </p>

                {/* TODO 수업 데이터로 전환해야 한다.*/}
              </div>
            </CardFooter>
          </Card>
        </div>
      </div>
    </div>
  );
}

export default MainDashBoard;
