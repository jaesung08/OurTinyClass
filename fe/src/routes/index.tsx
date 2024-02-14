import { LandingPage } from "@/features/landingpage/routes/LandingPage";
import { useRoutes } from "react-router-dom";

export const AppRoutes = () => {
  const defaultRoutes = [
    {
      path: "/",
      element: <LandingPage />,
    },
  ];
  const element = useRoutes([...defaultRoutes]);

  return <>{element}</>;
};
