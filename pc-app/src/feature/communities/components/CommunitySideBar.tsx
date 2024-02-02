import { Link, useLocation } from "react-router-dom";
import { CommunityHeaderContents } from "../assets/textContent";
import { useEffect, useState } from "react";
import { TYPE } from "../types";
import { getPathInfo } from "../utils/getPathInfo";

function FreeBoardHeader() {
  const location = useLocation();
  const [boardCategory, setBoardCategory] = useState(
    TYPE.BOARD_CATEGORY.NOTICE
  );
  const [boardType, setBoardType] = useState(TYPE.BOARD_TYPE.SCHOOL);

  useEffect(() => {
    const { boardType, boardCategory } = getPathInfo(location.pathname);
    setBoardCategory(boardCategory);
    setBoardType(boardType);
  }, [location]);

  const selectionStyle = (
    _boardType: number,
    _boardCategory: number
  ): string => {
    if (boardType === _boardType && boardCategory === _boardCategory) {
      return "bg-lime-400";
    } else {
      return "hover:bg-lime-300";
    }
  };

  return (
    <section className=" min-w-56 w-2/12 h-full bg-lime-50 py-3">
      <Link to={"/communities"}>
        <p className="text-xl px-3 py-4 font-bold "> 학교 게시판</p>
      </Link>
      <ul className="text-lg py-3 flex flex-col gap-1 bg-lime-200 ">
        {CommunityHeaderContents.map((community) => (
          <Link
            to={"/communities/school" + community.location}
            key={community.location}
          >
            <li
              className={` pl-10 py-3 rounded-2xl mx-2 duration-200 ${selectionStyle(
                TYPE.BOARD_TYPE.SCHOOL,
                community.id
              )}`}
            >
              {community.title}
            </li>
          </Link>
        ))}
      </ul>
    </section>
  );
}

export default FreeBoardHeader;
