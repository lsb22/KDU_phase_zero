import AddMovies from "./components/AddMovies";
import { Button } from "@/components/ui/button";

const App = () => {
  return (
    <div className="wrapper1">
      <h1 className="app-title">Movie WatchList</h1>
      <div className="wrapper2">
        <div className="part1">
          <h2 className="part1-title">Add your favourite Movie</h2>
          <AddMovies />
          <div className="">Movies in WatchList: 0</div>
          <Button className="bg-red-500 hover:bg-red-400">
            Clear Watchlist
          </Button>
        </div>
        <div className="part2">
          <input type="text" placeholder="Search your favourite movies here!" />
          <div className="">Display movies component</div>
        </div>
      </div>
    </div>
  );
};

export default App;
