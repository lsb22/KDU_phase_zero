const PlayMovie = () => {
  return <div>Now Playing: {localStorage.getItem("movie")}</div>;
};

export default PlayMovie;
