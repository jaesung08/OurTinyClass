import { TYPE } from "../types";
interface CommunityPathInfo {
  boardType: number;
  boardCategory: number;
}
export const getPathInfo = (pathname: string): CommunityPathInfo => {
  const pathParts = pathname.split("/");
  const pathInfo = {
    boardType: -1,
    boardCategory: -1,
  };

  if (pathParts[2]) {
    switch (pathParts[2]) {
      case "school":
        pathInfo.boardType = TYPE.BOARD_TYPE.SCHOOL;
        break;
      case "class":
        pathInfo.boardType = TYPE.BOARD_TYPE.CLASS;
    }
  }

  if (pathParts[3]) {
    switch (pathParts[3]) {
      case "notice":
        pathInfo.boardCategory = TYPE.BOARD_CATEGORY.NOTICE;
        break;
      case "free":
        pathInfo.boardCategory = TYPE.BOARD_CATEGORY.FREE;
        break;
      case "counseling":
        pathInfo.boardCategory = TYPE.BOARD_CATEGORY.COUNSELING;
        break;
      case "hobby":
        pathInfo.boardCategory = TYPE.BOARD_CATEGORY.HOBBY;
        break;
    }
  }
  return pathInfo;
};
