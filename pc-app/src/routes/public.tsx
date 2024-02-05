import { LecturesRoutes } from "@/feature/lecture/routes/index";
import { lazyImport } from "@/utils/lazyImport";
import { Navigate } from "react-router-dom";

const { AuthRoutes } = lazyImport(() => import("@/feature/auth"), "AuthRoutes");

export const publicRoutes = [
  {
    path: "/auth/*",
    element: <AuthRoutes />,
  },
  {
    path: "/lecture/*",
    element: <LecturesRoutes />,
  },

  { path: "*", element: <Navigate to="/auth/login" /> },
];
