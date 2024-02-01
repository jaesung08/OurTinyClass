import { Button } from "@nextui-org/button";
import { useState, useRef } from "react";
import { newComment } from "../api/comments";
import { Input } from "@nextui-org/react";

interface Comment {
  articleId: number;
  commentList: object[];
  setList: React.Dispatch<React.SetStateAction<object[]>>;
}

function Comments({ articleId, commentList, setList }: Comment) {
  const [createComment, setCreateComment] = useState<string>("");
  const inputFocus = useRef<HTMLInputElement | null>(null);
  const commentHandler = async () => {
    try {
      if (createComment.trim()) {
        const commentId = await newComment(articleId, createComment);
        setCreateComment("");
        setList([
          ...commentList,
          { id: commentId.data, content: createComment },
        ]);
      } else {
        // todo : sweetalert 부분으로 변경해야함
        alert("댓글을 작성하세요");
        inputFocus.current?.focus();
      }
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <article className="w-10/12 h-20 flex justify-between items-center">
      <Input
        type="text"
        placeholder="댓글을 입력하세요."
        value={createComment}
        onChange={(e) => setCreateComment(e.target.value)}
        ref={inputFocus}
      />
      <Button
        color="default"
        className="w-1/12 text-white text-xl shadow-2xl rounded-xl"
        size="lg"
        onClick={commentHandler}
      >
        작성
      </Button>
    </article>
  );
}

export default Comments;
