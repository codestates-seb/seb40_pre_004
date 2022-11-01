import axios from 'axios';
import { useNavigate, useLocation } from 'react-router-dom';
import styled from 'styled-components';
import Header from '../components/Header';

const S_Container = styled.div`
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI Adjusted',
    'Segoe UI', 'Liberation Sans', sans-serif;
  color: #232629;
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  background-color: #f1f2f3;
  box-sizing: border-box;
`;

const S_Main = styled.main`
  width: 420px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  & > h1 {
    font-size: 21px;
    margin-bottom: 12px;
  }

  & > p {
    font-size: 17px;
    text-align: center;
    margin-bottom: 17px;
  }
`;

const S_Svg = styled.svg`
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;
`;

const S_FormContainer = styled.section`
  width: 373px;
  padding: 24px;
  border: 1px solid gray;
  border-radius: 4px;
  margin-bottom: 24px;
  background-color: #ffffff;
`;

const S_Form = styled.form`
  display: flex;
  flex-direction: column;
  width: 373px;
`;

const S_SubmitButton = styled.button`
  background-color: #0a99ff;
  border: 0px;
  color: #ffffff;
  margin: 8px 0px;
  padding: 10.4px;

  &:hover {
    background-color: #3172c5;
    cursor: pointer;
  }
`;

const RegisterSuccess = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const { email, password } = location.state;

  const handleSubmit = (e) => {
    e.preventDefault();

    const body = { email, password };

    axios
      .post('/v1/auth/login', body)
      .then((response) => {
        if (response.status === 200) {
          navigate('/');
        }
      })
      .catch((error) =>
        console.log('회원가입은 성공했으나 로그인 실패', error)
      );
  };
  return (
    <S_Container>
      <Header />
      <S_Main>
        <h1>You&#39;re almost done!</h1>
        <p>
          You are about to create a new account on{' '}
          <strong>Stack Overflow</strong> using a login from{' '}
          <S_Svg size="18">
            <path d="M13 15v-3h1v4H3v-4h1v3h9Z" fill="#9EA3A9" />
            <path
              d="m10.02 2.73.91-.67 4.01 5.5-.8.62-4.12-5.45Zm3.01 6.65-5.2-4.21.78-.85 5.14 4.27-.72.79ZM6.12 8.1l6.19 2.74.45-.94L6.69 7l-.57 1.1Zm5.9 4.27L5.35 11.1l.21-1.11 6.6 1.42-.14.96ZM5 14h7v-1H5v1Z"
              fill="#F27009"
            />
          </S_Svg>
          Stack Exchange ({email})
        </p>
        <S_FormContainer>
          <S_Form onSubmit={handleSubmit}>
            <S_SubmitButton type="submit">Create my account</S_SubmitButton>
          </S_Form>
        </S_FormContainer>
      </S_Main>
    </S_Container>
  );
};

export default RegisterSuccess;
