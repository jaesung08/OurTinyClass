import SideBar from "@/components/Layout/SideBar";
import FreeBoardHeader from "../components/FreeBoardHeader";
import DetailArticleBody from "../components/DetailArticleBody";

function DetailArticle() {
  return (
    <section className="w-screen min-h-screen flex">
      <SideBar />
      <article className="w-11/12 min-h-screen flex overflow-x-hidden">
        <FreeBoardHeader />
        <DetailArticleBody />
      </article>
    </section>
  );
}

export default DetailArticle;
