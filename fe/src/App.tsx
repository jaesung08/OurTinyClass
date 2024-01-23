import { AppProvider } from "@/providers/app";
import { AppRoutes } from "@/routes";
import DetailArticle from "./features/communities/routes/DetailArticle";
import FreeBoardPage from "./features/communities/routes/FreeBoardPage";

function App() {
  return (
    <AppProvider>
      <AppRoutes />
    </AppProvider>
  );
}

export default App;
