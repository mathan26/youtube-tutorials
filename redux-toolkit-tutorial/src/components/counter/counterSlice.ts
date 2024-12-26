import { createSlice, PayloadAction } from "@reduxjs/toolkit";

const initialState = {
  value: 0,
};

export const counterSlice = createSlice({
  name: "counter",
  initialState,
  reducers: {
    incrementFromRedux: (state) => {
      state.value += 1;
    },
    decrementFromRedux: (state) => {
      state.value -= 1;
    },
    updateByGivenValueFromRedux: (state, action: PayloadAction<number>) => {
      state.value = state.value + action.payload;
    },
  },
});

export const { incrementFromRedux, decrementFromRedux, updateByGivenValueFromRedux } =
  counterSlice.actions;

export default counterSlice.reducer;
