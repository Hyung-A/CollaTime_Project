--------------------------------------
## 협업 규칙

1. 금일 협업 시작 전 간단한 회의를 통해 각자 업무 분담
2. 회의 직후에 형상관리자는 금일 구현할 기능에 대한 Issue 만들기
3. 형상 관리자가 Issue 만들면 다같이 Pull 받아서 작업 시작하기
4. 하원 한 시간 전(17시) 작업 중인 기능 Push & PullRequest 올린 후 다같이 Merge 하기
5. 위 해당 시간 안에 기능 구현을 다 마치지 못했을 경우 현 위치까지(오류 난다면 주석 처리 한 후)만 올리기
6. Push/Issue/PullRequest 시 아래 명시된 명명 규칙 따르기
7. Merge 후에는 이슈 닫기

--------------------------------------
## 명명 규칙

#### Branch : <br>
feature/이슈 번호 - 기능명 (기능 명을 영어로 작성 시 '앞글자는 대문자 + 카멜케이스 형식' ) <br>
ex) feature/1-LogIn

#### Issue : <br>
[feature][domain]@@ (@@ : 한글로 작성 )<br>
ex) [feature][scheduler] 로그인

#### PullRequest : <br>
[feature][domain]/#(이슈번호) @@ (@@ : 기능 명을 영어로 작성 시 '앞글자는 대문자 + 카멜케이스 형식' )<br>
ex) [feature][schdule]/#1 LogIn

#### Commit : <br>
금일 날짜_feature/이슈 번호 - 기능명(기능 명을 영어로 작성 시 '앞글자는 대문자 + 카멜케이스 형식' ) <br>
ex) 241016_feature/1-LogIn


