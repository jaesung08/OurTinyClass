function SideBar() {
  return (
    <>
      <article className="w-1/12 bg-slate-400 min-h-screen">
        <input
          className="focus:ring-2 focus:ring-blue-500 focus:outline-none appearance-none w-full text-sm leading-6 text-slate-900 placeholder-slate-400 rounded-md py-2 pl-10 ring-1 ring-slate-200 shadow-sm"
          type="text"
          aria-label="Filter projects"
          placeholder="Filter projects..."
        />
      </article>
    </>
  );
}

export default SideBar;
