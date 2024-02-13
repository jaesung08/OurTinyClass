import {
  Tabs,
  Tab,
  Card,
  CardBody,
  Select,
  SelectItem,
} from "@nextui-org/react";
import { ClassSelectOption, GradeSelectOption } from "../assets/textContent";
import { useState } from "react";

interface SelectGradeProps {
  gradeHandler: (name: string) => void;
  currentGrade: string;
}

interface SelectClassProps {
  classHandler: () => void;
  currentClass: string;
}

function SelectGrade({ gradeHandler, currentGrade }: SelectGradeProps) {
  return (
    <div className="flex w-full flex-wrap md:flex-nowrap gap-4">
      <Select
        label="학년 선택하기"
        aria-label="학년 선택하기"
        className="max-w-xs"
        value={currentGrade}
      >
        {GradeSelectOption.map((grade) => (
          <SelectItem
            key={grade.value}
            value={grade.value}
            onClick={() => gradeHandler(grade.name)}
          >
            {grade.name}
          </SelectItem>
        ))}
      </Select>
    </div>
  );
}

function SelectClass({ classHandler, currentClass }: SelectClassProps) {
  return (
    <div className="flex w-full flex-wrap md:flex-nowrap gap-4">
      <Select
        label="반 선택하기"
        aria-label="반 선택하기"
        className="max-w-xs"
        value={currentClass}
      >
        {ClassSelectOption.map((Class) => (
          <SelectItem
            key={Class.value}
            value={Class.value}
            onClick={() => classHandler}
          >
            {Class.name}
          </SelectItem>
        ))}
      </Select>
    </div>
  );
}

function TabItems() {
  const [currentGrade, setCurrentGrade] = useState<string>("");
  const [currentClass, setCurrentClass] = useState<string>("");
  const [isGrade, setIsGrade] = useState<boolean>(false);
  const [isClass, setIsClass] = useState<boolean>(false);

  const gradeHandler = (name: string) => {
    if (isClass) {
      setIsGrade(false);
    } else {
      setIsGrade(true);
      setCurrentGrade(name);
    }
  };

  const classHandler = () => {
    setIsClass(true);
    setCurrentClass("");
  };

  return (
    <div className="flex w-full flex-col">
      <Tabs aria-label="Options">
        <Tab key="반 목록 보기" title="반 목록 보기">
          <div className="flex mb-5">
            <SelectGrade
              gradeHandler={gradeHandler}
              currentGrade={currentGrade}
            />
            <SelectClass
              classHandler={classHandler}
              currentClass={currentClass}
            />
          </div>
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
          <div className="flex mb-5">
            <SelectGrade
              gradeHandler={gradeHandler}
              currentGrade={currentGrade}
            />
            {isGrade ? (
              <SelectClass
                classHandler={classHandler}
                currentClass={currentClass}
              />
            ) : null}
          </div>
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
