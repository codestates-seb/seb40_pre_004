import styled from 'styled-components';
import { Link } from 'react-router-dom';
import RegisterForm from '../components/RegisterForm';
import Header from '../components/Header';

const S_Svg = styled.svg`
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;
`;

const S_DescriptionContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  & > section {
    margin: 0px 48px 128px 0px;
    width: 410.891px;
    height: 285px;

    & > h1 {
      font-size: 27px;
      font-weight: 400;
    }

    & > ul {
      list-style: none;
      padding: 0px;

      & > li {
        display: flex;
        margin-bottom: 24px;
        font-size: 15px;

        & > svg {
          margin: -2px 8px 0px 0px;
          fill: #0a99ff;
        }
      }
    }

    & > div {
      font-size: 13px;
    }
  }
`;

const Description = () => {
  return (
    <S_DescriptionContainer>
      <section>
        <h1>Join the Stack Overflow community</h1>
        <ul>
          <li>
            <S_Svg size="26">
              <path
                opacity="0.5"
                d="M4.2 4H22a2 2 0 012 2v11.8a3 3 0 002-2.8V5a3 3 0 00-3-3H7a3 3 0 00-2.8 2z"
              />
              <path d="M1 7c0-1.1.9-2 2-2h18a2 2 0 012 2v12a2 2 0 01-2 2h-2v5l-5-5H3a2 2 0 01-2-2V7zm10.6 11.3c.7 0 1.2-.5 1.2-1.2s-.5-1.2-1.2-1.2c-.6 0-1.2.4-1.2 1.2 0 .7.5 1.1 1.2 1.2zm2.2-5.4l1-.9c.3-.4.4-.9.4-1.4 0-1-.3-1.7-1-2.2-.6-.5-1.4-.7-2.4-.7-.8 0-1.4.2-2 .5-.7.5-1 1.4-1 2.8h1.9v-.1c0-.4 0-.7.2-1 .2-.4.5-.6 1-.6s.8.1 1 .4a1.3 1.3 0 010 1.8l-.4.3-1.4 1.3c-.3.4-.4 1-.4 1.6 0 0 0 .2.2.2h1.5c.2 0 .2-.1.2-.2l.1-.7.5-.7.6-.4z"></path>
            </S_Svg>
            <span>Get unstuck — ask a question</span>
          </li>
          <li>
            <S_Svg size="26">
              <path d="M12 .7a2 2 0 013 0l8.5 9.6a1 1 0 01-.7 1.7H4.2a1 1 0 01-.7-1.7L12 .7z" />
              <path
                opacity="0.5"
                d="M20.6 16H6.4l7.1 8 7-8zM15 25.3a2 2 0 01-3 0l-8.5-9.6a1 1 0 01.7-1.7h18.6a1 1 0 01.7 1.7L15 25.3z"
              />
            </S_Svg>
            <span>Unlock new privileges like voting and commenting</span>
          </li>
          <li>
            <S_Svg size="26">
              <path d="M14.8 3a2 2 0 00-1.4.6l-10 10a2 2 0 000 2.8l8.2 8.2c.8.8 2 .8 2.8 0l10-10c.4-.4.6-.9.6-1.4V5a2 2 0 00-2-2h-8.2zm5.2 7a2 2 0 110-4 2 2 0 010 4z" />
              <path
                opacity=".5"
                d="M13 0a2 2 0 00-1.4.6l-10 10a2 2 0 000 2.8c.1-.2.3-.6.6-.8l10-10a2 2 0 011.4-.6h9.6a2 2 0 00-2-2H13z"
              />
            </S_Svg>
            <span>Save your favorite tags, filters, and jobs</span>
          </li>
          <li>
            <S_Svg size="26">
              <path d="M21 4V2H5v2H1v5c0 2 2 4 4 4v1c0 2.5 3 4 7 4v3H7s-1.2 2.3-1.2 3h14.4c0-.6-1.2-3-1.2-3h-5v-3c4 0 7-1.5 7-4v-1c2 0 4-2 4-4V4h-4zM5 11c-1 0-2-1-2-2V6h2v5zm11.5 2.7l-3.5-2-3.5 1.9L11 9.8 7.2 7.5h4.4L13 3.8l1.4 3.7h4L15.3 10l1.4 3.7h-.1zM23 9c0 1-1 2-2 2V6h2v3z" />
            </S_Svg>
            <span>Earn reputation and badges</span>
          </li>
        </ul>
        <div>
          Collaborate and share knowledge with a private group for FREE.
          <br />
          Get Stack Overflow for Teams free for up to 50 users.
        </div>
      </section>
    </S_DescriptionContainer>
  );
};

const S_OAuthContainer = styled.section`
  margin: -8px 0px 20px 0px;
  width: 100%;
  padding-top: 80px;
  & > button:nth-child(1) {
    background-color: #ffffff;

    &:hover {
      background-color: #f8f9f9;
    }
  }

  & > button:nth-child(2) {
    background-color: #2f3337;
    color: #ffffff;

    &:hover {
      background-color: #242629;
    }
  }

  & > button:nth-child(3) {
    background-color: #385399;
    color: #ffffff;

    &:hover {
      background-color: #314b86;
    }
  }
`;

const S_OAuthButton = styled.button`
  width: 100%;
  height: 37.781px;
  margin: 8px 0px;
  padding: 10.4px;
  border: 1px solid gray;
  border-radius: 4px;
  display: flex;
  justify-content: center;
  align-items: stretch;
  cursor: pointer;

  & > svg {
    margin-right: 4px;
  }
`;

const OAuth = () => {
  return (
    <S_OAuthContainer>
      <S_OAuthButton>
        <S_Svg size="18">
          <path
            d="M16.51 8H8.98v3h4.3c-.18 1-.74 1.48-1.6 2.04v2.01h2.6a7.8 7.8 0 0 0 2.38-5.88c0-.57-.05-.66-.15-1.18Z"
            fill="#4285F4"
          />
          <path
            d="M8.98 17c2.16 0 3.97-.72 5.3-1.94l-2.6-2a4.8 4.8 0 0 1-7.18-2.54H1.83v2.07A8 8 0 0 0 8.98 17Z"
            fill="#34A853"
          />
          <path
            d="M4.5 10.52a4.8 4.8 0 0 1 0-3.04V5.41H1.83a8 8 0 0 0 0 7.18l2.67-2.07Z"
            fill="#FBBC05"
          />
          <path
            d="M8.98 4.18c1.17 0 2.23.4 3.06 1.2l2.3-2.3A8 8 0 0 0 1.83 5.4L4.5 7.49a4.77 4.77 0 0 1 4.48-3.3Z"
            fill="#EA4335"
          />
        </S_Svg>
        Sign up with Google
      </S_OAuthButton>
      <S_OAuthButton>
        <S_Svg size="18">
          <path
            d="M9 1a8 8 0 0 0-2.53 15.59c.4.07.55-.17.55-.38l-.01-1.49c-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82a7.42 7.42 0 0 1 4 0c1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48l-.01 2.2c0 .21.15.46.55.38A8.01 8.01 0 0 0 9 1Z"
            fill="#fefefe"
          />
        </S_Svg>
        Sign up with Github
      </S_OAuthButton>
      <S_OAuthButton>
        <S_Svg size="18">
          <path
            d="M3 1a2 2 0 0 0-2 2v12c0 1.1.9 2 2 2h12a2 2 0 0 0 2-2V3a2 2 0 0 0-2-2H3Zm6.55 16v-6.2H7.46V8.4h2.09V6.61c0-2.07 1.26-3.2 3.1-3.2.88 0 1.64.07 1.87.1v2.16h-1.29c-1 0-1.19.48-1.19 1.18V8.4h2.39l-.31 2.42h-2.08V17h-2.5Z"
            fill="#ffffff"
          />
        </S_Svg>
        Sign up with Facebook
      </S_OAuthButton>
    </S_OAuthContainer>
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

const S_Link = styled(Link)`
  text-decoration: none;
  color: #0074cc;

  &:hover {
    color: #0a95ff;
  }
`;

const OtherOption = () => {
  return (
    <section>
      <div>
        Already have an account? <S_Link to="../login">Log in</S_Link>
      </div>
      <div>
        Are you an employer? Sign up on Talent{' '}
        <S_Svg size="14">
          <path d="M5 1H3a2 2 0 0 0-2 2v8c0 1.1.9 2 2 2h8a2 2 0 0 0 2-2V9h-2v2H3V3h2V1Zm2 0h6v6h-2V4.5L6.5 9 5 7.5 9.5 3H7V1Z" />
        </S_Svg>
      </div>
    </section>
  );
};

const S_Main = styled.main`
  display: flex;
`;

const S_SignUpContainer = styled.div`
  width: 316px;

  & > section:last-child {
    padding: 16px;
    margin-bottom: 24px;
    font-size: 13px;
    text-align: center;

    & > div:nth-child(2) {
      margin-top: 12px;
    }
  }
`;

const Register = () => {
  return (
    <S_Container>
      <Header />
      <S_Main>
        <Description />
        <S_SignUpContainer>
          <OAuth />
          <RegisterForm />
          <OtherOption />
        </S_SignUpContainer>
      </S_Main>
    </S_Container>
  );
};

export default Register;
