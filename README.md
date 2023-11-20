# 이커머스 프로젝트

## GOAL
무신사, 29cm 등 판매자와 구매자 간의 의류 상품 중개 이커머스 서버를 구축한다.

---
## 프로젝트 기능
### 1. 회원 관리
- [X] 회원 가입 (고객, 판매자)
    - 아이디, 이메일로 중복을 구분
    - 아이디, 이름, 이메일, 비밀번호, 핸드폰 번호, 배송지 주소 등록

- [X] 인증 (이메일)

- [X] 로그인 토큰 발행
- [X] 로그인 토큰을 통해 제어 확인
- [ ] 로그아웃

- [ ] 잔액 관리

- [X] 회원 정보 조회 / 수정(구매자, 판매자)
    - 아이디, 이메일 일치 시 회원 정보 조회
    - 비밀번호, 배송지 주소 변경

- [ ] 회원 탈퇴


### 2. 상품 관리
- **공통**
    - 상품 검색
        - [X] 상품명 검색
        - [ ] 카테고리 별 검색
            - 상품 정보에 등록된 카테고리를 통해 상품 검색

    - 검색 결과 표시 방법
        - [ ] 가격순
        - [ ] 최신순
      

- **판매자**
    - [X] 상품 등록 & 수정
        - 브랜드명
        - 상품명
            - '상품명_사이즈' 형태로 등록 및 수정
        - 가격
        - 사이즈
        - 수량
            - 0 ~ 999개 내 등록 및 수정
        - 상품시즌
            - '년도/시즌' 형태로 등록 및 수정
            - 예) 2022/SS, 2023FW
        - 남성용 / 여성용 / 공용
            - 남성용(M) / 여성용(W) / 공용(MW) 등록 및 수정
        - 카테고리
            - 상의/하의/아우터/악세서리 등 카테고리 등록 및 수정
        - 상품 등록일

    - 판매 상품 목록 조회 및 삭제
        - [X] 전체 판매 상품 목록 조회
        - [X] 판매 상품 삭제
        - [X] '장바구니에 추가할 상품의 수량이 부족합니다.' 상품 수량 확인


- **고객**
    - 고객 로그인 시 장바구니 접근 가능
    - 로그인 시 상품 추가 및 삭제 가능

    - [X] 장바구니 상품 추가 & 수정
    - [ ] 장바구니 조회
        - 브랜드명
        - 상품명
        - 수량
        - 사이즈
        - 가격

    - [ ] 옵션 변경
        - 사이즈, 수량 변경

    - [ ] 장바구니 품목 전체 삭제
    - [ ] 장바구니 전체 품목 총 수량 및 금액 표시


---
## ERD
![이커머스 프로젝트 drawio](https://github.com/hakdang113/zero_e-commerce/assets/127729985/5a92b718-926b-4b71-8a5c-61ac48983e7c)


---
## Tech Stack

### **언어** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JAVA(JDK 11)

### **서버** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Apache Tomcat 9.0

### **프레임워크** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Spring Framwork 2.7.17

### **DB** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MariaDB 3.1.3

### **IDE** &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Intellij 2023.2.4
