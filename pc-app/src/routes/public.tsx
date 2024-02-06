import MyPage from "@/feature/mypage/MyPage";
import { lazyImport } from "@/utils/lazyImport";
import { Navigate } from "react-router-dom";

const { AuthRoutes } = lazyImport(() => import("@/feature/auth"), "AuthRoutes");

export const publicRoutes = [
  {
    path: "/auth/*",
    element: <AuthRoutes />,
  },
  {
    path: "/mypage/*",
    element: <MyPage />,
  },
  { path: "*", element: <Navigate to="/auth/login" /> },
];
