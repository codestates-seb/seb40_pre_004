import styled from 'styled-components';
import DetailHeadLine from './DetailHeadLine';
import SideBar from './SideBar';

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
        <div>body</div>
        <div>
          <SideBar />
        </div>
      </div>
    </S_DetailBar>
  );
}

export default DetailBar;
