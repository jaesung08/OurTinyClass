import { commonAxios } from "@/lib/commonAxios";
import { FetchArticleListResponse } from "../types";

export const fetchArticles = async (
  boardType?: string,
  pages?: number
): Promise<FetchArticleListResponse> => {
  return await commonAxios.get("/community/articles", {
    params: {
      boardType: boardType ? boardType : "notice",
      page: pages,
    },
  });
};

interface SearchBoardProps {
  boardType: string;
  searchType?: string;
  searchValue?: string;
  page?: number;
  isClass?: boolean;
}
export const searchBoard = async ({
  boardType,
  searchType,
  searchValue,
  page,
  isClass,
}: SearchBoardProps): Promise<FetchArticleListResponse> => {
  return await commonAxios.get(`/community/articles`, {
    params: {
      boardType,
      page,
      searchValue,
      searchType,
      isClass,
      boardSize: 15,
    },
  });
};
