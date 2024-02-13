import {
  Tabs,
  Tab,
  Card,
  CardBody,
  Select,
  SelectItem,
} from "@nextui-org/react";
import { GradeSelectOption } from "../assets/textContent";

function SelectGrade() {
  return (
    <div className="flex w-full flex-wrap md:flex-nowrap gap-4">
      <Select
        label="학년 선택하기"
        className="max-w-xs"
        defaultSelectedKeys={[0]}
      >
        {GradeSelectOption.map((grade) => (
          <SelectItem key={grade.value} value={grade.value}>
            {grade.name}
          </SelectItem>
        ))}
      </Select>
    </div>
  );
}

function TabItems() {
  return (
    <div className="flex w-full flex-col">
      <Tabs aria-label="Options">
        <Tab key="반 목록 보기" title="반 목록 보기">
          <SelectGrade />
          <Card>
            <CardBody>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
              eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
              enim ad minim veniam, quis nostrud exercitation ullamco laboris
              nisi ut aliquip ex ea commodo consequat.
            </CardBody>
          </Card>
        </Tab>
        <Tab key="반 변경 하기" title="반 변경 하기">
          <Card>
            <CardBody>
              Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
              nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
              reprehenderit in voluptate velit esse cillum dolore eu fugiat
              nulla pariatur.
            </CardBody>
          </Card>
        </Tab>
      </Tabs>
    </div>
  );
}

const AdminClass = () => {
  return (
    <section className="w-10/12 flex flex-col mx-auto py-10">
      <p className="my-5 text-2xl">반 배정</p>
      <TabItems />
    </section>
  );
};

export default AdminClass;
