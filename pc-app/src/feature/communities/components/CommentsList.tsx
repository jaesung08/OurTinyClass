import { useState, useEffect } from "react";
import CommentRender from "./CommentRender";
import { Comment } from "..";

interface CommentsListProps {
  commentList: Comment[];
  remove: (id: number | undefined) => void;
  edit: (id: number | undefined, content: string | undefined) => void;
  setList: React.Dispatch<React.SetStateAction<object[]>>;
}

function CommentsList({
  commentList,
  remove,
  edit,
  setList,
}: CommentsListProps) {
  const [comments, setComments] = useState<Comment[]>(commentList);

  const removeHandler = (commentId: number | undefined): void => {
    remove(commentId);
    setList((preComments: Comment[]) => {
      return preComments.filter((comment) => comment.id !== commentId);
    });
  };
  const editHandler = (commentId: number | undefined) => {
    console.log(commentId);
    setList(
      commentList.map((comment) => {
        return comment.id === commentId
          ? { ...comment, isEdit: !comment.isEdit }
          : comment;
      })
    );
  };

  // 댓글 값 변경하는 함수

  useEffect(() => {
    if (commentList) {
      // todo : type 수정해야함
      const showList = commentList.map((comment: Comment, index: number) => {
        return (
          <CommentRender
            key={index}
            comment={comment}
            editHandler={editHandler}
            edit={edit}
          />
        );
      });
      setComments(showList);
    }
  }, [commentList]);

  return <>{comments.length !== 0 ? comments : "댓글이 존재하지 않습니다."}</>;
}

export default CommentsList;
