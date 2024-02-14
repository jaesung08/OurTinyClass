import CommunitySideBar from "./CommunitySideBar";

type CommunityLayoutProps = {
  children: React.ReactNode;
};
function CommunityLayout({ children }: CommunityLayoutProps) {
  return (
    <section className="w-screen min-h-screen flex">
      <CommunitySideBar />
      {children}
    </section>
  );
}

export default CommunityLayout;
