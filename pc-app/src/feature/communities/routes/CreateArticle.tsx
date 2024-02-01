import FreeBoardHeader from "../components/FreeBoardSideBar";
import CreateArticleBody from "../components/CreateArticleBody";

function CreateArticle() {
  return (
    <section className="w-screen min-h-screen flex">
      <FreeBoardHeader />
      <CreateArticleBody />
    </section>
  );
}

export default CreateArticle;
