import SideBar from "@/components/Layout/SideBar";
import FreeBoardHeader from "../components/FreeBoardHeader";
import CreateArticleBody from "../components/CreateArticleBody";

function CreateArticle() {
  return (
    <section className="w-screen min-h-screen flex">
      <SideBar />
      <article className="w-11/12 min-h-screen flex">
        <FreeBoardHeader />
        <CreateArticleBody />
      </article>
    </section>
  );
}

export default CreateArticle;
