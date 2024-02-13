import { Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, Input } from "@nextui-org/react";
import { useState } from "react";
import { SelectCategories } from "../assets/textContents";
import { Accordion, AccordionItem } from "@nextui-org/react";

type CreateScheduleProps = {
	isOpen: boolean;
	onOpenChange: (isOpen: boolean) => void;
};
export default function ScheduleModal({ isOpen, onOpenChange }: CreateScheduleProps) {
	const [todoSchedule, setTodoSchedule] = useState<string>("");
	return (
		<>
			<Modal isOpen={isOpen} onOpenChange={onOpenChange}>
				<ModalContent>
					{(onClose) => (
						<>
							<ModalHeader className="flex flex-col gap-1">일정 추가</ModalHeader>
							<ModalBody>
								<Input type="text" label="할일" disabled placeholder="할 일을 고르세요" value={todoSchedule} />
								<p className="mt-5">일반 항목</p>
								<div className="flex flex-col w-full overflow-y-scroll h-48 gap-5 scrollbar-hide">
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
															<p className="p-5 cursor-pointer" onClick={() => setTodoSchedule(info.title)} key={info.lectureId}>
																{info.title}
															</p>
														);
													})}
												</AccordionItem>
											</Accordion>
										);
									})}
								</div>
							</ModalBody>
							<ModalFooter>
								<Button color="danger" onPress={onClose}>
									닫기
								</Button>
								<Button color="primary" onPress={onClose}>
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
