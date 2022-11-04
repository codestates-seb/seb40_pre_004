import { useState } from 'react';
import styled from 'styled-components';
import CustomToolBar from '../components/CustomToolbar';
import Footer from '../components/Footer';
import ReactQuill from 'react-quill';
import 'react-quill/dist/quill.snow.css';
import Header from '../components/Header';
import { validateTitleForNewQ, validateBodyForNewQ } from '../api/validate';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const S_Content = styled.div`
  padding-top: 50px;

  min-height: 750px;
  overflow: visible;

  width: 100%;
  max-width: 1264px;
  margin: 0 auto;
`;

const S_Main = styled.main`
  margin-bottom: 48px;
`;

const S_QNotice = styled.div`
  height: 130px;
  background-image: url('https://cdn.sstatic.net/Img/ask/background.svg?v=2e9a8205b368');
  background-repeat: no-repeat !important;
  background-position: right bottom !important;
  display: flex !important;
  align-items: center !important;
  h1 {
    font-weight: 600;
    font-size: 27px;
    margin: 24px 0 27px;
    line-height: 1.3;
  }
`;

const S_FormStart = styled.div`
  width: 100%;
  display: flex;
  align-items: flex-start;
  margin: -8px;
`;

const S_FormBody = styled.div`
  margin: 8px;
  width: 64%;
  padding: 24px;
  flex-shrink: 0;
  background-color: white;
  border: 1px solid hsl(210, 8%, 90%);
  border-radius: 3px;
`;

const S_StackValidation = styled.div`
  display: flex !important;
  flex-direction: column;
  margin: -2px 0;
`;

const S_StackForm = styled.div`
  margin-bottom: 30px;
  &:last-child {
    margin-bottom: 0;
  }
`;

const S_FlexTitle = styled(S_StackValidation)``;

const S_Label = styled.label`
  cursor: pointer;
  font-size: 15px;
  font-weight: 600;
  padding: 0 2px;
`;

const S_Description = styled.div`
  margin: 2px 0;
  padding: 0 2px;
  color: hsl(210, 8%, 25%);
  font-size: 12px;
`;

const S_PsRelative = styled.div`
  margin: 6px 0 2px 0;
  position: relative;
  display: flex;
  > svg {
    color: hsl(358, 68%, 59%);
    vertical-align: bottom;

    position: absolute;
    top: 50%;
    right: 0.7em;
    margin-top: -9px;
    pointer-events: none;
    path {
      fill: currentColor;
    }
  }
`;

const S_InputTitle = styled.input`
  -webkit-appearance: none;
  width: 100%;
  padding: 0.4em 0.7em;
  border: 1px solid hsl(210, 8%, 75%);
  border-radius: 3px;
  background-color: white;
  font-size: 13px;
  &:focus {
    border-color: rgb(
      calc(89.25 + 0 * 0.65),
      calc(89.25 + 115.6 * 0.65),
      calc(89.25 + 224 * 0.65)
    );
    box-shadow: 0 0 0 4px hsla(206, 100%, 40%, 0.15);
    outline: 0;
  }
  &.err {
    border-color: hsl(358, 68%, 59%);
    box-shadow: 0 0 0 4px hsla(358, 62%, 47%, 0.15);
  }
`;

const S_ErrMessage = styled.div`
  margin: 2px 0;
  color: hsl(358, 68%, 59%);
  padding: 2px;
  font-size: 12px;
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

const S_WholeBox = styled.div`
  padding: 0px 9.1px;
  margin-top: 0px;
  margin-bottom: 0px;
  width: 100%;

  cursor: text;
  position: relative;
  overflow: hidden;
  white-space: normal;
  height: auto;
  min-height: 31px;
  padding: 2px 0 2px 2px;

  -webkit-appearance: none;
  border: 1px solid hsl(210, 8%, 75%);
  border-radius: 3px;

  display: flex;
  align-items: center;
  flex-wrap: wrap;
  &:focus-within {
    border-color: rgb(
      calc(89.25 + 0 * 0.65),
      calc(89.25 + 115.6 * 0.65),
      calc(89.25 + 224 * 0.65)
    );
    box-shadow: 0 0 0 4px hsla(206, 100%, 40%, 0.15);
  }
`;

const TagItem = styled.div`
  margin: 2px;
  display: inline-flex;
  justify-content: center;
  min-width: 0;
  padding: 0 4px;
  border: 1px solid hsl(205, 46%, 92%);
  border-radius: 3px;
  line-height: 1.84615385;
  vertical-align: middle;
  white-space: nowrap;
  background-color: hsl(205, 46%, 92%);
  span {
    font-size: 12px;
    color: hsl(205, 47%, 42%);
  }
`;

const S_DeleteBTN = styled.button`
  color: hsl(205, 47%, 42%);
  background-color: hsl(205, 46%, 92%);
  display: flex;
  align-content: center;
  align-self: center;
  justify-content: center;
  width: 16px;
  height: 16px;
  margin-left: 4px;
  padding: 1px;
  border-radius: 3px;
  &:hover {
    color: hsl(205, 46%, 92%);
    background-color: hsl(205, 47%, 42%);
  }
`;

const S_DeleteSvg = styled.svg`
  vertical-align: bottom;
  cursor: pointer;
  path {
    fill: currentColor;
  }
`;

const S_TagInput = styled.input`
  min-width: 150px;
  padding-left: 7.1px;
  height: 29px;
  box-sizing: content-box;

  border: none !important;
  box-shadow: none !important;
  outline: 0 !important;
`;

const S_FormSubmit = styled.div`
  display: flex;
  margin: 12px -8px 0 -8px;
`;

const S_BtnSubmit = styled.button`
  cursor: pointer;
  margin: 0 8px;
  color: white;
  background-color: hsl(206, 100%, 52%);
  box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100%);

  display: inline-block;
  padding: 0.8em;
  border: 1px solid hsl(206, 100%, 52%);
  border-radius: 3px;
  line-height: calc(15 / 13);
  &:hover {
    background-color: hsl(206, 100%, 40%);
  }
`;

const S_BtnClear = styled.button`
  cursor: pointer;
  margin: 0 8px;
  color: hsl(358, 62%, 47%);
  padding: 0.8em;
  &:hover {
    color: hsl(358, 64%, 41%);
  }
`;

const S_SidebarToggler = styled.div`
  margin: 8px;
  border: 1px solid hsl(210, 8%, 85%);
  border-radius: 3px;
`;

const S_TogglerTitle = styled.div`
  font-size: 15px;
  padding: 12px;
  border-bottom: 1px solid hsl(210, 8%, 85%);
`;

const S_TogglerBody = styled.div`
  display: flex;
  margin: 16px;
  div {
    margin: 0 8px;
    font-size: 12px;
    > p:first-child {
      clear: both;
      margin-bottom: 1em;
      margin-top: 0;
    }
    > p {
      margin-bottom: 0;
    }
  }
`;

function NewQuestion() {
  // title state
  const [title, setTitle] = useState('');

  // ReactQuill 라이브러리
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
  const [text, setText] = useState('');
  const handleText = (value) => {
    setText(value);
  };

  // 태그 박스 state
  const [tagItem, setTagItem] = useState('');
  const [tagList, setTagList] = useState([]);

  // title 유효성 검사
  const [titleValidateResult, setTitleValidateResult] = useState('');
  // body 유효성 검사
  const [bodyValidateResult, setBodyValidateResult] = useState('');

  const navigate = useNavigate();

  const { memberId, accessToken } = useSelector((state) => state.authToken);

  // onInput
  const onInput = (e) => {
    if (e.target.value.length > e.target.maxLength)
      e.target.value = e.target.value.slice(0, e.target.maxLength);
  };

  // 태그 박스 관련 function
  const onKeyPress = (e) => {
    if (e.target.value.length !== 0 && e.key === 'Enter') {
      e.preventDefault();
      submitTagItem();
    }
  };

  const submitTagItem = () => {
    let updatedTagList = [...tagList];
    updatedTagList.push(tagItem);
    setTagList(updatedTagList);
    setTagItem('');
  };

  const deleteTagItem = (e, index) => {
    e.preventDefault();
    const deleteTagItem = tagList[index];
    const filteredTagList = tagList.filter(
      (tagItem) => tagItem !== deleteTagItem
    );
    setTagList(filteredTagList);
  };

  // form 초기화 function
  const clearFrom = () => {
    setTitle('');
    setText('');
    setTagList([]);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setTitleValidateResult(validateTitleForNewQ(title));
    setBodyValidateResult(validateBodyForNewQ(text));

    if (
      validateTitleForNewQ(title) === 'valid' &&
      validateBodyForNewQ(text) === 'valid'
    ) {
      axios
        .post(
          '/v1/questions',
          {
            memberId,
            title,
            body: text,
            tags: tagList,
          },
          {
            headers: {
              Authorization: accessToken,
            },
          }
        )
        .then((res) => {
          console.log(res);
          navigate('/');
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  return (
    <>
      <Header />
      <S_Content>
        <form onSubmit={handleSubmit}>
          <S_Main>
            <S_QNotice>
              <h1>Ask a public question</h1>
            </S_QNotice>
            <S_FormStart>
              <S_FormBody>
                <S_StackValidation>
                  <S_StackForm>
                    <S_FlexTitle>
                      <div>
                        <S_Label htmlFor="title">Title</S_Label>
                      </div>
                      <div>
                        <S_Description>
                          <label htmlFor="title">
                            Be specific and imagine you’re asking a question to
                            another person.
                          </label>
                        </S_Description>
                      </div>
                    </S_FlexTitle>
                    <S_PsRelative>
                      <S_InputTitle
                        onInput={onInput}
                        id="title"
                        name="title"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        className={
                          titleValidateResult === 'empty' ||
                          titleValidateResult === 'short'
                            ? 'err'
                            : ''
                        }
                        type="text"
                        maxLength={300}
                        placeholder="e.g. Is there an R function for finding the index of an element in a vector?"
                      />
                      {titleValidateResult === 'empty' ||
                      titleValidateResult === 'short' ? (
                        <svg
                          aria-hidden="true"
                          width="18"
                          height="18"
                          viewBox="0 0 18 18"
                        >
                          <path d="M9 17c-4.36 0-8-3.64-8-8 0-4.36 3.64-8 8-8 4.36 0 8 3.64 8 8 0 4.36-3.64 8-8 8ZM8 4v6h2V4H8Zm0 8v2h2v-2H8Z"></path>
                        </svg>
                      ) : (
                        <></>
                      )}
                    </S_PsRelative>
                    {titleValidateResult === 'short' ? (
                      <S_ErrMessage>
                        Title must be at least 15 characters.
                      </S_ErrMessage>
                    ) : (
                      <></>
                    )}
                    {titleValidateResult === 'empty' ? (
                      <S_ErrMessage>Title is empty.</S_ErrMessage>
                    ) : (
                      <></>
                    )}
                  </S_StackForm>

                  <S_StackForm>
                    <S_FlexTitle>
                      <div>
                        <S_Label htmlFor="body">Body</S_Label>
                      </div>
                      <div>
                        <S_Description>
                          <label htmlFor="body">
                            The body of your question contains your problem
                            details and results. Minimum 30 characters.
                          </label>
                        </S_Description>
                      </div>
                    </S_FlexTitle>
                    <S_PsRelativeBody
                      className={bodyValidateResult === 'empty' ? 'err' : ''}
                    >
                      <CustomToolBar />
                      <ReactQuill
                        id="body"
                        modules={modules}
                        formats={formats}
                        value={text}
                        onChange={handleText}
                      />
                    </S_PsRelativeBody>
                    {bodyValidateResult === 'empty' ? (
                      <S_ErrMessage>Body is empty</S_ErrMessage>
                    ) : (
                      <></>
                    )}
                  </S_StackForm>

                  <S_StackForm>
                    <div>
                      <S_Label htmlFor="tags">Tags</S_Label>
                    </div>
                    <div>
                      <S_Description>
                        <label htmlFor="tags">
                          Add up to 5 tags to describe what your question is
                          about. Start typing to see suggestions.
                        </label>
                      </S_Description>
                    </div>
                    <S_PsRelative>
                      <S_WholeBox>
                        {tagList.map((tagItem, index) => {
                          return (
                            <TagItem key={index}>
                              <span>{tagItem}</span>
                              <S_DeleteBTN
                                onClick={(e) => deleteTagItem(e, index)}
                              >
                                <S_DeleteSvg
                                  width="14"
                                  height="14"
                                  viewBox="0 0 14 14"
                                >
                                  <path d="M12 3.41L10.59 2 7 5.59 3.41 2 2 3.41 5.59 7 2 10.59 3.41 12 7 8.41 10.59 12 12 10.59 8.41 7z"></path>
                                </S_DeleteSvg>
                              </S_DeleteBTN>
                            </TagItem>
                          );
                        })}
                        <S_TagInput
                          id="tags"
                          type="text"
                          autocomplete="off"
                          placeholder="e.g. (ajax objective-c r)"
                          value={tagItem}
                          onChange={(e) => setTagItem(e.target.value)}
                          onKeyPress={onKeyPress}
                        />
                      </S_WholeBox>
                    </S_PsRelative>
                  </S_StackForm>
                </S_StackValidation>
              </S_FormBody>
              <div style={{ width: '100%' }}>
                <S_SidebarToggler>
                  <S_TogglerTitle>Writing a good title</S_TogglerTitle>
                  <S_TogglerBody>
                    <div>
                      <svg
                        aria-hidden="true"
                        width="48"
                        height="48"
                        viewBox="0 0 48 48"
                      >
                        <path
                          opacity=".2"
                          d="M31.52 5.2a.34.34 0 0 0-.46.08L7 39.94a.34.34 0 0 0-.06.16l-.54 5.21c-.03.26.24.45.48.34l4.77-2.29c.05-.02.1-.06.13-.1L35.83 8.58a.34.34 0 0 0-.09-.47l-4.22-2.93Z"
                        ></path>
                        <path d="M28.53 2.82c.4-.58 1.2-.73 1.79-.32l4.22 2.92c.58.4.72 1.2.32 1.79L10.82 41.87c-.13.18-.3.33-.5.43l-4.77 2.28c-.9.44-1.93-.29-1.83-1.29l.55-5.2c.02-.22.1-.43.22-.6L28.53 2.81Zm4.43 3.81L29.74 4.4 28.2 6.6l3.22 2.24 1.53-2.21Zm-2.6 3.76-3.23-2.24-20.32 29.3 3.22 2.24 20.32-29.3ZM5.7 42.4 8.62 41l-2.57-1.78-.34 3.18Zm35.12.3a1 1 0 1 0-.9-1.78 35 35 0 0 1-7.94 3.06c-1.93.43-3.8.3-5.71-.04-.97-.17-1.93-.4-2.92-.64l-.3-.07c-.9-.21-1.81-.43-2.74-.62-2.9-.58-6.6-.49-9.43.65a1 1 0 0 0 .74 1.86c2.4-.96 5.68-1.07 8.3-.55.88.18 1.77.4 2.66.6l.3.08c1 .24 2 .48 3.03.66 2.07.37 4.22.53 6.5.02 3-.67 5.77-1.9 8.41-3.22Z"></path>
                      </svg>
                    </div>
                    <div>
                      <p>Your title should summarize the problem.</p>
                      <p>
                        You might find that you have a better idea of your title
                        after writing out the rest of the question.
                      </p>
                    </div>
                  </S_TogglerBody>
                </S_SidebarToggler>
              </div>
            </S_FormStart>
            <S_FormSubmit>
              <S_BtnSubmit type="submit">Post your question</S_BtnSubmit>
              <S_BtnClear onClick={clearFrom}>Discard draft</S_BtnClear>
            </S_FormSubmit>
          </S_Main>
        </form>
      </S_Content>
      <Footer />
    </>
  );
}

export default NewQuestion;
