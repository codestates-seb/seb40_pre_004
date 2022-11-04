import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { store } from './store/index';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { CookiesProvider } from 'react-cookie';
// import axios from 'axios';

// axios.defaults.baseURL =
//   'http://ec2-43-201-141-158.ap-northeast-2.compute.amazonaws.com:8080';
// axios.defaults.withCredentials = false;

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <CookiesProvider>
      <Provider store={store}>
        <BrowserRouter>
          <App />
        </BrowserRouter>
      </Provider>
    </CookiesProvider>
  </React.StrictMode>
);
