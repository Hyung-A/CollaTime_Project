use CollaTime;

-- 테이블 삭제
DROP TABLE IF EXISTS INQUIRY CASCADE;
DROP TABLE IF EXISTS VISIT CASCADE;
DROP TABLE IF EXISTS JOIN_PROJECT CASCADE;
DROP TABLE IF EXISTS CT_SCHEDULE CASCADE;
DROP TABLE IF EXISTS PROJECT CASCADE;
DROP TABLE IF EXISTS CATEGORY CASCADE;
DROP TABLE IF EXISTS CT_USER CASCADE;
DROP TABLE IF EXISTS COLOR CASCADE;
DROP TABLE IF EXISTS JOIN_SCHEDULE CASCADE;

-- 멤버 테이블 생성
CREATE TABLE IF NOT EXISTS CT_USER
(
    CT_USER_NO    INT AUTO_INCREMENT PRIMARY KEY COMMENT '회원번호',
    CT_USER_ID    VARCHAR(255) NOT NULL UNIQUE COMMENT '아이디',
    PASSWORD    VARCHAR(255) NOT NULL COMMENT '비밀번호',
    CT_USER_EMAIL    VARCHAR(50) NOT NULL COMMENT '이메일',
    CT_USER_NAME    VARCHAR(20) NOT NULL COMMENT '이름',
    CT_USER_NICKNAME    VARCHAR(20) NOT NULL UNIQUE COMMENT '닉네임',
    CT_USER_PICTURE    VARCHAR(50) NOT NULL COMMENT '프로필 그림',
    CT_USER_COLOR    VARCHAR(50) NOT NULL COMMENT '프로필 색',
    CT_USER_ROLE    VARCHAR(10) NOT NULL default 'USER' COMMENT '역할'
    )ENGINE=INNODB COMMENT = '회원';

-- 색상 테이블 생성
CREATE TABLE IF NOT EXISTS COLOR
(
    COLOR_CODE   VARCHAR(10) PRIMARY KEY COMMENT '색상코드',
    COLOR_NAME   VARCHAR(10) NOT NULL COMMENT '색상명'
    ) ENGINE=INNODB COMMENT = '색상코드';


-- 카테고리 테이블 생성
CREATE TABLE IF NOT EXISTS CATEGORY
(
    PROJECT_CATEGORY_CODE VARCHAR(10) PRIMARY KEY COMMENT '프로젝트 카테고리 코드',
    CATEGORY_NAME VARCHAR(10) NOT NULL COMMENT '카테고리 명'
    ) ENGINE = INNODB COMMENT '카테고리';

-- 프로젝트 테이블 생성
CREATE TABLE IF NOT EXISTS PROJECT
(
    PROJECT_NO INT PRIMARY KEY AUTO_INCREMENT COMMENT '프로젝트 번호',
    PROJECT_NAME VARCHAR(30) NOT NULL COMMENT '프로젝트 명',
    PROJECT_CATEGORY_CODE VARCHAR(10) COMMENT '프로젝트 카테고리 코드',
    PROJECT_START_DATE VARCHAR(255) NOT NULL COMMENT '프로젝트 시작 일자',
    PROJECT_END_DATE VARCHAR(255) NOT NULL COMMENT '프로젝트 종료 일자',
    PROJECT_SUMMARY VARCHAR(255) NOT NULL COMMENT '프로젝트 개요',
    PROJECT_PURPOSE VARCHAR(255) NOT NULL COMMENT '프로젝트 목적',
    PRODUCTOR_NO INT NOT NULL COMMENT '생성자 회원 번호'
    ) ENGINE = INNODB COMMENT '프로젝트';

-- 스케줄 테이블 생성
CREATE TABLE IF NOT EXISTS CT_SCHEDULE
(
    CT_SCHEDULE_NO    INT AUTO_INCREMENT PRIMARY KEY COMMENT '스케줄 번호',
    PROJECT_NO    INT NOT NULL COMMENT '프로젝트 번호',
    CT_SCHEDULE_START_DATE    DATE NOT NULL COMMENT '스케줄 시작일자',
    CT_SCHEDULE_END_DATE    DATE NOT NULL COMMENT '스케줄 종료일자',
    CT_SCHEDULE_CONTENT    VARCHAR(255) COMMENT '스케줄 내용',
    CT_SCHEDULE_TITLE    VARCHAR(255) NOT NULL COMMENT '스케줄 제목',
    CT_SCHEDULE_CREATOR    VARCHAR(255) NOT NULL COMMENT '스케줄 생성자',
    COLOR_CODE    VARCHAR(10) NOT NULL COMMENT '색상코드',
    CONSTRAINT FK_SCHEDULE_PROJECT_NO FOREIGN KEY (PROJECT_NO) REFERENCES PROJECT (PROJECT_NO) ON DELETE CASCADE,
    CONSTRAINT FK_SCHEDULE_COLOR_CODE FOREIGN KEY (COLOR_CODE) REFERENCES COLOR (COLOR_CODE) ON DELETE CASCADE
    ) ENGINE=INNODB COMMENT = '스케줄';

-- 프로젝트 참가자 테이블 생성
CREATE TABLE IF NOT EXISTS JOIN_PROJECT
(
    PROJECT_NO INT NOT NULL COMMENT '프로젝트 번호',
    CT_USER_NO INT NOT NULL COMMENT '회원 번호',
    RANDOM_JOIN_CODE VARCHAR(10) NOT NULL COMMENT '참가 랜덤 코드',
    EMAIL VARCHAR(225) NOT NULL COMMENT '회원 이메일'
    ) ENGINE = INNODB COMMENT '프로젝트 참가자';

-- 문의 테이블 생성
CREATE TABLE IF NOT EXISTS INQUIRY
(
    INQUIRY_NO    INT AUTO_INCREMENT PRIMARY KEY COMMENT '문의번호',
    INQUIRY_STATUS    VARCHAR(5) NOT NULL COMMENT '문의상태',
    INQUIRY_TITLE    VARCHAR(255) NOT NULL COMMENT '문의제목',
    INQUIRY_CONTENT    VARCHAR(255) NOT NULL COMMENT '문의내용',
    CT_USER_NO    INT NOT NULL COMMENT '회원번호',
    ANSWER_CONTENT    VARCHAR(255) COMMENT '답변내용',
    CONSTRAINT FK_USER_NO FOREIGN KEY (CT_USER_NO) REFERENCES CT_USER (CT_USER_NO) ON DELETE CASCADE
    )ENGINE=INNODB COMMENT = '문의';

-- 방문 테이블 생성
CREATE TABLE IF NOT EXISTS VISIT
(
--  DATE는 자동증가가 되지 않기 때문에 12시마다 자동으로 등록되도록 만들어야할 것 같다.
    VISIT_DATE    DATE PRIMARY KEY COMMENT '날짜',
    VISIT_COUNT    INT NOT NULL COMMENT '방문자수'
)ENGINE=INNODB COMMENT = '방문';

-- 스케줄 참가자 테이블 생성
CREATE TABLE IF NOT EXISTS JOIN_SCHEDULE
(
    MEMBER_NO    INT NOT NULL COMMENT '회원번호',
    SCHEDULE_NO    INT NOT NULL COMMENT '스케줄 번호'
) ENGINE=INNODB COMMENT = '스케줄 참가자';


-- 멤버 테이블 데이터 삽입
INSERT INTO CT_USER VALUES
                        (1, 'gudtjrWkd', 'gudtjrWkdWKd12!', 'gudtjr@gmail.com', '조형석', '형석짱짱', 'a2','red', 'ADMIN'),
                        (2, 'wngPqueen', 'wngPking321@@', 'wngP@gmail.com', '김주혜', '주혜퀸', 'a6','green', 'USER'),
                        (3, 'gkdmsgood', 'good1234!@', 'gkdms@gmail.com', '이하은', '하은굿', 'a7','blue', 'USER'),
                        (4, 'tndhksking', 'king!!1', 'tndhks@gmail.com', '박수완', '수완킹', 'a1', 'red', 'USER');

-- 문의 테이블 데이터 삽입
INSERT INTO INQUIRY VALUES
                        (1, '처리완료','마이페이지', '마이페이지가 보고싶어요', 2, '아직 개발중입니다.'),
                        (2, '처리중','스케줄 색상', '스케줄 색상 좀 늘려주세요', 3, null);

-- 방문 테이블 데이터 삽입
INSERT INTO VISIT VALUES
                      ('2024-10-27', 100),
                      ('2024-10-28', 60);

-- 카테고리 테이블 데이터 삽입
INSERT INTO CATEGORY VALUES("CT1", "은행");
INSERT INTO CATEGORY VALUES("CT2", "보험사");
INSERT INTO CATEGORY VALUES("CT3", "공공부문");
INSERT INTO CATEGORY VALUES("CT4", "웹포털");
INSERT INTO CATEGORY VALUES("CT5", "앱개발");
INSERT INTO CATEGORY VALUES("CT6", "기타");

-- 프로젝트 테이블 데이터 삽입
INSERT INTO PROJECT VALUES (null,"계산기 프로그램 개발", "CT5", "2023-11-15", "2023-12-20", "계산기 프로그램 어플리케이션 만들기", "복잡한 기능은 삭제된 간단하고 사용하기 쉬운 계산기 기능만 들어간 가장 기본적인 계산기 어플리케이션을 만든다.", "1" );
INSERT INTO PROJECT VALUES (null, "엽서 판매 웹사이트 제작", "CT4", "2024-02-01", "2024-04-20", "엽서 판매 웹 사이트 제작하기", "사진 취미러들의 사진을 받아 엽서로 제작하여 사진 입문자들의 사진도 대중들에게 사랑받을 수 있는 기회를 제공한다.", "2" );

-- 프로젝트 참가자 테이블 데이터 삽입
INSERT INTO JOIN_PROJECT VALUES(1, "-1", "ADF182WE", 'user1@gmail.com'),
							   (1, "-1", "GEF864SD", 'user2@gmail.com'),
							   (1, "-1", "WDS648HD", 'user3@gmail.com'),
							   (1, "-1", "HDW234GH", 'user4@gmail.com'),
							   (2, "-1", "GSE846DS", 'user5@gmail.com'),
							   (2, "-1", "KFG697GK", 'user6@gmail.com'),
							   (2, "-1", "LWO465DJ", 'user7@gmail.com'),
							   (2, "-1", "NKJ357DL", 'user8@gmail.com');

-- 색상 테이블 데이터 삽입
INSERT INTO COLOR VALUES('CO1','빨간색');
INSERT INTO COLOR VALUES('CO2','주황색');
INSERT INTO COLOR VALUES('CO3','노란색');
INSERT INTO COLOR VALUES('CO4','초록색');
INSERT INTO COLOR VALUES('CO5','파란색');
INSERT INTO COLOR VALUES('CO6','남색');
INSERT INTO COLOR VALUES('CO7','보라색');


-- 스케줄 테이블 데이터 삽입
INSERT INTO CT_SCHEDULE VALUES(1,1,'24-10-28','24-10-30',"","",1,'CO1');

-- 스케줄 참가자 데이터 삽입
INSERT INTO JOIN_SCHEDULE VALUES(1,1);
