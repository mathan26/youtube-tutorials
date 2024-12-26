import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../../store";
import {
  decrementFromRedux,
  incrementFromRedux,
  updateByGivenValueFromRedux,
} from "./counterSlice";
import { useState } from "react";

const Counter = () => {
  const counterValue = useSelector((state: RootState) => state.counter.value);
  const [countInput, setCountInput] = useState<number>(0);

  const dispatch: AppDispatch = useDispatch();

  const hanldeIncrement = () => {
    dispatch(incrementFromRedux());
  };

  const handleDecrement = () => {
    dispatch(decrementFromRedux());
  };

  const update = () => {
    dispatch(updateByGivenValueFromRedux(countInput));
  };

  return (
    <div className="flex flex-col justify-center items-center min-h-screen">
      <div className="bg-white p-8 rounded-lg shadow-md text-center h-[400px]">
        <h1 className="text-3xl font-bold text-gray-800 mb-4">Counter</h1>
        <p className="text-3xl text-gray-600 mb-6">{counterValue}</p>
        <div className="flex space-x-4 mb-2">
          <button
            className="px-4 py-2 bg-green-600 text-white hover:bg-green-800 transition duration-300 rounded-lg"
            onClick={hanldeIncrement}
          >
            increment by 1
          </button>
          <button
            className="px-4 py-2 bg-red-500 text-white hover:bg-red-600 transition duration-300 rounded-lg"
            onClick={handleDecrement}
          >
            decrement by 1
          </button>
        </div>
        <div className="flex flex-col space-x-4 mb-2 justify-center items-center">
          <input
            type="number"
            value={countInput}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) => {
              setCountInput(Number(e.target.value));
            }}
            name="counter"
            id="counterid"
            className="px-4 py-2 border focus:ring-2 focus:ring-blue-500 focus:outline-none mb-2 w-1/2"
          />
          <button
            className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-600 transition duration-300 mb-2"
            onClick={() => update()}
          >
            Increment by given input
          </button>
        </div>
      </div>
    </div>
  );
};

export default Counter;
