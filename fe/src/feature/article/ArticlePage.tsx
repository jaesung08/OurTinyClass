import SideBar from "@/components/Layout/SideBar";
import Header from "./components/Header";

function ArticlePage() {
  return (
    <section className="w-screen min-h-screen flex">
      <SideBar />
      <article className="w-11/12 bg-black min-h-screen">
        <Header />
      </article>
    </section>
  );
}

export default ArticlePage;
