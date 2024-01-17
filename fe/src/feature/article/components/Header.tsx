import { Accordion, AccordionItem } from "@nextui-org/react";
import { useState, useMemo } from "react";

function Header() {
  interface Articles {
    id: number;
    title: string;
    content: string;
    isActive: boolean;
  }
  let idCheck: number = 0;
  const [articleState, setArticleState] = useState<Articles[]>([
    {
      id: idCheck++,
      title: "공지사항",
      content: "공지 알려주는곳",
      isActive: true,
    },
    {
      id: idCheck++,
      title: "자유 게시판",
      content: "자유로운 곳",
      isActive: false,
    },
    {
      id: idCheck++,
      title: "고민 나눔",
      content: "가슴속 품고 있는 고민들을 나누는 곳",
      isActive: false,
    },
    {
      id: idCheck++,
      title: "취미 공유",
      content: "그님티?",
      isActive: false,
    },
  ]);

  const communityList: JSX.Element[] = useMemo(() => {
    return articleState.map(
      (
        article: { title: string; id: number; isActive: boolean },
        index: number
      ): JSX.Element => {
        return (
          <li
            className={
              article.isActive
                ? "pl-2 py-5 cursor-pointer list-none bg-lime-500"
                : "pl-2 py-5 cursor-pointer list-none"
            }
            key={article.id}
          >
            {article.title}
          </li>
        );
      }
    );
  }, [articleState]);

  return (
    <section className="w-2/12 bg-red-500 h-full">
      <div
        className="w-full bg-lime-500 flex justify-center items-center"
        style={{ height: "10%" }}
      >
        <p className="text-2xl text-white font-bold">TeenEduConnect</p>
      </div>
      <div className="w-full bg-lime-100" style={{ height: "90%" }}>
        <p className="font-bold text-2xl pl-5 py-4">Your Profile</p>
        <Accordion selectionMode="multiple">
          <AccordionItem
            key="1"
            className="font-light pl-2"
            aria-label="커뮤니티"
            title="커뮤니티"
          >
            {communityList}
          </AccordionItem>
          <AccordionItem
            key="2"
            className="font-light pl-2"
            aria-label="4반 게시판"
            title="4반 게시판"
          >
            <p className="pl-2 mt-3 mb-7 cursor-pointer">공지사항</p>
            <p className="pl-2 mt-3 mb-7 cursor-pointer">자유 게시판</p>
          </AccordionItem>
        </Accordion>
      </div>
    </section>
  );
}

export default Header;
