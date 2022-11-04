<<<<<<< HEAD
import { configureStore } from '@reduxjs/toolkit';
import tokenReducer from './Auth';

export default configureStore({
  reducer: {
    authToken: tokenReducer,
  },
});
=======
import { configureStore } from '@reduxjs/toolkit';
import tokenReducer from './Auth';

export const store = configureStore({
  reducer: {
    authToken: tokenReducer,
  },
});
>>>>>>> e0a7fb8766175677c5983d6e3f10547977eef650
