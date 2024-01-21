import { useRoutes } from "react-router-dom";

import { protectedRoutes } from "./protected";
import { publicRoutes } from "./public";
import Landing from "@/feature/misc/Landing";

export const AppRoutes = () => {
  const auth = undefined;
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
