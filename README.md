# RunnerShop 런닝화 쇼핑몰
 스프링부트 프레임워크를 사용하여 런닝화쇼핑몰 사이트 구현

## 개요
- 프로젝트 명 : runnsersshop
- 개발기간 : 2024.08.03 ~ 2024.8.10
- 개발인원 :  1명
- 주요기능
   - SpringSecurity 회원가입 및 로그인
   - Oauth2로그인 (구글 소셜로그인 구현) 
   - smtp 이메일 전송
## 사용기술
### #Back-End
#### core
- java-17
- SpringBoot
- SpringSecurity
- Spring Data JPA
- OAuth2.0
- Querydsl
- Docker
#### BuildTool
- Maven
#### DataBase
- MySQL

### #Front-End
- JavaScript
- BootStrap
- Thymeleaf
#### API
- Kakao 주소 API
- Google 로그인 API
- TossPayments
  
## 개발환경
- IntelliJ
- DBeaver
- Github

## 요구사항 분석
 ### 회원가입 페이지
- 이메일 형식 패턴 적용해 확인 및 비동기 방식으로 이메일 인증확인
- 한 개의 칸이라도 공백 혹은 빈칸이 있는지 확인하고 있다면, "OOO는 필수 입력 값입니다."의 메시지 보여주기
- 카카오 주소 API 사용

### 로그인 페이지
- 아이디와 비밀번호가 일치하지 않을 시 "아이디 또는 비밀번호가 일치하지 않습니다. "의 메시지를 보여주기
- 구글 소셜 로그인 구현
- 비 로그인시 (상품 화면 및 상세보기만 가능)

### 상품 등록페이지
- ADMIN 권한을 가진 회원만 상품등록가능


## ERD 다이어그램
![RunnersclubERD](https://github.com/user-attachments/assets/71724dd7-590a-4c66-9d9b-c54f837b6be1)

## API 명세서

![스크린샷 2024-08-12 231445](https://github.com/user-attachments/assets/8e25a75b-2c11-4efa-88b7-6c07e4a3d6c9)
![스크린샷 2024-08-12 231454](https://github.com/user-attachments/assets/231b9029-ffdf-46c5-b21b-322ba6d851ed)
![스크린샷 2024-08-12 231500](https://github.com/user-attachments/assets/ae8d5d59-e543-4e6d-a6b4-72c51092fae1)
![스크린샷 2024-08-12 231504](https://github.com/user-attachments/assets/7e971e6d-dcd8-47ab-898b-35c619134b75)
![스크린샷 2024-08-12 231637](https://github.com/user-attachments/assets/5f1ee5d3-b82c-47ce-b1a3-3140581bcaae)



## 실행


## 프로젝트 후기
혼자 공부하며 처음 만들어본 프로젝트이기 때문에 부족한 부분에 대한 아쉬움도 많이 남았습니다.
효율적인 설계를 위해 고민하고 찾아보며 실제로 많이 공부할 수 있었던 부분도 많았습니다.
책이나 블로그, 강의로 공부한 예제에서 납득했던 부분들은 실제로 코드를 짜면서 다양한 애로 사항을 접하게 되었고 이러한 애로사항을 해결하면서 더욱더 깨달음을 얻는 경험이었습니다.

## 프로젝트 보안사항


## 참고자료
