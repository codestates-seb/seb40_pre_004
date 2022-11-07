import styled from 'styled-components';
import MainBar from './MainBar';
import SideBar from './SideBar';

const S_Content = styled.div`
  max-width: 1100px;
  width: calc(100% - 164px);
  background-color: hsl(0, 0%, 100%);
  border: 1px solid hsl(210, 8%, 85%);
  border-top-width: 0;
  border-bottom-width: 0;
  border-left-width: 1px;
  border-right-width: 0;
  padding: 24px;
  box-sizing: border-box;
`;

function Main() {
  return (
    <S_Content>
      <MainBar />
      <SideBar />
    </S_Content>
  );
}

export default Main;
