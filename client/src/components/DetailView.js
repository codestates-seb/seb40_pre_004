import styled from 'styled-components';
import { useState } from 'react';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import CustomToolBar from './CustomToolbar';
import { Link } from 'react-router-dom';
import DOMPurify from 'dompurify';
import Answers from './Answers';
import { getTime, diff } from '../api/time';

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

const S_PostTagBox = styled.div`
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
  border-bottom: 1px solid rgb(186, 191, 196);
`;

const S_FlexItem = styled.div`
  cursor: pointer;
  &:hover {
    color: hsl(210, 8%, 55%);
  }
`;

const S_Link = styled(Link)`
  margin: 4px;
  color: hsl(210, 8%, 45%);
  &:hover {
    color: hsl(210, 8%, 55%);
  }
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

const S_CList = styled.div`
  border-bottom: 1px solid rgb(186, 191, 196);
  margin: 10px;
  padding-bottom: 10px;
`;
const S_CommentToggle = styled.div`
  color: hsl(210, 8%, 55%);
  opacity: 0.6;
  padding: 0 3px 2px;
  margin: 20px 20px 0px 20px;
  border-bottom: 1px solid rgb(186, 191, 196);
  span {
    cursor: pointer;
    position: relative;
    bottom: 10px;
    &:hover {
      color: hsl(206, 100%, 52%);
    }
  }
  input {
    border: 1px solid rgb(186, 191, 196);
    margin-bottom: 10px;
    padding: 4px 200px 4px 10px;
    &:focus {
      box-shadow: rgb(0, 116, 204, 0.15) 0px 0px 0px 4px;
      outline: none;
      border-radius: 3px;
    }
  }
  button {
    margin-left: 10px;
    background-color: rgb(10, 149, 255);
    border: 1px solid white;
    border-radius: 3px;
    display: inline-block;
    box-sizing: border-box;
    padding: 5px;
    cursor: pointer;
    text-align: center;
    position: relative;
    font-size: 13px;
    color: white;
    box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
    &:hover {
      background-color: hsl(206, 100%, 40%);
    }
  }
`;

const S_Answers = styled.div`
  margin: 20px 20px 0px 20px;
`;

const S_Word = styled.p`
  font-size: 23px;
  margin-bottom: 20px;
`;

const S_UserName = styled.div`
  display: block;
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

const S_Link2 = styled(Link)`
  color: hsl(206, 100%, 40%);
  :hover {
    color: hsl(206, 100%, 52%);
  }
`;

const S_Word2 = styled.div`
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

const S_Img = styled.div`
  display: flex;
  width: 35px;
  height: 35px;
  margin-right: 10px;
`;

const S_Div = styled.div`
  display: flex;
`;

function DetailView({
  id,
  title,
  content,
  userName,
  asked,
  tags,
  answers,
  memberId,
  setItem,
}) {
  const [isEditing, setIsEditing] = useState(false); // input 숨기기
  const [createComment, setCreateComment] = useState(''); //코멘트입력값 저장
  const [commentArray, setCommentArray] = useState([]); // 코멘트입력값배열 저장 공간

  function commentToggle() {
    setIsEditing(!isEditing);
  }

  const onChange = (e) => setCreateComment(e.target.value);
  const onSubmit = (e) => {
    e.preventDefault();
    if (createComment === '') {
      return;
    }
    setCommentArray((commentValueList) => [createComment, ...commentValueList]);
    setCreateComment('');
  };

  const time = diff(new Date().getDate(), getTime(asked).getDate());

  return (
    <S_PostLayout>
      <S_Ad>
        <img
          src="https://tpc.googlesyndication.com/simgad/10582817586221403560"
          alt="ad"
        />
      </S_Ad>
      <S_PostCell>
        <S_PostBody>
          <S_PostContent
            dangerouslySetInnerHTML={{
              __html: DOMPurify.sanitize(content), //본문 태그 출력 무시
            }}
          ></S_PostContent>
        </S_PostBody>
        <S_PostTagBox>
          <S_PostTagList>
            {tags && tags.length > 0 ? (
              <ul>
                {tags.map((tag, index) => (
                  <li key={index}>{tag}</li>
                ))}
              </ul>
            ) : (
              ''
            )}
          </S_PostTagList>
        </S_PostTagBox>
        <S_DFlex>
          <S_FlexItem>
            Share
            <S_Link
              to={{
                pathname: `/update/${id}`,
                state: {
                  title: title,
                  name: userName,
                  body: content,
                  memberId: memberId,
                },
              }}
            >
              Edit
            </S_Link>
            Follow
          </S_FlexItem>
          <S_PostSignature>
            <S_Div>
              <div>asked {time} days ago</div>
            </S_Div>
            <S_Div>
              <S_Img>
                <img
                  src="https://cdn.hellodd.com/news/photo/202005/71835_craw1.jpg"
                  alt="고양이"
                />
              </S_Img>
              <S_UserName>{userName}</S_UserName>
            </S_Div>
          </S_PostSignature>
        </S_DFlex>
        <div>
          {commentArray.map((value, id) => (
            <li key={id}>
              <S_CList>
                <span>{value}</span>
              </S_CList>
            </li>
          ))}
        </div>
        <S_CommentToggle onClick={commentToggle}>
          <span>Add a comment</span>
          {isEditing ? (
            <div>
              <form onSubmit={onSubmit}>
                <input
                  type="text"
                  placeholder="Add a comment"
                  value={createComment}
                  onChange={onChange}
                />
                <button>Send !</button>
              </form>
            </div>
          ) : (
            ''
          )}
        </S_CommentToggle>
      </S_PostCell>
      <S_Answers>
        <Answers answers={answers} id={id} setItem={setItem} />
      </S_Answers>
      <S_Word2>
        Know someone who can answer? Share a link to this&nbsp;
        <S_Link2>question</S_Link2> via&nbsp;
        <S_Link2>email</S_Link2>, <S_Link>Twitter</S_Link>, or&nbsp;
        <S_Link2>Facebook.</S_Link2>
      </S_Word2>
      <div>
        <FormBox />
      </div>
      <div>
        <S_Btn type="submit">Post Your Answer</S_Btn>
      </div>
      <S_Div>
        <S_Word2>Browse other questions tagged&nbsp;</S_Word2>
        <S_PostTagBox>
          <S_PostTagList>
            {tags && tags.length > 0 ? (
              <ul>
                {tags.map((tag, index) => (
                  <li key={index}>{tag}</li>
                ))}
              </ul>
            ) : (
              ''
            )}
          </S_PostTagList>
        </S_PostTagBox>
        <S_Word2> or&nbsp;</S_Word2>
        <S_Link2 to="/questions/ask">
          <S_Word2>ask your own question.</S_Word2>
        </S_Link2>
      </S_Div>
    </S_PostLayout>
  );
}

function FormBox() {
  const [aText, setAtext] = useState('');
  const handleText = (value) => {
    setAtext(value);
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
  {
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
}

export default DetailView;
