import { userState } from "@/atoms/user";
import { Plan } from "@/feature/schedule";
import { getCurrentDayName } from "@/utils/DateFormattingHelpers";
import {
  Button,
  Card,
  CardFooter,
  CardHeader,
  Divider,
} from "@nextui-org/react";
import * as dayjs from "dayjs";
import { useState } from "react";
import { useRecoilState } from "recoil";
function MainDashBoard() {
  const todayDate = dayjs();
  const userInfo = useRecoilState(userState);
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
  const attendance = {
    status: 0,
    checkIn: "2024-01-22",
    checkOut: "2024-01-23",
  };

  return (
    <div className="flex w-full">
      <div className="bg-white min-h-screen w-5/6 ">
        <header className="flex justify-between items-center mb-6 shadow w-full py-5 pl-24 pr-10">
          <h1 className="text-2xl font-bold">나만의 작은 코너</h1>
          <div className="flex space-x-2"></div>
        </header>
        <main className="flex-col gap-8">
          <p>
            안녕하세요, <span>{userInfo[0].name}</span>님
          </p>
          <section className="flex gap-6">
            <AttendanceCard
              todayDate={todayDate}
              checkIn={attendance.checkIn}
              checkOut={attendance.checkOut}
              status={attendance.status}
            />
            <NoticeListCard />
          </section>
        </main>
        <div className="px-24 h-1/2">
          <div className="flex flex-col gap-5 h-full">
            <CalenderHeader
              planStartDate={planStartDate}
              onClickChangePlanDate={onClickChangePlanDate}
            />
            <Calender planList={planList} todayDate={todayDate} />
          </div>
        </div>
      </div>
      <div className="bg-lime-100 w-1/4 p-10">
        <CurrentLectureCard />
      </div>
    </div>
  );
}

export default MainDashBoard;
