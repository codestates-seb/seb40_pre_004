import axios from 'axios';
import styled from 'styled-components';
import useInput from '../hooks/useInput';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { validateEmail, validatePasswordForLogin } from '../api/validate';
import { setRefreshToken } from '../storage/Cookie';
import { useDispatch } from 'react-redux';
import { SET_TOKEN } from '../store/Auth';

const S_FormContainer = styled.section`
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI Adjusted',
    'Segoe UI', 'Liberation Sans', sans-serif;
  color: #232629;
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

const LoginForm = () => {
  const [email, emailBind] = useInput('');
  const [password, passwordBind] = useInput('');

  // email 유효성 검사 결과
  const [emailValidationResult, setEmailValidationResult] = useState(null);
  // password 유효성 검사 결과
  const [passwordValidationResult, setPasswordValidationResult] =
    useState(null);

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();

    setEmailValidationResult(validateEmail(email));
    setPasswordValidationResult(validatePasswordForLogin(password));

    const body = {
      email,
      password,
    };

    if (
      validateEmail(email) === 'valid' &&
      validatePasswordForLogin(password) === 'valid'
    ) {
      axios
        .post('/v1/members/login', body)
        .then((response) => {
          if (response.status === 200) {
            const accessToken = response.headers.authorization;
            const refreshToken = response.headers.refresh;
            const memberId = response.data.data;
            setRefreshToken(refreshToken);
            dispatch(SET_TOKEN({ memberId, accessToken }));

            navigate('/');
          }
        })
        .catch((error) => {
          if (error.response.status === 401) {
            setEmailValidationResult('incorrect');
          }
        });
    }
  };

  return (
    <S_FormContainer>
      <S_Form onSubmit={handleSubmit}>
        <S_FormInputWrapper validationResult={emailValidationResult}>
          <label htmlFor="email">Email</label>
          <input type="text" id="email" {...emailBind} />
          {['empty', 'invalid', 'incorrect'].includes(emailValidationResult) ? (
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
          {emailValidationResult === 'incorrect' ? (
            <span>The email or password is incorrect.</span>
          ) : null}
        </S_FormInputWrapper>
        <S_FormInputWrapper validationResult={passwordValidationResult}>
          <label htmlFor="password">Password</label>
          <input type="password" id="password" {...passwordBind} />
          {passwordValidationResult === 'empty' ? (
            <>
              <S_Svg size="18">
                <path
                  d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"
                  fill="#d0393e"
                />
              </S_Svg>
              <span>Password cannot be empty.</span>
            </>
          ) : null}
        </S_FormInputWrapper>
        <S_SubmitButton type="submit">Log in</S_SubmitButton>
      </S_Form>
    </S_FormContainer>
  );
};

export default LoginForm;
