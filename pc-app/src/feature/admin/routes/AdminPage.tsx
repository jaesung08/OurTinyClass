import { useState } from "react";
import AdminNavBar from "../components/AdminNavBar";
import AdminUserBody from "../components/AdminUserBody";
import AdminArticleBody from "../components/AdminArticleBody";
import AdminAccept from "../components/AdminAccept";

const AdminPage = () => {
  const [currentPage, setCurrentPage] = useState<string>("user");

  const pageHandler = (page: string): void => {
    setCurrentPage(page);
  };

  const renderPage = () => {
    switch (currentPage) {
      case "user":
        return <AdminUserBody />;
      case "article":
        return <AdminArticleBody />;
      case "accept":
        return <AdminAccept />;
      default:
        return null;
    }
  };

  return (
    <>
      <AdminNavBar setpage={pageHandler} />
      {renderPage()}
    </>
  );
};

export default AdminPage;
