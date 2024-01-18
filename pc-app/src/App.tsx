import { HashRouter, Route, Routes } from "react-router-dom";
import JoinRoom from "./feature/classroom/pages/JoinRoom";
import Video from "./feature/classroom/pages/meeting";
import Home from "./Home";

function App() {
  return (
    <>
      <HashRouter basename="/">
        <Routes>
          <Route path="/" Component={Home}></Route>
          <Route path="/join-classroom" Component={JoinRoom}></Route>
          <Route path="/video" Component={Video} />
        </Routes>
      </HashRouter>
    </>
  );
}

export default App;
