import Certification from "@/feature/auth/routes/Certification";
import { lazyImport } from "@/utils/lazyImport";

const { AuthRoutes } = lazyImport(() => import("@/feature/auth"), "AuthRoutes");

export const publicRoutes = [
  {
    path: "/auth/*",
    element: <AuthRoutes />,
  },

  { path: "/certificate", element: <Certification /> },
];
