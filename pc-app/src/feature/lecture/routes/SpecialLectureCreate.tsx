import AppLayout from "@/components/Layout/AppLayout";
import { Input, Select, SelectItem, Textarea, Button } from "@nextui-org/react";
import { ClassTime, LectureCategory } from "../assets/textContent";
import { useState } from "react";
import ReactDatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "../assets/datepicker.scss";

export const SpecialLectureCreate = () => {
  const [startDate, setStartDate] = useState<Date | null>(new Date());
  return (
    <AppLayout>
      <section className="w-full">
        <p className="text-2xl ml-12 my-5">특강 생성</p>
        <div className="flex flex-col ml-12 my-5 gap-5">
          <p className="text-lg">특강 분류</p>
          <Select
            color="default"
            aria-label="특강 분류 선택"
            defaultSelectedKeys={[LectureCategory[0].name]}
            className="max-w-xs"
          >
            {LectureCategory.map((category) => (
              <SelectItem key={category.name} value={category.name}>
                {category.name}
              </SelectItem>
            ))}
          </Select>
        </div>
        <div className="flex flex-col ml-12 my-5 gap-5">
          <p className="text-2xl">특강 제목</p>
          <Input
            className="bg-white w-11/12"
            type="text"
            size="sm"
            placeholder="제목을 입력하세요."
          />
        </div>
        <div className="flex flex-col ml-12 my-5 gap-5">
          <p className="text-lg">특강 설명</p>
          <Textarea
            aria-label="특강 설명"
            placeholder="특강 설명을 입력하세요"
            className="w-11/12"
            minRows={15}
            maxRows={15}
            cacheMeasurements={true}
          />
        </div>
        <div className="flex ml-12 my-5 gap-40">
          <div className="flex flex-col w-2/6 gap-5 relative">
            <p>특강 날짜</p>
            <ReactDatePicker
              className="relative px-3 w-full inline-flex shadow-sm tap-highlight-transparent bg-default-100 data-[hover=true]:bg-default-200 group-data-[focus=true]:bg-default-100 rounded-medium flex-col items-start justify-center gap-0 outline-none data-[focus-visible=true]:z-10 data-[focus-visible=true]:outline-2 data-[focus-visible=true]:outline-focus data-[focus-visible=true]:outline-offset-2 h-14 min-h-unit-14 py-2 cursor-pointer"
              dateFormat="yyyy년 MM월 dd일"
              dateFormatCalendar="yyyy년 MM월"
              selected={startDate}
              popperPlacement="bottom"
              onChange={(date) => setStartDate(date)}
            />
          </div>
          <div className="flex flex-col w-2/6 gap-5">
            <p>특강 교시</p>
            <Select
              color="default"
              aria-label="특강 분류 선택"
              defaultSelectedKeys={[ClassTime[0].name]}
              className="max-w-full"
            >
              {ClassTime.map((time) => (
                <SelectItem key={time.name} value={time.value}>
                  {time.name}
                </SelectItem>
              ))}
            </Select>
          </div>
        </div>
        <Button className="ml-12 my-5 w-2/12 bg-lime-500 text-white">
          생성하기
        </Button>
      </section>
    </AppLayout>
  );
};
