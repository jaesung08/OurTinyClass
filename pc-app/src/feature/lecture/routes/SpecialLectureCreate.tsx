import AppLayout from '@/components/Layout/AppLayout';
import { Input, Select, SelectItem, Textarea, Button } from '@nextui-org/react';
import { LectureCategory } from '../assets/textContent';
import EditIcon from '@/assets/img/EditIcon';

export const SpecialLectureCreate = () => {
  return (
    <AppLayout>
      <section className="w-full">
        <p className="text-3xl ml-12 my-5">특강 생성</p>
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
        <div className="flex ml-12 my-5 gap-5">
          <div className="flex flex-col w-2/6 gap-5">
            <p>특강 날짜</p>
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
          <div className="flex flex-col w-2/6 gap-5">
            <p>특강 교시</p>
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
        </div>
        <Button className="ml-12 my-5 w-2/12 bg-lime-500 text-white">
          생성하기
        </Button>
      </section>
    </AppLayout>
  );
};
