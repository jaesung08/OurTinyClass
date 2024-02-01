import { commonAxios } from "@/lib/commonAxios";

export const freeBoard = async (boardType?: string, pages?: number) => {
  return await commonAxios.get("/community/articles", {
    params: {
      boardType: boardType ? boardType : "notice",
      page: pages,
    },
  });
};

export const searchBoard = async (
  boardType: string,
  searchType: string,
  searchValue: string,
  pages?: number
) => {
  return await commonAxios.get(`/community/articles`, {
    params: {
      boardType: boardType ? boardType : "notice",
      searchType: searchType,
      searchValue: searchValue,
      page: pages,
    },
  });
};
