import axios from 'axios';
import styled from 'styled-components';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import useInput from '../hooks/useInput';
import useCheckbox from '../hooks/useCheckbox';
import {
  validateDisplayName,
  validateEmail,
  validatePasswordForRegister,
  validateIsHuman,
} from '../api/validate';
import { setRefreshToken } from '../storage/Cookie';
import { SET_TOKEN } from '../store/Auth';
import { useDispatch } from 'react-redux';

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

  & > span {
    width: 57px;
    color: #d0393e;
    margin-left: 3px;
  }
`;

const S_OptInContainer = styled.div`
  display: flex;
  align-items: flex-start;
  margin: 6px 0px;

  & > label {
    font-size: 12px;
    width: 233px;
    margin-left: 4px;
  }
`;

const S_Svg = styled.svg`
  width: ${(props) => props.size}px;
  height: ${(props) => props.size}px;
`;

const RegisterForm = () => {
  const [displayName, displayNameBind] = useInput('');
  const [email, emailBind] = useInput('');
  const [password, passwordBind] = useInput('');

  const [isHuman, isHumanBind] = useCheckbox(false);
  const [optIn, optInBind] = useCheckbox(false);

  // displayName 유효성 검사 결과
  const [displayNameValidationResult, setDisplayNameValidationResult] =
    useState(null);
  // email 유효성 검사 결과
  const [emailValidationResult, setEmailValidationResult] = useState(null);
  // password 유효성 검사 결과
  const [passwordValidationResult, setPasswordValidationResult] =
    useState(null);
  // isHuman 유효성 검사 결과
  const [isHumanValidationResult, setIsHumanValidationResult] = useState(null);

  const navigate = useNavigate();

  const dispatch = useDispatch();

  useEffect(() => {
    if (password.length >= 8) {
      setPasswordValidationResult(null);
    }
  }, [password]);

  const handleSubmit = (e) => {
    e.preventDefault();

    setDisplayNameValidationResult(validateDisplayName(displayName, email));
    setEmailValidationResult(validateEmail(email));
    setPasswordValidationResult(validatePasswordForRegister(password));
    setIsHumanValidationResult(validateIsHuman(isHuman));

    if (
      validateDisplayName(displayName, email) === 'valid' &&
      validateEmail(email) === 'valid' &&
      validatePasswordForRegister(password) === 'valid' &&
      validateIsHuman(isHuman) === 'valid'
    ) {
      const registerBody = {
        displayName,
        email,
        password,
        optIn,
      };

      axios
        .post('v1/members', registerBody)
        .then((response) => {
          if (response.status === 201) {
            navigate('/register-success', { state: { email, password } });
          }
        })
        .catch((error) => {
          if (error.response.status === 303) {
            const loginBody = {
              email,
              password,
            };

            switch (error.response.data.message) {
              case 'LOGIN_SUCCESS':
                axios
                  .post('/v1/auth/login', loginBody)
                  .then((response) => {
                    if (response.status === 200) {
                      const accessToken =
                        response.headers.authorization.slice(7);
                      const refreshToken = response.headers.refresh;
                      const memberId = response.data;
                      setRefreshToken(refreshToken);
                      dispatch(SET_TOKEN({ memberId, accessToken }));

                      navigate('/');
                    }
                  })
                  .catch((error) => console.log(error));
                break;
              case 'FIND_PASSWORD':
                navigate('/account-recovery');
                break;
              default:
                console.log(error);
            }
          } else {
            console.log('회원가입 실패', error);
          }
        });
    }
  };

  return (
    <S_FormContainer>
      <S_Form onSubmit={handleSubmit}>
        <S_FormInputWrapper validationResult={displayNameValidationResult}>
          <label htmlFor="display-name">Display name</label>
          <input type="text" id="display-name" {...displayNameBind} />
          {displayNameValidationResult === null ||
          displayNameValidationResult === 'valid' ? null : (
            <S_Svg size="18">
              <path
                d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"
                fill="#d0393e"
              />
            </S_Svg>
          )}
          {displayNameValidationResult === 'sameWithEmail' ? (
            <span>
              Name and email address must be different. If you don&apos;t want
              to enter a name, just leave it blank.
            </span>
          ) : null}
        </S_FormInputWrapper>
        <S_FormInputWrapper validationResult={emailValidationResult}>
          <label htmlFor="email">Email</label>
          <input type="text" id="email" {...emailBind} />
          {emailValidationResult === null ||
          emailValidationResult === 'valid' ? null : (
            <S_Svg size="18">
              <path
                d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"
                fill="#d0393e"
              />
            </S_Svg>
          )}
          {emailValidationResult === 'empty' ? (
            <span>Email cannot be empty.</span>
          ) : null}
          {emailValidationResult === 'invalid' ? (
            <span>{email} is not a valid email address.</span>
          ) : null}
        </S_FormInputWrapper>
        <S_FormInputWrapper validationResult={passwordValidationResult}>
          <label htmlFor="password">Password</label>
          <input type="password" id="password" {...passwordBind} />
          {passwordValidationResult === null ||
          passwordValidationResult === 'valid' ? null : (
            <S_Svg size="18">
              <path
                d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"
                fill="#d0393e"
              />
            </S_Svg>
          )}
          {passwordValidationResult === 'empty' ? (
            <span>Password cannot be empty.</span>
          ) : null}
          {passwordValidationResult === 'missingNumberAndLetter' ? (
            <>
              <span>
                Please add one of the following things to make your password
                stronger:
              </span>
              <ul>
                <li>letters</li>
                <li>numbers</li>
              </ul>
            </>
          ) : null}
          {passwordValidationResult === 'missingNumber' ? (
            <>
              <span>
                Please add one of the following things to make your password
                stronger:
              </span>
              <ul>
                <li>letters</li>
              </ul>
            </>
          ) : null}
          {passwordValidationResult === 'missingLetter' ? (
            <>
              <span>
                Please add one of the following things to make your password
                stronger:
              </span>
              <ul>
                <li>letters</li>
              </ul>
            </>
          ) : null}
          {passwordValidationResult === 'short' ? (
            <span>
              Must contain at least {8 - password.length} more characters.
            </span>
          ) : null}
          <p>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
        </S_FormInputWrapper>
        <S_RecaptchaContainer>
          <div>
            <div>
              <input type="checkbox" id="recaptcha" {...isHumanBind} />
              <label htmlFor="recaptcha">I&apos;m not a robot</label>
            </div>
          </div>
          {isHumanValidationResult === 'invalid' ? (
            <span>CAPTCHA response required.</span>
          ) : null}
        </S_RecaptchaContainer>
        <S_OptInContainer>
          <input type="checkbox" id="opt-in" {...optInBind} />
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
