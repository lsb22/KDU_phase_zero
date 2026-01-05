import { useState } from "react";
import MovieCard from "./MovieCard";

const PremiumRentals = () => {
  const movies = ["movie1", "movie2", "movie3"];
  const [selectedMovie, setMovie] = useState("");
  return (
    <div className="flex gap-x-3">
      {movies.map((movie, idx) => (
        <MovieCard
          movie={movie}
          key={idx}
          setMovie={(movie) => setMovie(movie)}
          selectedMovie={selectedMovie}
        />
      ))}
    </div>
  );
};

export default PremiumRentals;
