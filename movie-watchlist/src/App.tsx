import HomePage from "./components/HomePage";
import { Route, Routes } from "react-router-dom";
import PremiumRentals from "./components/PremiumRentals";
import PlayMovie from "./components/PlayMovie";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/rentals" element={<PremiumRentals />} />
      <Route path="/playMovie" element={<PlayMovie />} />
    </Routes>
  );
};

export default App;
