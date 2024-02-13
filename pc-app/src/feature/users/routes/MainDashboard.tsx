import { getCurrentDayName } from "@/utils/DateFormattingHelpers";
import { Button, Card, CardBody, CardFooter, CardHeader } from "@nextui-org/react";
import dayjs from "dayjs";
import { useCallback, useEffect, useState } from "react";
import { checkIn } from "../../attendance/api/checkIn";
import { Attendance } from "@/feature/attendance";
import { getTodayAttendance } from "@/feature/attendance/api/getAttendance";
import Swal from "sweetalert2";
import { useMutation } from "@tanstack/react-query";
import { checkOut } from "@/feature/attendance/api/checkOut";
import { useRecoilValue } from "recoil";
import { userState } from "@/atoms/user";
import { NavigateFunction, useNavigate } from "react-router-dom";
import ScheduleBoard from "@/feature/schedule/components/ScheduleBoard";
import { searchBoard } from "@/feature/communities/api/freeBoard";
import { Board } from "@/feature/communities";

interface AttendanceCardProps {
	todayDate: dayjs.Dayjs;
	checkIn?: string;
	checkOut?: string;
	status?: number;
	doCheckIn: () => void;
	doCheckOut: () => void;
}

//입실, 퇴실 정보를 조회하고 입, 퇴실을 할 수 있는 기능을 가진 카드
function AttendanceCard({ todayDate, checkIn, checkOut, doCheckIn, doCheckOut }: AttendanceCardProps) {
	return (
		<div className="bg-green-400 p-4 shadow flex flex-col gap-3 space-y-4 rounded w-4/12 h-full">
			<h2 className="text-lg font-bold">출석체크 현황</h2>
			<div className="flex items-center space-x-2 justify-between w-full ">
				<div className="flex flex-col gap">
					<div className="w-3 bg-blue-500 h-1 rounded" />
					<span className="font-bold text-blue-500 text-lg mb-3">{todayDate.format("MM.DD")}</span>
					<span className="text-blue-500 text-xs">{getCurrentDayName(todayDate.day())?.fullKr ?? ""}</span>
				</div>
				<div className="flex h-16 gap-4">
					{checkIn ? (
						<div className="p-2 flex flex-col items-center bg-zinc-500 justify-evenly">
							<span className="text-white h-8">{dayjs(checkIn).format("hh:mm")}</span>
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
									<span className="text-white h-8">{dayjs(checkOut).format("hh:mm")}</span>
									<span className="text-white text-xs ">퇴실하기</span>
								</Button>
							) : (
								// 입실을 했지만 아직 퇴실을 하지 않은 상태
								<Button onClick={doCheckOut} className="text-blue-500 bg-white h-full rounded-none w-8 font-bold">
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
	const [notices, setNotices] = useState([] as Board[]);
	const getNotices = async () => {
		const response = await searchBoard({
			boardType: "notice",
			boardSize: 3,
		});
		setNotices(response.data.content);
	};

	useEffect(() => {
		getNotices();
	}, []);
	return (
		<div className=" flex flex-grow bg-white p-4 rounded-lg shadow flex-col w-1/2">
			<h2 className="text-lg font-semibold mb-4">공지사항</h2>
			<ul className="space-y-2">
				{notices.slice(0, 3).map((notice) => (
					<li id={notice.id.toString()} key={notice.id} className="flex">
						<span className="text-sm mr-4 text-green-500 ">{notice.name}</span>
						<span className="text-sm truncate w-4/5">{notice.title}</span>
					</li>
				))}
			</ul>
		</div>
	);
}

interface CurrentLectureCardProps {
	navigator: NavigateFunction;
}
function CurrentLectureCard({ navigator }: CurrentLectureCardProps) {
	const goClassRoom = () => navigator("/video");
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
						<p>● 34/40</p> {/* TODO 여기에 ●은 아이콘으로 바꾸고, 34와 40은 현재 스케줄의 수업 관련 데이터로 바꿔야 한다.*/}
					</div>
					<div className="flex  items-center w-full">
						<p>
							수업 종류 : <span> 정규 </span>
						</p>

						{/* TODO 수업 데이터로 전환해야 한다.*/}
					</div>
				</CardBody>

				<CardFooter>
					<Button color="success" onClick={goClassRoom}>
						참석하기
					</Button>
				</CardFooter>
			</Card>
		</div>
	);
}
export default function MainDashBoard() {

	const todayDate = dayjs().startOf("day");

	const [attendanceState, setAttendanceState] = useState<Attendance | undefined>();
	const userInfo = useRecoilValue(userState);
	const navigator = useNavigate();

	const getAttendanceState = useCallback(async () => {
		try {
			const res = await getTodayAttendance(userInfo.memberId);
			setAttendanceState(res.data.attendanceOnDate);
		} catch (e) {
			console.log(e);
		}
	}, [userInfo.memberId]);

	useEffect(() => {
		getAttendanceState();
	}, [getAttendanceState]);

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
			Swal.fire("주의", e?.response.data.message ?? "등교에 실패하였습니다. 다시 시도해주세요.", "warning");
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
			Swal.fire("에러 발생!", e?.response.data.message ?? "하교에 실패하였습니다. 다시 시도해주세요.", "error");
		},
	});

	const doCheckIn = async () => {
		checkInMutation.mutate();
	};

	const doCheckOut = async () => {
		checkOutMutation.mutate();
	};

	return (
		<div className="flex w-full">
			<div className="bg-white min-h-screen  w-3/4">
				<header className="flex justify-between items-center mb-6 shadow w-full py-5 pl-24 pr-10">
					<h1 className="text-2xl font-bold">나만의 작은 코너</h1>
					<div className="flex space-x-2"></div>
				</header>
				<section className="flex flex-grow w-full gap-6 px-24">
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
						<ScheduleBoard editMode={false} />
					</div>
				</div>
			</div>
			<div className="bg-lime-100 w-1/4 p-10">
				<CurrentLectureCard navigator={navigator} />
			</div>
		</div>
	);
}
