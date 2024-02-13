import AcceptCard from "./AcceptCard";

const AdminAccept = () => {
  return (
    <section className="w-10/12 flex flex-col mx-auto py-10">
      <p className="my-5 text-2xl">유저 승인 목록</p>
      <div className="mx-auto w-full h-[40rem] overflow-y-scroll scrollbar-hide flex flex-wrap gap-10 justify-around py-5 border-2 rounded-lg">
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
        <AcceptCard />
      </div>
    </section>
  );
};

export default AdminAccept;
