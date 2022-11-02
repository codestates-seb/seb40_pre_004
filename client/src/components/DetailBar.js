import styled from 'styled-components';
import DetailHeadLine from './DetailHeadLine';
import SideBar from './SideBar';
import DetailView from './DetailView';

const S_DetailBar = styled.div`
  width: 100%;
  float: left;
`;

function DetailBar() {
  return (
    <S_DetailBar>
      <DetailHeadLine
        title={'제목입니다'}
        asked={'today'}
        modified={'today'}
        viewed={9}
      />
      <div>
        <div>
          <SideBar />
        </div>
        <div>
          <DetailView
            content={
              'I`m working on a project using Git for versioning. I`m now working on a feature in a different branch. I have not completed my changes but I need to switch to a different branch from master branch. I have tried to stash my changes with'
            }
            count={2}
            tag={'git'}
            actiontime={14}
            username={'이름'}
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
