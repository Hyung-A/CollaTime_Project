CREATE DATABASE CollaTime;
-- CREATE SCHEMA menudb;


 CREATE DATABASE CollaTime;
GRANT ALL PRIVILEGES ON CollaTime.* TO 'ohgiraffers'@'%';

SHOW GRANTS FOR 'ohgiraffers'@'%';

USE CollaTime;




제약조건때문에 테이블 삭제가 안될 경우

1.root 계정으로 접속 후

1)다음 명령어 collatime 삭제
drop database collatime;

2)collatime 생성

CREATE DATABASE CollaTime character set =utf8mb4 collate =utf8mb4_unicode_ci;
-- 유니코드 오류 방지

-- create user  'ohgiraffers'@'%' identified by 'ohgiraffers';
GRANT ALL PRIVILEGES ON CollaTime.* TO 'ohgiraffers'@'%';


2,collatime 데이터베이스 접속 후 실행


'ohgiraffers'@'%'  라는 유저생성
create user  'ohgiraffers'@'%' identified by 'ohgiraffers'


       'ohgiraffers'@'%' 를 CollaTime 데이터 베이스 모든 권한 부여 명령어
GRANT ALL PRIVILEGES ON CollaTime.* TO 'ohgiraffers'@'%';




















