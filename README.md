# Reservation System - README
## 프로젝트 개요
이 프로젝트는 매장 예약 시스템을 관리하는 애플리케이션입니다.  
사용자는 온라인으로 매장에 대한 예약을 생성하고, 예약 상태를 관리할 수 있습니다.  
또한, 예약에 대한 리뷰를 작성하고 수정할 수 있는 기능을 제공합니다.  
시스템은 회원 가입과 로그인 기능을 포함하며, 매장 관리 및 예약 처리, 리뷰 작성 등의 기능을 제공합니다.

## 주요 기능
- 회원 관리: 회원 가입, 로그인, 역할에 따른 권한 관리 (ROLE_USER, ROLE_PARTNER).
- 매장 관리: 매장 등록, 매장 검색, 매장 소유자 관리.
- 예약 관리: 예약 생성, 상태 변경 (PENDING, CONFIRMED, COMPLETED, CANCELLED), 도착 확인.
- 리뷰 관리: 예약 완료 후 리뷰 작성 및 수정, 리뷰 삭제.

## 기술 스택
- Java 17
- Spring Boot: 백엔드 프레임워크
- Spring Data JPA: 데이터베이스 처리
- Spring Security: 사용자 인증 및 권한 관리
- MySQL: 관계형 데이터베이스
- BCryptPasswordEncoder: 비밀번호 암호화

## 설치 방법
### 1. 클론 후 의존성 설치
```bash
git clone https://github.com/yourusername/reservation-system.git
cd reservation-system
```
### 2. MySQL 데이터베이스 설정
- MySQL 데이터베이스를 생성한 후, application.properties 파일에 데이터베이스 연결 정보를 설정합니다.
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/reservation_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```
### 3. 빌드 및 실행
- Maven을 사용하여 프로젝트를 빌드하고 실행합니다.
```bash
./mvnw clean install
./mvnw spring-boot:run
```
- 애플리케이션이 실행되면 기본적으로 http://localhost:8080에서 접근 가능합니다.

## API 엔드포인트
### 회원 관리
- 회원 가입
  - `POST /api/members/register`
  - 요청 본문:
    ```json
    {
      "name" : "Gwanghwa Lee",
      "email" : "gh.lee@gwanghwa.net",
      "password" : "securePassword123",
      "role" : "ROLE_PARTNER"
    }
    ```
- 회원 로그인
  - `POST /api/members/login`
  - 요청 본문:
    ```json
    {
      "email": "gh.lee@gwanghwa.com",
      "password" : "securePassword123"
    }
    ```
### 매장 관리
- 매장 등록
  - `POST /api/stores`
  - 요청 본문:
    ```json
    {
      "name" : "GwangHwaNet",
      "location" : "123 Main Street",
      "description" : "None",
      "ownerId" : 1
    }
- 매장 검색
  - `GET /api/stores/{name}`
  - 응답:
    ```json
    {
      "id" : 1,
      "name" : "GwangHwaNet",
      "location" : "123 Main Street",
      "description" : "None",
      "owner" : { ... }
    }
    ```
### 예약 관리
- 예약 생성
  - `POST /api/reservations`
  - 요청 본문:
    ```json
    {
      "storeId": 1,
      "memberId": 1,
      "reservationTime": "2024-12-01T19:00:00"
    }
- 예약 상태 변경
  - `PUT /api/reservations/confirm/{reservationId}`
  - 응답 : 예약 상태가 "CONFIRMED"로 변경된 예약 객체.
### 리뷰 관리
- 리뷰 작성
  - `POST /api/reviews/{reservationId}`
  - 요청 본문:
    ```json
    {
      "content": "Great service!",
      "rating": 5
    }
- 리뷰 조회
  - `GET /api/reviews/{reservationId}`
  - 응답:
    ```json
    {
      "content": "Great service!",
      "rating": 5,
      "createdAt": "2024-12-01T20:00:00"
    }
    ```
## 프로젝트 구조
```bash
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── net
│   │   │   │   ├── gwanghwa
│   │   │   │   │   ├── reservation
│   │   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── entity
│   │   │   │   │   │   ├── repository
│   │   │   │   │   │   ├── service
│   │   │   │   │   │   └── type
│   │   │   └── resources
│   │   │       ├── application.properties
│   │   │       └── static
│   │   └── test
│   └── pom.xml
```
- controller : 각 기능별 REST API 엔드포인트 정의
- entity : 데이터베이스 엔티티
- exception : 예외 처리 및 커스텀 예외 클래스
- repository : 데이터베이스 접근 계층
- service : 비즈니스 로직 처리
- type : enum 등 유형 정의
## 기여 방법
1. **이슈 제기** : 버그, 기능 개선 등 이슈를 제기해주세요.
1. **풀 리퀘스트** : 기능 개선 및 버그 수정을 위해 풀 리퀘스트를 제출해주세요.
## 라이센스
Copyright (c) 2024 GwangHwa Lee

본 소프트웨어는 MIT 라이센스에 따라 라이센스됩니다. 아래의 조건을 준수하십시오:

1. 소프트웨어와 함께 제공되는 원본 라이센스 및 저작권 고지를 소프트웨어의 모든 복제본 또는 상당 부분에 포함시켜야 합니다.
1. 소프트웨어는 "있는 그대로" 제공되며, 명시적 또는 묵시적인 보증 없이 제공됩니다. 사용자는 소프트웨어를 사용하여 발생하는 모든 위험을 스스로 부담합니다.