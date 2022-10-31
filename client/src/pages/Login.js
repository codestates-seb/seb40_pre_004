import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Header from '../components/Header';
import OAuthButtons from '../components/OAuthButtons';
import LoginForm from '../components/LoginForm';

const S_Svg = styled.svg`
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;
`;

const S_Link = styled(Link)`
  text-decoration: none;
  color: #0074cc;

  &:hover {
    color: #0a95ff;
  }
`;

const S_OtherOption = styled.section`
  &:last-child {
    padding: 16px;
    font-size: 13px;
    text-align: center;
  }

  & > div:nth-child(2) {
    margin-top: 12px;
  }
`;

const OtherOption = () => {
  return (
    <S_OtherOption>
      <div>
        Don&apos;t have an account? <S_Link to="../register">Sign up</S_Link>
      </div>
      <div>
        Are you an employer? Sign up on Talent{' '}
        <S_Svg size="14">
          <path d="M5 1H3a2 2 0 0 0-2 2v8c0 1.1.9 2 2 2h8a2 2 0 0 0 2-2V9h-2v2H3V3h2V1Zm2 0h6v6h-2V4.5L6.5 9 5 7.5 9.5 3H7V1Z" />
        </S_Svg>
      </div>
    </S_OtherOption>
  );
};

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
  width: 316px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const S_LogoSvg = styled.svg`
  width: 32px;
  height: 37px;
  margin-bottom: 24px;
`;

const Login = () => {
  return (
    <S_Container>
      <Header />
      <S_Main>
        <S_LogoSvg>
          <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB" />
          <path
            d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
            fill="#F48024"
          />
        </S_LogoSvg>
        <OAuthButtons text="Log in" />
        <LoginForm />
        <OtherOption />
      </S_Main>
    </S_Container>
  );
};

export default Login;
