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
- 상품 수정가능

### 주문 기능
- tossPaymets와 연결하여 주문기능 구현 

## ERD 다이어그램
![RunnersclubERD](https://github.com/user-attachments/assets/a21db42a-b02a-4c90-917a-006069e326ea)


## API 명세서

![스크린샷 2024-08-12 231445](https://github.com/user-attachments/assets/8e25a75b-2c11-4efa-88b7-6c07e4a3d6c9)
![스크린샷 2024-08-12 231454](https://github.com/user-attachments/assets/231b9029-ffdf-46c5-b21b-322ba6d851ed)
![스크린샷 2024-08-12 231500](https://github.com/user-attachments/assets/ae8d5d59-e543-4e6d-a6b4-72c51092fae1)
![스크린샷 2024-08-12 231504](https://github.com/user-attachments/assets/7e971e6d-dcd8-47ab-898b-35c619134b75)
![스크린샷 2024-08-12 231637](https://github.com/user-attachments/assets/5f1ee5d3-b82c-47ce-b1a3-3140581bcaae)



## 프로젝트 시연영상

[![Video Label](http://img.youtube.com/vi/rHHF39xR8lU/0.jpg)](https://youtu.be/rHHF39xR8lU)


## 프로젝트 후기
이번 프로젝트는 혼자 공부하며 진행한 만큼 완성도 면에서 아쉬움이 많이 남았습니다. 하지만 효율적인 설계를 위해 끊임없이 고민하고 자료를 찾아보면서, 실제로 많은 것을 배울 수 있는 소중한 경험이었습니다. 특히, QueryDSL과 JPA를 활용하는 과정에서 기술적인 어려움을 겪으며, 실무적인 지식을 쌓을 수 있었습니다.
QueryDSL을 사용하여 복잡한 쿼리를 작성할 때, 동적 쿼리를 구현하는 데 있어 특히 어려움을 느꼈습니다. 예를 들어, 여러 조건을 조합하여 최적의 쿼리를 생성하는 부분에서 성능 최적화와 코드의 가독성 사이에서 고민이 많았습니다. 이러한 문제를 해결하기 위해 다양한 자료를 참고하고, 여러 번의 시행착오를 거치면서 QueryDSL의 장점과 한계를 보다 명확하게 이해할 수 있었습니다.
또한, JPA를 사용해 데이터베이스 관계를 설정하는 과정에서 연관 관계 매핑에 대한 깊이 있는 이해가 필요하다는 것을 절감했습니다. 다대다, 일대다와 같은 관계를 맺는 과정에서 예상치 못한 문제가 발생하기도 했으며, 특히 Lazy Loading과 Eager Loading의 사용 시기와 그로 인한 성능 이슈를 경험하게 되었습니다. 이를 통해 데이터베이스 설계 시 관계 설정이 애플리케이션의 성능에 큰 영향을 미친다는 점을 깨닫고, 더 신중하게 설계를 해야 함을 배우게 되었습니다.
다만, 아쉬운 점은 실제 사용자가 접속할 수 있는 환경에서 애플리케이션을 운영해보는 경험을 하지 못했다는 것입니다. 이 부분이 부족해 프로젝트가 실제로 어떤 방식으로 사용자에게 전달될지를 경험하지 못한 것이 아쉽습니다. 이러한 점을 보완하기 위해, 다음 프로젝트에서는 실제 사용자들이 접근할 수 있는 환경에서 애플리케이션을 배포하고 운영해보는 것을 목표로 삼을 것입니다. 이를 통해 개발 과정 전반에 대한 이해도를 더욱 높이고, 실제 사용자 피드백을 바탕으로 개선할 부분을 찾아낼 계획입니다.



