import { Route, Routes } from "react-router-dom";
import { SpecialLectureCreate } from "./SpecialLectureCreate";

export const LecturesRoutes = () => {
  return (
    <Routes>
      <Route path="create" element={<SpecialLectureCreate />} />
    </Routes>
  );
};
