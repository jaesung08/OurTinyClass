import { Accordion, AccordionItem } from "@nextui-org/react";
import { useState, useMemo } from "react";
import { useNavigate } from "react-router-dom";

interface Articles {
  id: number;
  title: string;
  content: string;
  isActive: boolean;
}

function FreeBoardHeader() {
  const navigator = useNavigate();
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

  // 게시판 리스트 가져오는 부분
  const communityList: JSX.Element[] = useMemo(() => {
    return articleState.map(
      (article: {
        title: string;
        id: number;
        content: string;
        isActive: boolean;
      }): JSX.Element => {
        return (
          <li
            className={
              article.isActive
                ? "pl-2 py-5 cursor-pointer list-none bg-lime-500"
                : "pl-2 py-5 cursor-pointer list-none"
            }
            key={article.id}
            value={article.id}
            // todo : 게시판 별 링크 다르게 설정
            onClick={(e) => {
              const updatedArticleState = articleState.map((article, index) => {
                if (e.currentTarget.value === index) {
                  return { ...article, isActive: true };
                } else {
                  return { ...article, isActive: false };
                }
              });
              setArticleState(updatedArticleState);
              navigator("/communities", {
                state: {
                  title: `${article.title}`,
                  content: `${article.content}`,
                },
              });
            }}
          >
            {article.title}
          </li>
        );
      }
    );
  }, [articleState]);

  return (
    <section className="w-2/12 h-full bg-lime-50">
      <div className="w-full min-h-[calc(100vh - 5rem)]">
        {/* todo : 패딩 크기 조절해야함 */}
        <Accordion selectionMode="multiple" className="p-0">
          <AccordionItem
            key="1"
            className="font-light py-1 px-1"
            aria-label="학교 게시판"
            title="학교 게시판"
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
