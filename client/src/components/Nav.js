import { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const S_LeftSidebar = styled.div`
  width: 164px;
  flex-shrink: 0;
  box-shadow: 0 0 0 hsl(210deg 8% 5% / 5%);
  transition: box-shadow ease-in-out 0.1s, transform ease-in-out 0.1s;
  transform: translateZ(0);
  position: relative !important;
  z-index: 10;
`;

const S_SidebarContainer = styled.div`
  position: sticky;
  width: auto;
  margin-bottom: 0 0 8px;
  padding-top: 24px;
  max-height: 100vh;
  overflow-y: auto;
  top: 50px;
  max-height: calc(100vh - var(50px));

  width: 164px;
`;

const S_Ol = styled.ol`
  padding: 0;
  margin: 0 0 12px;
`;

const S_Li = styled.li`
  position: relative !important;
  a {
    display: block;
    padding: 4px;
    line-height: 2;
    &.sub {
      display: flex;
      //수정
      padding: 8px 6px 8px 6px;
      color: hsl(210, 8%, 35%);
      svg {
        flex-shrink: 0;
        margin-top: -1px;
        margin-right: 4px;
        fill: hsl(210, 8%, 55%);
        vertical-align: bottom;
        &:hover {
          fill: #222;
        }
        &.orange {
          fill: hsl(27, 90%, 55%);
        }
      }
      span {
        line-height: 15px;
      }
      &.inSub {
        padding-left: 30px;
      }
    }
    &:hover {
      color: #222;
    }
  }
  &.active {
    border-right: 3px solid hsl(27deg 90% 55%);
  }
  &.active a {
    font-weight: bold;
    background-color: hsl(210, 8%, 95%);
    color: #222;
  }
`;

// PUBLIC Li
const S_LiText = styled.li`
  text-transform: uppercase !important;
  color: hsl(210, 8%, 45%) !important;
  font-size: 11px;
  // 수정
  margin-left: 4px !important;
  margin-bottom: 4px !important;
  margin-top: 16px !important;
  &.collect {
    display: flex !important;
    justify-content: space-between !important;
    a {
      margin-right: 8px;
      svg {
        fill: hsl(210, 8%, 45%);
      }
    }
  }
`;

const S_LiFlex = styled.div`
  display: flex !important;
  align-items: center !important;
  div {
    overflow: hidden;
    max-width: 100%;
    text-overflow: ellipsis !important;
    white-space: nowrap;
  }
`;

const S_TeamsAd = styled.div`
  overflow: hidden;
  padding: 12px 12px 6px !important;
  border-color: hsl(210, 8%, 90%);
  border-top-left-radius: 3px;
  border-bottom-left-radius: 3px;
  border-left-style: solid !important;
  border-left-width: 1px !important;
  border-bottom-style: solid !important;
  border-bottom-width: 1px !important;
  border-top-style: solid !important;
  border-top-width: 1px !important;
  strong {
    margin-bottom: 6px !important;
    color: hsl(210, 8%, 20%);
    font-weight: bold;
  }
  img {
    height: auto !important;
    max-width: 100% !important;
    display: block !important;
    margin-top: 8px !important;
    margin-bottom: 8px !important;
    margin-left: auto !important;
    margin-right: auto !important;
  }
  a {
    padding: 0.6em;
    font-size: 11px;
    color: hsl(210, 8%, 45%);
    position: relative;
    display: inline-block;
    border: 1px solid transparent;
    border-radius: 3px;
    outline: none;
    font-family: inherit;
    line-height: 13px;
    text-align: center;
    text-decoration: none;
    cursor: pointer;
    user-select: none;
    width: 100% !important;
  }
  a.button {
    color: white;
    box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
    padding: 0.8em;
    background-color: hsl(27, 90%, 55%);
  }
`;

function Nav() {
  const [active, setActive] = useState('Home');

  return (
    <S_LeftSidebar>
      <S_SidebarContainer>
        <nav>
          <S_Ol>
            <S_Li
              className={'Home' === active ? 'active' : ''}
              onClick={() => setActive('Home')}
            >
              <Link to="/">
                <S_LiFlex>
                  <div>Home</div>
                </S_LiFlex>
              </Link>
            </S_Li>
            <li>
              <S_Ol>
                <S_LiText>Public</S_LiText>
                <S_Li
                  className={'Questions' === active ? 'active' : ''}
                  onClick={() => setActive('Questions')}
                >
                  <Link to="/" className="sub">
                    <svg
                      aria-hidden="true"
                      width="18"
                      height="18"
                      viewBox="0 0 18 18"
                    >
                      <path d="M9 1C4.64 1 1 4.64 1 9c0 4.36 3.64 8 8 8 4.36 0 8-3.64 8-8 0-4.36-3.64-8-8-8ZM8 15.32a6.46 6.46 0 0 1-4.3-2.74 6.46 6.46 0 0 1-.93-5.01L7 11.68v.8c0 .88.12 1.32 1 1.32v1.52Zm5.72-2c-.2-.66-1-1.32-1.72-1.32h-1v-2c0-.44-.56-1-1-1H6V7h1c.44 0 1-.56 1-1V5h2c.88 0 1.4-.72 1.4-1.6v-.33a6.45 6.45 0 0 1 3.83 4.51 6.45 6.45 0 0 1-1.51 5.73v.01Z"></path>
                    </svg>
                    <span>Questions</span>
                  </Link>
                </S_Li>
                <S_Li
                  className={'Tags' === active ? 'active' : ''}
                  onClick={() => setActive('Tags')}
                >
                  <Link to="/" className="sub inSub">
                    <S_LiFlex>
                      <div>Tags</div>
                    </S_LiFlex>
                  </Link>
                </S_Li>
                <S_Li
                  className={'Users' === active ? 'active' : ''}
                  onClick={() => setActive('Users')}
                >
                  <Link to="/" className="sub inSub">
                    <S_LiFlex>
                      <div>Users</div>
                    </S_LiFlex>
                  </Link>
                </S_Li>
                <S_Li
                  className={'Companies' === active ? 'active' : ''}
                  onClick={() => setActive('Companies')}
                >
                  <Link to="/" className="sub inSub">
                    <S_LiFlex>
                      <div>Companies</div>
                    </S_LiFlex>
                  </Link>
                </S_Li>
                <S_LiText className="collect">
                  <div>Collectives</div>
                  <div>
                    <a href="/">
                      <svg
                        aria-hidden="true"
                        width="14"
                        height="14"
                        viewBox="0 0 14 14"
                      >
                        <path d="M7 1a6 6 0 1 1 0 12A6 6 0 0 1 7 1Zm1 10V6H6v5h2Zm0-6V3H6v2h2Z"></path>
                      </svg>
                    </a>
                  </div>
                </S_LiText>
                <S_Li
                  className={'Explore Collectives' === active ? 'active' : ''}
                  onClick={() => setActive('Explore Collectives')}
                >
                  <Link to="/" className="sub">
                    <svg
                      aria-hidden="true"
                      width="18"
                      height="18"
                      viewBox="0 0 18 18"
                      className="orange"
                    >
                      <path d="M9.86.89a1.14 1.14 0 0 0-1.72 0l-.5.58c-.3.35-.79.48-1.23.33l-.72-.25a1.14 1.14 0 0 0-1.49.85l-.14.76c-.1.45-.45.8-.9.9l-.76.14c-.67.14-1.08.83-.85 1.49l.25.72c.15.44.02.92-.33 1.23l-.58.5a1.14 1.14 0 0 0 0 1.72l.58.5c.35.3.48.79.33 1.23l-.25.72c-.23.66.18 1.35.85 1.49l.76.14c.45.1.8.45.9.9l.14.76c.14.67.83 1.08 1.49.85l.72-.25c.44-.15.92-.02 1.23.33l.5.58c.46.52 1.26.52 1.72 0l.5-.58c.3-.35.79-.48 1.23-.33l.72.25c.66.23 1.35-.18 1.49-.85l.14-.76c.1-.45.45-.8.9-.9l.76-.14c.67-.14 1.08-.83.85-1.49l-.25-.72c-.15-.44-.02-.92.33-1.23l.58-.5c.52-.46.52-1.26 0-1.72l-.58-.5c-.35-.3-.48-.79-.33-1.23l.25-.72a1.14 1.14 0 0 0-.85-1.49l-.76-.14c-.45-.1-.8-.45-.9-.9l-.14-.76a1.14 1.14 0 0 0-1.49-.85l-.72.25c-.44.15-.92.02-1.23-.33l-.5-.58Zm-.49 2.67L10.6 6.6c.05.15.19.24.34.25l3.26.22c.36.03.5.48.23.71l-2.5 2.1a.4.4 0 0 0-.14.4l.8 3.16a.4.4 0 0 1-.6.44L9.2 12.13a.4.4 0 0 0-.42 0l-2.77 1.74a.4.4 0 0 1-.6-.44l.8-3.16a.4.4 0 0 0-.13-.4l-2.5-2.1a.4.4 0 0 1 .22-.7l3.26-.23a.4.4 0 0 0 .34-.25l1.22-3.03a.4.4 0 0 1 .74 0Z"></path>
                    </svg>
                    <span>Explore Collectives</span>
                  </Link>
                </S_Li>
              </S_Ol>
            </li>
            <li>
              <S_Ol>
                <li>
                  <S_LiText>Teams</S_LiText>
                </li>
                <S_TeamsAd>
                  <strong>Stack Overflow for Teams</strong> – Start
                  collaborating and sharing organizational knowledge.
                  <img
                    width="139"
                    height="114"
                    src="https://cdn.sstatic.net/Img/teams/teams-illo-free-sidebar-promo.svg?v=47faa659a05e"
                    alt="ad"
                  ></img>
                  <a
                    className="button"
                    href="https://try.stackoverflow.co/why-teams/?utm_source=so-owned&utm_medium=side-bar&utm_campaign=campaign-38&utm_content=cta"
                  >
                    Create a free Team
                  </a>
                  <a href="Why Teams?">Why Teams?</a>
                </S_TeamsAd>
              </S_Ol>
            </li>
          </S_Ol>
        </nav>
      </S_SidebarContainer>
    </S_LeftSidebar>
  );
}

export default Nav;
