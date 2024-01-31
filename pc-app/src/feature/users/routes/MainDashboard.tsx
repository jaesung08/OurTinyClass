import { Plan } from "@/feature/schedule";
import { getCurrentDayName } from "@/utils/DateFormattingHelpers";
import {
  Button,
  Card,
  CardBody,
  CardFooter,
  CardHeader,
  Divider,
} from "@nextui-org/react";
import * as dayjs from "dayjs";
import { useEffect, useState } from "react";
import { checkIn } from "../../attendance/api/checkIn";
import { Attendance } from "@/feature/attendance";
import { getTodayAttendance } from "@/feature/attendance/api/getAttendance";
import Swal from "sweetalert2";
import { useMutation } from "@tanstack/react-query";
import { checkOut } from "@/feature/attendance/api/checkOut";
import { Tag } from "@/components/Elements/Tag/Tag";

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
    date: "2024-01-23",
    scheduleList: [
      {
        isMentoring: false,
        addUserId: 232,
        isRegular: true,
        title: "노래연습",
        lectureId: 1,
      },
      {
        isMentoring: true,
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
    date: "2024-01-22",
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

interface AttendanceCardProps {
  todayDate: dayjs.Dayjs;
  checkIn?: string;
  checkOut?: string;
  status?: number;
  doCheckIn: () => void;
  doCheckOut: () => void;
}

//입실, 퇴실 정보를 조회하고 입, 퇴실을 할 수 있는 기능을 가진 카드
function AttendanceCard({
  todayDate,
  checkIn,
  checkOut,
  doCheckIn,
  doCheckOut,
}: AttendanceCardProps) {
  return (
    <div className="bg-green-400 p-4 shadow flex flex-col gap-3 space-y-4 rounded w-4/12 h-full">
      <h2 className="text-lg font-bold">출석체크 현황</h2>
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
          {checkIn ? (
            <div className="p-2 flex flex-col items-center bg-zinc-500 justify-evenly">
              <span className="text-white h-8">
                {dayjs(checkIn).format("hh:mm")}
              </span>
              <span className="text-white text-xs">정상 출석</span>
            </div>
          ) : (
            <Button
              onClick={doCheckIn}
              className="text-blue-500 bg-white h-full rounded-none w-8 font-bold hover:bg-blue-600 hover:text-white "
            >
              입실하기
            </Button>
          )}

          {
            checkIn ? ( // 아직 입실을 하지 않았다면 퇴실 버튼을 보일 필요가 없으므로 입실 여부 확인.
              checkOut ? ( // 퇴실을 한 상태라면, 다시 갱신할 수 있는 버튼
                <Button
                  onClick={doCheckOut}
                  className="p-2 flex flex-col items-center bg-zinc-500 justify-evenly rounded-none h-full hover:bg-blue-500 font-bold"
                >
                  <span className="text-white h-8">
                    {dayjs(checkOut).format("hh:mm")}
                  </span>
                  <span className="text-white text-xs ">퇴실하기</span>
                </Button>
              ) : (
                // 입실을 했지만 아직 퇴실을 하지 않은 상태
                <Button
                  onClick={doCheckOut}
                  className="text-blue-500 bg-white h-full rounded-none w-8 font-bold"
                >
                  퇴실하기
                </Button>
              )
            ) : null // 아직 입실을 하지 않았다면 퇴실 버튼을 보일 필요가 없음.
          }
        </div>
      </div>
    </div>
  );
}

function NoticeListCard() {
  return (
    <div className="flex-1">
      <div className="bg-white p-4 rounded-lg shadow">
        <h2 className="text-lg font-semibold mb-4">공지사항 4</h2>
        <ul className="space-y-2">
          {dummyNotices.slice(0, 3).map((notice) => (
            <li id={notice.id.toString()} key={notice.id} className="flex">
              <span className="text-sm mr-4 text-green-500">
                {notice.writer}
              </span>
              <span className="text-sm truncate w-4/5">
                09:00 시작하는 일과를 위한 준비를 하여봅씨다람쥐 아라비아 어쩌구
                저쩌구
              </span>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

interface CalenderHeaderProps {
  planStartDate: dayjs.Dayjs;
  onClickChangePlanDate: (isBefore: boolean) => void;
}

function CalenderHeader({
  planStartDate,
  onClickChangePlanDate,
}: CalenderHeaderProps) {
  return (
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
  );
}

interface CalenderProps {
  todayDate: dayjs.Dayjs;
  planList: Plan[];
}

function Calender({ planList, todayDate }: CalenderProps) {
  return (
    <div className="flex w-full h-full">
      <Divider orientation="vertical" />

      <ul className="flex flex-col">
        {/*가장 앞 목차 번호 */}
        <Divider />
        <li className=" h-10 w-10">{/* 왼쪽 맨 위 빈칸 */}</li>
        {Array.from({ length: 6 }, (_, i) => (
          <li key={i} className=" w-10 h-1/6 ">
            <Divider />
            <p className=" text-center flex justify-center items-center h-full">
              {i + 1}
            </p>
          </li>
        ))}
        <Divider />
        {/*가장 앞 목차 번호 끝 */}
      </ul>
      <Divider orientation="vertical" />
      <div className="flex w-full">
        {/*시간표 부분 */}
        {planList.map((plan) => (
          <div key={plan.id} className="flex w-1/5 h-full">
            <Divider orientation="vertical" />
            {/*하루치 시간표 세로로 한줄씩 렌더링 */}
            <ul
              className={`flex flex-col h-full w-full ${
                todayDate.diff(dayjs(plan.date), "D") == 0
                  ? "bg-green-50 font-bold"
                  : ""
              }`}
            >
              <Divider />
              {/* 시간표에서 날짜 보여주는 맨 윗칸 */}
              <li className=" w-full text-center h-10 flex items-center justify-center">
                {`${dayjs(plan.date).format("YYYY-MM-DD")} (${
                  getCurrentDayName(dayjs(plan.date).day())?.shortKr
                })`}
              </li>
              <Divider />
              {plan.scheduleList.map((schedule, index) => (
                <li key={index} className="h-1/6 w-full ">
                  <p className=" h-full w-full text-center flex items-center justify-center">
                    {schedule.isMentoring ? (
                      <Tag color="red">멘토링</Tag>
                    ) : null}
                    {schedule.isRegular ? <Tag color="blue"> 정규 </Tag> : null}
                    {schedule.title}
                  </p>
                  <Divider />
                </li>
              ))}
            </ul>
          </div>
        ))}
        <Divider orientation="vertical" />
      </div>
    </div>
  );
}

function CurrentLectureCard() {
  return (
    <div>
      <Card className="p-4 rounded-lg">
        <CardHeader>
          <div className="flex justify-between w-full gap-3">
            <h6 className="text-lg font-semibold">3학년 2반 교실</h6>
          </div>
        </CardHeader>
        <CardBody>
          <div className="flex justify-between items-center w-full">
            <p>참석자 수 : </p>
            <p>● 34/40</p>{" "}
            {/* TODO 여기에 ●은 아이콘으로 바꾸고, 34와 40은 현재 스케줄의 수업 관련 데이터로 바꿔야 한다.*/}
          </div>
          <div className="flex  items-center w-full">
            <p>
              수업 종류 : <span> 정규 </span>
            </p>

            {/* TODO 수업 데이터로 전환해야 한다.*/}
          </div>
        </CardBody>

        <CardFooter>
          <Button color="success">참석하기</Button>
        </CardFooter>
      </Card>
    </div>
  );
}
export default function MainDashBoard() {
  const todayDate = dayjs().startOf("day");
  const [planStartDate, setPlanStartDate] = useState(todayDate);
  const [attendanceState, setAttendanceState] = useState<
    Attendance | undefined
  >();

  useEffect(() => {
    getAttendanceState();
  }, []);
  const onClickChangePlanDate = (isBefore: boolean) => {
    if (isBefore) {
      setPlanStartDate(planStartDate.subtract(7, "day")); // 일주일 빼기
    } else {
      setPlanStartDate(planStartDate.add(7, "day")); // 일주일 더하기
    }
  };

  const checkInMutation = useMutation({
    mutationFn: checkIn,
    onSuccess: (res) => {
      setAttendanceState({
        date: attendanceState?.date,
        checkOut: attendanceState?.checkOut,
        checkIn: res.data.checkInTime,
        status: res.data.status,
      });
    },
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    onError: (e: any) => {
      Swal.fire(
        "에러 발생!",
        e?.response.data.message ?? "등교에 실패하였습니다. 다시 시도해주세요.",
        "error"
      );
    },
  });

  const checkOutMutation = useMutation({
    mutationFn: checkOut,
    onSuccess: (res) => {
      setAttendanceState({
        date: attendanceState?.date,
        checkIn: attendanceState?.checkIn,
        checkOut: res.data.checkOutTime,
        status: res.data.status,
      });
    },
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    onError: (e: any) => {
      Swal.fire(
        "에러 발생!",
        e?.response.data.message ?? "하교에 실패하였습니다. 다시 시도해주세요.",
        "error"
      );
    },
  });

  const doCheckIn = async () => {
    checkInMutation.mutate();
  };

  const doCheckOut = async () => {
    checkOutMutation.mutate();
  };

  const getAttendanceState = async () => {
    try {
      const res = await getTodayAttendance();
      setAttendanceState(res.data.attendanceOnDate);
    } catch (e) {
      console.log(e);
    }
  };
  return (
    <div className="flex w-full">
      <div className="bg-white min-h-screen w-5/6 ">
        <header className="flex justify-between items-center mb-6 shadow w-full py-5 pl-24 pr-10">
          <h1 className="text-2xl font-bold">나만의 작은 코너</h1>
          <div className="flex space-x-2"></div>
        </header>
        <section className="flex gap-6 px-24">
          <AttendanceCard
            todayDate={todayDate}
            checkIn={attendanceState?.checkIn}
            checkOut={attendanceState?.checkOut}
            status={attendanceState?.status}
            doCheckIn={doCheckIn}
            doCheckOut={doCheckOut}
          />
          <NoticeListCard />
        </section>
        <div className="px-24 h-1/2 mt-5">
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
