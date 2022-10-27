import { Link } from 'react-router-dom';
import styled from 'styled-components';

const S_MainBar = styled.div`
  width: calc(100% - 300px - var(24px));
  float: left;
`;

const S_DFlex = styled.div`
  display: flex !important;
  div {
    margin-left: 12px !important;
    a {
      color: white;
      background-color: hsl(206, 100%, 52%);
      box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);

      position: relative;
      display: inline-block;
      padding: 0.8em;
      border: 1px solid hsl(206, 100%, 52%);
      border-radius: 3px;
      outline: none;
      line-height: calc((13 + 2) / 13);
      text-align: center;
      user-select: none;
      white-space: nowrap !important;
    }
  }
`;

const S_H1 = styled.h1`
  font-size: 1.61538462rem;
  flex: 1 auto !important;
  line-height: 1.3;
  margin: 0 0 1em;
`;

const S_DFlexCenter = styled(S_DFlex)`
  margin-bottom: 16px !important;
  align-items: center;
`;

function MainBar() {
  return (
    <S_MainBar>
      <S_DFlex>
        <S_H1>Top Questions</S_H1>
        <div>
          <Link to="/">Ask Question</Link>
        </div>
      </S_DFlex>
      <S_DFlexCenter></S_DFlexCenter>
    </S_MainBar>
  );
}

export default MainBar;
