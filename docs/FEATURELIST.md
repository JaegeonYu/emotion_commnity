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
### Comment
### DB
-[x] MySQL 연결을 위한 application.yml 세팅
