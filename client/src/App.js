import { Routes, Route } from 'react-router-dom';
import GlobalStyle from './GlobalStyle';
import Home from './pages/Home';
import NewQuestion from './pages/NewQuestion';
import Register from './pages/Register';
import Login from './pages/Login';
import AccountRecovery from './pages/AccountRecovery';
import RegisterSuccess from './pages/RegisterSuccess';


function App() {
  return (
    <>
      <GlobalStyle />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/account-recovery" element={<AccountRecovery />} />
        <Route path="/register-success" element={<RegisterSuccess />} />
        <Route path="/questions/ask" element={<NewQuestion />} />
      </Routes>
    </>
  );
}

export default App;
