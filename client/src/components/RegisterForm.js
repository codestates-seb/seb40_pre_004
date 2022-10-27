import styled from 'styled-components';
import { useState } from 'react';

const S_FormContainer = styled.section`
  width: 268px;
  padding: 24px;
  border: 1px solid gray;
  border-radius: 4px;
  margin-bottom: 24px;
  background-color: #ffffff;

  & > div {
    margin-top: 32px;
    font-size: 12px;
  }
`;

const S_Form = styled.form`
  display: flex;
  flex-direction: column;
  width: 268px;

  & > button {
    background-color: #0a99ff;
    border: 0px;
    color: #ffffff;
    margin: 8px 0px;
    padding: 10.4px;
  }

  & > button:hover {
    background-color: #3172c5;
    cursor: pointer;
  }
`;

const S_FormInputWrapper = styled.div`
  margin: 6px 0px;
  display: flex;
  flex-direction: column;

  & > label {
    font-size: 15px;
    font-weight: 500;
    margin: 2px 0px;
    padding: 0px 2px;
  }

  & > input {
    padding: 7.8px 9.1px;
  }

  & > p {
    margin: 4px 0px;
    font-size: 12px;
    color: #6a737c;
  }
`;

const S_RecaptchaContainer = styled.div`
  border: 1px solid gray;
  margin: 6px 0px;
  padding: 8px 0px;
  height: 144px;
  background-color: #f1f2f3;
  display: flex;
  justify-content: center;
  align-items: center;

  & > div {
    width: 156px;
    height: 136px;
    border: 1px solid gray;
    background-color: #f9f9f9;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 14px;

    & > div {
      display: flex;
      align-items: center;

      & > input {
        width: 24px;
        height: 24px;
      }

      & > label {
        padding-left: 12px;
      }
    }
  }
`;

const S_OptInContainer = styled.div`
  display: flex;
  align-items: flex-start;
  margin: 6px 0px;

  & > label {
    font-size: 12px;
    width: 233px;
  }
`;

const S_Svg = styled.svg`
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;
`;

const RegisterForm = () => {
  const [displayName, setDisplayName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleDisplayName = (e) => {
    setDisplayName(e.target.value);
  };

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };

  const handlePassword = (e) => {
    setPassword(e.target.value);
  };

  return (
    <S_FormContainer>
      <S_Form>
        <S_FormInputWrapper>
          <label htmlFor="display-name">Display name</label>
          <input
            type="text"
            id="display-name"
            value={displayName}
            onChange={handleDisplayName}
          />
        </S_FormInputWrapper>
        <S_FormInputWrapper>
          <label htmlFor="email">Email</label>
          <input type="text" id="email" value={email} onChange={handleEmail} />
        </S_FormInputWrapper>
        <S_FormInputWrapper>
          <label htmlFor="password">Password</label>
          <input
            type="text"
            id="password"
            value={password}
            onChange={handlePassword}
          />
          <p>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
        </S_FormInputWrapper>
        <S_RecaptchaContainer>
          <div>
            <div>
              <input type="checkbox" id="recaptcha" />
              <label htmlFor="recaptcha">I&apos;m not a robot</label>
            </div>
          </div>
        </S_RecaptchaContainer>
        <S_OptInContainer>
          <input type="checkbox" id="opt-in" />
          <label htmlFor="opt-in">
            Opt-in to receive occasional product updates, user research
            invitations, company announcements, and digests.
          </label>
          <S_Svg size="14">
            <path
              d="M7 1C3.74 1 1 3.77 1 7c0 3.26 2.77 6 6 6 3.27 0 6-2.73 6-6s-2.73-6-6-6Zm1.06 9.06c-.02.63-.48 1.02-1.1 1-.57-.02-1.03-.43-1.01-1.06.02-.63.5-1.04 1.08-1.02.6.02 1.05.45 1.03 1.08Zm.73-3.07-.47.3c-.2.15-.36.36-.44.6a3.6 3.6 0 0 0-.08.65c0 .04-.03.14-.16.14h-1.4c-.14 0-.16-.09-.16-.13-.01-.5.11-.99.36-1.42A4.6 4.6 0 0 1 7.7 6.07c.15-.1.21-.21.3-.33.18-.2.28-.47.28-.74.01-.67-.53-1.14-1.18-1.14-.9 0-1.18.7-1.18 1.46H4.2c0-1.17.31-1.92.98-2.36a3.5 3.5 0 0 1 1.83-.44c.88 0 1.58.16 2.2.62.58.42.88 1.02.88 1.82 0 .5-.17.9-.43 1.24-.15.2-.44.47-.86.79h-.01Z"
              fill="#6a737c"
            />
          </S_Svg>
        </S_OptInContainer>
        <button type="submit">Sign up</button>
      </S_Form>
      <div>
        By clicking “Sign up”, you agree to our terms of service, privacy policy
        and cookie policy
      </div>
    </S_FormContainer>
  );
};

export default RegisterForm;
