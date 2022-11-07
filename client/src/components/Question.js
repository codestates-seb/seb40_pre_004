import { Link } from 'react-router-dom';
import styled from 'styled-components';

const S_QuestionSummary = styled.div`
  position: relative;
  display: flex;
  border-bottom: 1px solid hsl(210, 8%, 90%);
  padding: 16px;
`;

const S_SummaryStatus = styled.div`
  gap: calc(6px * 1);
  margin-right: calc(16px * 1);
  margin-bottom: 4px;
  width: calc(calc(96px * 1) + calc(12px * 1));
  display: flex;
  flex-direction: column;
  flex-shrink: 0;
  flex-wrap: wrap;
  align-items: flex-end;
  color: hsl(210, 8%, 45%);
`;

const S_StatusVote = styled.div`
  color: hsl(210, 8%, 5%);
  display: inline-flex;
  gap: 0.3em;
  align-items: center;
  justify-content: center;
  white-space: nowrap;
  border: 1px solid transparent;
  span:first-child {
    font-weight: 500;
  }
  &.green {
    color: hsl(140, 41%, 31%);
    border: 1px solid hsl(140, 41%, 31%);
    border-radius: 3px;
    padding: 2px 4px;
  }
`;

const S_SummaryContent = styled.div`
  flex-grow: 1;
  max-width: 100%;
`;

const S_H3 = styled.h3`
  display: block;
  font-size: 17px;
  margin-top: -0.15rem;
  margin-bottom: 0.3846rem;
  padding-right: 24px;
  line-height: calc((13 + 4) / 13);
  word-break: break-word !important;
  overflow-wrap: break-word !important;
  hyphens: auto !important;
  a {
    color: hsl(206, 100%, 40%);
    word-spacing: -2px;
    &:hover {
      color: hsl(206, 100%, 52%);
    }
  }
`;

const S_SummaryMeta = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
  column-gap: 6px;
  row-gap: 8px;
`;

const S_SummaryMetaTags = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  line-height: 18px;
  float: left;
  ul {
    margin-bottom: 13px;
  }
  ul li {
    font-size: 12px;
    color: hsl(205, 46%, 32%);
    background-color: hsl(205, 46%, 92%);
    border: 1px solid hsl(205, 46%, 92%);
    display: inline-block;
    padding: 0.4em 0.5em;
    margin: 3px 6px 2px 0;
    line-height: 1;
    white-space: nowrap;
    border-radius: 3px;
  }
`;

const S_UserCard = styled.div`
  display: flex;
  align-items: center;
  gap: 4px;
  padding: 0;

  flex-wrap: wrap;
  margin-left: auto;
  justify-content: flex-end;
  line-height: 1;
`;

const S_UserCardInfo = styled.div`
  flex-direction: row;
  align-items: center;
  display: flex;
  gap: 4px;
  div {
    white-space: nowrap;
    min-width: 0;
    font-size: 12px;
    a {
      margin: 2px;
      color: hsl(206, 100%, 40%);
    }
  }
`;

const S_UserCardTheme = styled.div`
  white-space: nowrap;
  grid-column: 1 / 3;
  grid-row: 1 / 2;
  color: hsl(210, 8%, 45%);
  font-size: 12px;
  a {
    color: hsl(210, 8%, 45%);
  }
`;

function Question({ title, displayName, createdAt, id, tags }) {
  const sideData = [
    {
      answers: [
        {
          body: '이렇게 하면 됩니다',
        },
        {
          body: '이렇게 하면 됩니다',
        },
      ],
    },
  ];

  return (
    <S_QuestionSummary>
      <S_SummaryStatus>
        <S_StatusVote>
          <span>0</span>
          <span>votes</span>
        </S_StatusVote>
        <S_StatusVote className={sideData[0].answers.length > 0 ? 'green' : ''}>
          <span>{sideData[0].answers.length}</span>
          <span>{sideData[0].answers.length === 1 ? 'answer' : 'answers'}</span>
        </S_StatusVote>
        <S_StatusVote>
          <span>2</span>
          <span>views</span>
        </S_StatusVote>
      </S_SummaryStatus>
      <S_SummaryContent>
        <S_H3>
          <Link to={`/detail/${id}`}>{title}</Link>
        </S_H3>
        <S_SummaryMeta>
          <S_SummaryMetaTags>
            <ul>
              {tags.map((tag, idx) => (
                <li key={idx}>{tag}</li>
              ))}
            </ul>
          </S_SummaryMetaTags>
          <S_UserCard>
            <S_UserCardInfo>
              <div>
                <a href="/">{displayName}</a>
              </div>
            </S_UserCardInfo>
            <S_UserCardTheme>
              <a href="/">{createdAt}</a>
            </S_UserCardTheme>
          </S_UserCard>
        </S_SummaryMeta>
      </S_SummaryContent>
    </S_QuestionSummary>
  );
}

export default Question;
