import FreeBoardHeader from "../components/FreeBoardHeader";
import FreeBoardBody from "../components/FreeBoardBody";

function FreeBoardPage() {
  return (
    <section className="w-screen min-h-screen flex">
      <FreeBoardHeader />
      <FreeBoardBody />
    </section>
  );
}

export default FreeBoardPage;
