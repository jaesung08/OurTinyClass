import { Avatar, Button, AvatarFallback, AvatarImage } from "@nextui-org/react";

function CommentsList() {
  return (
    <>
      {/* <article className="my-5 border-black w-10/12 flex justify-between">
        <div className="w-5/6 flex flex-col">
          <div className="flex items-center">
            <Avatar
              src="https://i.pravatar.cc/150?u=a042581f4e29026024d"
              className="mx-2"
            />
            <p className="mx-2">김가루</p>
          </div>
          <p className="mt-3 pl-3 w-5/6 break-words">
            hellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohello
          </p>
        </div>
        <div className="w-1/6 flex items-start justify-end">
          <p>삭제</p>　　<p>수정</p>
        </div>
        <hr className="w-full" />
      </article>
      <article className="my-5 border-black w-10/12 flex justify-between">
        <div className="w-5/6 flex flex-col">
          <div className="flex items-center">
            <Avatar
              src="https://i.pravatar.cc/150?u=a042581f4e29026024d"
              className="mx-2"
            />
            <p className="mx-2">김가루</p>
          </div>
          <p className="mt-3 pl-3 w-5/6 break-words">hello</p>
        </div>
        <div className="w-1/6 flex flex-col items-end justify-between">
          <Button
            color="primary"
            className="w-1/12 text-white text-xl py-3 mr-5 shadow-2xl rounded-xl"
          >
            수정
          </Button>{" "}
          <Button
            color="danger"
            className="w-1/12 text-white text-xl py-3 mr-5 shadow-2xl rounded-xl"
          >
            삭제
          </Button>
        </div>
    </article> */}
      <h2 className="font-semibold text-xl mt-7 mb-5">Comments</h2>
      <hr className="w-10/12" />
      <div className="grid gap-6 w-10/12">
        <div className="text-sm flex items-start gap-4 w-full my-5">
          <div className="grid gap-1.5 w-full">
            <div className="flex items-center gap-2">
              <Avatar
                src="https://i.pravatar.cc/150?u=a042581f4e29026024d"
                className="mx-2"
              />
              <div className="font-semibold">@iamwillpursell</div>
              <div className="text-gray-500 text-xs dark:text-gray-400">
                5 months ago
              </div>
              <Button className="ml-auto" size="icon" variant="ghost">
                <PencilIcon className="h-4 w-4" />
                <span className="sr-only">Edit comment</span>
              </Button>
              <Button size="icon" variant="ghost">
                <TrashIcon className="h-4 w-4" />
                <span className="sr-only">Delete comment</span>
              </Button>
            </div>
            <div className="w-full text-lg">
              I really love the ecosystem Vercel is creating. The way each
              component can be added and modified with ease really makes these
              tools attractive. I really love the ecosystem Vercel is creating.
              The way each component can be added and modified with ease really
              makes these tools attractive.
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

function PencilIcon(props) {
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

function TrashIcon(props) {
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
