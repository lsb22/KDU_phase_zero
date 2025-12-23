const App = () => {
  return (
    <div className="wrapper1">
      <h1 className="app-title">Movie WatchList</h1>
      <div className="wrapper2">
        <div className="part1">
          <h2 className="part1-title">Add your favourite Movie</h2>
          <div className="">Add movies Component</div>
          <div className="">Movies in WatchList: 0</div>
          <button className="">Clear Watchlist</button>
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
