import AdminPage from "@/feature/admin/routes/AdminPage";
import { lazyImport } from "@/utils/lazyImport";
import { Navigate } from "react-router-dom";

const { AuthRoutes } = lazyImport(() => import("@/feature/auth"), "AuthRoutes");

export const publicRoutes = [
  {
    path: "/auth/*",
    element: <AuthRoutes />,
  },
  {
    path: "/admins/*",
    element: <AdminPage />,
  },
  { path: "*", element: <Navigate to="/auth/login" /> },
];
