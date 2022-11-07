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
    }
    try {
      fetchItem();
    } catch (err) {
      console.error(err);
    }
  }, []);
  {
    return (
      <S_DetailBar>
        <DetailHeadLine
          title={item.title}
          asked={item.createdAt}
          modified={item.modifiedAt}
        />
        <div>
          <div>
            <SideBar />
          </div>
          <div>
            <DetailView
              id={questionId}
              content={item.body}
              tags={item.tags}
              userName={item.displayName}
              asked={item.createdAt}
              answers={item.answers}
              memberId={item.memberId}
              setItem={setItem}
            />
          </div>
        </div>
      </S_DetailBar>
    );
  }
}
export default DetailBar;
