import { Navigate, useRoutes } from "react-router-dom";

import { protectedRoutes } from "./protected";
import { publicRoutes } from "./public";
import Video from "@/feature/classroom/pages/Video";

export const AppRoutes = () => {
	const auth = localStorage.getItem("accessToken");
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
