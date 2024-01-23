import { BaseResponse } from "@/types/BaseResponse";

export interface Attendance {
  date?: string;
  status?: number;
  checkIn?: string;
  checkOut?: string;
}

export interface getTodayAttendanceResponse extends BaseResponse {
  data: {
    attendanceOnDate: Attendance;
  };
}

export interface CheckInResponse extends BaseResponse {
  data: {
    checkInTime: string;
    status: number;
  };
}
