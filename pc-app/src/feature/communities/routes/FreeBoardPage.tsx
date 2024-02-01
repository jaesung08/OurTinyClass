import FreeBoardSideBar from "../components/FreeBoardSideBar";
import FreeBoardBody from "../components/FreeBoardBody";

function FreeBoardPage() {
  return (
    <section className="w-screen min-h-screen flex">
      <FreeBoardSideBar />
      <FreeBoardBody />
    </section>
  );
}

export default FreeBoardPage;
