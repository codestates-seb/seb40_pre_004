import axios from 'axios';
import styled from 'styled-components';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { SET_TOKEN, DELETE_TOKEN } from '../store/Auth';
import { removeCookieToken } from '../storage/Cookie';

const S_Header = styled.header`
  box-shadow: 0 3px 3px rgba(0, 0, 0, 0.1);
  display: flex;
  background-color: #f8f9f9;
  color: #232629;
  border-top: 3.5px solid #f48225;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 9999;
`;

const S_TopbarContainer = styled.div`
  height: 40px;
  display: flex;
  margin: auto;
  align-items: center;
  padding: 3px;
`;

const S_TopbarLogo = styled.div`
  display: block;
  text-indent: -9999em;
  height: 30px;
  width: 146px;
  margin-left: 0;
  padding-top: 8px;
  background-position: 0 -500px;
  background-image: url(https://cdn.sstatic.net/Img/unified/sprites.svg?v=fcc0ea44ba27);
  &:hover {
    background-color: hsl(210, 8%, 90%);
  }
`;

const S_Nav = styled.ol`
  display: flex;
  justify-content: space-evenly;
  position: relative;
  a {
    display: flex;
    color: rgba(82, 89, 96);
    font-size: 13px;
    margin: 2px;
    padding: 6px 20px;
  }
  a:hover {
    border-radius: 1000px;
    color: black;
    background-color: hsl(210, 8%, 90%);
  }
`;

const S_NavDropdownWord = styled.li`
  position: relative;
  a:active {
    border-radius: 1000px;
    color: white;
    background-color: #f48225;
  }
`;

const S_NavDropdown = styled.div`
  position: absolute;
  z-index: 99999;
  left: -61px;
  top: 37px;
`;

const S_NavDropdownBox = styled.div`
  background-color: white;
  border-radius: 4px;
  width: 230px;
  left: 27px;
  top: 50px;
  box-shadow: 0 3px 3px rgb(0 0 0 / 10%);
`;

const S_NavDropdownitem = styled.li`
  box-sizing: border-box;
  line-height: 17px;
  margin: 6px;
  padding: 10px 7px 7px 7px;
  cursor: pointer;
  &:hover {
    background-color: rgb(186, 191, 196);
    border-radius: 4px;
  }
`;

const S_NavDropdownitem2 = styled(S_NavDropdownitem)`
  border-top: 1px solid rgb(186, 191, 196);
  margin: 0px;
  padding: 14px;
  background-color: #f8f9f9;
  border-radius: 4px;
  &:hover {
    background-color: #f8f9f9;
  }
  span:hover {
    color: #0c0c0c;
  }
`;

const S_NavDropdownword = styled.span`
  color: #0c0c0c;
  display: block;
`;

const S_NavDropdownword2 = styled.span`
  color: #6a737c;
  font-size: 12px;
`;

const S_TopbarArrow = styled.div`
  position: absolute;
  top: -4px;
  right: 110px;
  width: 0px;
  height: 0px;
  border-bottom: 10px solid white;
  border-left: 8px solid transparent;
  border-right: 8px solid transparent;
  filter: drop-shadow(2px -5px 10px rgb(186, 191, 196));
`;

const S_TopbarSearchLabel = styled.label`
  display: flex;
  position: relative;
  //809px
  width: 710px;
  height: 35px;
  border: 1px solid rgb(186, 191, 196);
  border-radius: 6px;
  &.login {
    width: 830px;
  }
  svg {
    display: inline-block;
    width: 1em;
    height: 1em;
    stroke-width: 0;
    position: absolute;
    top: 50%;
    left: 10px;
    transform: translateY(-50%);
  }
`;

const S_TopbarSearchInput = styled.input`
  display: inline-block;
  box-sizing: border-box;
  padding: 10px 30px;
  width: 100%;
  height: 35px;
  color: rgb(59, 64, 69);
  border: none;
  &:focus {
    box-shadow: rgb(0, 116, 204, 0.15) 0px 0px 0px 4px;
    outline: none;
    border-radius: 3px;
  }
`;

const S_TopbarInputWrap = styled.div`
  position: absolute;
  z-index: 99999;
  margin-top: 25px;
  box-shadow: 0 3px 3px rgb(0 0 0 / 10%);
`;

const S_TopbarInputDropdown = styled.div`
  background-color: white;
`;
const S_TopbarInputDropdownBox = styled.div`
  display: flex;
  width: 690px;
  height: 150px;
  padding: 9px 9px 0px 9px;
  font-size: 13px;
`;

const S_TopbarInputDropdownitem = styled.div`
  width: 400px;
  display: flex;
  margin-bottom: 10px;
  padding: 3px;
  box-sizing: border-box;
`;

const S_TopbarInputDropdownBox2 = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-top: 1px solid rgb(186, 191, 196);
  padding: 10px;
`;

const S_TopbarInputDropdownbtn = styled.a`
  background-color: rgb(225, 236, 244);
  border: 1px solid rgb(122, 167, 199);
  padding: 5px;
  border-radius: 4px;
  color: rgb(57, 115, 157);
  cursor: pointer;
  font-size: 12px;
  &:hover {
    background-color: hsl(205, 57%, 81%);
  }
`;

const S_TopbarInputDropdownword = styled.span`
  cursor: pointer;
  color: hsl(206, 100%, 40%);
  font-size: 11px;
  &:hover {
    color: hsl(206, 100%, 52%);
  }
`;

const S_TopbarInputArrow = styled(S_TopbarArrow)`
  top: -27px;
  right: 380px;
`;

const S_LinkBtn = styled(Link)`
  background-color: rgb(225, 236, 244);
  border: 1px solid rgb(122, 167, 199);
  display: inline-block;
  box-sizing: border-box;
  padding: 8px 10.4px 10.4px 8px;
  margin-left: 8px;
  border-radius: 3px;
  cursor: pointer;
  text-align: center;
  position: relative;
  font-size: 13px;
  color: rgb(57, 115, 157);
  box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 70%);
  &:hover {
    background-color: hsl(205, 57%, 81%);
  }
`;

const S_LinkBtn2 = styled(Link)`
  background-color: rgb(10, 149, 255);
  border: 1px solid white;
  border-radius: 3px;
  display: inline-block;
  box-sizing: border-box;
  padding: 8px 10.4px 10.4px 8px;
  margin: 0px 0px 0px 4px;
  cursor: pointer;
  text-align: center;
  position: relative;
  font-size: 13px;
  color: white;
  box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
`;

const S_TopbarLoggedInNav = styled.nav`
  height: 100%;
  display: block;
  box-sizing: border-box;
`;

const S_TopbarLoggedInOl = styled.ol`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100%;
  padding-inline-start: 40px;
  margin-top: 0px;
`;

const S_TopbarLoggedInItem = styled.li`
  width: 20px;
  height: 16px;
  cursor: pointer;
  padding: 15px 10px 15px 10px;
  &:hover {
    background-color: hsl(210, 8%, 90%);
  }
`;

const S_TopbarLoggedInDropdown = styled.div`
  box-sizing: border-box;
  width: 375px;
  position: absolute;
  box-sizing: border-box;
  border-left: 1px solid hsl(210, 8%, 90%);
  border-right: 1px solid hsl(210, 8%, 90%);
  border-bottom: 1px solid hsl(210, 8%, 90%);
  right: 297px;
  top: 47px;
  border: none;
  box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
    0 2px 8px hsla(0, 0%, 0%, 0.05);
`;

const S_TopbarLoggedInDropdownItem = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 10px;
  background-color: hsl(210, 8%, 95%);
  padding: 8px 10px;
  width: 100%;
  position: relative;
  cursor: pointer;
  color: #0074cc;
  font-weight: bold;
  font-size: 12px;
  div:hover {
    color: hsl(206, 100%, 52%);
  }
`;

const S_TopbarLoggedInDropdownItem2 = styled(S_TopbarLoggedInDropdownItem)`
  padding: 10px;
  background-color: white;
  &:hover {
    background-color: hsl(205, 46%, 92%);
  }
`;

const S_TopbarLoggedInDropdownWord = styled.div`
  font-size: 13px;
  color: #0074cc;
`;

const S_TopbarLoggedInDropdownWord2 = styled.div`
  font-weight: normal;
  font-size: 13px;
`;

const S_TopbarLoggedInDropdownSvg = styled.svg`
  width: 16px;
  height: 16px;
  margin-right: 7px;
`;

function Header() {
  const [view, setView] = useState(false);
  const [down, setDown] = useState(false);
  const [drop, setDrop] = useState(false);

  const dispatch = useDispatch();
  const { authenticated, accessToken, expireTime } = useSelector(
    (state) => state.authToken
  );

  useEffect(() => {
    // access token 재발급
    if (accessToken === null || new Date().getTime() - expireTime < 30 * 1000) {
      axios
        .get('/url-is-not-defined')
        .then((response) => {
          if (response.status === 201) {
            const accessToken = response.headers.authorization.slice(7);
            const memberId = response.data;
            dispatch(SET_TOKEN({ memberId, accessToken }));
          } else {
            console.log(response);
          }
        })
        .catch((error) => {
          if (error.response.status === 403) {
            dispatch(DELETE_TOKEN());
            removeCookieToken();
          } else {
            console.log(
              '백엔드 access token 재발급 로직이 구현되지 않아서 발생하는 오류입니다.'
            );
          }
        });
    }
  }, []);

  return (
    <S_Header>
      <S_TopbarContainer>
        <Link to="/">
          <S_TopbarLogo>스택오버플로우</S_TopbarLogo>
        </Link>
        <S_Nav>
          {!authenticated && (
            <li>
              <a href="#;">About</a>
            </li>
          )}
          <S_NavDropdownWord>
            <a
              href="#;"
              onClick={() => {
                setView(!view);
              }}
            >
              Products
            </a>
            {view && <Dropdown />}
          </S_NavDropdownWord>
          {!authenticated && (
            <li>
              <a href="#;">For Teams</a>
            </li>
          )}
        </S_Nav>
        <div>
          <S_TopbarSearchLabel className={authenticated ? 'login' : ''}>
            <svg aria-hidden="true" viewBox="0 0 18 18">
              <path
                d="m18 16.5-5.14-5.18h-.35a7 7 0 1 0-1.19 1.19v.35L16.5 18l1.5-1.5ZM12 7A5 5 0 1 1 2 7a5 5 0 0 1 10 0Z"
                fill="rgba(59, 64, 69)"
              ></path>
            </svg>
            <S_TopbarSearchInput
              type="text"
              placeholder="Search..."
              maxLength="240"
              aria-controls="top-search"
              onClick={() => {
                setDown(!down);
              }}
            ></S_TopbarSearchInput>
          </S_TopbarSearchLabel>
          {down && <Dropdown2 />}
        </div>
        {authenticated ? (
          <S_TopbarLoggedInNav>
            <S_TopbarLoggedInOl>
              <S_TopbarLoggedInItem>
                <svg aria-hidden="true" viewBox="0 0 20 18">
                  <path
                    d="M4.63 1h10.56a2 2 0 0 1 1.94 1.35L20 10.79V15a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-4.21l2.78-8.44c.25-.8 1-1.36 1.85-1.35Zm8.28 12 2-2h2.95l-2.44-7.32a1 1 0 0 0-.95-.68H5.35a1 1 0 0 0-.95.68L1.96 11h2.95l2 2h6Z"
                    fill="rgba(59, 64, 69)"
                  ></path>
                </svg>
              </S_TopbarLoggedInItem>
              <S_TopbarLoggedInItem>
                <svg aria-hidden="true" viewBox="0 0 18 18">
                  <path
                    d="M15 2V1H3v1H0v4c0 1.6 1.4 3 3 3v1c.4 1.5 3 2.6 5 3v2H5s-1 1.5-1 2h10c0-.4-1-2-1-2h-3v-2c2-.4 4.6-1.5 5-3V9c1.6-.2 3-1.4 3-3V2h-3ZM3 7c-.5 0-1-.5-1-1V4h1v3Zm8.4 2.5L9 8 6.6 9.4l1-2.7L5 5h3l1-2.7L10 5h2.8l-2.3 1.8 1 2.7h-.1ZM16 6c0 .5-.5 1-1 1V4h1v2Z"
                    fill="rgba(59, 64, 69)"
                  ></path>
                </svg>
              </S_TopbarLoggedInItem>
              <S_TopbarLoggedInItem>
                <svg aria-hidden="true" viewBox="0 0 18 18">
                  <path
                    d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8Zm.81 12.13c-.02.71-.55 1.15-1.24 1.13-.66-.02-1.17-.49-1.15-1.2.02-.72.56-1.18 1.22-1.16.7.03 1.2.51 1.17 1.23ZM11.77 8c-.59.66-1.78 1.09-2.05 1.97a4 4 0 0 0-.09.75c0 .05-.03.16-.18.16H7.88c-.16 0-.18-.1-.18-.15.06-1.35.66-2.2 1.83-2.88.39-.29.7-.75.7-1.24.01-1.24-1.64-1.82-2.35-.72-.21.33-.18.73-.18 1.1H5.75c0-1.97 1.03-3.26 3.03-3.26 1.75 0 3.47.87 3.47 2.83 0 .57-.2 1.05-.48 1.44Z"
                    fill="rgba(59, 64, 69)"
                  ></path>
                </svg>
              </S_TopbarLoggedInItem>
              <S_TopbarLoggedInItem
                onClick={() => {
                  setDrop(!drop);
                }}
              >
                <svg aria-hidden="true" viewBox="0 0 18 18">
                  <path
                    d="M15 1H3a2 2 0 0 0-2 2v2h16V3a2 2 0 0 0-2-2ZM1 13c0 1.1.9 2 2 2h8v3l3-3h1a2 2 0 0 0 2-2v-2H1v2Zm16-7H1v4h16V6Z"
                    fill="rgba(59, 64, 69)"
                  ></path>
                </svg>
              </S_TopbarLoggedInItem>
            </S_TopbarLoggedInOl>
            {drop && <Dropdown3 />}
          </S_TopbarLoggedInNav>
        ) : (
          <>
            <S_LinkBtn to="/login">Log in</S_LinkBtn>
            <S_LinkBtn2 to="/register">Sign up</S_LinkBtn2>
          </>
        )}
      </S_TopbarContainer>
    </S_Header>
  );
}

function Dropdown() {
  const obj = {
    data: [
      { word: 'Stack Overflow', word2: 'Public questions & answers' },
      {
        word: 'Stack Overflow for Teams',
        word2:
          'Where developers & technologists share private knowledge with coworkers',
      },
      { word: 'Talent', word2: 'Build your employer brand' },
      {
        word: 'Advertising',
        word2: 'Reach developers & technologists worldwide',
      },
    ],
  };

  return (
    <S_NavDropdown>
      <S_TopbarArrow />
      <S_NavDropdownBox>
        <ol>
          {obj.data.map((item, index) => {
            return (
              <S_NavDropdownitem key={index}>
                <S_NavDropdownword>{item.word}</S_NavDropdownword>
                <S_NavDropdownword2>{item.word2}</S_NavDropdownword2>
              </S_NavDropdownitem>
            );
          })}
          <S_NavDropdownitem2>
            <S_NavDropdownword2>About the company</S_NavDropdownword2>
          </S_NavDropdownitem2>
        </ol>
      </S_NavDropdownBox>
    </S_NavDropdown>
  );
}

function Dropdown2() {
  const obj1 = {
    data: [
      { word: '[ tag ]', word2: 'search within a tag' },
      {
        word: 'user : 1234',
        word2: 'search by author',
      },
      { word: '"words here"', word2: 'exact phrase' },
      {
        word: 'collective : "Name"',
        word2: 'collective content',
      },
    ],
  };

  const obj2 = {
    data: [
      { word: 'answers : 0', word2: 'unanswered questions' },
      {
        word: 'score : 3',
        word2: 'posts with a 3+ score',
      },
      { word: 'is : question', word2: 'type of post' },
      {
        word: 'isaccepted : yes',
        word2: 'search within status',
      },
    ],
  };

  return (
    <S_TopbarInputWrap>
      <S_TopbarInputArrow />
      <S_TopbarInputDropdown>
        <S_TopbarInputDropdownBox>
          <div>
            {obj1.data.map((item, index) => {
              return (
                <S_TopbarInputDropdownitem key={index}>
                  <S_NavDropdownword>{item.word}&nbsp;</S_NavDropdownword>
                  <S_NavDropdownword2>{item.word2}</S_NavDropdownword2>
                </S_TopbarInputDropdownitem>
              );
            })}
          </div>
          <div>
            {obj2.data.map((item, index) => {
              return (
                <S_TopbarInputDropdownitem key={index}>
                  <S_NavDropdownword>{item.word}&nbsp;</S_NavDropdownword>
                  <S_NavDropdownword2>{item.word2}</S_NavDropdownword2>
                </S_TopbarInputDropdownitem>
              );
            })}
          </div>
        </S_TopbarInputDropdownBox>
        <S_TopbarInputDropdownBox2>
          <S_TopbarInputDropdownbtn>Ask a question</S_TopbarInputDropdownbtn>
          <S_TopbarInputDropdownword>Search help</S_TopbarInputDropdownword>
        </S_TopbarInputDropdownBox2>
      </S_TopbarInputDropdown>
    </S_TopbarInputWrap>
  );
}

function Dropdown3() {
  const dispatch = useDispatch();

  const logOutHandler = () => {
    dispatch(DELETE_TOKEN());
    removeCookieToken();
  };

  return (
    <S_TopbarLoggedInDropdown>
      <S_TopbarLoggedInDropdownItem>
        CURRENT COMMUNITY
      </S_TopbarLoggedInDropdownItem>
      <S_TopbarLoggedInDropdownItem2>
        <Link to="/">
          <S_TopbarLoggedInDropdownWord>
            <S_TopbarLoggedInDropdownSvg aria-hidden="true" viewBox="0 0 32 37">
              <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB"></path>
              <path
                d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
                fill="#F48024"
              ></path>
            </S_TopbarLoggedInDropdownSvg>
            Stack Overflow
          </S_TopbarLoggedInDropdownWord>
        </Link>
        <S_TopbarLoggedInDropdownWord2 onClick={logOutHandler}>
          log out
        </S_TopbarLoggedInDropdownWord2>
      </S_TopbarLoggedInDropdownItem2>
      <S_TopbarLoggedInDropdownItem2>
        <S_TopbarLoggedInDropdownWord>
          Delete My Account
        </S_TopbarLoggedInDropdownWord>
      </S_TopbarLoggedInDropdownItem2>
    </S_TopbarLoggedInDropdown>
  );
}

export default Header;
