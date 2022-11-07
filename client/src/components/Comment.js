import styled from 'styled-components';
import { useState } from 'react';
import { transDate } from '../api/time';
import { useSelector } from 'react-redux';
import axios from 'axios';

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

const S_Comment = styled.div`
  margin: 20px;
`;

const S_CList = styled.div`
  border-bottom: 1px solid rgb(186, 191, 196);
  margin: 10px;
  padding-bottom: 10px;
`;

const S_CUserName = styled.span`
  color: hsl(206, 100%, 40%);
  :hover {
    color: hsl(206, 100%, 52%);
  }
`;

const S_CCreatedAt = styled.span`
  margin-left: 6px;
  color: hsl(210, 8%, 45%);
`;

const Comment = ({ answer, setItem, id }) => {
  const [isEditing, setIsEditing] = useState(false); // input 숨기기
  const [createComment, setCreateComment] = useState(''); //코멘트입력값 저장
  const [commentArray, setCommentArray] = useState([]); // 코멘트입력값배열 저장 공간
  const { memberId, accessToken } = useSelector((state) => state.authToken);

  function commentToggle() {
    setIsEditing(!isEditing);
  }

  const onChange = (e) => setCreateComment(e.target.value);

  const onSubmit = (e) => {
    e.preventDefault();
    if (createComment === '') {
      return;
    }
    setCommentArray([createComment, ...commentArray]);
    setCreateComment('');

    axios
      .post(
        '/v1/comments',
        {
          memberId,
          answerId: answer.answerId,
          body: createComment,
        },
        {
          headers: {
            Authorization: accessToken,
          },
        }
      )
      .then(() => {
        async function fetchItem() {
          const res = await axios.get(`/v1/questions/${id}`);
          let data = res.data.data;
          setItem(data);
        }
        try {
          fetchItem();
        } catch (err) {
          console.error(err);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  console.log(answer);

  return (
    <>
      <S_Comment>
        {/* 기존의 댓글 데이터 띄우기 */}
        <ul>
          {answer.comments.map((comment, id) => (
            <li key={id}>
              <S_CList>
                <span>{comment.body} -</span>
                <S_CUserName> {comment.displayName}</S_CUserName>
                <S_CCreatedAt>{transDate(comment.createdAt)}</S_CCreatedAt>
              </S_CList>
            </li>
          ))}
          {/* 새로 추가되는 댓글 띄우기 */}
          {commentArray.map((value, id) => (
            <li key={id}>
              <S_CList>
                <span>{value} - </span>
                {/* 작성자 이름 들어가야함 */}
                <S_CUserName>{}</S_CUserName>
                <S_CCreatedAt>{transDate()}</S_CCreatedAt>
              </S_CList>
            </li>
          ))}
        </ul>
      </S_Comment>
      {/* 토글눌러서 인풋창 띄우기 */}
      <S_CommentToggle>
        <button onClick={commentToggle}>Add a comment</button>
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
    </>
  );
};

export default Comment;
