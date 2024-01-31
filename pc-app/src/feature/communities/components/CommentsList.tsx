import { Avatar, Button } from "@nextui-org/react";

function CommentsList() {
  return (
    <>
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
              <Button className="ml-auto" size="lg" variant="ghost">
                <PencilIcon className="h-4 w-4" />
                <span className="sr-only">Edit comment</span>
              </Button>
              <Button size="lg" variant="ghost">
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
