import { Button, Avatar } from "@nextui-org/react";
import { useEffect, useMemo, useState } from "react";
import ReactQuill from "react-quill";
import Comments from "./Comments";
import CommentsList from "./CommentsList";
import { useLocation } from "react-router-dom";
import { editDetail, getDetail } from "../api/detailBoard";
import { editComment, removeComment } from "../api/comments";

function DetailArticleBody() {
  const [isArticleEdit, setIsArticleEdit] = useState<boolean>(false);
  const [articleContent, setArticleContent] = useState<string>("");
  const [articleTitle, setArticleTitle] = useState<string>("");
  const [commentList, setCommentList] = useState<object[]>([]);
  const { state } = useLocation();

  // 렌더링 시 데이터 불러오는 부분
  useEffect(() => {
    const renderDetail = async () => {
      try {
        const article = await getDetail(state);
        setArticleContent(article.data.content);
        setArticleTitle(article.data.title);
        setCommentList(propertyAddHandler(article.data.articleComments));
      } catch (error) {
        console.error(error);
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

  // 게시글 수정 페이지 옵션 버튼들
  const modules = useMemo(
    () => ({
      toolbar: [
        ["link", "image", "video"],
        [{ header: [1, 2, 3, false] }],
        ["bold", "italic", "underline", "strike"],
        ["blockquote"],
        [{ list: "ordered" }, { list: "bullet" }],
        [{ color: [] }, { background: [] }],
        [{ align: [] }],
      ],
      clipboard: {
        // toggle to add extra line breaks when pasting HTML:
        matchVisual: false,
      },
    }),
    []
  );

  // 게시글 수정 버튼 활성화
  const editArticle = () => {
    setIsArticleEdit(!isArticleEdit);
  };

  // 게시글 수정 후 값 갱신
  const checkValue = (e: string) => {
    const check = e.split("<p>");
    const textValue = check[1].split("</p>");
    setArticleContent(textValue[0]);
  };

  // 게시글 수정 완료 버튼 동작
  const completeArticleEdit = async () => {
    try {
      await editDetail(articleTitle, articleContent, state);
      setIsArticleEdit(!isArticleEdit);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <article>
      <div
        className="w-full  pb-20"
        style={{ minHeight: "calc(100vh - 5rem)" }}
      >
        <div
          className="w-full flex items-center mx-20"
          style={{ height: "10rem" }}
        >
          <span>
            <a href="">커뮤니티</a> {">"} <a href="">고민 나눔</a> 🍳
          </span>
        </div>
        <div className="w-11/12 flex items-center justify-around">
          <div className="w-8/12">
            <p className="text-3xl font-bold truncate">{articleTitle}</p>
            <div className="flex w-full items-center my-5">
              <Avatar
                src="https://i.pravatar.cc/150?u=a042581f4e29026024d"
                className="mx-2"
              />
              <p className="mx-2">김가루</p>
              <p className="mx-2">2024-12-10</p>
            </div>
          </div>
          <div className="w-2/12">
            {isArticleEdit ? (
              <Button
                className="w-1/12 text-white text-xl rounded-xl"
                style={{
                  backgroundColor: "#52c41a",
                  boxShadow: "0px 0px 10px rgba(0,0,0,0.1)",
                }}
                size="lg"
                onClick={completeArticleEdit}
              >
                완료
              </Button>
            ) : (
              <Button
                className="w-1/12 text-white text-xl rounded-xl"
                style={{
                  backgroundColor: "#52c41a",
                  boxShadow: "0px 0px 10px rgba(0,0,0,0.1)",
                }}
                size="lg"
                onClick={editArticle}
              >
                수정
              </Button>
            )}
          </div>
        </div>
        {isArticleEdit ? (
          <div className="w-10/12 flex items-center ml-10 mb-10  h-96">
            <ReactQuill
              modules={modules}
              style={{ width: "100%", height: "70%" }}
              value={articleContent}
              onChange={(e) => checkValue(e)}
            />
          </div>
        ) : (
          <div className="w-10/12">
            <p className="w-full py-20 pl-10">{articleContent}</p>
          </div>
        )}

        <div className="w-11/12 ml-10">
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

export default DetailArticleBody;
