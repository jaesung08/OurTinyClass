import { Button, Input } from "@nextui-org/react";
import dayjs from "dayjs";
import { useState, useEffect } from "react";

interface CommentsListProps {
  commentList: object[];
  remove: (id: number | undefined) => void;
  edit: (id: number | undefined, content: string | undefined) => void;
  setList: React.Dispatch<React.SetStateAction<object[]>>;
}

interface Comment {
  id?: number;
  name?: string;
  content?: string;
  isEdit?: boolean;
  modifiedAt?: string;
}

function CommentsList({
  commentList,
  remove,
  edit,
  setList,
}: CommentsListProps) {
  const [comments, setComments] = useState<object[]>([]);
	const [editingComment, setEditingComment] = useState<string>("")
  const removeHandler = (commentId: number | undefined): void => {
    remove(commentId);
    setList((preComments: Comment[]) => {
      return preComments.filter((comment) => comment.id !== commentId);
    });
  };

  const editHandler = (commentId: number | undefined, commentContent: string) => {
    setEditingComment(commentContent)
    setList((preComments: object[]) =>
      preComments.map((comment: Comment) => {
        return commentId === comment.id
          ? { ...comment, isEdit: !comment.isEdit }
          : comment;
      })
    );
  };

  const completeHandler = (
    commentId: number | undefined,
    content: string | undefined
  ): void => {
    edit(commentId, content);
    setList((preComments: object[]) =>
    preComments.map((comment: Comment) => {
      return commentId === comment.id
        ? { ...comment, isEdit: !comment.isEdit }
        : comment;
    })
  );
  };

  // 댓글 값 변경하는 함수


  useEffect(() => {
    if (commentList) {
      // todo : type 수정해야함
      const showList = commentList.map((comment: Comment) => {
        return (
          <div key={comment.id} className="grid gap-6 w-full">
            <div className="text-sm flex items-start gap-4 w-full my-5">
              <div className="grid gap-1.5 w-full">
                <div className="flex items-center gap-2">
                  <div className="font-semibold">{comment.name}</div>
                  <div className="text-gray-500 text-xs dark:text-gray-400">
                    {dayjs(comment.modifiedAt).format("YYYY-MM-DD HH:mm")}
                  </div>
                  {comment.isEdit ? (
                    <Button
                      className="ml-auto"
                      size="lg"
                      variant="ghost"
                      isIconOnly
                      onClick={() =>
                        completeHandler(comment.id, editingComment)
                      }
                    >
                      <EditIcon className="h-6 w-6" />
                      <span className="sr-only">Complete Comment</span>
                    </Button>
                  ) : (
                    <Button
                      className="ml-auto"
                      size="lg"
                      variant="ghost"
                      onClick={() => editHandler(comment.id, comment.content)}
                      isIconOnly
                    >
                      <PencilIcon className="h-4 w-4" />
                      <span className="sr-only">Edit comment</span>
                    </Button>
                  )}
                  <Button
                    size="lg"
                    variant="ghost"
                    isIconOnly
                    onClick={() => removeHandler(comment.id)}
                  >
                    <TrashIcon className="h-4 w-4" />
                    <span className="sr-only">Delete comment</span>
                  </Button>
                </div>
                {comment.isEdit ? (
                  <Input
                    className="bg-white w-4/6"
                    type="text"
                    size="sm"
                    onChange={(e) => {
                      setEditingComment(e.target.value)
                      console.log(editingComment)
                    }}
                  />
                ) : (
                  <div className="w-full text-lg">{comment.content}</div>
                )}
              </div>
            </div>
          </div>
        );
      });
      setComments(showList);
    }
  }, [commentList]);

  return <>{comments.length !== 0 ? comments : "댓글이 존재하지 않습니다."}</>;
}

function EditIcon(props: { className?: string }) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth="1.5"
      stroke="currentColor"
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="M9 12.75L11.25 15 15 9.75M21 12c0 1.268-.63 2.39-1.593 3.068a3.745 3.745 0 01-1.043 3.296 3.745 3.745 0 01-3.296 1.043A3.745 3.745 0 0112 21c-1.268 0-2.39-.63-3.068-1.593a3.746 3.746 0 01-3.296-1.043 3.745 3.745 0 01-1.043-3.296A3.745 3.745 0 013 12c0-1.268.63-2.39 1.593-3.068a3.745 3.745 0 011.043-3.296 3.746 3.746 0 013.296-1.043A3.746 3.746 0 0112 3c1.268 0 2.39.63 3.068 1.593a3.746 3.746 0 013.296 1.043 3.746 3.746 0 011.043 3.296A3.745 3.745 0 0121 12z"
      />
    </svg>
  );
}

function PencilIcon(props: { className?: string }) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M17 3a2.85 2.83 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5Z" />
      <path d="m15 5 4 4" />
    </svg>
  );
}

function TrashIcon(props: { className?: string }) {
  return (
    <svg
      {...props}
      xmlns="http://www.w3.org/2000/svg"
      width="24"
      height="24"
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="2"
      strokeLinecap="round"
      strokeLinejoin="round"
    >
      <path d="M3 6h18" />
      <path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6" />
      <path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2" />
    </svg>
  );
}

export default CommentsList;
