# Emotion Filter Community
## 1. 프로젝트 소개
인터넷의 익명성이 주는 단점 중에 하나는 남이 쓴 부정적인 글에 상처를 받을 수 있다는 점이라고 생각합니다.

기본적인 CRUD 기능을 가진 커뮤니티에 댓글 작성 시 글의 내용을 네이버 감정분석 API를 이용해 긍정, 중립, 부정 태그를 매겨 사용자가 원하는 감정범위의 댓글을 볼 수 있는 댓글감정 필터링 서비스를 추가해 구현해가며 배워보고 싶어 시작했습니다.

## 2. 프로젝트 기능
    게시판 - CRUD, 페이징 처리, 검색
    사용자 - 회원가입, 로그인
    댓글 - CRUD, 감정분석 태그, 댓글 필터링
## 3. 사용기술
주요 프레임워크/라이브러리
- Java11
- SpringBoot 2.7.6
- JPA(Spring Data JPA) 5.6.14
- Spring Security 5.7.5
- Naver Clover Sentiment 감정분석 API

빌드툴
- gradle 7.6

데이터베이스
- MySQL 8.0.28

프론트엔드
- Html/CSS
- Thymeleaf 3.0.15

## 4. 요구사항 분석
 1. 회원 가입 페이지
    - 유효성 검사
      - 닉네임은 2~10자이며, 특수문자를 제외한 한글 (ㄱ~ㅎ, 가~힣), 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성
      - 이메일은 @를 포함한 이메일 형식으로 구성
      - 비밀번호는 8~16자이며, 영문 대소문자, 숫자, 특수문자로 구성
      - 전체적으로 빈칸, 공백 확인
    - 중복 확인
      - DB에 존재하는 아이디로 회원 가입 시에 중복예외
      - DB에 존재하는 닉네임로 회원 가입 시에 중복예외
      - DB에 존재하는 이메일로 회원 가입 시에 중복예외
 2. 로그인 페이지
    - 미 로그인 시 이용 가능 페이지
      - 회원 가입 페이지
      - 로그인 페이지
      - 게시글 목록 페이지
      - 게시글 상세보기 페이지
      - 게시글 검색 목록 페이지
      - 외에 로그인 필요한 페이지에 접근시 로그인 페이지로 이동
    - 로그인 
      - 아이디 또는 비밀번호가 일치하지 않을 경우 로그인 에러 처리
      - 로그인 성공 시 게시글 목록 페이지로 이동
 3. 회원 정보 수정
    - 사용자 정보 (닉네임, 비밀번호) 수정
      - 아이디, 이메일은 읽기 전용
      - 전체적으로 빈칸, 공백 확인
      - DB에 존재하는 닉네임로 회원 가입 시에 중복예외
      - 닉네임은 2~10자이며, 특수문자를 제외한 한글 (ㄱ~ㅎ, 가~힣), 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성
      - 비밀번호는 8~16자이며, 영문 대소문자, 숫자, 특수문자로 구성
    - 사용자 감정 수정
      - 감정 드롭다운으로 선택, 현재 비밀번호 입력
      - 비밀번호 미 일치시 알람창 예외처리 및 리다이렉트
 4. 게시글 검사
    - 게시글 작성, 수정 시 제목, 내용 빈칸, 공백 확인
      - 예외 발생 시 리다이렉트
    - 작성자만 수정, 삭제 등 글관리 가능
 5. 댓글 검사
    - 댓글 작성, 수정 시 내용 빈칸, 공백 확인
      - 예외 발생 시 알람창 및 리다이렉트
    - 작성자만 수정, 삭제 등 댓글관리 가능
 6. 댓글 필터링
    - 댓글 작성, 수정 시 감정분석 API 연동해 감정태그 추가
    - 사용자의 감정에 따라 게시글 상세 조회 시 조건에 맞지 않는 댓글 삭제
      - 긍정일 때, 긍정을 제외한 댓글 삭제
      - 중립일 때, 부정 댓글 삭제
      - 부정일 때, 모든 댓글 조회
## 5. DB 설계
![img.png](img.png)

## 6. API 설계
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)
## 8. 개발내용


    
    