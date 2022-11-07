import styled from 'styled-components';
import Header from '../components/Header';
import Footer from '../components/Footer';
import Main from '../components/Main';
import Nav from '../components/Nav';

const S_Container = styled.div`
  max-width: 1264px;
  width: 100%;
  background: none;
  display: flex;
  justify-content: space-between;
  margin: 0 auto;
  position: relative;
  flex: 1 0 auto;
  padding-top: 60px;
`;

const Home = () => {
  return (
    <>
      <S_Container>
        <Header />
        <Nav />
        <Main />
      </S_Container>
      <Footer />
    </>
  );
};

export default Home;
