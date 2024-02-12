import {Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, Button, Input} from "@nextui-org/react";
import { useState } from "react";
import { SelectCategories } from "../assets/textContents";
import {Listbox, ListboxItem} from "@nextui-org/react";


type CreateScheduleProps = {
    isOpen: boolean;
    onOpenChange: (isOpen: boolean) => void;
}
export default function ScheduleModal({
    isOpen,
    onOpenChange,
}: CreateScheduleProps
) {
    const [ todoSchedule, setTodoSchedule ] = useState<string>("")
  return (
    <>
      <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">일정 추가</ModalHeader>
              <ModalBody>
              <Input type="text" label="할일" disabled placeholder="할 일을 고르세요" value={todoSchedule}/>
                {/* <div className="flex flex-col w-full overflow-y-scroll h-80 gap-5 scrollbar-hide">
                    {
                        SelectCategories.map((item) => {
                            return <p className="bg-white px-2 py-3 border-2 rounded-lg cursor-pointer" key={item.id} onClick={() => setTodoSchedule(item.name)}>{item.name}</p>
                        })
                    }
                </div> */}
    <p className="mt-5">일반 항목</p>
    <div className="flex flex-col w-full overflow-y-scroll h-32 gap-5 scrollbar-hide">
      <Listbox
        aria-label="Actions"
        onAction={(key) => setTodoSchedule(key)}
      >
        {
          SelectCategories.map((item) => {
            return <ListboxItem key={item.name}>{item.name}</ListboxItem>                          
          })
        }
      </Listbox>
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
