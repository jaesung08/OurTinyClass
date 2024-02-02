import { Navigate, useRoutes } from "react-router-dom";

import { protectedRoutes } from "./protected";
import { publicRoutes } from "./public";
import Cookies from "js-cookie";
import Video from "@/feature/classroom/pages/meeting";

export const AppRoutes = () => {
  const auth = Cookies.get("accessToken");
  const defaultRoutes = [
    { path: "video", element: <Video /> },

    {
      path: "*",
      element: <Navigate to={auth ? "main-dashboard" : "auth/login"} />,
    },
  ];

  const routes = auth ? protectedRoutes : publicRoutes;

  const element = useRoutes([...defaultRoutes, ...routes]);

  return <>{element}</>;
};
