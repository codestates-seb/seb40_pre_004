import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import Question from './Question';
import axios from 'axios';
import { useSelector } from 'react-redux';

const S_MainBar = styled.div`
  width: calc(100% - 300px - 24px);
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

const S_DFlexCenter = styled.div`
  display: flex !important;
  margin-bottom: 16px !important;
  align-items: center;
`;

const S_EmptyFlex = styled.div`
  flex: 1 auto !important;
`;

// class = 'd-flex s-btn-group js-filter-btn'
const S_DFlexGroup = styled.div`
  display: flex !important;
  flex-wrap: wrap;
  margin-bottom: 1px;
  a {
    border-top-right-radius: 0;
    border-bottom-right-radius: 0;

    border: 1px solid hsl(210, 8%, 55%);
    margin-right: -1px;
    color: hsl(210, 8%, 25%);
    background-color: white;
    white-space: nowrap;

    position: relative;
    display: inline-block;
    padding: 0.8em;
    line-height: calc((13 + 2) / 13);
    text-align: center;
    user-select: none;
    &:first-child {
      border-top-left-radius: 3px;
      border-bottom-left-radius: 3px;
    }
    &:last-child {
      border-top-right-radius: 3px;
      border-bottom-right-radius: 3px;
    }
    &.active {
      background-color: hsl(210, 8%, 90%);
    }
  }
`;

const S_Questions = styled.div`
  clear: both;
  margin-left: -24px;
  border-top: 1px solid hsl(210, 8%, 85%);
  > div {
    margin-bottom: 30px;
  }
`;

function MainBar() {
  // Tab메뉴 a링크에 active클래스 추가 or not
  // interesting = 0, bountied = 1, hot = 2, week = 3, month = 4
  const [btnColor, setBtnColor] = useState('0');
  // question 데이터 리스트
  const [qList, setQList] = useState([]);

  // axios 전체 리스트 Get
  useEffect(() => {
    async function getAllQuestions() {
      const res = await axios.get('/questions?page=1&size=10');
      setQList(res.data.data);
    }
    getAllQuestions();
  }, []);

  const { authenticated } = useSelector((state) => state.authToken);

  return (
    <S_MainBar>
      <S_DFlex>
        <S_H1>Top Questions</S_H1>
        <div>
          {authenticated ? (
            <Link to="/questions/ask">Ask Question</Link>
          ) : (
            <Link to="/login">Ask Question</Link>
          )}
        </div>
      </S_DFlex>

      <S_DFlexCenter>
        <S_EmptyFlex></S_EmptyFlex>
        <div>
          <S_DFlexGroup>
            <Link
              to="/"
              className={'0' === btnColor ? 'active' : ''}
              onClick={() => setBtnColor('0')}
            >
              Interesting
            </Link>
            <Link
              to="/"
              className={'1' === btnColor ? 'active' : ''}
              onClick={() => setBtnColor('1')}
            >
              Bountied
            </Link>
            <Link
              to="/"
              className={'2' === btnColor ? 'active' : ''}
              onClick={() => setBtnColor('2')}
            >
              Hot
            </Link>
            <Link
              to="/"
              className={'3' === btnColor ? 'active' : ''}
              onClick={() => setBtnColor('3')}
            >
              Week
            </Link>
            <Link
              to="/"
              className={'4' === btnColor ? 'active' : ''}
              onClick={() => setBtnColor('4')}
            >
              Month
            </Link>
          </S_DFlexGroup>
        </div>
      </S_DFlexCenter>

      <S_Questions>
        <div>
          {qList.map((list) => (
            <Question
              key={list.questionId}
              id={list.questionId}
              title={list.title}
              displayName={list.displayName}
              createdAt={list.createdAt}
              tags={list.tags}
              answerCount={list.answerCount}
            />
          ))}
        </div>
      </S_Questions>
    </S_MainBar>
  );
}

export default MainBar;
