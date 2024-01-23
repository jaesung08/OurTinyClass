function Header() {
  return (
    <section className="w-2/12 bg-red-500 h-full">
      <div
        className="w-full bg-lime-500 flex justify-center items-center"
        style={{ height: "10%" }}
      >
        <p className="text-2xl text-white font font-bold">TeenEduConnect</p>
      </div>
      <div className="w-full bg-yellow-700" style={{ height: "90%" }}>
        여기는 항목들 들어가는 부분
      </div>
    </section>
  );
}

export default Header;
