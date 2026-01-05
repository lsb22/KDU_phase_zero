import { useEffect, useState } from "react";
import { NavLink } from "react-router";

interface Movies {
  movie: string;
  setMovie: (moviee: string) => void;
  selectedMovie: string;
}

const MovieCard = ({ movie, setMovie, selectedMovie }: Movies) => {
  const [timer, setTimer] = useState(10);
  const [id, setId] = useState(0);

  const startTimer = () => {
    const id = setInterval(() => {
      setTimer((prevTimer) => prevTimer - 1);
    }, 1000);
    setId(id);
  };

  useEffect(() => {
    if (timer === 0) {
      clearInterval(id);
    }
  }, [timer]);

  return (
    <div className="flex flex-col gap-y-5 border-2 w-70 h-70 items-center justify-center">
      <h1>{movie}</h1>
      {timer === 0 ? (
        <NavLink
          className="border-2 rounded-md p-3 active:scale-[0.9]"
          to="/playMovie"
          onClick={() => {
            localStorage.setItem("movie", movie);
          }}
        >
          Watch Now
        </NavLink>
      ) : (
        <button
          className="border-2 rounded-md p-3 active:scale-[0.9]"
          onClick={() => {
            startTimer();
            setMovie(movie);
          }}
          disabled={selectedMovie.length !== 0}
        >
          Start Countdown: {timer}
        </button>
      )}
      <button
        onClick={() => {
          clearInterval(id);
          setTimer(10);
        }}
        className="border-2 rounded-md p-3 active:scale-[0.9]"
      >
        Reset
      </button>
    </div>
  );
};

export default MovieCard;
