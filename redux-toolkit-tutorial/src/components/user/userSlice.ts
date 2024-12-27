import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

interface UserProps {
  name: string;
  email: string;
}

interface initialStateProps {
  user: Partial<UserProps>;
  loading: boolean;
  error: string;
}

const initialState: initialStateProps = {
  user: {},
  loading: false,
  error: "",
};

export const fetchUser = createAsyncThunk("user/fetchUser", async () => {
  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/users/1");
    if (!response.ok) {
      throw new Error("failed to fetch user");
    }
    return await response.json();
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
  } catch (error: any) {
    return error.message;
  }
});

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchUser.pending, (state) => {
        state.loading = true;
        state.error = "";
      })
      .addCase(fetchUser.fulfilled, (state, action) => {
        state.user = action.payload;
        state.loading = false;
      })
      .addCase(fetchUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload as string;
      });
  },
});

export default userSlice.reducer;
