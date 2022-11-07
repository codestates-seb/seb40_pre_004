# 💻 Stack Overflow 클론 코딩

개발자 질의응답 커뮤니티 Stack Overflow 클론 프로젝트<br>
개발 기간: 22.10.20 - 22.11.07

배포 주소: http://seb40-pre-004-stack-overflow.s3-website.ap-northeast-2.amazonaws.com/<br>
API 문서: http://ec2-43-201-141-158.ap-northeast-2.compute.amazonaws.com:8080/docs/index.html

## 📋 목차

1. [🍣 팀 소개](#-팀-소개)
2. [🛠️ 기술 스택](#%EF%B8%8F-기술-스택)
3. [☀️ 프로젝트 핵심 기능](#%EF%B8%8F-프로젝트-핵심-기능)
4. [📃 DB 테이블 설계](#-db-테이블-설계)
5. [✅ 커밋 메시지 규칙](#-커밋-메시지-규칙)

## 🍣 팀 소개

안녕하세요. 팀 사조참치 입니다.

|  이름  |   역할   |                       Github                       |
| :----: | :------: | :------------------------------------------------: |
| 박종원 | 팀장, FE |       [@jwp9633](https://github.com/jwp9633)       |
| 이혜린 | 팀원, FE |    [@hyeleeeeen](https://github.com/hyeleeeeen)    |
| 임성욱 | 팀원, FE |      [@aroowsVd](https://github.com/aroowsVd)      |
| 박영기 | 팀원, BE | [@park-yeong-ki](https://github.com/park-yeong-ki) |
| 신현상 | 팀원, BE |     [@Dev-Sam32](https://github.com/Dev-Sam32)     |
| 조민택 | 팀원, BE |    [@MintaekCho](https://github.com/MintaekCho)    |

## 🛠️ 기술 스택

### 프론트엔드

- <img src="https://img.shields.io/badge/React-3776AB?style=for-the-badge&logo=React&logoColor=white">
- Redux Toolkit
- Styled-component

### 백엔드

- Spring Boot
- Spring Security
- Spring Data JPA
- Java
- MySQL
- Redis

## ☀️ 프로젝트 핵심 기능

### 회원가입 / 로그인 / 로그아웃
<img width="800" alt="회원가입" src="https://user-images.githubusercontent.com/47207736/200261670-3811eee1-e295-4953-b56d-5effba9868cf.png">
<img width="800" alt="로그인" src="https://user-images.githubusercontent.com/47207736/200261876-cc7562b6-eaaf-4c5b-8a6b-7332fce48502.png">
<img width="800" alt="로그아웃" src="https://user-images.githubusercontent.com/47207736/200271416-33c6650b-69a6-467c-8cb4-dfc6b6c91fbf.png">

### 질문 조회 / 작성 / 수정 / 삭제

### 답변 조회 / 작성

### 댓글 조회 / 작성

## 📃 DB 테이블 설계

![db테이블](https://user-images.githubusercontent.com/107581387/200233111-9db7b04b-b5bb-4a28-a32e-6f0d0e79289e.png)

## ✅ 커밋 메시지 규칙

### 공통

1. 최대한 한글로 작성한다.
2. 제목과 본문, 본문과 꼬리말은 빈 줄로 구분한다.

### 형식

```
<유형>: <제목>

<본문>

<꼬리말>
```

### \<유형>

- 기능: 새로운 기능에 대한 커밋
- 버그: 버그 수정에 대한 커밋
- 문서: 문서 수정에 대한 커밋
- 스타일: 코드 스타일 혹은 포맷 등에 관한 커밋
- 리팩토링: 코드 리팩토링에 대한 커밋
- 테스트: 테스트 코드 수정에 대한 커밋
- 빌드: 빌드 관련 파일 수정에 대한 커밋
- 배포: 배포 관련 설정 수정에 대한 커밋
- 기타: 그 외 자잘한 수정에 대한 커밋

### \<제목>

1. 개조식으로 작성한다.
2. 50글자 내로 작성한다.
3. 끝에 마침표를 찍지 않는다.

### \<본문>

1. 제목 만으로 표현이 가능할 때 생략이 가능하다.
2. 최대한 자세하게 작성한다.
3. 어떻게 변경했는지 보다 무엇을 변경했는지, 왜 변경했는지 설명한다.
4. 문단을 작성할 때 적절한 곳에서 줄바꿈을 해준다.
5. 문단을 추가하고 싶으면 문단 사이에 빈 줄을 넣는다.

### \<꼬리말>

1. 관련 이슈가 있다면 작성하고, 없으면 생략한다.
2. `유형: #이슈 번호` 형식으로 작성한다.
   - 해결: 이슈를 해결 했을 때 사용
   - 참고: 참고할 이슈가 있을 때 사용
3. 이슈가 여러 개 있다면 반점(,)으로 구분한다.

### 예시

```
기능: 변화를 요약하여 50자 내로 개조식으로 작성

필요하다면 자세한 설명을 작성한다.

이 커밋이 해결한 문제에 대해 설명한다. 문제를 어떻게 해결했는지는 코드가 설명하므로
왜 이런 변화를 만들었는가에 집중한다. 문단을 작성할 때 적절한 곳에서 줄바꿈을 해준다.

문단을 추가하고 싶으면 문단 사이에 빈 줄을 넣는다.

해결: #123
참고: #456, #789
```
