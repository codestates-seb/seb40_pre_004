import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  /* 여백 초기화 */
  body, div, ul, li, dl, dd, dt, ol, h1, h2, h3, h4, h5, h6, input, fieldset, legend, p, select, table, th, td, tr, textarea, button, form, figure, figcaption {
    margin:0;
    padding:0;
  }

  /* a 링크 초기화 */
  a {
    color: #222;
    text-decoration: none;
  }
  a:hover {
    color: #2698cb;
  }

  /* 폰트 초기화 */
  body, input, textarea, select, button, table {
    font-family: 'Noto Sans KR', 'AppleSDGothicNeo-Regular', 'Malgun Gothic', '맑은 고딕', 'dotum', '돋움', sans-serif;
    color: #222;
    font-size: 13px;
    line-height: 1.5;
  }

  /* 폰트 스타일 초기화 */
  em, address {
    font-style: normal;
  }

  /* 블릿기호 초기화 */
  ul, li, ol {
    list-style:none;
  }

  /* 제목 태그 초기화 */
  h1, h2, h3, h4, h5, h6 {
    font-size: 13px; font-weight: normal;
  }

  /* 테두리 초기화 */
  img, fieldset {
    border:0 none;
  }

  /* 버튼 초기화 */
  button {
    border: 0;
  }

  /* 반응형 */
  video, img {
    width: 100%;
  }
`;

export default GlobalStyle;
