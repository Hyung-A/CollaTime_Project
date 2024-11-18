use CollaTime;

-- 테이블 삭제
DROP TABLE IF EXISTS INQUIRY CASCADE;
DROP TABLE IF EXISTS VISIT CASCADE;
DROP TABLE IF EXISTS JOIN_PROJECT CASCADE;
DROP TABLE IF EXISTS CT_SCHEDULE_PARTICIPANT CASCADE;
DROP TABLE IF EXISTS CT_SCHEDULE CASCADE;
DROP TABLE IF EXISTS PROJECT CASCADE;
DROP TABLE IF EXISTS CATEGORY CASCADE;
DROP TABLE IF EXISTS CT_USER CASCADE;
DROP TABLE IF EXISTS COLOR CASCADE;
-- DROP TABLE IF EXISTS CT_SCHEDULE_PARTICIPANT CASCADE;
-- DROP TABLE IF EXISTS JOIN_SCHEDULE CASCADE;

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

CREATE TABLE `ct_schedule` (
                               `CT_SCHEDULE_NO` int(100) NOT NULL AUTO_INCREMENT COMMENT '스케줄 번호',
                               `PROJECT_NO` int(100) NOT NULL COMMENT '프로젝트 번호',
                               `CT_SCHEDULE_START_DATE` date NOT NULL COMMENT '스케줄 시작일자',
                               `CT_SCHEDULE_END_DATE` date NOT NULL COMMENT '스케줄 종료일자',
                               `CT_SCHEDULE_CONTENT` varchar(255) DEFAULT NULL COMMENT '스케줄 내용',
                               `CT_SCHEDULE_TITLE` varchar(255) NOT NULL COMMENT '스케줄 제목',
                               `CT_SCHEDULE_CREATOR` varchar(255) NOT NULL COMMENT '스케줄 생성자',
                               `COLOR_CODE` varchar(10) NOT NULL COMMENT '색상코드',
                               `TEXT_COLOR_CODE` varchar(10) DEFAULT NULL COMMENT '글자색',
                               PRIMARY KEY (`CT_SCHEDULE_NO`),
                               KEY `FK_SCHEDULE_PROJECT_NO` (`PROJECT_NO`),
                               KEY `FK_SCHEDULE_COLOR_CODE` (`COLOR_CODE`),
                               CONSTRAINT `FK_SCHEDULE_PROJECT_NO` FOREIGN KEY (`PROJECT_NO`) REFERENCES `project` (`PROJECT_NO`) ON DELETE CASCADE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='스케줄';



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
    PROJECT_NO INT COMMENT '위임 프로젝트번호',
    PASSAUTH_NAME VARCHAR(20) COMMENT '위임받는 회원번호',
    CONSTRAINT FK_USER_NO FOREIGN KEY (CT_USER_NO) REFERENCES CT_USER (CT_USER_NO) ON DELETE CASCADE
    )ENGINE=INNODB COMMENT = '문의';

-- 방문 테이블 생성
CREATE TABLE IF NOT EXISTS VISIT
(
--  DATE는 자동증가가 되지 않기 때문에 12시마다 자동으로 등록되도록 만들어야할 것 같다.
    VISIT_DATE    DATE PRIMARY KEY COMMENT '날짜',
    VISIT_COUNT    INT NOT NULL COMMENT '방문자수'
)ENGINE=INNODB COMMENT = '방문';

-- -- 스케줄 참가자 테이블 생성
-- CREATE TABLE IF NOT EXISTS JOIN_SCHEDULE
-- (
--     MEMBER_NO    INT NOT NULL COMMENT '회원번호',
--     SCHEDULE_NO    INT NOT NULL COMMENT '스케줄 번호'
-- ) ENGINE=INNODB COMMENT = '스케줄 참가자';

-- 스케줄 참여 팀원 테이블 생성
CREATE TABLE `ct_schedule_participant` (
                                           `CT_SCHEDULE_NO` int(50) NOT NULL COMMENT '스케줄 번호',
                                           `PARTICIPANT_NO` int(11) NOT NULL COMMENT '파티참가 회원번호',
                                           CONSTRAINT `FK_CT_SCHEDULE_NO` FOREIGN KEY (`CT_SCHEDULE_NO`) REFERENCES `ct_schedule` (`CT_SCHEDULE_NO`) ON DELETE CASCADE,
                                           CONSTRAINT `FK_CT_USER_NO` FOREIGN KEY (`PARTICIPANT_NO`) REFERENCES `ct_user` (`CT_USER_NO`) ON DELETE CASCADE,
                                           INDEX `idx_schedule_participant` (`CT_SCHEDULE_NO`, `PARTICIPANT_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='스케쥴 파티원 테이블';

-- 멤버 테이블 데이터 삽입
INSERT INTO CT_USER VALUES
                        (1, 'gudtjrWkd', 'gudtjrWkdWKd12!', 'gudtjr@gmail.com', '조형석', '형석짱짱', 'a2','red', 'ADMIN'),
                        (2, 'wngPqueen', 'wngPking321@@', 'wngP@gmail.com', '김주혜', '주혜퀸', 'a6','green', 'USER'),
                        (3, 'gkdmsgood', 'good1234!@', 'gkdms@gmail.com', '이하은', '하은굿', 'a7','blue', 'USER'),
                        (4, 'tndhksking', 'king!!1', 'tndhks@gmail.com', '박수완', '수완킹', 'a1', 'red', 'USER');
                        

-- 문의 테이블 데이터 삽입
INSERT INTO INQUIRY VALUES
                        (1, '읽음','마이페이지', '마이페이지가 보고싶어요', 2, '아직 개발중입니다.', null, null),
                        (2, '답변','마이페이지', '프로필 늘려주세요', 1, '아직 개발중입니다.', null, null),
                        (3, '문의처리중','스케줄 색상', '스케줄 색상 좀 늘려주세요', 3, null, null, null),
                        (4, '답변','스케줄 글자 색', '스케줄 구간에 있는 색 때문에 검정 글씨가 잘 안보일 때가 있어요 ㅠㅜ 글자색도 설정 가능하게 해주시면 감사하겠습니다.', 5, '글자 색 변경 기능이 이번에 업데이트 되었습니다!! 소중한 의견 감사드리고 항상 CollaTime을 사용해주셔서 감사합니다.', null, null),
                        (5, '읽음','프로필 종류', '프로필이 정말 귀염뽀짝하네요. 그런데 종류가 너무 적습니다 ㅠㅜ 프로필을 종류를 좀 더 만들어주세요!!! 저는 코뿔소 좋아합니다 ㅎㅎㅎ', 5, '프로필 종류는 현재 개발중에 있습니다. 이번년도 말까지 업데이트 예정이니 기다려주세요!', null, null),
                        (6, '답변','정말 화가 나네요.', '너무 잘 쓰고 있어서 화가 나네요 ㅎㅎ 앞으로도 많은 기능이 추가될 것이라고 생각합니다ㅎㅎ 개발자님 화이팅!', 5, '감사합니다! ㅎㅎ 앞으로도 더 발전하는 CollaTime이 되겠습니다.', null, null);
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
INSERT INTO PROJECT VALUES (null,"계산기 프로그램 개발", "CT5", "2023-11-15", "2023-12-20", "계산기 프로그램 어플리케이션 만들기", "복잡한 기능은 삭제된 간단하고 사용하기 쉬운 계산기 기능만 들어간 가장 기본적인 계산기 어플리케이션을 만든다.", "1" ),
						   (null, "엽서 판매 웹사이트 제작", "CT4", "2024-02-01", "2024-04-20", "엽서 판매 웹 사이트 제작하기", "사진 취미러들의 사진을 받아 엽서로 제작하여 사진 입문자들의 사진도 대중들에게 사랑받을 수 있는 기회를 제공한다.", "2" ),
                           (null, "계산기 어플", "CT5", "2024-10-25","2024-11-25","장사를 하시는 어르신들이 사용하기 편한 계산기 어플을 개발하고자 한다.", "크고 단순한 UI와 정말 기본적인 사칙연산 및 장사에 필요한 계산 기능만 들어있는 계산기 어플을 개발하여 어르신들도 사용하기 좋은 계산기를 제공하는 것이 목표이다.", "5"),
                           (null, "아이스크림 가게 키오스크", "CT4", "2024-11-05","2024-12-20","아이스크림 가게 키오스크 제작", "아이스크림 가게 키오스크 제작", "5");

-- 프로젝트 참가자 테이블 데이터 삽입
INSERT INTO JOIN_PROJECT VALUES(1, "-1", "ADF182WE", 'user1@gmail.com'),
							   (1, "-1", "GEF864SD", 'user2@gmail.com'),
							   (1, "-1", "WDS648HD", 'user3@gmail.com'),
							   (1, "-1", "HDW234GH", 'user4@gmail.com'),
							   (2, "-1", "GSE846DS", 'user5@gmail.com'),
							   (2, "-1", "KFG697GK", 'user6@gmail.com'),
							   (2, "-1", "LWO465DJ", 'user7@gmail.com'),
							   (2, "-1", "NKJ357DL", 'user8@gmail.com'),
                               (3, "2", "JFH23!KF", 'wngP@gmail.com'),
                               (3, "4", "HE2KF3LF", 'tndhks@gmail.com'),
                               (4, "3", "H3SDH312", 'gkdms@gmail.com');

-- 색상 테이블 데이터 삽입
INSERT INTO COLOR VALUES('CO1','빨간색');
INSERT INTO COLOR VALUES('CO2','주황색');
INSERT INTO COLOR VALUES('CO3','노란색');
INSERT INTO COLOR VALUES('CO4','초록색');
INSERT INTO COLOR VALUES('CO5','파란색');
INSERT INTO COLOR VALUES('CO6','남색');
INSERT INTO COLOR VALUES('CO7','보라색');

INSERT INTO CT_SCHEDULE VALUES(0, 3, "2024-10-25", "2024-10-25", "새 프로젝트 기획 회의 있습니다.","새 프로젝트 기획 회의", "test", "#FEC4C4", "#000000");
INSERT INTO CT_SCHEDULE VALUES(0, 3, "2024-10-28", "2024-10-30", "새 프로젝트 기획 기간입니다. 각자 생각하시고 기간 안에 제출해주세요.", "새 프로젝트 기획안 제출", "test", "#D9F0FB","#000000"),
                              (0, 3, "2024-10-31", "2024-10-31", "UI 기획 회의 있습니다.", "UI 기획 회의", "test", "#EDD5C0","#000000"),
                              (0, 3, "2024-10-31", "2024-11-05","UI 기획안 제출 기간입니다. 기간안에 제출해주세요.", "UI 기획안 제출", "test", "#FFDAFA", "#000000"),
                              (0, 3, "2024-11-06", "2024-11-20","프로그램 개발 기간입니다. 화이팅해주세요", "개발 기간", "test", "#B7E58E", "#000000"),
                              (0, 3, "2024-11-21", "2024-11-22","프로그램 시연 및 테스트 기간", "프로그램 테스트 및 수정", "test", "#FFEFCC", "#000000");
INSERT INTO CT_SCHEDULE VALUES(0, 3, "2024-11-25", "2024-11-25","프로젝트 최종 발표 및 프로그램 배포하는 날입니다. 조금만 화이팅 해주세요.", "프로젝트 최종 보고회", "test", "#DADFFF", "#000000");
                              

--
-- -- 스케줄 테이블 데이터 삽입
-- INSERT INTO CT_SCHEDULE VALUES(1,1,'24-10-28','24-10-30',"","",1,'CO1');
--
-- -- 스케줄 참가자 데이터 삽입
-- INSERT INTO JOIN_SCHEDULE VALUES(1,1);


-- -- 스케줄 테이블 데이터 삽입
-- -- INSERT INTO CT_SCHEDULE VALUES(1,1,'24-10-28','24-10-30',"","",1,'CO1');


-- 방문자 데이터 추가
insert into visit values ('2024-11-12', 140);
insert into visit values ('2024-11-11', 120);
insert into visit values ('2024-01-11', 120);
insert into visit values ('2024-02-11', 140);
insert into visit values ('2024-03-11', 290);
insert into visit values ('2024-04-11', 500);
insert into visit values ('2024-05-11', 620);
insert into visit values ('2024-06-11', 1000);
insert into visit values ('2024-07-11', 900);
insert into visit values ('2024-08-11', 830);
insert into visit values ('2024-09-11', 450);
insert into visit values ('2024-10-11', 300);
insert into visit values ('2024-12-11', 348);
insert into visit values ('2024-11-18', 50);
insert into visit values ('2024-11-19', 70);
insert into visit values ('2024-11-20', 56);
insert into visit values ('2024-11-21', 84);
insert into visit values ('2024-11-22', 96);
insert into visit values ('2024-11-23', 45);
insert into visit values ('2024-11-24', 18);