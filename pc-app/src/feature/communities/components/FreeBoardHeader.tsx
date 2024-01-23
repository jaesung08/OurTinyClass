import { Accordion, AccordionItem } from "@nextui-org/react";
import { useState, useMemo } from "react";
import { useNavigate } from "react-router-dom";

function FreeBoardHeader() {
  const navigator = useNavigate();
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
      title: "공지사항 🔊",
      content: "공지 알려주는곳",
      isActive: true,
    },
    {
      id: idCheck++,
      title: "자유 게시판 ✨",
      content: "자유로운 곳",
      isActive: false,
    },
    {
      id: idCheck++,
      title: "고민 나눔 🧶",
      content: "가슴속 품고 있는 고민들을 나누는 곳",
      isActive: false,
    },
    {
      id: idCheck++,
      title: "취미 공유 🎨",
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
            onClick={() => navigator("/communities")}
          >
            {article.title}
          </li>
        );
      }
    );
  }, [articleState]);

  return (
    <section className="w-2/12 h-full bg-lime-50">
      <div className="w-full bg-lime-500 flex justify-center items-center h-20">
        <p className="text-lg text-white font-bold">TeenEduConnect</p>
      </div>
      <div className="w-full" style={{ minHeight: "calc(100vh - 5rem)" }}>
        <p className="font-bold text-lg pl-5 py-4">나작사 커뮤니티</p>
        <Accordion selectionMode="multiple" style={{ padding: "0" }}>
          <AccordionItem
            key="1"
            className="font-light py-1 px-1"
            aria-label="커뮤니티"
            title="커뮤니티"
          >
            {communityList}
          </AccordionItem>
          <AccordionItem
            key="2"
            className="font-light px-2"
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

export default FreeBoardHeader;
