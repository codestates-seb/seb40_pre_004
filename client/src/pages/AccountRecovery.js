import styled from 'styled-components';
import Header from '../components/Header';
import useInput from '../hooks/useInput';
import { useState } from 'react';
import { validateEmail } from '../api/validate';

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

const S_EmailSentContainer = styled.section`
  width: 493px;
  padding: 16px;
  border: 1px solid #a6d9b7;
  background-color: #dbf0e2;
  display: flex;

  & > div {
    & > svg {
      margin: -4px 8px 0px 0px;
    }

    & > div:nth-child(1) {
      font-size: 17px;
      margin-bottom: 8px;
    }
  }
`;

const S_FormContainer = styled.section`
  width: 268px;
  padding: 24px;
  border: 1px solid gray;
  border-radius: 4px;
  margin-bottom: 24px;
  background-color: #ffffff;
`;

const S_Form = styled.form`
  display: flex;
  flex-direction: column;
  width: 268px;
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
    padding-right: ${(props) =>
      props.validationResult !== null && props.validationResult !== 'valid'
        ? '32px'
        : ''};
    border: ${(props) =>
      props.validationResult !== null && props.validationResult !== 'valid'
        ? '1px solid #d0393e'
        : ''};
  }

  & > svg {
    position: relative;
    top: -28px;
    right: -242px;
  }

  & > span {
    margin-top: -16px;
    color: #d0393e;
  }

  & > ul {
    color: #d0393e;
    padding-left: 30px;

    & > li {
      list-style-type: disc;
    }
  }
`;

const S_Svg = styled.svg`
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;
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

const EmailSent = ({ email }) => {
  return (
    <S_EmailSentContainer>
      <div>
        <S_Svg size="36">
          <path d="m6 14 8 8L30 6v8L14 30l-8-8v-8Z" fill="#3d8f58" />
        </S_Svg>
      </div>
      <div>
        <div>Account recovery email sent to {email}</div>
        <div>
          If you don&#39;t see this email in your inbox within 15 minutes, look
          for it in your junk mail folder. If you find it there, please mark it
          as “Not Junk”.
        </div>
      </div>
    </S_EmailSentContainer>
  );
};

const Form = ({ email, emailBind, setIsEmailSent }) => {
  // email 유효성 검사 결과
  const [emailValidationResult, setEmailValidationResult] = useState(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    setEmailValidationResult(validateEmail(email));

    if (validateEmail(email) === 'valid') {
      setIsEmailSent(true);
    }
  };
  return (
    <S_FormContainer>
      <S_Form onSubmit={handleSubmit}>
        <p>
          Forgot your account&#39;s password or having trouble logging into your
          Team? Enter your email address and we&#39;ll send you a recovery link.
        </p>
        <S_FormInputWrapper validationResult={emailValidationResult}>
          <label htmlFor="email">Email</label>
          <input type="text" id="email" {...emailBind} />
          {['empty', 'invalid'].includes(emailValidationResult) ? (
            <S_Svg size="18">
              <path
                d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"
                fill="#d0393e"
              />
            </S_Svg>
          ) : null}
          {emailValidationResult === 'empty' ? (
            <span>Email cannot be empty.</span>
          ) : null}
          {emailValidationResult === 'invalid' ? (
            <span>The email is not a valid email address.</span>
          ) : null}
        </S_FormInputWrapper>
        <S_SubmitButton type="submit">Send recovery email</S_SubmitButton>
      </S_Form>
    </S_FormContainer>
  );
};

const AccountRecovery = () => {
  const [email, emailBind] = useInput('');

  // 계정 복구 email 전송 여부
  const [isEmailSent, setIsEmailSent] = useState(false);

  return (
    <S_Container>
      <Header />
      <S_Main>
        {isEmailSent ? (
          <EmailSent email={email} />
        ) : (
          <Form
            email={email}
            emailBind={emailBind}
            setIsEmailSent={setIsEmailSent}
          />
        )}
      </S_Main>
    </S_Container>
  );
};

export default AccountRecovery;
