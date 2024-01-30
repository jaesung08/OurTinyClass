import { Button, Image } from "@nextui-org/react";
import { useState } from "react";
import MentorImg from "../assets/MentorImage.png";
import StudentImg from "../assets/StudentImage.png";
import MentorDocumentForm from "../components/MentorDocumentForm";
import { CODE } from "@/types/Code";
import StudentDocumentForm from "../components/StudentDocumentForm";
import {
  mentorCertification,
  studentCertification,
} from "../api/certification";
import { ActualFileObject } from "filepond";
import { useMutation } from "@tanstack/react-query";
import Swal from "sweetalert2";
import { AxiosResponse } from "axios";
import { useNavigate } from "react-router-dom";

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
            " bg-lime-500 text-white font-bold px-5 py-2 rounded-full"
          }
        >
          회원 유형 선택
        </li>
        <li
          className={
            getStepStyle(1) +
            " bg-lime-500 text-white font-bold px-5 py-2 rounded-full"
          }
        >
          회원 정보 등록
        </li>
        <li
          className={
            getStepStyle(2) +
            " bg-lime-500 text-white font-bold px-5 py-2 rounded-full"
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
        <ul className="flex gap-20 w-full">
          <li className="flex flex-col items-center justify-center w-full border-3 rounded-lg p-10 hover:border-lime-300 hover:bg-lime-50">
            <Image height={200} width={200} src={StudentImg} radius="full" />
            <h2 className=" my-5 text-3xl font-bold"> 학생</h2>
            <Button
              onClick={() => onSelectRole(CODE.ROLE.STUDENT_SELECT)}
              className="w-full bg-lime-500 text-2xl py-6 text-white"
            >
              가입하기
            </Button>
          </li>
          <li className="flex flex-col items-center justify-center w-full border-3 rounded-lg p-10 hover:border-lime-300 hover:bg-lime-50">
            <Image height={200} width={200} src={MentorImg} radius="full" />
            <h2 className=" my-5 text-3xl font-bold"> 멘토</h2>
            <Button
              onClick={() => onSelectRole(CODE.ROLE.MENTOR_SELECT)}
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

interface SubmitDocumentProps {
  role: number;
  onSubmitStudentDocument: (
    quitReason: string,
    beforeSchoolType: number,
    beforeSchool: string,
    quitSchoolDocument: ActualFileObject
  ) => void;
  onSubmitMentorDocument: (
    belong: string,
    belongDocument: ActualFileObject
  ) => void;
  goBefore: (current: number) => void;
  loading: boolean;
}
function SubmitDocument({
  role,
  onSubmitStudentDocument,
  onSubmitMentorDocument,
  goBefore,
  loading,
}: SubmitDocumentProps) {
  return (
    <div className="flex flex-col items-center justify-center">
      <h1 className="my-6 text-xl">서류를 제출해주세요</h1>
      {role === CODE.ROLE.MENTOR_SELECT ? (
        <MentorDocumentForm
          onSubmit={onSubmitMentorDocument}
          goBefore={goBefore}
          loading={loading}
        />
      ) : (
        <StudentDocumentForm
          onSubmit={onSubmitStudentDocument}
          goBefore={goBefore}
          loading={loading}
        />
      )}
    </div>
  );
}

function Certification() {
  const [certificationStep, setCertificationStep] = useState(0);
  const [role, setRole] = useState<null | number>(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const onSelectRole = (selectedRole: number) => {
    setRole(selectedRole);
    setCertificationStep((certificationStep) => certificationStep + 1);
  };

  const goBefore = (current: number) => {
    setCertificationStep(current - 1);
  };
  const studentCertificationMutation = useMutation({
    mutationFn: (studentCertificationData: {
      quitReason: string;
      beforeSchoolType: number;
      beforeSchool: string;
      quitSchoolDocument: ActualFileObject;
    }) => {
      return studentCertification(
        studentCertificationData.beforeSchool,
        studentCertificationData.beforeSchoolType,
        studentCertificationData.quitReason,
        studentCertificationData.quitSchoolDocument
      );
    },

    onSuccess: () => {
      // 회원 정보 등록 성공 처리
      Swal.fire("성공!", "회원 정보 등록에 성공하였습니다.", "success").then(
        () => {
          navigate("/auth/login");
        }
      );
      setLoading(false);
    },
    onError: (error: AxiosResponse) => {
      // 회원 정보 등록 실패 처리

      Swal.fire(
        "에러 발생",
        error.data?.message ?? "회원 정보 등록에 실패하였습니다.",
        "error"
      );
      setLoading(false);
    },
  });

  const mentorCertificationMutation = useMutation({
    mutationFn: (mentorCertificationData: {
      belong: string;
      belongDocument: ActualFileObject;
    }) => {
      return mentorCertification(
        mentorCertificationData.belong,
        mentorCertificationData.belongDocument
      );
    },

    onSuccess: () => {
      // 회원 정보 등록 성공 처리
      Swal.fire("성공!", "회원 정보 등록에 성공하였습니다.", "success").then(
        () => {
          navigate("/auth/login");
        }
      );
      setLoading(false);
    },
    onError: (error: AxiosResponse) => {
      // 회원 정보 등록 실패 처리

      Swal.fire(
        "에러 발생",
        error.data?.message ?? "회원 정보 등록에 실패하였습니다.",
        "error"
      );
      setLoading(false);
    },
  });

  const onSubmitStudentDocument = (
    quitReason: string,
    beforeSchoolType: number,
    beforeSchool: string,
    quitSchoolDocument: ActualFileObject
  ) => {
    setLoading(true);
    studentCertificationMutation.mutate({
      quitReason,
      beforeSchoolType,
      beforeSchool,
      quitSchoolDocument,
    });
  };

  const onSubmitMentorDocument = (
    belong: string,
    belongDocument: ActualFileObject
  ) => {
    setLoading(true);
    mentorCertificationMutation.mutate({
      belong,
      belongDocument,
    });
  };

  return (
    <div className=" w-full h-full p-10">
      <Steps certificationStep={certificationStep} />
      {certificationStep === 0 ? (
        <SelectRole onSelectRole={onSelectRole} />
      ) : null}
      {certificationStep === 1 && role !== null ? (
        <SubmitDocument
          onSubmitStudentDocument={onSubmitStudentDocument}
          onSubmitMentorDocument={onSubmitMentorDocument}
          goBefore={goBefore}
          role={role}
          loading={loading}
        />
      ) : null}
    </div>
  );
}

export default Certification;
