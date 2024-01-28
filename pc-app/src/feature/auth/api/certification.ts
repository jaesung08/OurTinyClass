import { axios } from "@/lib/axios";
import { ActualFileObject } from "filepond";

export const studentCertification = (
  beforeSchool: string,
  beforeSchoolType: number,
  quitReason: string,
  quitSchoolDocument: ActualFileObject
) => {
  return axios.post("/members/certification/student", {
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
  return axios.post("/members/certification/mentor", {
    belong,
    belongDocument,
  });
};
