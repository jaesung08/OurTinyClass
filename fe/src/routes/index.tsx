import { LandingPage } from "@/feature/landingpage/routes/LandingPage";
import { useRoutes } from "react-router-dom";
import { publicRoutes } from "./public";
// import Cookies from "js-cookie";

export const AppRoutes = () => {
  // const auth = Cookies.get("accessToken");
  const defaultRoutes = [
    {
      path: "/",
      element: <LandingPage />,
    },
  ];

  const routes = publicRoutes;
  const element = useRoutes([...defaultRoutes, ...routes]);

  return <>{element}</>;
};
