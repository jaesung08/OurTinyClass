import FreeBoardHeader from "../components/FreeBoardHeader";
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
