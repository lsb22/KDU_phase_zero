import { useState } from "react";
import AddMovies from "./components/AddMovies";
import { Button } from "@/components/ui/button";

interface Movie {
  name: string;
  rating: string;
  watched: boolean;
}

const App = () => {
  const [movies, setMovies] = useState<Movie[]>([]);

  const sendFormData = (movieName: string, movieRatings: string) => {
    setMovies([
      ...movies,
      {
        name: movieName,
        rating: movieRatings,
        watched: false,
      },
    ]);
  };

  return (
    <div className="wrapper1">
      <h1 className="app-title">Movie WatchList</h1>
      <div className="wrapper2">
        <div className="part1 flex flex-col items-center justify-center gap-y-10 p-3">
          <h2 className="part1-title w-full text-center border p-2.5 text-[2rem] rounded-md">
            Add your favourite Movie
          </h2>
          <AddMovies sendFormData={sendFormData} />
          <div className="w-full flex flex-col gap-y-7">
            <div className="border rounded-md p-2 text-center">
              Movies in WatchList: 0
            </div>
            <Button className="bg-red-500 hover:bg-red-600 active:scale-[0.9]">
              Clear Watchlist
            </Button>
          </div>
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
