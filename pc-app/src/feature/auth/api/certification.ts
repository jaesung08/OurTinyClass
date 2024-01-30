import { fileAxios } from "@/lib/fileAxios";
import { ActualFileObject } from "filepond";

export const studentCertification = (
  beforeSchool: string,
  beforeSchoolType: number,
  quitReason: string,
  quitSchoolDocument: ActualFileObject
) => {
  return fileAxios.post("/members/certification/student", {
    beforeSchool,
    beforeSchoolType,
    quitReason,
    quitSchoolDocument,
  });
};

export const mentorCertification = (
  belong: string,
  belongDocument: ActualFileObject
) => {
  return fileAxios.post("/members/certification/mentor", {
    belong,
    belongDocument,
  });
};
