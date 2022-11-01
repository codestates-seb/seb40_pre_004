import styled from 'styled-components';

const S_Ad = styled.div`
  width: 100px;

  img {
    width: 728px;
    margin-left: 25px;
    margin-top: 15px;
  }
`;

// const S_Post = styled.div``

function DetailView() {
  return (
    <>
      <S_Ad>
        <img
          src="https://tpc.googlesyndication.com/simgad/10582817586221403560"
          alt="ad"
        ></img>
      </S_Ad>
    </>
  );
}

export default DetailView;
