import { Button, Image } from "@nextui-org/react";
import { useState } from "react";
import MentorImg from "../assets/MentorImage.png";
import StudentImg from "../assets/StudentImage.png";

interface StepsProps {
  certificationStep: number;
}
function Steps({ certificationStep }: StepsProps) {
  const getStepStyle = (step: number): string => {
    if (certificationStep == step) {
      return "bg-lime-500 text-white";
    } else {
      return " bg-slate-500 text-black";
    }
  };
  return (
    <div>
      <ul className="flex justify-center items-center gap-7">
        <li
          className={
            getStepStyle(0) +
            " bg-lime-500 text-white font-bold px-10 py-5 rounded-full"
          }
        >
          회원 유형 선택
        </li>
        <li
          className={
            getStepStyle(1) +
            " bg-lime-500 text-white font-bold px-10 py-5 rounded-full"
          }
        >
          회원 정보 등록
        </li>
        <li
          className={
            getStepStyle(2) +
            " bg-lime-500 text-white font-bold px-10 py-5 rounded-full"
          }
        >
          회원 승인 대기
        </li>
      </ul>
    </div>
  );
}
interface SelectRoleProps {
  onSelectRole: (role: number) => void;
}
function SelectRole({ onSelectRole }: SelectRoleProps) {
  return (
    <div className=" max-w-7xl mx-auto">
      <h1 className=" text-center my-14 text-2xl">
        활동할 역할을 선택해주세요
      </h1>
      <div className="flex items-center justify-center">
        <ul className="flex gap-1 w-full">
          <li className="flex flex-col items-center justify-center w-full border-3 rounded-lg p-10 hover:border-lime-300 hover:bg-lime-50">
            <Image src={StudentImg} radius="full" />
            <h2 className=" my-5 text-3xl font-bold"> 학생</h2>
            <Button
              onClick={() => onSelectRole(0)}
              className="w-full bg-lime-500 text-2xl py-6 text-white"
            >
              가입하기
            </Button>
          </li>
          <li className="flex flex-col items-center justify-center w-full border-3 rounded-lg p-10 hover:border-lime-300 hover:bg-lime-50">
            <Image src={MentorImg} radius="full" />
            <h2 className=" my-5 text-3xl font-bold"> 멘토</h2>
            <Button
              onClick={() => onSelectRole(1)}
              className="w-full bg-lime-500 text-2xl py-6 text-white"
            >
              가입하기
            </Button>
          </li>
        </ul>
      </div>
    </div>
  );
}

function Certification() {
  const [certificationStep, setSertification] = useState(0);
  const [role, setRole] = useState<null | number>(null);

  const onSelectRole = (selectedRole: number) => {
    setRole(selectedRole);
    setSertification((certificationStep) => certificationStep + 1);
  };

  return (
    <div className=" w-full h-full p-10">
      <Steps certificationStep={certificationStep} />
      {certificationStep === 0 ? (
        <SelectRole onSelectRole={onSelectRole} />
      ) : null}
    </div>
  );
}

export default Certification;
