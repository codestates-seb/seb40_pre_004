import styled from 'styled-components';
import { Link } from 'react-router-dom';

const S_H1 = styled.h1`
  font-size: 1.61538462rem;
  flex: 1 auto !important;
  line-height: 1.3;
`;

const S_DFlex = styled.div`
  display: flex !important;
  border-bottom: 1px solid hsl(210, 8%, 90%);
  flex-direction: column;
  div {
    margin-left: 12px !important;
    margin-top: 10px;
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

const S_Div = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const S_Div2 = styled.div`
  display: flex;
  margin-bottom: 20px;
`;

const S_Span = styled.span`
  color: #6a737c;
`;

const S_Span2 = styled.span`
  color: black;
`;

function DetailHeadLine({ title, asked, modified, viewed }) {
  return (
    <S_DFlex>
      <S_Div>
        <div>
          <S_H1>{title}</S_H1>
        </div>
        <div>
          <Link to="/">Ask Question</Link>
        </div>
      </S_Div>
      <S_Div2>
        <div>
          <S_Span>Asked </S_Span>
          <S_Span2>{asked}</S_Span2>
        </div>
        <div>
          <S_Span>Modified </S_Span>
          <S_Span2>{modified}</S_Span2>
        </div>
        <div>
          <S_Span>viewed </S_Span>
          <S_Span2>{viewed} times</S_Span2>
        </div>
      </S_Div2>
    </S_DFlex>
  );
}

export default DetailHeadLine;
