import {
  Button,
  Input,
  Modal,
  ModalBody,
  ModalContent,
  ModalFooter,
  ModalHeader,
  Switch,
  Table,
  TableBody,
  TableCell,
  TableColumn,
  TableHeader,
  TableRow,
  useDisclosure,
} from "@nextui-org/react";
import { useEffect, useState } from "react";
import {
  requestGetMentorCertList,
  requestGetStudentCertDetail,
  requestGetStudentCertList,
} from "../api/users";
import { StudentCertDetail } from "../types";

type StudentDetailProps = {
  isOpen: boolean;
  onOpenChange: () => void;
  articleId: number | undefined;
  onClose: () => void;
};
const StudentDetailModal = ({
  isOpen,
  onOpenChange,
  onClose,
  articleId,
}: StudentDetailProps) => {
  const [student, setStudent] = useState<StudentCertDetail>();
  useEffect(() => {
    if (isOpen && articleId) {
      const getDetail = async () => {
        const res = await requestGetStudentCertDetail(articleId);
        setStudent(res.data);
      };
      getDetail();
    }
  }, [isOpen, articleId]);

  return (
    <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              학생 신청 상세 조회
            </ModalHeader>
            <ModalBody>
              <Input> </Input>
            </ModalBody>
            <ModalFooter>
              <Button color="danger" variant="light" onPress={onClose}>
                닫기
              </Button>
              <Button color="danger" variant="light" onPress={onClose}>
                거절
              </Button>
              <Button color="primary">승인</Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  );
};

const AdminAccept = () => {
  const [isStudent, setIsStudent] = useState(true);
  const [certDataList, setCertDataList] = useState(
    Array<{ articleId: number; memberName: string; birthday: string }>
  );
  const [articleId, setArticleId] = useState(-1);
  const { isOpen, onOpen, onOpenChange, onClose } = useDisclosure();
  const getStudentCertList = async () => {
    const res = await requestGetStudentCertList();
    setCertDataList(res.data);
  };

  const getMentorCertList = async () => {
    const res = await requestGetMentorCertList();
    setCertDataList(res.data);
  };

  const onOpenModal = (id: number) => {
    setArticleId(id);
    onOpen();
  };

  useEffect(() => {
    if (isStudent) {
      getStudentCertList();
    } else {
      getMentorCertList();
    }
  }, [isStudent]);

  return (
    <section className="w-10/12 flex flex-col mx-auto py-10">
      <p className="my-5 text-2xl">등업 요청 목록</p>

      <Switch isSelected={isStudent} onValueChange={setIsStudent}>
        {isStudent ? "학생" : "멘토"}
      </Switch>

      <Table
        selectionMode="single"
        aria-label="등업 요청 관리 게시판"
        className="mt-10 max-h-[60vh]"
        isHeaderSticky>
        <TableHeader>
          {/* <TableColumn key="userId">계정</TableColumn> */}
          <TableColumn key="articleId">계정</TableColumn>
          <TableColumn key="memberName">이름</TableColumn>
          <TableColumn key="birthday">학년</TableColumn>
        </TableHeader>
        <TableBody items={certDataList}>
          {(article) => (
            <TableRow
              onClick={() => onOpenModal(article.articleId)}
              key={article.articleId}>
              <TableCell>{article.articleId}</TableCell>
              <TableCell>{article.memberName}</TableCell>
              <TableCell>{article.birthday}</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
      <StudentDetailModal
        isOpen={isStudent && isOpen}
        onOpenChange={onOpenChange}
        onClose={onClose}
        articleId={articleId}
      />
    </section>
  );
};

export default AdminAccept;
