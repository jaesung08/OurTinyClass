import { Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, Input } from "@nextui-org/react";
import { useEffect, useState } from "react";
import { SelectCategories } from "../assets/textContents";
import { Accordion, AccordionItem } from "@nextui-org/react";
import { createSchedule, getSpecialLecture, getFreeLecture } from "../api/createSchedule";

type CreateScheduleProps = {
	isOpen: boolean;
	onOpenChange: (isOpen: boolean) => void;
	date: string;
	dayOfWeek: number;
	fetchScheduleList: () => void;
};

export default function ScheduleModal({ isOpen, onOpenChange, date, dayOfWeek, fetchScheduleList }: CreateScheduleProps) {
	const [todoSchedule, setTodoSchedule] = useState<string>("");
	const [currentLecture, setCurrentLecture] = useState<number>(1);

	useEffect(() => {
		const getCategories = async () => {
			try {
				const responseSpecial = await getSpecialLecture(date, dayOfWeek);
				const responseFree = await getFreeLecture(0);
				console.log(responseFree, responseSpecial);
			} catch (error) {
				console.error(error);
			}
		};
		getCategories();
	}, []);

	const scheduleCreate = async (lectureId: number) => {
		try {
			await createSchedule(lectureId, date, dayOfWeek);
			fetchScheduleList();
			console.log(fetchScheduleList());
		} catch (error) {
			console.error(error);
		}
	};

	return (
		<>
			<Modal isOpen={isOpen} onOpenChange={onOpenChange} className="max-w-[800px]">
				<ModalContent>
					{(onClose) => (
						<>
							<ModalHeader className="flex flex-col gap-1">일정 추가</ModalHeader>
							<ModalBody>
								<Input type="text" label="할일" disabled placeholder="할 일을 고르세요" value={todoSchedule} />
								<div className="flex mt-5">
									<p className="w-[400px]">일반 항목</p>
									<p className="w-[400px]">특강 항목</p>
								</div>
								<div className="flex">
									<div className="flex flex-col w-full overflow-y-scroll h-52 gap-5 scrollbar-hide">
										{SelectCategories.map((item) => {
											return (
												<Accordion key={item.id}>
													<AccordionItem
														className="border-1 rounded-xl px-5"
														key={item.id}
														aria-label={`${item.id} ${item.id}`}
														title={`${item.name}`}
													>
														{item.content.map((info) => {
															return (
																<p
																	className="p-5 cursor-pointer"
																	onClick={() => {
																		setTodoSchedule(info.title);
																		setCurrentLecture(info.lectureId);
																	}}
																	key={info.lectureId}
																>
																	{info.title}
																</p>
															);
														})}
													</AccordionItem>
												</Accordion>
											);
										})}
									</div>
									<div className="flex flex-col w-full overflow-y-scroll h-52 gap-5 scrollbar-hide">
										{SelectCategories.map((item) => {
											return (
												<Accordion key={item.id}>
													<AccordionItem
														className="border-1 rounded-xl px-5"
														key={item.id}
														aria-label={`${item.id} ${item.id}`}
														title={`${item.name}`}
													>
														{item.content.map((info) => {
															return (
																<p
																	className="p-5 cursor-pointer"
																	onClick={() => {
																		setTodoSchedule(info.title);
																		setCurrentLecture(info.lectureId);
																	}}
																	key={info.lectureId}
																>
																	{info.title}
																</p>
															);
														})}
													</AccordionItem>
												</Accordion>
											);
										})}
									</div>
								</div>
							</ModalBody>
							<ModalFooter>
								<Button color="danger" onPress={onClose}>
									닫기
								</Button>
								<Button
									color="primary"
									onPress={onClose}
									onClick={() => {
										scheduleCreate(currentLecture);
									}}
								>
									추가
								</Button>
							</ModalFooter>
						</>
					)}
				</ModalContent>
			</Modal>
		</>
	);
}
