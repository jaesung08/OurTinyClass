import {
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Avatar,
  Button,
} from "@nextui-org/react";
import { LectureCardProps } from "../types";
import { useNavigate } from "react-router-dom";

export function LectureCard({
  lecture,
  deleteHandler,
}: LectureCardProps): JSX.Element {
  const navigator = useNavigate();
  const currentRole =
    lecture.member.role === "ROLE_ADMIN"
      ? "관리자"
      : lecture.member.role === "ROLE_MENTOR"
      ? "멘토"
      : "학생";
  return (
    <Card className="w-96 h-[15rem]">
      <div className="flex h-5/6 items-center">
        <CardHeader className="flex flex-col gap-5 w-2/6">
          <Avatar
            isBordered
            radius="full"
            size="lg"
            src="https://cdn.imweb.me/upload/S20220826948cbdc34dca3/a7e21622bc1f0.png"
          />
          <div className="flex flex-col gap-1 items-center justify-center">
            <h4 className="text-small font-semibold leading-none text-default-600">
              {lecture.member.name}
            </h4>
            <h5 className="text-small tracking-tight text-default-400">
              {currentRole}
            </h5>
          </div>
        </CardHeader>
        <CardBody className="flex flex-col h-full px-3 py-0 text-small text-default-400 justify-between w-4/6">
          <p className="pt-2 h-1/6 truncate">제목 : {lecture.title}</p>
          <p className="h-4/6">설명 : {lecture.description}</p>
          <span className="pb-2 h-1/6">수강일 : {lecture.date}</span>
        </CardBody>
      </div>
      <CardFooter className="justify-around gap-3 h-1/6">
        {currentRole === "관리자" || currentRole === "멘토" ? (
          <>
            <Button
              className="w-3/6 text-white"
              color="success"
              onClick={() => navigator(`./edit/${lecture.id}`)}
            >
              수정
            </Button>
            <Button
              className="w-3/6"
              color="danger"
              onClick={() => deleteHandler(lecture.id)}
            >
              삭제
            </Button>
          </>
        ) : (
          <>
            <Button className="w-3/6 bg-white">❤️</Button>
            <Button className="w-3/6 bg-white">✔️</Button>
          </>
        )}
      </CardFooter>
    </Card>
  );
}
