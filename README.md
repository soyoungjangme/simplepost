# JSP를 활용한 게시판 구현

### 페이지 별 기능

1. [게시판 목록](#-게시판-목록)
2. [조회수](#-조회수)
3. [ 게시글 내용](#-게시글-내용-보기)
4. [ 댓글](#-댓글)
5. [ 헤더](#-헤더)
6. [ 가입, 로그인](#-가입-로그인)
7. [ 패스워드 해싱](-#-패스워드-해싱)
8. [ 개발 기간](#-개발기간)

---
### ⭐ 게시판 목록

###### 제목, 작성일자, 작성자 닉네임, 조회수

<img src="https://github.com/user-attachments/assets/c5807ea2-14fb-4d02-86cb-67aca07a2909" alt="글목록 이미지">


##### ✅ 조회수 
날짜가 지나면 동일한 유저의 조회여도 조회수 +1 증가

    ex. A유저가 1/5 23:30 B게시물 조회 : B게시물 조회수 1

        A유저가 1/6 01:21 B게시물 조회 : B게시물 조회수 2

🔗[조회관리 살펴보기](https://soyoungjang.tistory.com/24)

---
### ⭐ 게시글 내용 보기

###### 작성자, 작성일시, 제목, 내용, 댓글
<br>

- #### 글작성자 = 유저
###### 삭제/수정 버튼 ㅇ
<img src="https://github.com/user-attachments/assets/a303978b-1be8-4c79-aaa4-c94d78190260" width="900">

- #### 글작성자 not 유저
###### 삭제/수정 버튼 x
<img src="https://github.com/user-attachments/assets/6d9cdd7f-47c0-4acd-af2b-8bfd0124c17e" width="900">

##### ✅ 댓글
###### 작성자 닉네임, 작성일자

###### [접속유저 = 작성자]일 경우 삭제버튼 생성

🔗[댓글동작 살펴보기](https://soyoungjang.tistory.com/30)

---
### ⭐ 헤더

> ##### 세션이 null일 경우
<img src="https://github.com/user-attachments/assets/8d828722-0aed-44db-8f4f-e08a9d3cff94" alt="헤더" width="700">


🔗[세션관리 살펴보기](https://soyoungjang.tistory.com/23)

---
### ⭐ 가입, 로그인
- ##### 패스워드 해싱
![암호화_DB](https://github.com/user-attachments/assets/8fa3d684-dea4-4c37-98be-66fcfbc5c49a)

##### ✅ 해싱이 필요한 경우
1.회원가입

2.로그인

##### 회원가입
> 비밀번호를 해싱한 후에 데이터를 삽입합니다. 그러면 해싱된 비밀번호가 DB에 저장됩니다.

##### 로그인
> 유저가 입력한 비밀번호와 해시값인 DB 비밀번호를 일치시켜야 하기 때문에 BCrypt.verifyer()를 통해 입력값을 해시값으로 변환시킨 후, DB값과 비교합니다.


- ##### 아이디 중복확인

| 글작성자=유저 | 글작성자!=유저 |
|----------|----------|
|중복확인 안하고 가입 시|ID중복확인|
|<img src="https://github.com/user-attachments/assets/815f1e1c-19d3-4da4-aeb1-a52b52633af9" width="450">|<img src="https://github.com/user-attachments/assets/06fb7bd5-f110-4ec6-838e-abeaaeb9e7a9" width="450">|

- ##### 로그인
![로그인](https://github.com/user-attachments/assets/87ec69db-abd7-49f7-8b6b-af03dc4494be)


---
### 💻 개발기간
##### 1인 프로젝트
##### 총 10일
###### 2024.12.23 ~ 2024.12.29
###### 2025.01.06 ~ 2025.01.08
