import React, { useEffect, useState } from "react";
import alarmMp3 from "./../assets/alarm-clock-90867.mp3";
const Pomodoro = () => {
  const [timer, setTimer] = useState(25 * 60);

  const [mode, setMode] = useState("pomodoro");

  const [isRunning, setIsRuning] = useState(false);

  const alarmSound = new Audio(alarmMp3);

  useEffect(() => {
    let timer;
    if (isRunning) {
      timer = setInterval(() => {
        setTimer((prev) => {
          const timerCurrentValue = prev;
          if (timerCurrentValue === 0) {
            alarmSound.play();
            handleReset();
          }
          return prev > 0 ? prev - 1 : 0;
        });
      }, 1000);
    }

    return () => clearInterval(timer);
  }, [isRunning]);

  const formatTimer = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const secs = seconds % 60;
    return `${minutes.toString().padStart(2, "0")}:${secs.toString().padStart(2, "0")}`;
  };

  const handleStartPause = () => {
    setIsRuning(!isRunning);
  };

  const handleReset = () => {
    setIsRuning(false);

    if (mode === "pomodoro") {
      setTimer(25 * 60);
    }
    if (mode === "short-break") {
      setTimer(5 * 60);
    }
    if (mode === "long-break") {
      setTimer(15 * 60);
    }
  };

  const handleMode = (mode) => {
    if (mode === "pomodoro") {
      setMode("pomodoro");
      setTimer(25 * 60);
    }
    if (mode === "short-break") {
      setMode("short-break");
      setTimer(5 * 60);
    }
    if (mode === "long-break") {
      setMode("long-break");
      setTimer(15 * 60);
    }
    setIsRuning(false);
  };

  return (
    <div className="flex justify-center w-full mt-4">
      <div className="flex flex-col h-[300px] w-[500px] bg-purple-400 rounded-md shadow-md">
        <div className="flex gap-4 justify-center my-2">
          <button
            onClick={() => handleMode("pomodoro")}
            className={`${
              mode === "pomodoro" ? "bg-red-400" : ""
            } text-white px-4 py-2 rounded-md hover:bg-purple-600 foucs:outline-none
           focus:ring-purple-400 focus:ring-offset-2 transition duration-200`}
          >
            Pomdoro
          </button>
          <button
            onClick={() => handleMode("short-break")}
            className={`${
              mode === "short-break" ? "bg-red-400" : ""
            } text-white px-4 py-2 rounded-md hover:bg-purple-600 foucs:outline-none
           focus:ring-purple-400 focus:ring-offset-2 transition duration-200`}
          >
            Short Break
          </button>
          <button
            onClick={() => handleMode("long-break")}
            className={`${
              mode === "long-break" ? "bg-red-400" : ""
            } text-white px-4 py-2 rounded-md hover:bg-purple-600 foucs:outline-none
           focus:ring-purple-400 focus:ring-offset-2 transition duration-200`}
          >
            Long Break
          </button>
        </div>
        <h1 className="text-9xl fond-bold text-center gap-4 text-white">{formatTimer(timer)}</h1>
        <div className="flex justify-center items-center gap-4 mt-4">
          <button
            className="w-[200px] font-bold text-purple-800 text-2xl bg-slate-100 py-2 px-2 rounded-md"
            onClick={handleStartPause}
          >
            {!isRunning ? "START" : "PAUSE"}
          </button>
          <button
            onClick={handleReset}
            className="w-[200px] font-bold text-purple-800 text-2xl bg-slate-100 py-2 px-2 rounded-md"
          >
            RESET
          </button>
        </div>
      </div>
    </div>
  );
};

export default Pomodoro;
