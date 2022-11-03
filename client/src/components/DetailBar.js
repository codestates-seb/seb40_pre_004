import styled from 'styled-components';
import DetailHeadLine from './DetailHeadLine';
import SideBar from './SideBar';
import DetailView from './DetailView';
import axios from 'axios';
import { useEffect, useState } from 'react';

const S_DetailBar = styled.div`
  width: 100%;
  float: left;
`;

function DetailBar({ questionId }) {
  const [item, setItem] = useState([]);
  // // axios 리스트 Get
  useEffect(() => {
    async function fetchItem() {
      const res = await axios.get(`/v1/questions/${questionId}`);
      let data = res.data.data;
      setItem(data);
      console.log(data);
    }
    fetchItem();
  }, []);

  return (
    <S_DetailBar>
      <DetailHeadLine
        key={item.questionId}
        title={item.title}
        asked={item.createdAt}
        modified={item.modifiedAt}
        viewed={9}
      />
      <div>
        <div>
          <SideBar />
        </div>
        <div>
          <DetailView
            key={questionId}
            id={questionId}
            content={item.body}
            count={2}
            tags={item.tags}
            actiontime={14}
            username={item.displayName}
            answercontent={
              'I assume you have untracked files. By default, git stash the uncommitted changes(staged and un-staged files) and overlooks untracked and ignored files. you can add them for tracking,'
            }
            answertime={12}
            answerusername={'답변자이름'}
          />
        </div>
      </div>
    </S_DetailBar>
  );
}

export default DetailBar;
