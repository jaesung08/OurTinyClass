import { Button, Select, SelectItem, Input } from "@nextui-org/react";
import { axios } from "@/lib/axios";
import { useEffect, useState, useMemo } from "react";
import ReactQuill from "react-quill";
import { useNavigate } from "react-router-dom";
import { createBoard } from "../api/createBoard";

function CreateArticleBody() {
  const navigator = useNavigate();
  const [articleCategory, setArticleCategory] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const selectCategory = (e) => {
    console.log(e.target.value);
    setArticleCategory(e.target.value);
  };

  const BoardCreate = async () => {
    try {
      await createBoard(title, content, articleCategory);
      navigator("/communities");
    } catch (error) {
      console.log(error);
    }
  };
  const itemLists = [
    {
      category: "공지사항",
      value: "notice",
    },
    {
      category: "고민 상담",
      value: "1",
    },
    {
      category: "테스트 게시판",
      value: "2",
    },
  ];
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
  return (
    <section className="w-10/12 h-full border-l-3 realtive">
      <Button
        className="fixed bottom-5 right-5 text-white text-xl rounded-full"
        style={{
          backgroundColor: "#52c41a",
          boxShadow: "0px 0px 10px rgba(0,0,0,0.1)",
        }}
        onClick={() => {
          navigator("/communities/");
        }}
        size="lg"
      >
        ←
      </Button>
      <div className="w-full bg-lime-500 flex items-center justify-center h-20"></div>
      <div className="w-full bg-white" style={{ height: "90%" }}>
        <div
          className="w-full flex justify-center items-center"
          style={{ height: "90%" }}
        >
          <div
            className="w-5/6 flex flex-col  py-3 items-center"
            style={{ height: "90%" }}
          >
            <div className="w-full" style={{ height: "15%" }}>
              <form className="flex justify-between items-center">
                <Select
                  className="bg-white w-1/6 rounded-xl"
                  size="sm"
                  onChange={(e) => selectCategory(e)}
                >
                  {itemLists.map(
                    (
                      item: { category: string; value: string }
                      // index: number
                    ) => (
                      <SelectItem key={item.value} value={item.value}>
                        {item.category}
                      </SelectItem>
                    )
                  )}
                </Select>
                <Input
                  className="bg-white w-4/6"
                  type="text"
                  size="sm"
                  placeholder="제목을 입력하세요."
                  onChange={(e) => setTitle(e.target.value)}
                />

                <Button
                  className="w-1/12 text-white text-xl rounded-xl"
                  style={{
                    backgroundColor: "#52c41a",
                    boxShadow: "0px 0px 10px rgba(0,0,0,0.1)",
                  }}
                  size="lg"
                  onClick={BoardCreate}
                >
                  작성
                </Button>
              </form>
            </div>
            <ReactQuill
              modules={modules}
              style={{ width: "100%", height: "70%" }}
              onChange={(e) => setContent(e)}
            />
          </div>
        </div>
      </div>
    </section>
  );
}

export default CreateArticleBody;
