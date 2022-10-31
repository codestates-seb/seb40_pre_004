import styled from 'styled-components';
import { useState } from 'react';
import { Link } from 'react-router-dom';

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
  background-position: 0 -500px;
  background-image: url(https://cdn.sstatic.net/Img/unified/sprites.svg?v=fcc0ea44ba27);
  &: hover {
    background-color: hsl(210, 8%, 90%);
  }
`;

const S_Navigation = styled.ol`
  display: flex;
  width: 290px;
  justify-content: space-evenly;
  position: relative;
  a {
    display: flex;
    align-items: center;
    border: none;
    color: rgba(82, 89, 96);
    font-size: 13px;
    margin: 2px;
    padding: 6px 12px;
  }
  a:hover {
    border-radius: 1000px;
    color: black;
    background-color: hsl(210, 8%, 90%);
  }
`;

const S_NavigationDropdown = styled.li`
  a:hover {
    border-radius: 1000px;
    color: white;
    background-color: #f48225;
  }
`;

const S_NavigationDropdownBox = styled.div`
  position: absolute;
  z-index: 99999;
  background-color: white;
  border-radius: 4px;
  width: 230px;
  left: 27px;
  top: 50px;
  box-shadow: 0 3px 3px rgb(0 0 0 / 10%);
`;

const S_NavigationDropdownitem = styled.li`
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
const S_NavigationDropdownitem2 = styled(S_NavigationDropdownitem)`
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
const S_NavigationDropdownword = styled.span`
  color: #0c0c0c;
  display: block;
`;

const S_NavigationDropdownword2 = styled.span`
  color: #6a737c;
  font-size: 12px;
`;

const S_TopbarArrow = styled.div`
  position: absolute;
  top: 35px;
  right: 145px;
  width: 0px;
  height: 0px;
  border-bottom: 15px solid white;
  border-left: 10px solid transparent;
  border-right: 10px solid transparent;
  filter: drop-shadow(2px -5px 10px rgb(186, 191, 196));
`;

const S_TopbarSearchLabel = styled.label`
  display: flex;
  z-index: 1;
  position: relative;
  width: 710px;
  height: 35px;
  border: 1px solid rgb(186, 191, 196);
  border-radius: 5px;

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
  z-index: -1;
  color: rgb(59, 64, 69);
  border: none;
  &:focus {
    box-shadow: rgb(0, 116, 204, 0.15) 0px 0px 0px 4px;
    outline: none;
    border-radius: 3px;
  }
`;
const S_TopbarInputDropdown = styled.div`
  position: absolute;
  top: 60px;
  box-shadow: 0 3px 3px rgb(0 0 0 / 10%);
  border-radius: 4px;
  background-color: white;
`;
const S_TopbarInputDropdownBox = styled.div`
  display: flex;
  width: 690px;
  height: 150px;
  padding: 9px 9px 0px 9px;
  font-size: 13px;
  z-index: 99999;
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
  border-radius: 0 0 4px 4px;
  border-top: 1px solid rgb(186, 191, 196);
  padding: 10px;
`;

const S_TopbarInputDropdownbtn = styled.a`
  background-color: rgb(225, 236, 244);
  border: 1px solid rgb(122, 167, 199);
  padding: 4px;
  border-radius: 4px;
  color: rgb(57, 115, 157);
  cursor: pointer;
`;

const S_TopbarInputDropdownword = styled.span`
  cursor: pointer;
  color: hsl(206, 100%, 40%);

  &:hover {
    color: hsl(206, 100%, 52%);
  }
`;

const S_TopbarInputArrow = styled(S_TopbarArrow)`
  top: 45px;
  right: 700px;
`;

const S_TopbarBnt1 = styled.a`
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

const S_TopbarBnt2 = styled.a`
  background-color: rgb(10, 149, 255);
  border: 1px solid white;
  display: inline-block;
  box-sizing: border-box;
  padding: 8px 10.4px 10.4px 8px;
  margin: 0px 0px 0px 4px;
  border-radius: 3px;
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

function Header() {
  const [view, setView] = useState(false);
  const [down, setDown] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  function onToggle() {
    setIsLoggedIn(!isLoggedIn);
  }

  return (
    <S_Header>
      <S_TopbarContainer>
        <Link to="/">
          <S_TopbarLogo>스택오버플로우</S_TopbarLogo>
        </Link>
        <S_Navigation>
          <li>
            <a href="#;">About</a>
          </li>
          <S_NavigationDropdown>
            <a
              href="#;"
              onClick={() => {
                setView(!view);
              }}
            >
              Products
            </a>
            {view && <Dropdown />}
          </S_NavigationDropdown>
          <li>
            <a href="#;">For Teams</a>
          </li>
        </S_Navigation>
        <div>
          <S_TopbarSearchLabel>
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
        <Link to="/login">
          <S_TopbarBnt1>Log in</S_TopbarBnt1>
        </Link>
        <Link to="/register">
          <S_TopbarBnt2>Sign up</S_TopbarBnt2>{' '}
        </Link>
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
    <div>
      <S_TopbarArrow />
      <S_NavigationDropdownBox>
        <ol>
          {obj.data.map((item, index) => {
            return (
              <S_NavigationDropdownitem key={index}>
                <S_NavigationDropdownword>{item.word}</S_NavigationDropdownword>
                <S_NavigationDropdownword2>
                  {item.word2}
                </S_NavigationDropdownword2>
              </S_NavigationDropdownitem>
            );
          })}
          <S_NavigationDropdownitem2>
            <S_NavigationDropdownword2>
              About the company
            </S_NavigationDropdownword2>
          </S_NavigationDropdownitem2>
        </ol>
      </S_NavigationDropdownBox>
    </div>
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
    <div>
      <S_TopbarInputArrow />
      <S_TopbarInputDropdown>
        <S_TopbarInputDropdownBox>
          <div>
            {obj1.data.map((item, index) => {
              return (
                <S_TopbarInputDropdownitem key={index}>
                  <S_NavigationDropdownword>
                    {item.word}&nbsp;
                  </S_NavigationDropdownword>
                  <S_NavigationDropdownword2>
                    {item.word2}
                  </S_NavigationDropdownword2>
                </S_TopbarInputDropdownitem>
              );
            })}
          </div>
          <div>
            {obj2.data.map((item, index) => {
              return (
                <S_TopbarInputDropdownitem key={index}>
                  <S_NavigationDropdownword>
                    {item.word}&nbsp;
                  </S_NavigationDropdownword>
                  <S_NavigationDropdownword2>
                    {item.word2}
                  </S_NavigationDropdownword2>
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
    </div>
  );
}
export default Header;
