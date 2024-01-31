import { Button } from "@nextui-org/button";

function Comments() {
  return (
    <form className="w-10/12 h-20 flex justify-between items-center">
      <input
        type="text"
        className="w-5/6 p-5 rounded-lg shadow-2xl border-2"
        placeholder="댓글을 입력하세요."
      />
      <Button
        color="default"
        className="w-1/12 text-white text-xl py-7 mr-5 shadow-2xl rounded-xl"
      >
        작성
      </Button>
    </form>
  );
}

export default Comments;
