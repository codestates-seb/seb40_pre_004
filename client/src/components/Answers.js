import styled from 'styled-components';
import { useState } from 'react';
import Answer from './Answer';

const S_AnswersHeader = styled.div`
  display: flex;
  justify-content: space-between;
`;

const S_Word = styled.p`
  font-size: 23px;
  margin-bottom: 20px;
`;

const S_SelectBox = styled.div`
  display: flex;
  margin-left: 10px;
`;

const S_SelectItem = styled.select`
  border-radius: 4px;
  border-color: rgb(186, 191, 196);
  width: 244px;
  height: 32px;
  &:focus {
    box-shadow: rgb(0, 116, 204, 0.15) 0px 0px 0px 4px;
    outline: none;
    border-radius: 3px;
  }
`;

function Answers({ answers, id, setItem }) {
  return (
    <>
      <S_AnswersHeader>
        <div>
          {answers && answers.length > 0 ? (
            <S_Word>{answers.length} Answer</S_Word>
          ) : (
            ''
          )}
        </div>
        <S_SelectBox>
          Sorted by:&nbsp;
          <div>
            <SelectMenu />
          </div>
        </S_SelectBox>
      </S_AnswersHeader>
      {answers && answers.length > 0
        ? answers.map((answer, memberId) => (
            <div key={memberId}>
              <Answer answer={answer} id={id} setItem={setItem} />
            </div>
          ))
        : ''}
    </>
  );
}

function SelectMenu() {
  const [selected, setSelected] = useState('');

  const handleSelect = (e) => {
    setSelected(e.target.value);
  };

  return (
    <div>
      <div>
        <S_SelectItem onChange={handleSelect} value={selected}>
          {<option>{'Date created (oldest first)'}</option>}
        </S_SelectItem>
        <p>{selected}</p>
      </div>
    </div>
  );
}

export default Answers;
