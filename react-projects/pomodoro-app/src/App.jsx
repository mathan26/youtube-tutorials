import React from "react";
import Pomodoro from "./components/Pomodoro";

const App = () => {
  return (
    <div className="bg-purple-500 min-h-screen">
      <h1 className="text-white text-center text-4xl font-bold py-2.5">Pomodoro Time Focus</h1>

      <Pomodoro />
    </div>
  );
};

export default App;
