## 기능목록

### Post
- [x] Repository, Service, Controller, Entity 생성
- [x] Controller Thymeleaf mapping
- [x] RequestDTO, ResponseDTO 생성
- [ ] Post Create 작성 및 테스트
  - [x] PostService write
  - [ ] Post RequestDTO @NotBlank, Controller @Valid
  - [ ] Binding Result + thymeleaft로 Exception Controll
  - [x] Service Test
  - [x] Thymeleaf Mapping
  - [x] Spring Security Authentication 이용한 username 넘기기
- [x] Post Read 작성 및 테스트
  - [x] PostService getList
  - [x] 전체 Post 카운트 
  - [x] PostService get
  - [x] Service Test
  - [x] Thymeleaf Mapping
- [x] Post Edit 작성 및 테스트
  - [x] PostService edit
  - [x] Thymeleaf Mapping
  - [x] Service Test
- [x] Post Delete 작성 및 테스트
  - [x] PostService delete
  - [x] Thymeleaf Mapping
  - [x] Service Test
### User
- [x] Entity 생성
- [x] Post <-> User 연관관계 mapping
- [x] User, Post 컨비니언스 메소드 생성 

### Security
- [x] webSecurity config 설정
- [x] Log in view page 생성
- [x] AccountController 생성 <-> login thymeleaf mapping
- [x] Invalid Login or Logout thymeleaf 설정
- [x] Sign up View page 생성
- [x] Add Controller Sign up and thymeleaf mapping
- [x] User <-> Role 양방향 연관관계 mapping
- [x] User Repository, Service 생성
- [ ] Security <-> JDBC 접근 -> Security <-> JPA 접근으로 변경
  - [x] CustomUserDetails 클래스 생성
  - [x] CustomUserDetailService 클래스 생성
  - [x] Role Entity -> Enum 변경

### Page
- [x] PageDTO 생성
  - [x] 시작페이지, 끝페이지, 현재페이지, Page 객체
- [x] PostService getList 메소드 Pageable 파라미터 추가
- [x] PostController Pageable 파라미터 추가
- [x] Thymeleaf pagination view 추가
  - [x] each, classAppend, link 추가

### Search
- [x] Repository Containing 메소드 추가
- [x] Thymeleaf view 검색 부분 추가
- [x] PostController, Service search 메소드 추가
- [x] Security Config search 권한 설정

### Comment
- [x] Comment Entity 생성
- [ ] Comment Repository, Service, Controller 생성
- [ ] Comment Service write,update 시 네이버 감정분석 API 연결할 Comment API Controller 생성
- [x] Post <-> Comment 연관관계 맵핑(N:1)
- [x] User <-> Comment 연관관계 맵핑(N:1)
- [ ] Comment Dto 생성
- [ ] Thymeleaf 게시글 상세보기 View 댓글 리스트, 작성 폼 추가
### DB
-[x] MySQL 연결을 위한 application.yml 세팅
