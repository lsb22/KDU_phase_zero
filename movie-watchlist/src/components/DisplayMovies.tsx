import type { Movie } from "@/App";
import { Trash2Icon } from "lucide-react";
import { Input } from "@/components/ui/input";

interface Props {
  movies: Movie[];
  updateMovie: (movieName: string) => void;
  deleteMovie: (movieName: string) => void;
}

const DisplayMovies = ({ movies, updateMovie, deleteMovie }: Props) => {
  return (
    <div className="py-7 flex flex-col gap-y-4">
      {movies.length === 0 ? (
        <h2 className="text-3xl">
          Your watchlist is empty. Add your first movie!
        </h2>
      ) : (
        movies.map((movie, idx) => (
          <div
            className="flex justify-between items-center border px-3 py-1"
            key={idx}
          >
            <div className="flex flex-col">
              <h2>{movie.name.toUpperCase()}</h2>
              <h4>{movie.rating}</h4>
            </div>
            <div className="flex gap-x-5 px-3">
              <div className="flex items-center gap-x-3">
                Watched Movie{" "}
                <Input
                  type="checkbox"
                  className="h-5 w-5"
                  onClick={() => updateMovie(movie.name)}
                  checked={movie.watched}
                />
              </div>
              <Trash2Icon
                className="text-red-600 hover:text-red-700 active:scale-[0.9]"
                onClick={() => deleteMovie(movie.name)}
              />
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default DisplayMovies;
