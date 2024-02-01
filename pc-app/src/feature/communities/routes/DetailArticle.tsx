import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { getDetail } from "../api/detailBoard";
import { editComment, removeComment } from "../api/comments";
import { BoardDetail } from "../types/board";
import {
  BreadcrumbItem,
  Breadcrumbs,
  Button,
  Skeleton,
} from "@nextui-org/react";
import dayjs from "dayjs";
import Comments from "../components/Comments";
import CommentsList from "../components/CommentsList";

function DetailArticle() {
  const [isLoaded, setIsLoaded] = useState(false);
  const [commentList, setCommentList] = useState<object[]>([]);
  const [boardDetails, setBoardDetails] = useState<null | BoardDetail>(null);
  const { state } = useLocation();

  // 렌더링 시 데이터 불러오는 부분
  useEffect(() => {
    const renderDetail = async () => {
      try {
        setIsLoaded(false);
        const res = await getDetail(state);
        setBoardDetails(res.data);
        setCommentList(propertyAddHandler(res.data.articleComments));
      } catch (error) {
        console.error(error);
      } finally {
        setIsLoaded(true);
      }
    };
    renderDetail();
  }, [state]);

  // 수정 중인지 체크하는 변수 추가 함수
  const propertyAddHandler = (probsComments: object[]) => {
    return probsComments.map((item) => {
      return {
        ...item,
        isEdit: false,
      };
    });
  };

  // 댓글 삭제 함수
  const commentRemove = (commentId: number | undefined) => {
    try {
      removeComment(commentId);
      // 여기에 전체 댓글 갱신
    } catch (error) {
      console.error(error);
    }
  };

  // 댓글 수정 함수
  const commentEdit = (
    commentId: number | undefined,
    content: string | undefined
  ) => {
    try {
      editComment(commentId, content);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <article className="w-10/12 xl:w-8/12 ">
      <div className="w-full p-4 md:p-6 lg:p-8">
        <div className="flex flex-col gap-10 prose prose-gray dark:prose-invert">
          <div className="flex flex-col gap-3 space-y-2 not-prose">
            <Breadcrumbs size="lg">
              <BreadcrumbItem>학교게시판</BreadcrumbItem>
              <BreadcrumbItem>자유게시판</BreadcrumbItem>
              <BreadcrumbItem>게시글 조회</BreadcrumbItem>
            </Breadcrumbs>
            <Skeleton isLoaded={isLoaded} className="rounded-lg w-full">
              <h1 className="text-2xl font-extrabold tracking-tight lg:text-3xl xl:text-4xl lg:leading-[3.5rem] w-full">
                {boardDetails ? boardDetails.title : ""}
              </h1>
            </Skeleton>

            <div className="flex items-center text-gray-500 dark:text-gray-400">
              <Skeleton isLoaded={isLoaded} className="rounded-lg w-full ">
                <div className="w-full flex justify-between content-between">
                  <div className="flex items-center">
                    <span>{boardDetails ? boardDetails.name : ""}</span>
                    <span className="mx-2">•</span>
                    <span>
                      {boardDetails
                        ? boardDetails.modifiedAt
                          ? `${dayjs(boardDetails?.modifiedAt).format(
                              "YYYY년 MM월 DD일"
                            )} 수정`
                          : `${dayjs(boardDetails?.createdAt).format(
                              "YYYY년 MM월 DD일"
                            )} 작성`
                        : ""}
                    </span>
                  </div>
                  <Button
                    variant="ghost"
                    color="success"
                    className=" font-bold"
                  >
                    수정
                  </Button>
                </div>
              </Skeleton>
            </div>
          </div>
          <Skeleton isLoaded={isLoaded} className="rounded-lg">
            <p>{boardDetails ? boardDetails.content : ""}</p>
          </Skeleton>
          <Comments
            articleId={state}
            commentList={commentList}
            setList={setCommentList}
          />
          <CommentsList
            commentList={commentList}
            remove={commentRemove}
            edit={commentEdit}
            setList={setCommentList}
          />
        </div>
      </div>
    </article>
  );
}

export default DetailArticle;
