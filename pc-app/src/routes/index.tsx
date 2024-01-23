import { useRoutes } from "react-router-dom";

import { protectedRoutes } from "./protected";
import { publicRoutes } from "./public";
import Landing from "@/feature/misc/Landing";
import Cookies from "js-cookie";

export const AppRoutes = () => {
  const auth = Cookies.get("token");
  const defaultRoutes = [
    {
      path: "/",
      element: <Landing />,
    },
  ];

  const routes = auth ? protectedRoutes : publicRoutes;

  const element = useRoutes([...defaultRoutes, ...routes]);

  return <>{element}</>;
};
