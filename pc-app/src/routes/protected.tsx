import { Suspense } from "react";
import { Outlet } from "react-router-dom";
import { Spinner } from "@/components/Elements";
import MainDashboard from "@/feature/users/routes/MainDashboard";
import JoinRoom from "@/feature/classroom/pages/JoinRoom";
import { CommunitiesRoutes } from "@/feature/communities/routes";
import AppLayout from "@/components/Layout/AppLayout";
import ChatHome from "@/feature/chat/routes/ChatHome";
import Certification from "@/feature/auth/routes/Certification";
import EditSchedules from "@/feature/schedule/routes/EditSchedules";
import { StompSessionProvider } from "react-stomp-hooks";
import { SOCKET_URL } from "@/config";
import { StompClientProvider } from "@/providers/StompClient";
import Mypage from "@/feature/users/routes/Mypage";

const App = () => {
	return (
		<StompSessionProvider
			url={`ws://${SOCKET_URL}/ws/chat`}
			connectHeaders={{
				Authorization: localStorage.getItem("accessToken") ?? "",
			}}
		>
			<StompClientProvider>
				<AppLayout>
					<Suspense
						fallback={
							<div className="h-full w-full flex items-center justify-center">
								<Spinner size="xl" />
							</div>
						}
					>
						<Outlet />
					</Suspense>
				</AppLayout>
			</StompClientProvider>
		</StompSessionProvider>
	);
};

export const protectedRoutes = [
	{
		element: <App />,
		children: [
			{ path: "join-classroom", element: <JoinRoom /> },
			{ path: "main-dashboard", element: <MainDashboard /> },
			{ path: "communities/*", element: <CommunitiesRoutes /> },
			{ path: "chats", element: <ChatHome /> },
			{ path: "certificates", element: <Certification /> },
			{ path: "schedule", element: <EditSchedules /> },
			{ path: "/mypage", element: <Mypage /> },
		],
	},
];
