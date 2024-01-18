import SideBar from "@/components/Layout/SideBar";
import Header from "./components/Header";
import ArticleBody from "./components/ArticleBody";
import { useState } from "react";

function ArticlePage() {
  return (
    <section className="w-screen min-h-screen flex">
      <SideBar />
      <article className="w-11/12 bg-black min-h-screen flex">
        <Header />
        <ArticleBody />
      </article>
    </section>
  );
}

export default ArticlePage;
