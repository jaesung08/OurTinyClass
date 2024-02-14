import { useState } from "react";
import AdminNavBar from "../components/AdminNavBar";
import AdminArticleBody from "../components/AdminArticleBody";
import AdminAccept from "../components/AdminAccept";
import AdminClass from "../components/AdminClass";
import AdminUserBody from "../components/AdminArticleBody";

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
      case "class":
        return <AdminClass />;
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
