import { Route, Routes } from "react-router-dom";
import CreateArticle from "./CreateArticle";
import FreeBoardPage from "./Community";
import DetailArticle from "./DetailArticle";

export const CommunitiesRoutes = () => {
  return (
    <Routes>
      <Route path="create" element={<CreateArticle />} />
      <Route path="detail" element={<DetailArticle />} />
      <Route path="" element={<FreeBoardPage />} />
    </Routes>
  );
};
