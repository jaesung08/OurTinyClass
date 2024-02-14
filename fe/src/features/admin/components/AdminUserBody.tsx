import {
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  Button,
  Modal,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  useDisclosure,
  Input,
} from "@nextui-org/react";
import dayjs from "dayjs";
import { useEffect, useState } from "react";
import { User } from "../types";
import {
  fetchUserList,
  requestCreateClassroom,
  requestEditUserClass,
  requestEditUserInfo,
} from "../api/users";

type UserEditModal = {
  isOpen: boolean;
  onOpenChange: () => void;
  user: User | undefined;
  onClose: () => void;
};
const UserEditModal = ({
  isOpen,
  onOpenChange,
  user,
  onClose,
}: UserEditModal) => {
  const [inputName, setInputName] = useState("");
  const [inputEmail, setInputEmail] = useState("");
  const [inputRole, setInputRole] = useState("");
  const [inputGrade, setInputGrade] = useState("");
  const [inputClassId, setInputClassId] = useState("");

  useEffect(() => {
    if (user && isOpen) {
      setInputName(user.name);
      setInputEmail(user.email);
      setInputRole(user.role);
      setInputGrade(
        user.classRooms?.number ? "" + user.classRooms?.number : ""
      );
      setInputClassId(user.classRooms?.id ? "" + user.classRooms?.id : "");
    }
  }, [user, isOpen]);

  const editUserInfo = async () => {
    if (user) {
      if (user.classRooms?.id !== +inputClassId) {
        await requestEditUserClass(user.memberId, +inputClassId);
      }
      await requestEditUserInfo(
        user.memberId,
        inputName,
        inputEmail,
        inputRole
      );
      onClose();
    }
  };

  return (
    <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              유저 정보 수정
            </ModalHeader>
            <ModalBody>
              <Input
                label={"이름"}
                value={inputName}
                onChange={(e) => setInputName(e.target.value)}></Input>
              <Input
                label={"이메일"}
                value={inputEmail}
                onChange={(e) => setInputEmail(e.target.value)}></Input>
              <Input readOnly label={"학년"} value={inputGrade}></Input>
              <Input
                label={"반 id"}
                value={inputClassId}
                onChange={(e) => setInputClassId(e.target.value)}></Input>
              <Input
                label={"권한"}
                value={inputRole}
                onChange={(e) => setInputRole(e.target.value)}></Input>
            </ModalBody>
            <ModalFooter>
              <Button color="danger" variant="light" onPress={onClose}>
                닫기
              </Button>
              <Button color="primary" onPress={editUserInfo}>
                수정
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  );
};

const AddClassroomModal = ({
  isOpen,
  onOpenChange,
  onClose,
}: UserEditModal) => {
  const [inputYear, setInputYear] = useState<string>("");
  const [inputGrade, setInputGrade] = useState<string>("");
  const [inputClass, setInputClass] = useState<string>("");

  useEffect(() => {
    if (isOpen) {
      setInputYear("");
      setInputGrade("");
      setInputClass("");
    }
  }, [isOpen]);

  const editUserInfo = async () => {
    try {
      await requestCreateClassroom(+inputYear, +inputGrade, +inputClass);
      onClose();
    } catch (e) {
      console.error(e);
    }
  };

  return (
    <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
      <ModalContent>
        {(onClose) => (
          <>
            <ModalHeader className="flex flex-col gap-1">
              유저 정보 수정
            </ModalHeader>
            <ModalBody>
              <Input
                label={"년도"}
                value={inputYear}
                onChange={(e) => setInputYear(e.target.value)}></Input>
              <Input
                label={"학년"}
                value={inputGrade}
                onChange={(e) => setInputGrade(e.target.value)}></Input>
              <Input
                label={"반"}
                value={inputClass}
                onChange={(e) => setInputClass(e.target.value)}></Input>
            </ModalBody>
            <ModalFooter>
              <Button color="danger" variant="light" onPress={onClose}>
                닫기
              </Button>
              <Button color="primary" onPress={editUserInfo}>
                생성
              </Button>
            </ModalFooter>
          </>
        )}
      </ModalContent>
    </Modal>
  );
};

const AdminUserBody = () => {
  const [userList, setUserList] = useState<User[]>([]);
  const [selectedKeys, setSelectedKeys] = useState<Set<string>>(new Set([]));
  const [selectedUser, setSelectedUser] = useState<User | undefined>(undefined);
  const { isOpen, onOpen, onOpenChange, onClose } = useDisclosure();
  const [modal, setModal] = useState("");

  const getUserList = async () => {
    try {
      const res = await fetchUserList();
      setUserList(res.data);
    } catch (e) {
      console.error(e);
    }
  };

  const onSelectionChange = (keys: "all" | Set<string | number>) => {
    if (keys !== "all" && isStringSet(keys)) {
      setSelectedKeys(keys);
    }
  };

  function isStringSet(set: Set<string | number>): set is Set<string> {
    for (const item of set) {
      if (typeof item !== "string") {
        return false; // 요소 중에 문자열이 아닌 것이 있으면 false 반환
      }
    }
    return true; // 모든 요소가 문자열이면 true 반환
  }

  const onOpenUserEditModal = (user: User) => {
    setModal("userEdit");
    setSelectedUser(user);
    onOpen();
  };

  const onOpenAddClassroomModal = () => {
    setModal("addClass");
    onOpen();
  };

  useEffect(() => {
    console.log(selectedKeys);
  }, [selectedKeys]);

  useEffect(() => {
    getUserList();
  }, []);

  return (
    <section className="w-10/12 flex flex-col mx-auto py-10">
      <div className="flex justify-between">
        <p className="my-5 text-2xl">유저 관리</p>
        <Button onPress={onOpenAddClassroomModal} color="primary">
          방 추가
        </Button>
      </div>
      <div className="flex gap-10">
        <Table
          selectionMode="multiple"
          aria-label="유저 관리 게시판"
          className="mt-10 max-h-[60vh]"
          isHeaderSticky
          selectedKeys={selectedKeys}
          onSelectionChange={onSelectionChange}>
          <TableHeader>
            <TableColumn key="memberId">ID</TableColumn>
            <TableColumn key="name">이름</TableColumn>
            <TableColumn key="grade">학년</TableColumn>
            <TableColumn key="class">반</TableColumn>
            <TableColumn key="email">이메일</TableColumn>
            <TableColumn key="birthday">생일</TableColumn>
            <TableColumn key="role">권한</TableColumn>
            <TableColumn key="edit">수정</TableColumn>
          </TableHeader>
          <TableBody items={userList}>
            {(user) => (
              <TableRow key={user.memberId}>
                <TableCell>{user.memberId}</TableCell>
                <TableCell>{user.name}</TableCell>
                <TableCell>{user.classRooms?.grade ?? ""}</TableCell>
                <TableCell>{user.classRooms?.number ?? ""}</TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{dayjs(user.birthday).format("YY-MM-DD")}</TableCell>
                <TableCell>{user.role}</TableCell>
                <TableCell>
                  <Button
                    onPress={() => onOpenUserEditModal(user)}
                    color="primary">
                    수정
                  </Button>
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </div>
      <UserEditModal
        isOpen={isOpen && modal == "userEdit"}
        onOpenChange={onOpenChange}
        user={selectedUser}
        onClose={onClose}
      />

      <AddClassroomModal
        isOpen={isOpen && modal == "addClass"}
        onOpenChange={onOpenChange}
        user={selectedUser}
        onClose={onClose}
      />
    </section>
  );
};
export default AdminUserBody;
