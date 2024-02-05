import dayjs from "dayjs";
import { Divider } from "@nextui-org/react";
import { getCurrentDayName } from "@/utils/DateFormattingHelpers";
import { Tag } from "@/components/Elements/Tag/Tag";
import { useEffect, useState } from "react";
import { Plan, Schedule } from "..";
import { getWeekSchedules } from "../api/getSchedule";
import { useRecoilValue } from "recoil";
import { userState } from "@/atoms/user";

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
            .add(4, "day")
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

function ScheduleBoard() {
  const userInfo = useRecoilValue(userState);
  const todayDate = dayjs().startOf("day");
  const [planStartDate, setPlanStartDate] = useState(
    dayjs().subtract(dayjs().day() - 1, "day")
  );
  const [planList, setPlanList] = useState([] as Plan[]);

  useEffect(() => {
    const fetchScheduleList = async () => {
      try {
        const res = await getWeekSchedules(
          userInfo.memberId,
          planStartDate.format("YYYY-MM-DD")
        );
        if (res.data.monday == planStartDate.format("YYYY-MM-DD")) {
          const newPlanList = [] as Plan[];
          for (let planIndex = 0; planIndex < 5; planIndex++) {
            newPlanList.push({
              id: planIndex,
              date: planStartDate.add(planIndex, "day"),
              scheduleList: [] as Schedule[],
              dayOfWeek: planIndex,
            });
          }
          let scheduleIndex = 0;
          res.data.scheduleList.forEach((schedule) => {
            if (scheduleIndex != schedule.timeTable) {
              for (let i = scheduleIndex; i < schedule.timeTable; i++) {
                newPlanList[schedule.dayOfWeek].scheduleList[scheduleIndex] =
                  null;
              }
            }
            newPlanList[schedule.dayOfWeek].scheduleList[schedule.timeTable] =
              schedule;
            scheduleIndex++;
          });
          setPlanList(newPlanList);
        } else {
          // 지금 화면에 보여야 할 스케줄 기간과 서버가 준 기간이 다르므로 아무 처리도 하지 않습니다.
        }
      } catch (e) {
        console.error(e);
      }
    };
    fetchScheduleList();
  });

  const onClickChangePlanDate = (isBefore: boolean) => {
    if (isBefore) {
      setPlanStartDate(planStartDate.subtract(7, "day")); // 일주일 빼기
    } else {
      setPlanStartDate(planStartDate.add(7, "day")); // 일주일 더하기
    }
  };

  return (
    <div className="flex flex-col gap-5 h-full">
      <CalenderHeader
        planStartDate={planStartDate}
        onClickChangePlanDate={onClickChangePlanDate}
      />

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
                  todayDate.diff(plan.date, "D") == 0
                    ? "bg-green-50 font-bold"
                    : ""
                }`}
              >
                <Divider />
                {/* 시간표에서 날짜 보여주는 맨 윗칸 */}
                <li className=" w-full text-center h-10 flex items-center justify-center">
                  {`${plan.date.format("YYYY-MM-DD")} (${
                    getCurrentDayName(plan.date.day())?.shortKr
                  })`}
                </li>
                <Divider />
                {plan.scheduleList.map((schedule, index) => (
                  <li key={index} className="h-1/6 w-full ">
                    {schedule === null ? null : (
                      <p className=" h-full w-full text-center flex items-center justify-center">
                        {schedule.lectureType == "mentoring" ? (
                          <Tag color="red">멘토링</Tag>
                        ) : null}
                        {!schedule.deletable ? (
                          <Tag color="blue"> 정규 </Tag>
                        ) : null}
                        {schedule.title}
                      </p>
                    )}
                    <Divider />
                  </li>
                ))}
              </ul>
            </div>
          ))}
          <Divider orientation="vertical" />
        </div>
      </div>
    </div>
  );
}

export default ScheduleBoard;
