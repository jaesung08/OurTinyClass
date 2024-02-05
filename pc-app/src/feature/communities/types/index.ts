import { BaseResponse } from "@/types/BaseResponse";

export interface Board {
  id: number;
  title: string;
  name: string;
  createdAt: string;
  hit: number;
}

export interface Comment {
  id: number;
  content: string;
  createdAt: string;
  modifiedAt: string;
  name: string;
}

export interface BoardDetail extends Board {
  content: string;
  modifiedAt: string;
  articleComments: Comment[];
}

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
