import styled from 'styled-components';

const S_Footer = styled.footer`
  background-color: hsl(210, 8%, 15%);
  color: hsl(210, 8%, 60%);
`;

const S_Container = styled.div`
  max-width: 1264px;
  width: 100%;
  margin: 0 auto;
  padding: 32px 12px 12px;
  display: flex;
  flex-flow: row wrap;
`;

const S_FooterLogo = styled.div`
  flex: 0 0 64px;
  margin: -12px 0px 32px;
  a {
    color: var(--blue-600);
    cursor: pointer;
    svg {
      width: 32px;
      height: 37px;
      vertical-align: bottom;
    }
  }
`;

const S_FooterNav = styled.nav`
  display: flex;
  flex: 2 1 auto;
  flex-wrap: wrap;
`;

const S_FooterCol = styled.div`
  padding: 0 12px 24px 0;
  flex: 1 0 auto;
`;

const S_H5 = styled.h5`
  text-transform: uppercase;
  font-weight: bold;
  margin-bottom: 12px;
  color: hsl(210, 8%, 75%);
  line-height: calc(
    (var(--stacks-internals-lh-unit) + 4) / var(--stacks-internals-lh-unit)
  );
  a {
    color: hsl(210, 8%, 75%);
    text-decoration: none;
  }
`;

const S_FooterCopyRight = styled.div`
  flex: 1 1 150px;
  display: flex;
  flex-direction: column;
`;

const S_Ul = styled.ul`
  margin: 0;
  list-style: none;
  li a {
    color: #9199a1;
    padding: 4px 0;
    line-height: calc(
      (var(--stacks-internals-lh-unit) + 4) / var(--stacks-internals-lh-unit)
    );
    display: inline-block;
    &:hover {
      color: hsl(210, 8%, 65%);
    }
  }
`;

const S_UlSocial = styled(S_Ul)`
  display: flex;
  li {
    margin-left: 12px;
    &:first-child {
      margin-left: 0;
    }
  }
  a {
    font-size: 11px;
  }
`;

const S_P = styled.p`
  font-size: 11px;
  margin-top: auto;
  margin-bottom: 24px;
  span a {
    line-height: inherit;
    color: hsl(210, 8%, 60%);
    padding: 0;
  }
`;

function Footer() {
  return (
    <S_Footer>
      <S_Container>
        <S_FooterLogo>
          <a href="https://stackoverflow.com" aria-label="Stack Overflow">
            <svg aria-hidden="true" viewBox="0 0 32 37">
              <path d="M26 33v-9h4v13H0V24h4v9h22Z" fill="#BCBBBB"></path>
              <path
                d="m21.5 0-2.7 2 9.9 13.3 2.7-2L21.5 0ZM26 18.4 13.3 7.8l2.1-2.5 12.7 10.6-2.1 2.5ZM9.1 15.2l15 7 1.4-3-15-7-1.4 3Zm14 10.79.68-2.95-16.1-3.35L7 23l16.1 2.99ZM23 30H7v-3h16v3Z"
                fill="#F48024"
              ></path>
            </svg>
          </a>
        </S_FooterLogo>
        <S_FooterNav>
          <S_FooterCol>
            <S_H5>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link: 15})"
              >
                Stack Overflow
              </a>
            </S_H5>
            <S_Ul>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 16})"
                >
                  Questions
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 3})"
                >
                  Help
                </a>
              </li>
            </S_Ul>
          </S_FooterCol>
          <S_FooterCol>
            <S_H5>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link: 19})"
              >
                Products
              </a>
            </S_H5>
            <S_Ul>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 29})"
                >
                  Teams
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 21})"
                >
                  Advertising
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 40})"
                >
                  Collectives
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 20})"
                >
                  Talent
                </a>
              </li>
            </S_Ul>
          </S_FooterCol>
          <S_FooterCol>
            <S_H5>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link: 1})"
              >
                Company
              </a>
            </S_H5>
            <S_Ul>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 1})"
                >
                  About
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 27})"
                >
                  Press
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 9})"
                >
                  Work Here
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 7})"
                >
                  Legal
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 8})"
                >
                  Privacy Policy
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 37})"
                >
                  Terms of Service
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 13})"
                >
                  Contact Us
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 7})"
                >
                  Cookie Settings
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 39})"
                >
                  Cookie Policy
                </a>
              </li>
            </S_Ul>
          </S_FooterCol>
          <S_FooterCol>
            <S_H5>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link: 30})"
              >
                Stack Exchange Network
              </a>
            </S_H5>
            <S_Ul>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  Technology
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  Culture & recreation
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  Life & arts
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  Science
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  Professional
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  Business
                </a>
              </li>
              <li>
                <a
                  style={{
                    margin: '16px 0 0 0',
                  }}
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  API
                </a>
              </li>
              <li>
                <a
                  href="/"
                  data-gps-track="footer.click({ location: 1, link: 24})"
                >
                  Data
                </a>
              </li>
            </S_Ul>
          </S_FooterCol>
        </S_FooterNav>
        <S_FooterCopyRight>
          <S_UlSocial>
            <li>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link:4 })"
              >
                Blog
              </a>
            </li>
            <li>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link:4 })"
              >
                Facebook
              </a>
            </li>
            <li>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link:4 })"
              >
                Twitter
              </a>
            </li>
            <li>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link:4 })"
              >
                LinkedIn
              </a>
            </li>
            <li>
              <a
                href="https://stackoverflow.com"
                data-gps-track="footer.click({ location: 1, link:4 })"
              >
                Instagram
              </a>
            </li>
          </S_UlSocial>
          <S_P>
            Site design / logo © 2022 Stack Exchange Inc; user contributions
            licensed under
            <span>
              <a href="/">CC BY-SA</a>
            </span>
            .<span>rev 2022.10.25.20449</span>
          </S_P>
        </S_FooterCopyRight>
      </S_Container>
    </S_Footer>
  );
}

export default Footer;
