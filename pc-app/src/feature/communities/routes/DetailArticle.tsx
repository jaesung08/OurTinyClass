import FreeBoardHeader from "../components/FreeBoardSideBar";
import DetailArticleBody from "../components/DetailArticleBody";

function DetailArticle() {
  return (
    <section className="w-screen  min-h-screen flex overflow-x-hidden">
      <FreeBoardHeader />
      <DetailArticleBody />
    </section>
  );
}

export default DetailArticle;
