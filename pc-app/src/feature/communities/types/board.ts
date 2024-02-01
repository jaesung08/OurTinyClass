import { BaseResponse } from "@/types/BaseResponse";

export interface Board {
  id: number;
  title: string;
  name: string;
  createdAt: string;
  hit: number;
}

export interface BoardDetail extends Board {
  content: string;
  modifiedAt: string;
  articleComments: [];
}

export const TYPE = {
  BOARD_TYPE: {
    SCHOOL: 0, // 학교 게시판
    CLASS: 1, // 학급 게시판
  },
  BOARD_CATEGORY: {
    NOTICE: 0, // 공지사항 게시판
    FREE: 1, // 자유게시판
    CONCERN: 2, // 고민나눔 게시판
    HOBBY: 3, // 취미 공유 게시판
  },
};

export interface FetchArticleListResponse extends BaseResponse {
  data: {
    content: Array<Board>;
    pageNumber: number;
    totalPages: number;
    number: number;
  };
}

export interface FetchArticleDetailResponse extends BaseResponse {
  data: BoardDetail;
}
