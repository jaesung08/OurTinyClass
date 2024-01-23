import { CommunitiesRoutes } from "@/feature/communities";
import { lazyImport } from "@/utils/lazyImport";

const { AuthRoutes } = lazyImport(() => import("@/feature/auth"), "AuthRoutes");

export const publicRoutes = [
  {
    path: "/auth/*",
    element: <AuthRoutes />,
  },
  {
    path: "/communities/*",
    element: <CommunitiesRoutes />,
  },
];
