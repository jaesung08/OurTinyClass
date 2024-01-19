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
  Avatar,
} from "@nextui-org/react";
import { useState } from "react";
import ReactQuill from "react-quill";
import Comments from "./Comments";
import CommentsList from "./CommentsList";
import { input } from "@testing-library/user-event/dist/cjs/event/input.js";

function DetailArticleBody() {
  const [isArticleEdit, setIsArticleEdit] = useState<boolean>(false);
  const [articleContent, setArticleContent] = useState<string>(
    "Lorem ipsum dolor sit, amet consectetur adipisicing elit. Dolor beatae ipsa sit maxime commodi quas accusamus exercitationem obcaecati vitae unde illum, dolores totam, ratione a? Unde aspernatur porro sapiente quae? Placeat cum quam omnis totam unde quia beatae! Optio rerum ratione minima itaque beatae cumque aliquam a alias voluptatibus doloremque, earum, cum distinctio, eos ea? Vero dolor excepturi fuga hic. Excepturi quod fuga ducimus hic exercitationem, fugit quos tempora a ad eius, impedit, odit placeat sequi nisi natus non voluptas labore harum esse necessitatibus? Laudantium, nisi mollitia! Blanditiis, corporis autem. Cum reprehenderit illo a explicabo, veniam iusto! Earum corrupti accusamus quas expedita itaque saepe laboriosam rerum, numquam temporibus assumenda eum, quibusdam nisi ipsum animi laborum est tempore! Natus, obcaecati adipisci! Facilis iure consectetur aspernatur obcaecati reiciendis ut minima, nihil eligendi nobis placeat, blanditiis ratione amet doloremque quo consequatur non eius, saepe id hic nesciunt vero? Nostrum animi quae eaque nisi? beatae ipsa sit maxime commodi quas accusamus exercitationem obcaecati vitae unde illum, dolores totam, ratione a? Unde aspernatur porro sapiente quae? Placeat cum quam omnis totam unde quia beatae! Optio rerum ratione minima itaque beatae cumque aliquam a alias voluptatibus doloremque, earum, cum distinctio, eos ea? Vero dolor excepturi fuga hic. Excepturi quod fuga ducimus hic exercitationem, fugit quos tempora a ad eius, impedit, odit placeat sequi nisi natus non voluptas labore harum esse necessitatibus? Laudantium, nisi mollitia! Blanditiis, corporis autem. Cum reprehenderit illo a explicabo, veniam iusto! Earum corrupti accusamus quas expedita itaque saepe laboriosam rerum, numquam temporibus assumenda eum, quibusdam nisi ipsum animi laborum est tempore! Natus, obcaecati adipisci! Facilis iure consectetur aspernatur obcaecati reiciendis ut minima, nihil eligendi nobis placeat, blanditiis ratione amet doloremque quo consequatur non eius, saepe id hic nesciunt vero? Nostrum animi quae eaque nisi?"
  );

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

  const editArticle = () => {
    setIsArticleEdit(!isArticleEdit);
  };
  const checkValue = (e) => {
    console.log(e);
    const check = e.split("<p>");
    const textValue = check[1].split("</p>");
    console.log(textValue);
    setArticleContent(textValue[0]);
  };
  const completeArticleEdit = () => {
    setIsArticleEdit(!isArticleEdit);
  };
  return (
    <article className="w-10/12 h-full border-l-1">
      <div className="w-full bg-lime-500 flex items-center justify-center h-20"></div>
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
        <div className="w-full flex items-center justify-around">
          <div className="w-8/12">
            <p className="text-3xl font-bold">여기는 제목 란입니다.</p>
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
                color="success"
                className="w-1/12 text-white text-xl py-7 shadow-xl rounded-xl"
                onClick={completeArticleEdit}
              >
                완료
              </Button>
            ) : (
              <Button
                color="success"
                className="w-1/12 text-white text-xl py-7 shadow-xl rounded-xl"
                onClick={editArticle}
              >
                수정
              </Button>
            )}
          </div>
        </div>
        {isArticleEdit ? (
          <div className="w-10/12">
            <ReactQuill
              className="w-full py-20 pl-10"
              modules={modules}
              value={articleContent}
              onChange={(e) => checkValue(e)}
            />
          </div>
        ) : (
          <div className="w-10/12">
            <p className="w-full py-20 pl-10">{articleContent}</p>
          </div>
        )}

        <div className="w-full mx-10">
          <Comments />
          <CommentsList />
        </div>
      </div>
    </article>
  );
}

export default DetailArticleBody;
