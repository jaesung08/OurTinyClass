import {
  Table,
  TableHeader,
  TableBody,
  TableColumn,
  TableRow,
  TableCell,
  Button,
  Select,
  SelectItem,
  Input,
  Pagination,
  PaginationItemType,
  PaginationItemRenderProps,
} from "@nextui-org/react";
import ReactQuill from "react-quill";
function CreateArticleBody() {
  const itemLists = [
    {
      category: "자유 게시판",
      value: 0,
    },
    {
      category: "고민 상담",
      value: 1,
    },
    {
      category: "테스트 게시판",
      value: 2,
    },
  ];
  const modules = {
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
  };
  return (
    <section className="w-10/12 h-full border-l-3">
      <div
        className="w-full bg-lime-500 flex items-center justify-center"
        style={{ height: "10%" }}
      ></div>
      <div className="w-full bg-white" style={{ height: "90%" }}>
        <div
          className="w-full flex justify-center items-center"
          style={{ height: "90%" }}
        >
          <div
            className="w-5/6 flex flex-col justify-between py-3 items-center"
            style={{ height: "90%" }}
          >
            <div className="w-full" style={{ height: "15%" }}>
              <form className="flex justify-between items-center">
                <Select
                  label="카테고리"
                  className="ml-5 w-2/12 shadow-xl rounded-xl"
                >
                  {itemLists.map(
                    (
                      item: { category: string; value: number },
                      index: number
                    ) => (
                      <SelectItem key={index} value={item.value}>
                        {item.category}
                      </SelectItem>
                    )
                  )}
                </Select>
                <div className="flex w-8/12 flex-wrap md:flex-nowrap gap-4 shadow-xl rounded-xl">
                  <Input
                    type="text"
                    label="제목"
                    placeholder="제목을 입력하세요."
                  />
                </div>
                <Button
                  color="success"
                  className="w-1/12 text-white text-xl py-7 mr-5 shadow-xl rounded-xl"
                >
                  작성
                </Button>
              </form>
            </div>
            <ReactQuill
              modules={modules}
              style={{ width: "90%", height: "80%" }}
            />
          </div>
        </div>
      </div>
    </section>
  );
}

export default CreateArticleBody;
