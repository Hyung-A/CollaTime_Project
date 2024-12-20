<img src="https://github.com/user-attachments/assets/bfc45619-5509-4d0a-a7c0-52cea21d2854" width="500" height="180"/>

-----------------------------------------------

# CollaTime


## 목차
1. [프로젝트 소개 및 개요](#프로젝트-소개-및-개요)
2. [개발 기간](#개발-기간)
3. [사용 언어 및 개발 툴](#사용-언어-및-개발-툴)
4. [팀원 소개](#팀원-소개)
5. [모델링](#모델링)
6. [프로젝트 주요 기능](#프로젝트-주요-기능)<br>
   * [회원 관리](#회원-관리)<br>
   * [프로젝트 관리](#프로젝트-관리)<br>
   * [스케줄러 관리](#스케줄러-관리)<br>
   * [관리자 페이지](#관리자-페이지)<br>
   * [마이 페이지](#마이-페이지)<br>
7. [프로젝트 설치 및 실행 방법](#프로젝트-설치-및-실행-방법)
8. [프로젝트 사용 방법](#프로젝트-사용-방법)

-----------------------------------------------

###  프로젝트 소개 및 개요
#### CollaTime(Collaboration Time) : 협업 스케줄러

&ensp;대학생 혹은 협업에 익숙하지 않은 사용자들을 주 타겟층으로 한 협업 스케쥴러 프로젝트이다. <br>
현재의 협업툴들은 인터페이스와 사용법들이 전문가들에게 최적화된 기능들을 제공하고 있다. <br>
그렇기에 협업에 익숙하지 않은 분(이하 초보자)들 혹은 학생들이 접근하기에는 다소 어려움이 있을 것이라는 생각에서 시작하게 되었고 초보자나 학생들이 편하게 쓸 수 있는 협업 스케줄러를 만들자는 취지로 이 프로젝트를 기획했다. <br>
&ensp;코로나 팬데믹의 여파로 인해 협업툴을 쓰는 회사들이 많아졌고 현재 많은 기업들에게 있어 협업툴은 선택이 아니라 필수가 되어가고 있다. <br>
하지만 취업을 준비하는 초보자 혹은 학생들에게 있어서 기존 시장에 출시되어 있는 협업툴들을 처음부터 사용하기에는 인터페이스와 사용법들에 대한 진입장벽이 높다. <br>
이를 위해 본 CollaTime은 가시성이 좋은 인터페이스와 간편한 사용법을 제공하여 초보자 혹은 학생들에게 협업에 대한 경험을 제공하는 것이 목표이다. <br>

-----------------------------------------------

### 개발 기간

2024년 10월 16일 ~ 2024년 11월 18일 <br>

-----------------------------------------------

### 사용 언어 및 개발 툴

Spring Boot : JDK IntelliJ IDEA 2024.2.3 <br>
Thymeleaf : JDK IntelliJ IDEA 2024.2.3 <br>
DataBase : MySQL Workbench 8.0 CE <br>


-----------------------------------------------

### 협업 툴

Figma : https://www.figma.com/design/ksFZ8rr89oyWGSo4gaNKF7/Untitled?node-id=0-1&node-type=canvas&t=OAzPUWNqy36qpgcN-0 <br>
Discord : 회의 진행

-----------------------------------------------

### 팀원 소개

| PM<br>(Project Manager) | CM<br>(Config Manager) | DB<br>(Database Manager) |  
| :------: | :------: | :------: | 
| [![HS](https://github.com/user-attachments/assets/b9b9af1c-144b-4131-8f4c-01b5550d1113)](https://github.com/Passbob) | [![JH](https://github.com/user-attachments/assets/e582c649-34ab-479d-8d78-e9fb6ddcd0a3)](https://github.com/JUHYE0925) | [![SW](https://github.com/user-attachments/assets/c2f4095c-a507-452a-8a43-655fc01d1d86)](https://github.com/suwanpp) | 
| 팀장 : 조형석 | 팀원 : 김주혜 | 팀원 : 박수완 | 
| 담당 : 로그인/회원가입/마이페이지/문의하기/관리자 | 담당 : 프로젝트/에러페이지/메인 | 담당 : 스케줄러 | 

-----------------------------------------------

### 모델링

![Group 1241](https://github.com/user-attachments/assets/2a581305-aeea-4997-aa87-c8a40c1f775d)


-----------------------------------------------

### 프로젝트 주요 기능

#### <회원 관리>

1. 로그인
   - 아이디와 비밀번호를 통해서 로그인 가능하며 로그인 시 메인 화면으로 들어갈 수 있다. <br>
   - 만약 아이디와 비밀번호가 맞지 않는다면 실패하게 된다. <br>
   - 로그인 화면에서 회원가입 창에 들어갈 수 있도록 만들어야한다. <br>
   - 아이디 찾기를 할 수 있다. <br>
   - 비밀번호 찾기를 할 수 있다.<br>

2. 보안
   - Session 기반 Spring Security

3. 회원가입
   - 이름, 닉네임, 이메일 주소, 아이디, 비밀번호를 필수로 입력한다. <br>
   - 비밀번호는 영문자, 숫자, 특수 문자 모두 사용해 최소 8글자 이상으로 만들어야 한다. <br>
   - 아이디와 비밀번호는 기존 DB정보와 비교해 중복 여부를 확인한다. <br>
   

#### <프로젝트 관리>

1. 프로젝트 생성
   - 새 프로젝트를 생성할 수 있으며 프로젝트 생성 시 프로젝트 설정 창으로 넘어간다. <br>
   - 새 프로젝트 생성 시 프로젝트명, 개요, 목표 및 목적, 시작 날짜, 종료 날짜를 필수로 지정한다. <br>
   - 프로젝트 성격에 따라 카테고리로 나눠 관리할 수 있도록 한다. <br>
   - 프로젝트 참여자에 대한 글 작성, 조회, 접근 권한을 설정한다 <br>
   - 프로젝트 데이터 저장 유효 기간에 대해 고지한다. <br>
   - 필수 입력사항을 기입하지 않거나, 데이터 저장 유효기간에 비동의 시 새 프로젝트 생성은 실패한다. <br>
   - 프로젝트 생성 완료 시 메인 창으로 넘어가며 방금 생성한 프로젝트도 메인 창에 추가한다. <br>

2. 팀원 관리
   - 프로젝트 참여 인원을 이메일을 통해 초대하여 추가한다. <br>
   - 프로젝트 참여 인원을 삭제하며 삭제 시 프로젝트 참여 시 부여된 권한 및 정보는 제거된다. <br>

3. 프로젝트 수정
   - 프로젝트 정보를 수정할 수 있다. <br>

4. 프로젝트 삭제
   - 프로젝트 정보를 삭제한다. <br>
   - 삭제한 프로젝트는 프로젝트 창에서 제거한다. <br>


#### <스케줄러 관리>

1. 스케줄 생성
   - 프로젝트 스케줄을 새로 생성할 수 있다. <br>
   - 본 기능의 권한은 프로젝트에 참여한 인원으로 제한한다.<br>

2. 스케줄 조회
   - 캘린더 UI를 통해 본인이 참여하고 있는 프로젝트에 한해서 각각의 프로젝트의 전체조회와 상세조회가 가능하다.<br>
   - 본 기능의 권한은 프로젝트에 참여한 인원으로 제한한다.<br>

3. 스케줄 수정
   - 상세조회를 통해 스케줄 내용을 수정할 수 있다.<br>
   - 본 기능의 권한은 프로젝트에 참여한 인원으로 제한한다.<br>

4. 스케줄 삭제
   - 스케줄을 삭제할 수 있다.<br>
   - 본 기능의 권한은 프로젝트에 참여한 인원으로 제한한다.<br>

5. 검색
   - 키워드를 통해 특정 스케줄을 검색할 수 있다.<br>
   - 스케줄 검색 시 검색 결과 스케줄 정보를 보여준다.<br>

6. 실시간 공유 및 업데이트 **(보류)**
   - 스케줄의 변경사항 생길 시 참여 인원 모두에게 실시간으로 수정 사항을 공유한다.<br>

7. 파일 업데이트
   - 프로젝트에서 공유되어야 할 파일을 업로드한다. <br>
   - 본 기능 권한은 프로젝트 참여 인원으로 제한한다.<br>

8. 파일 다운로드
   - 프로젝트에서 공유되어야 할 파일을 다운로드 가능하다.<br>
   - 본 기능 권한은 프로젝트 참여 인원으로 제한한다.<br>


#### <관리자 페이지>

1. 회원 조회 
   - 가입된 회원을 전체 조회, 상세 조회할 수 있다. <br>
   - 가입된 회원을 검색할 수 있다.<br>

2. 회원 정보 수정
   - 관리자가 회원의 정보를 직접 수정할 수 있다.<br>
    
3. 회원 정보 삭제
   - 관리자가 회원의 정보를 직접 삭제할 수 있다.<br>

4. 회원 정보 검색
   - 관리자가 회원 정보를 검색할 수 있다.<br>
   
5. 프로젝트 조회 
   - 프로젝트를 전체 조회, 상세 조회 할 수 있다.<br>
   - 프로젝트 관련 키워드를 통해 검색할 수 있다.<br>

6. 프로젝트 삭제
   - 관리자가 프로젝트 정보를 삭제할 수 있다.<br>

7. 프로젝트 검색
   - 관리자가 프로젝트 검색할 수 있다.<br>   

8. 방문자수 조회
   - 사이트의 하루 방문자 수 조회가 가능하다.<br>

9. 프로젝트 관리 권한 위임
    - 프로젝트 관리 권한 요청이 들어올 시 기존 프로젝트 관리자에서 다른 팀원으로 관리자 권한을 위임할 수 있다.<br>

#### <마이 페이지>

1. 회원 정보 수정
   - 회원은 마이페이지에서 개인 정보를 수정할 수 있다.(아이디, 닉네임, 비밀번호 등)
   - 회원은 마이페이지의 개인정보 수정 창에서 회원탈퇴를 할 수 있다.

2. 문의 내역 조회
   - 회원은 본인이 관지라에게 문의한 문의 내역을 전체 조회, 상세 조회할 수 있다.
   
-----------------------------------------------

### 프로젝트 설치 및 실행 방법

-----------------------------------------------

### 프로젝트 사용 방법

-----------------------------------------------

https://www.freecodecamp.org/korean/news/gisheobeu-peurojegteue-rideumi-paileul-jal-jagseonghaneun-bangbeob/
