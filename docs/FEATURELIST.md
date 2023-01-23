## 기능목록

### Post
- [x] Repository, Service, Controller, Entity 생성
- [x] Controller Thymeleaf mapping
- [x] RequestDTO, ResponseDTO 생성
- [x] Post Create 작성 및 테스트
  - [x] PostService write
  - [x] Service Test
  - [x] Thymeleaf Mapping
  - [x] Spring Security Authentication 이용한 username 넘기기
- [x] Post Read 작성 및 테스트
  - [x] PostService getList
  - [x] 전체 Post 카운트 
  - [x] PostService get
  - [x] Service Test
  - [x] Thymeleaf Mapping
  - [x] PostService User Emotion Filter 추가
- [x] Post Edit 작성 및 테스트
  - [x] PostService edit
  - [x] Thymeleaf Mapping
  - [x] Service Test
- [x] Post Delete 작성 및 테스트
  - [x] PostService delete
  - [x] Thymeleaf Mapping
  - [x] Service Test
- [x] 작성자만 Post Delete, Update button 접근제어
  - [x] Thymeleaf Security Authentication.username == Post.username 접근 가능
- [x] JPA AUDITING extends 추가

### User
- [x] Entity 생성
- [x] Post <-> User 연관관계 mapping
- [x] JPA AUDITING extends 추가
- [x] nickname, email field 추가
- [x] 댓글 필터를 위한 Emotion field 추가 및 필터 설정
  - [x] Emotion 필드 추가
  - [x] 사용자 정보 변경 추가
  - [x] Emotion 변경 추가

### Security
- [x] webSecurity config 설정
- [x] Log in view page 생성
- [x] AccountController 생성 <-> login thymeleaf mapping
- [x] Invalid Login or Logout thymeleaf 설정
- [x] Sign up View page 생성
- [x] Add Controller Sign up and thymeleaf mapping
- [x] User <-> Role 양방향 연관관계 mapping
- [x] User Repository, Service 생성
- [x] Security <-> JDBC 접근 -> Security <-> JPA 접근으로 변경
  - [x] CustomUserDetails 클래스 생성
  - [x] CustomUserDetailService 클래스 생성
  - [x] Role Entity -> Enum 변경
- [x] UserDetail nickname 추가, Controller @AuthenticationPrincipal 접근

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
- [x] Comment Repository 생성
- [ ] Comment Service, Controller 생성
  - [x] Comment Create 작성
  - [x] Comment delete 작성
  - [x] Comment update 작성
- [x] Comment Service write,update 시 네이버 감정분석 API 호출 및 값 저장
  - [x] Rest Template API 호출
  - [ ] http response status 200 -> 과정 진행
  - [x] Parsing Class 생성
- [x] Post <-> Comment 연관관계 맵핑(N:1)
- [x] User <-> Comment 연관관계 맵핑(N:1)
- [x] Comment Dto 생성
- [x] PostController, Service 상세보기에 댓글 추가
  - [x] PostDto 댓글추가
- [x] Thymeleaf 게시글 상세보기 View 댓글 리스트, 작성 폼 추가
- [ ] 비동기로 변경 후 새로고침 없이 댓글 컨트롤
### DB
-[x] MySQL 연결을 위한 application.yml 세팅


### 예외처리
- [x] 회원 가입 필드 유효성 검사
  - [x] @Pattern, @NotBlank + @Valid 유효성 검사
- [x] 회원 가입 중복 검사
  - [x] JPA Repository(existsQuery) + BindingResult addFiledError
- [x] 로그인 오류
  - [x] Spring Security LoginFailure Handler + Thymeleaf
- [x] 해당 글,사용자, 댓글, 권한이 없을 때
  - [x] Abstract Exception + Controller Advice 
- [x] 컨트롤러 상위 예외 외엔 ErrorController 사용
