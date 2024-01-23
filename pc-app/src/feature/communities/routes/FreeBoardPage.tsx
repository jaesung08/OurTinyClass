import SideBar from "@/components/Layout/SideBar";
import FreeBoardHeader from "../components/FreeBoardHeader";
import FreeBoardBody from "../components/FreeBoardBody";

function FreeBoardPage() {
  return (
    <section className="w-screen min-h-screen flex">
      <SideBar />
      <article className="w-11/12 min-h-screen flex">
        <FreeBoardHeader />
        <FreeBoardBody />
      </article>
    </section>
  );
}

export default FreeBoardPage;
