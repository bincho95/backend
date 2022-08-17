# 회원가입 API 구현

회원 가입 및 비밀번호 재설정이 가능한 API를 제작

* Member 관련 API
* 인증  관련 API



##### Framework

- spring boot

##### DataBase

- mariaDB

##### MQ

-  RabbitMQ   

##### SMS 

- Naver Cloud Service Sens

##### 배포환경

- AWS EC2 Ubuntu



### 구성

---

![poster](http://drive.google.com/uc?export=view&id=1YtLZadzXwQAf8yyhpa6F8pJlYWmVlj5u)

DataBase 와 RabbitMQ의 설정과 커넥션 저보를 담고있는 Common Module 과 Api 를 처리하는 Api Module , Sms 발송을 담당 하는 Sms Module 이렇게 멀티모듈 프로잭트로 구성되어 있습니다.

로컬 실행시 yml 파일 속에 커넥션 계정 정보들이 Jasypt 을통해 암호화가 되어 있기 때문에 gradle 빌드 후 실행시에 VM OPTION(-Djasypt.encryptor.password=1234)을 넣어서 실행 시켜줘야 합니다 (ex java -jar -Djasypt.encryptor.password=1234  **.jar )

테스트는 로컬로도 가능 하나 AWS EC2 에 배포 및 실행 시켜 두었기 때문에 (http://3.39.88.21:7788/ ) 를 통해서도 테스트가 가능합니다.



## API

**Url** 

- http://3.39.88.21:7788/ (Local 실행의 경우 : http://localhost:7788) 

### Member

---

**identifier** : nickname or mobile Number or email



**Member 생성**

POST : /member/create

| parameter | type   | description                 |
| --------- | ------ | --------------------------- |
| email     | String | 생성 하려는 회원의 Email    |
| nickname  | String | 생성 하려는 회원의 nickname |
| password  | String | 생성 하려는 회원의 password |
| name      | String | 생성 하려는 회원의 name     |
| mobile    | String | 생성 하려는 회원의 mobile   |





**Member 정보 조회**

GET : /member/info/{identifier}



**Member 정보 수정**

**PUT** : /member/edit/{id}



**휴대 전화를 통한 Member 비밀번호 임시 발급**

**GET**: /member/change-password/{mobile}



**Member 비밀번호 체크**

**POST** : /member/password-check

| parameter  | type   | description                                |
| ---------- | ------ | ------------------------------------------ |
| identifier | String | 입력된 nickname  or email or mobile number |
| password   | String | 입력된 password                            |



**Email 중복 체크**

**GET** : /member/exist-email/{email}



**Mobile 중복 체크** 

**GET**: /member/exist-mobile/{mobile}



**Nickname 중복 체크**

**GET**: /member/exist-nickname/{nickname}



## 인증 API



**SMS 인증메세지 발송**

**POST** : /verification/send

| parameter | type   | description                   |
| --------- | ------ | ----------------------------- |
| sessionId | String | 인증 시도하려는 Session Id    |
| mobile    | String | 인증 시도하려는 Mobile Number |



**인증번호 확인**

**POST**: /verification/check

| parameter | type   | description                   |
| --------- | ------ | ----------------------------- |
| sessionId | String | 인증 시도하려는 Session Id    |
| mobile    | String | 인증 시도하려는 Mobile Number |
| code      | String | 입력받은 인증 번호            |

