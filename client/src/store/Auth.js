<<<<<<< HEAD
import { createSlice } from '@reduxjs/toolkit';

const TOKEN_TIME_OUT = 600 * 1000;

const initialState = {
  authenticated: false,
  memberId: null,
  accessToken: null,
  expireTime: null,
};

const tokenSlice = createSlice({
  name: 'authToken',
  initialState,
  reducers: {
    SET_TOKEN: (state, action) => {
      state.authenticated = true;
      state.memberId = action.payload.memberId;
      state.accessToken = action.payload.accessToken;
      state.expireTime = new Date().getTime() + TOKEN_TIME_OUT;
    },
    DELETE_TOKEN: (state) => {
      state.authenticated = false;
      state.memberId = null;
      state.accessToken = null;
      state.expireTime = null;
    },
  },
});

export const { SET_TOKEN, DELETE_TOKEN } = tokenSlice.actions;
export default tokenSlice.reducer;
=======
import { createSlice } from '@reduxjs/toolkit';

const TOKEN_TIME_OUT = 10 * 60 * 1000;

const initialState = {
  authenticated: false,
  memberId: null,
  accessToken: null,
  expireTime: null,
};

const tokenSlice = createSlice({
  name: 'authToken',
  initialState,
  reducers: {
    SET_TOKEN: (state, action) => {
      state.authenticated = true;
      state.memberId = action.payload.memberId;
      state.accessToken = action.payload.accessToken;
      state.expireTime = new Date().getTime() + TOKEN_TIME_OUT;
    },
    DELETE_TOKEN: (state) => {
      state.authenticated = false;
      state.memberId = null;
      state.accessToken = null;
      state.expireTime = null;
    },
  },
});

export const { SET_TOKEN, DELETE_TOKEN } = tokenSlice.actions;
export default tokenSlice.reducer;
>>>>>>> e0a7fb8766175677c5983d6e3f10547977eef650
