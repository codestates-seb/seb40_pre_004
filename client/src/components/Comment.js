import styled from 'styled-components';
import { useState } from 'react';

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

const Comment = (answer) => {
  const [isComment, setIsComment] = useState(false);
  console.log(answer);

  function CommentToggle() {
    setIsComment(!isComment);
  }

  const [userName] = useState('hyelyn'); //사용자 정보
  const [comment, setComment] = useState(''); //사용자가 입력하는 댓글
  const [feedComments, setFeedComments] = useState([]); //댓글 리스트
  const [isValid, setIsValid] = useState(false); //유효성 검사

  let post = (e) => {
    const copyFeedComments = [...feedComments];
    copyFeedComments.push(comment);
    setFeedComments(copyFeedComments);
    setComment('');
  };

  return (
    <>
      <S_Comment>
        {answer && answer.length > 0 ? (
          <ul>
            {answer.comments.map((comment) => (
              <li key={comment}>
                <S_CList>
                  <span>{comment.body} -</span>
                  <S_CUserName> {comment.displayName}</S_CUserName>
                  <S_CCreatedAt>{comment.createdAt}</S_CCreatedAt>
                </S_CList>
              </li>
            ))}
          </ul>
        ) : (
          ''
        )}
      </S_Comment>
      <S_CommentLink>
        {isComment ? (
          <form>
            <input
              type="text"
              placeholder="댓글달기..."
              onChange={(e) => {
                setComment(e.target.value);
              }}
              onKeyUp={(e) => {
                e.target.value.lenght > 0
                  ? setIsValid(true)
                  : setIsValid(false);
              }}
              value={comment}
            />
            <button type="button" onClick={post}>
              send !
            </button>
          </form>
        ) : (
          <a href="#;" onClick={CommentToggle}>
            Add a comment
          </a>
        )}
      </S_CommentLink>
    </>
  );
};

export default Comment;
