import styled from 'styled-components';
import { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import CustomToolBar from '../components/CustomToolbar';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const S_PostLayout = styled.div`
  margin: 20px;
  display: flex;
  flex-direction: column;
`;

const S_Ad = styled.div`
  width: 100px;
  img {
    width: 728px;
    margin-left: 20px;
  }
`;

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

const S_PostTagDiv = styled.div`
  display: flex;
  margin-top: 20px;
  margin-bottom: 35px;
`;

const S_PostTagList = styled.div`
  flex-wrap: wrap;
  gap: 4px;
  line-height: 18px;
  float: left;
  color: hsl(205, 46%, 32%);
  ul {
    margin-bottom: 13px;
  }
  ul li {
    font-size: 12px;
    background-color: hsl(205, 46%, 92%);
    border: 1px solid hsl(205, 46%, 92%);
    display: inline-block;
    padding: 0.4em 0.5em;
    margin: 3px 6px 2px 0;
    line-height: 1;
    white-space: nowrap;
    border-radius: 3px;
    cursor: pointer;
  }
  li:hover {
    background-color: hsl(205, 57%, 81%);
  }
`;

const S_DFlex = styled.div`
  display: flex;
  justify-content: space-between;
  color: hsl(210, 8%, 45%);
  margin-top: 30px;
`;

const S_FlexItem = styled.div`
  p {
    cursor: pointer;
  }
`;

const S_PostSignature = styled.div`
  display: inline-block;
  box-sizing: border-box;
  width: 200px;
  height: 67px;
  background-color: hsl(205, 53%, 88%);
  padding: 5px 6px 7px 7px;
  border-radius: 3px;
`;

const S_select = styled.select`
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

const S_CommentLink = styled.div`
  color: hsl(210, 8%, 55%);
  opacity: 0.6;
  padding: 0 3px 2px;
  margin: 20px 20px 0px 20px;
  border-bottom: 1px solid rgb(186, 191, 196);

  a {
    cursor: pointer;
    position: relative;
    bottom: 20px;
  }

  a:hover {
    color: hsl(206, 100%, 52%);
  }
`;

const S_Answers = styled.div`
  margin: 20px 20px 0px 20px;
`;

const S_AnswersHeader = styled.div`
  display: flex;
  justify-content: space-between;
`;

const S_Word = styled.p`
  font-size: 23px;
  margin-bottom: 20px;
`;

const S_Select = styled.div`
  display: flex;
  margin-left: 10px;
`;

const S_AnswerPostSignature = styled(S_PostSignature)`
  background-color: white;
`;

const S_UserActionTime = styled.div``;

const S_UserName = styled.div`
  color: hsl(206, 100%, 40%);
  :hover {
    color: hsl(206, 100%, 52%);
  }
`;

//form

const S_StackForm = styled.div`
  margin-bottom: 30px;
  margin-right: 20px;
`;

const S_Btn = styled.button`
  background-color: rgb(10, 149, 255);
  border: 1px solid white;
  border-radius: 5px;
  display: inline-block;
  box-sizing: border-box;
  padding: 10px 11px 10.4px 10px;
  margin: 0px 0px 0px 4px;
  cursor: pointer;
  text-align: center;
  position: relative;
  font-size: 13px;
  color: white;
  box-shadow: inset 0 1px 0 0 hsl(210, 8%, 90%);
  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
`;

const S_Link = styled(Link)`
  color: hsl(206, 100%, 40%);
  :hover {
    color: hsl(206, 100%, 52%);
  }
`;

const S_Word2 = styled.span`
  font-size: 18px;
  margin: 20px 0px 20px 0px;
`;

const S_PsRelativeBody = styled.div`
  width: 100%;
  margin: 10px 0 8px;
  border-radius: 3px;
  &:focus-within {
    box-shadow: 0 0 0 4px hsla(206, 100%, 40%, 0.15);
  }
  &.err {
    box-shadow: 0 0 0 4px hsla(358, 62%, 47%, 0.15);
  }
  .ql-toolbar.ql-snow {
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
  }
  .ql-container.ql-snow {
    margin-top: -1px;
    border-bottom-left-radius: 3px;
    border-bottom-right-radius: 3px;
    min-height: 210px;
  }
`;
function DetailView({ content, username, tags, questionid }) {
  const [comment, setComment] = useState(true);

  function createComment() {
    setComment(!comment);
  }

  return (
    <S_PostLayout>
      <S_Ad>
        <img
          src="https://tpc.googlesyndication.com/simgad/10582817586221403560"
          alt="ad"
        ></img>
      </S_Ad>
      <S_PostCell>
        <S_PostBody>
          <S_PostContent>{content}</S_PostContent>
        </S_PostBody>
        <S_PostTagDiv>
          <S_PostTagList>
            {tags && tags.length > 0 ? (
              <ul>
                {' '}
                {tags.map((tag) => (
                  <li key={tag}>{tag}</li>
                ))}
              </ul>
            ) : (
              ''
            )}
          </S_PostTagList>
        </S_PostTagDiv>
        <S_DFlex>
          <S_FlexItem>
            <p>Share Edit Follow</p>
          </S_FlexItem>
          <S_PostSignature>
            <S_UserActionTime>{} hours ago</S_UserActionTime>
            <S_UserName>{username}</S_UserName>
          </S_PostSignature>
        </S_DFlex>
      </S_PostCell>
      <S_CommentLink>
        {comment ? (
          <a href="#;" onClick={createComment}>
            Add a comment
          </a>
        ) : (
          <form>
            <input type="text" placeholder="댓글..." />
            <button onClick={createComment}>send !</button>
          </form>
        )}
      </S_CommentLink>
      <S_Answers>
        <S_AnswersHeader>
          <div>
            <S_Word>{} Answers</S_Word>
          </div>
          <S_Select>
            Sorted by:&nbsp;
            <div>
              <Selecting />
            </div>
          </S_Select>
        </S_AnswersHeader>
        <S_PostCell>
          <S_PostBody>
            <S_PostContent>{}</S_PostContent>
          </S_PostBody>
          <S_DFlex>
            <S_FlexItem>
              <p>Share Edit Follow</p>
            </S_FlexItem>
            <S_AnswerPostSignature>
              <S_UserActionTime>answered {} hours ago</S_UserActionTime>
              <S_UserName>{}</S_UserName>
            </S_AnswerPostSignature>
          </S_DFlex>
        </S_PostCell>
      </S_Answers>
      <S_CommentLink>
        <a href="#;">Add a comment</a>
      </S_CommentLink>
      {/* 댓글 없을때 */}
      <S_Word2>
        Know someone who can answer? Share a link to this&nbsp;
        <S_Link>question</S_Link> via&nbsp;
        <S_Link>email</S_Link>, <S_Link>Twitter</S_Link>, or&nbsp;
        <S_Link>Facebook.</S_Link>
      </S_Word2>
      <form onClick={handleSubmit}>
        <div>
          <FormBox />
        </div>
        <div>
          <S_Btn type="submit">Post Your Answer</S_Btn>
        </div>
      </form>
      <S_Word2>Browse other questions tagged&nbsp;</S_Word2>
      <S_PostTagDiv>
        <S_PostTagList>
          {tags && tags.length > 0 ? (
            <ul>
              {' '}
              {tags.map((tag) => (
                <li key={tag}>{tag}</li>
              ))}
            </ul>
          ) : (
            ''
          )}
        </S_PostTagList>
      </S_PostTagDiv>
      or{' '}
      <S_Link to="/questions/ask">
        <S_Word2>ask your own question.</S_Word2>
      </S_Link>
    </S_PostLayout>
  );
}

function Selecting() {
  const [Selected, setSelected] = useState('');

  const handleSelect = (e) => {
    setSelected(e.target.value);
  };

  return (
    <div>
      <div>
        <S_select onChange={handleSelect} value={Selected}>
          {<option>{'Date created (oldest first)'}</option>}
        </S_select>
        <p>{Selected}</p>
      </div>
    </div>
  );
}

function FormBox() {
  const [aText, setAtext] = useState('');
  const handleText = (value) => {
    setText(value);
    const navigate = useNavigate();

    const handleSubmit = (e) => {
      e.preventDefault();

      axios
        .post(
          `/v1/questions/${questionid}`,
          {
            memberId: 'memberId',
            body: answer,
          },
          {
            headers: {
              Authorization: 'token',
            },
          }
        )
        .then((res) => {
          console.log(res);
          navigate(`/v1/questions/${questionid}`);
        })
        .catch((err) => {
          console.log(err);
        });
    };

    const modules = {
      toolbar: {
        container: '#toolbar',
      },
    };
    const formats = [
      'header',
      'font',
      'size',
      'bold',
      'italic',
      'underline',
      'list',
      'bullet',
      'align',
      'color',
      'background',
      'image',
    ];
  };
  return (
    <>
      <div>
        <S_Word>Your Answer</S_Word>
      </div>

      <S_StackForm>
        <S_PsRelativeBody>
          <CustomToolBar />
          <ReactQuill
            modules={modules}
            formats={formats}
            value={aText}
            onChange={handleText}
          />
        </S_PsRelativeBody>
      </S_StackForm>
    </>
  );
}

// function createComment (){
// const [comment ,setComment] = useState('');
// const [userName] = useState('hyeyln');
// const [feedComment, setFeedComment] = useState([]);

// return ();

// }

export default DetailView;
