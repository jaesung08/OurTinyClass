import { Suspense } from "react";
import { Navigate, Outlet } from "react-router-dom";

import { Spinner } from "@/components/Elements";
import MainDashboard from "@/feature/users/routes/MainDashboard";
import JoinRoom from "@/feature/classroom/pages/JoinRoom";
import Video from "@/feature/classroom/pages/meeting";

const App = () => {
  return (
    <Suspense
      fallback={
        <div className="h-full w-full flex items-center justify-center">
          <Spinner size="xl" />
        </div>
      }
    >
      <Outlet />
    </Suspense>
  );
};

export const protectedRoutes = [
  {
    element: <App />,
    children: [
      { path: "/main-dashboard", element: <MainDashboard /> },
      { path: "/join-classroom", element: <JoinRoom /> },
      { path: "/video", element: <Video /> },
      { path: "*", element: <Navigate to="." /> },
    ],
  },
];
