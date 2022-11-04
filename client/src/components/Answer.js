import styled from 'styled-components';

const S_PostCell = styled.div`
  margin: 20px;
`;

const S_PostBody = styled.div`
  display: inline-block;
  width: 100%;
  height: auto;
`;

const S_PostContent = styled.p`
  font-size: 16px;
`;

const S_DFlex = styled.div`
  display: flex;
  justify-content: space-between;
  color: hsl(210, 8%, 45%);
  margin-top: 30px;
  border-bottom: 1px solid rgb(186, 191, 196);
`;
const S_PostSignature = styled.div`
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  width: 200px;
  height: 67px;
  background-color: hsl(205, 53%, 88%);
  padding: 5px 6px 7px 7px;
  border-radius: 3px;
  margin-bottom: 20px;
`;

const S_AnswerPostSignature = styled(S_PostSignature)`
  background-color: white;
`;

const S_FlexItem = styled.div`
  p {
    cursor: pointer;
  }
`;

const S_UserName = styled.div`
  display: block;
  color: hsl(206, 100%, 40%);
  :hover {
    color: hsl(206, 100%, 52%);
  }
`;
const S_UserActionTime = styled.div``;

const S_Img = styled.div`
  display: flex;
  width: 35px;
  height: 35px;
  margin-right: 10px;
`;
const S_div = styled.div`
  display: flex;
`;

function Answer(answer) {
  return (
    <S_PostCell>
      <S_PostBody>
        {answer && answer.length > 0 ? (
          <S_PostContent>{answer.body}</S_PostContent>
        ) : (
          ''
        )}
      </S_PostBody>
      <S_DFlex>
        <S_FlexItem>
          <p>Share Edit Follow</p>
        </S_FlexItem>
        <S_AnswerPostSignature>
          <S_UserActionTime>answered 1 hours ago</S_UserActionTime>
          <S_div>
            <S_Img>
              <img
                src="https://pbs.twimg.com/media/EyNja1BWUAEpxJh?format=jpg&name=900x900"
                alt="고양이"
              ></img>
            </S_Img>
            {answer && answer.length > 0 ? (
              <S_UserName>{answer.memberDisplayName}</S_UserName>
            ) : (
              ''
            )}
          </S_div>
        </S_AnswerPostSignature>
      </S_DFlex>
    </S_PostCell>
  );
}

export default Answer;
