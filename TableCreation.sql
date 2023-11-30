-----------------------------------------------------------------------------------------------
------------------------------------------- 테이블 삭제 -----------------------------------------
-----------------------------------------------------------------------------------------------

-- 강성 개체 테이블
DROP TABLE 개인회원 CASCADE CONSTRAINTS;
DROP TABLE 기업 CASCADE CONSTRAINTS;
DROP TABLE 기업회원 CASCADE CONSTRAINTS;
-- 약성 개체 테이블
DROP TABLE 이력서 CASCADE CONSTRAINTS;
DROP TABLE 채용_게시글 CASCADE CONSTRAINTS;
DROP TABLE 채용_설명회 CASCADE CONSTRAINTS;
-- 다중값 테이블
DROP TABLE 이력서_자격증 CASCADE CONSTRAINTS;
DROP TABLE 이력서_경력 CASCADE CONSTRAINTS;
-- 관계 테이블
DROP TABLE 포지션_제안 CASCADE CONSTRAINTS;
DROP TABLE 지원 CASCADE CONSTRAINTS;
-- 통계 및 변경 테이블
DROP TABLE 게시글수 CASCADE CONSTRAINTS;
DROP TABLE 개인_포인트_수정_내역 CASCADE CONSTRAINTS;
DROP TABLE 기업_포인트_수정_내역 CASCADE CONSTRAINTS;
DROP TABLE 개인_회원_정보_변경 CASCADE CONSTRAINTS;
DROP TABLE 기업_회원_정보_변경 CASCADE CONSTRAINTS;
DROP TABLE 연봉_평균_계산 CASCADE CONSTRAINTS;

-- 시퀀스
DROP SEQUENCE 채용_게시글번호_SEQ;
-----------------------------------------------------------------------------------------------
------------------------------------------- 테이블 생성 -----------------------------------------
-----------------------------------------------------------------------------------------------

-- 강성 개체
CREATE TABLE 개인회원(
    회원ID NVARCHAR2(32),
    비밀번호 NVARCHAR2(32),
    이름 NCHAR(8),
    생년월일 DATE,
    성별 NCHAR (2),
    휴대폰 NCHAR(13),
    거주_지역 NCHAR(6),
    개인정보_유효기간 NUMBER(2,0),
    가입일자 DATE,
    포인트 NUMBER(38,0),
    이력서_작성수 NUMBER(38,0),
    기업_이름 NVARCHAR2(60),
    연봉 NUMBER(38,0),
    직책 NCHAR(6),
    CONSTRAINT PK_개인회원 PRIMARY KEY(회원ID),
    CONSTRAINT UK_개인회원 UNIQUE (휴대폰)
);

CREATE TABLE 기업(
 이름 NVARCHAR2(60),
 산업 NVARCHAR2(60),
 기업구분 NVARCHAR2(20),
 자본금 NUMBER(38,0),
 대표자 NVARCHAR2(60),
 사원수 NUMBER(5,0),
 설립일 DATE,
 매출액 NUMBER(38,0),
 대졸초임 NUMBER(38,0),
 지역 NCHAR(6),
 CONSTRAINT PK_기업 PRIMARY KEY(이름)
);

CREATE TABLE 기업회원(
    기업명 NVARCHAR2 (60),
    회원ID NVARCHAR2(32),
    비밀번호 NVARCHAR2(32),
    이름 NVARCHAR2(8),
    사업자등록번호 NVARCHAR2(12),
    휴대폰 NCHAR(13),
    개인정보_유효기간 NUMBER(2,0),
    가입일자 DATE,
    포인트 NUMBER(38,0),
    게시글_작성수 NUMBER(38,0),
    CONSTRAINT PK_기업회원 PRIMARY KEY(회원ID),
    CONSTRAINT FK_기업회원 FOREIGN KEY (기업명) REFERENCES 기업(이름)
);

-- 약성 개체
CREATE TABLE 이력서(
    작성자ID NVARCHAR2(32),
    이력서명 NVARCHAR2(40),
    학력 NVARCHAR2(16),
    토익 NUMBER(3,0),
    해외_경험_횟수 NUMBER(2,0),
    작성일자 DATE,
    CONSTRAINT PK_이력서 PRIMARY KEY (작성자ID, 이력서명),
    CONSTRAINT FK_이력서 FOREIGN KEY(작성자ID) REFERENCES 개인회원(회원ID)
);

CREATE TABLE 채용_게시글(
    게시글_번호 NUMBER(38,0),
    작성자ID NVARCHAR2(32),
    직종 NVARCHAR2(60),
    채용_분류 NVARCHAR2(60),
    고용형태 NVARCHAR2(10),
    급여 NUMBER(38,0),
    지역 NCHAR(6),
    근무시간 NUMBER(2,0),
    모집인원 NUMBER(38,0),
    직책 NCHAR(6),
    마감일 DATE,
    CONSTRAINT PK_채용_게시글 PRIMARY KEY(게시글_번호),
    CONSTRAINT FK_채용_게시글 FOREIGN KEY (작성자ID) REFERENCES 기업회원(회원ID)
);

CREATE TABLE 채용_설명회(
    기업명 NVARCHAR2(60),
    설명회명 NVARCHAR2(60),
    회차 NUMBER(3,0),
    일시 DATE,
    장소 NVARCHAR2(60),
    CONSTRAINT PK_채용_설명회 PRIMARY KEY(기업명, 설명회명, 회차),
    CONSTRAINT FK_채용_설명회 FOREIGN KEY (기업명) REFERENCES 기업(이름)
);

-- 다중값 테이블
CREATE TABLE 이력서_자격증(
    이력서_작성자 NVARCHAR2(32),
    이력서명 NVARCHAR2(40),
    자격증명 NVARCHAR2(32),
    CONSTRAINT PK_이력서_자격증 PRIMARY KEY(이력서_작성자,이력서명,자격증명),
    CONSTRAINT FK_이력서_자격증 FOREIGN KEY(이력서_작성자,이력서명) REFERENCES 이력서(작성자ID,이력서명)
);

CREATE TABLE 이력서_경력(
    회원ID NVARCHAR2(32),
    이력서명 NVARCHAR2(40),
    경력_위치 NVARCHAR2(60),
    년수 NUMBER(2,0),
    직급 NCHAR(6),
    연봉 NUMBER(11,0),
    CONSTRAINT PK_이력서_경력 PRIMARY KEY (회원ID,이력서명,경력_위치),
    CONSTRAINT FK_이력서_경력 FOREIGN KEY (회원ID, 이력서명) REFERENCES 이력서(작성자ID, 이력서명)
);

-- 관계 테이블
CREATE TABLE 포지션_제안(
    제안자 NVARCHAR2(32),
    이력서_작성자 NVARCHAR2(32),
    이력서명 NVARCHAR2(40),
    마감기한 DATE,
    CONSTRAINT FK_포지션_제안_제안자 FOREIGN KEY(제안자) REFERENCES 기업회원(회원ID),
    CONSTRAINT FK_포지션_제안_이력서 FOREIGN KEY(이력서_작성자, 이력서명) REFERENCES 이력서(작성자ID, 이력서명),
    CONSTRAINT PK_포지션_제안 PRIMARY KEY(제안자, 이력서_작성자,이력서명)
);

CREATE TABLE 지원(
    게시글_번호 NUMBER(38,0),
    지원자 NVARCHAR2 (32),
    이력서명 NVARCHAR2(40),
    일시정보 DATE,
    합격_여부 CHAR(1),
    CONSTRAINT FK_지원_채용게시글 FOREIGN KEY (게시글_번호) REFERENCES 채용_게시글(게시글_번호),
    CONSTRAINT FK_지원_이력서 FOREIGN KEY (지원자, 이력서명) REFERENCES 이력서(작성자ID, 이력서명),
    CONSTRAINT PK_지원 PRIMARY KEY(게시글_번호,지원자,이력서명)
);

-- 변경내역 및 통계 저장 테이블
CREATE TABLE 게시글수(
    분류 NVARCHAR2(32),
    게시글수 NUMBER(38,0)
);

CREATE TABLE 개인_포인트_수정_내역(
    회원ID NVARCHAR2(32),
    내역 NCHAR(40),
    포인트 NUMBER(38,0),
    CONSTRAINT FK_개인_포인트수정내역 FOREIGN KEY (회원ID) REFERENCES 개인회원(회원ID)
);

CREATE TABLE 기업_포인트_수정_내역(
    회원ID NVARCHAR2(32),
    내역 NCHAR(40),
    포인트 NUMBER(38,0),
    CONSTRAINT FK_기업_포인트수정내역 FOREIGN KEY (회원ID) REFERENCES 기업회원(회원ID)
);

CREATE TABLE 개인_회원_정보_변경(
    회원ID NVARCHAR2(32),
    변경_열 NCHAR(40),
    변경_데이터 NVARCHAR2(76),
    CONSTRAINT FK_개인_정보_수정내역 FOREIGN KEY (회원ID) REFERENCES 기업회원(회원ID)
);

CREATE TABLE 기업_회원_정보_변경(
    회원ID NVARCHAR2(32),
    변경_열 NCHAR(40),
    변경_데이터 NVARCHAR2(76),
    CONSTRAINT FK_기업_정보_수정내역 FOREIGN KEY (회원ID) REFERENCES 기업회원(회원ID)
);

CREATE TABLE 연봉_평균_계산(
    기업명 NVARCHAR2(60),
    직책 NCHAR(6),
    평균 NUMBER(38,0),
    CONSTRAINT FK_연봉_평균_계산 FOREIGN KEY (기업명) REFERENCES 기업(이름),
    CONSTRAINT PK_연봉_평균_계산 PRIMARY KEY (기업명, 직책)
);
-----------------------------------------------------------------------------------------------
-------------------------------------------- 시퀀스 --------------------------------------------
-----------------------------------------------------------------------------------------------
-- 시퀀스: 게시글 번호 자동 부여
CREATE SEQUENCE 채용_게시글번호_SEQ
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 99999999
CYCLE;

-----------------------------------------------------------------------------------------------
-------------------------------------------- 트리거 --------------------------------------------
-----------------------------------------------------------------------------------------------
-- 시퀀스: 게시글 번호 자동 부여
CREATE OR REPLACE TRIGGER 채용_게시글번호_SEQ_TRIG BEFORE INSERT ON 채용_게시글
FOR EACH ROW
BEGIN
    SELECT 채용_게시글번호_SEQ.NEXTVAL INTO :NEW.게시글_번호 FROM DUAL;
END;

-- 회원 정보 수정 평균 연봉 재연산 및 정보 변경 내역 저장

-- 채용/설명회 게시글 작성

-- 포인트 충전 및 사용

-- 회원 탈퇴
-----------------------------------------------------------------------------------------------
------------------------------------------- 프로시저 -------------------------------------------
-----------------------------------------------------------------------------------------------
------------------------------------- [ID/PW 찾기 FROM] -----------------------------------------
-- 개인회원
CREATE OR REPLACE PROCEDURE PASSWROD_PROTECTION_PERSONAL(고객ID IN 개인회원.회원ID%TYPE, 부분비밀번호 OUT CHAR)
AS
BEGIN
    SELECT RPAD(SUBSTR(비밀번호,1,3),LENGTH(비밀번호),'*') INTO 부분비밀번호 FROM 개인회원 WHERE 회원ID = 고객ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
        부분비밀번호 := '아이디가 존재하지 않습니다!';
END;
-- 기업회원
CREATE OR REPLACE PROCEDURE PASSWROD_PROTECTION_BUSINESS(고객ID IN 개인회원.회원ID%TYPE, 부분비밀번호 OUT CHAR)
AS
BEGIN
    SELECT RPAD(SUBSTR(비밀번호,1,3),LENGTH(비밀번호),'*') INTO 부분비밀번호 FROM 기업회원 WHERE 회원ID = 고객ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
        부분비밀번호 := '아이디가 존재하지 않습니다!';
END;

-------------------------------------- [회원가입 FROM] ------------------------------------------
-- 개인 회원
CREATE OR REPLACE PROCEDURE CREATE_ACCOUNT_PERSONAL(
    회원이름 IN 개인회원.이름%TYPE,
    전화번호 IN 개인회원.전화번호%TYPE,
    회원ID IN 개인회원.회원ID%TYPE,
    비밀번호 IN 개인회원.비밀번호%TYPE,
    생년월일 IN 개인회원.생년월일%TYPE,
    성별 IN 개인회원.성별%TYPE)
AS
    변환된_전화번호 개인회원.전화번호%TYPE;
BEGIN   

    IF((SELECT LENGTH(전화번호) FROM DUAL) != 13) THEN
        BEGIN
            변환된_전화번호 := SUBSTR(전화번호, 1,3) || '-' || SUBSTR(전화번호, 4,4) || '-' || SUBSTR(전화번호, 8,4);
    RETURN '회원가입 완료';
END;
-- 기업 회원
CREATE OR REPLACE PROCEDURE CREATE_ACCOUNT_BUSINESS()
AS
BEGIN
END;
-------------------------------------- [이력서 조회 FROM] ------------------------------------------
CREATE OR REPLACE PROCEDURE 
------------------------------------ [스케쥴러 + 프로시저] ------------------------------------------
-- 이력서 삭제 프로시저
CREATE OR REPLACE PROCEDURE DELETE_RESUME_DEADLINE()
AS
    현재_날짜 DATE := SYSDATE;
BEGIN
    DELETE FROM 이력서 WHERE 작성일자 < (현재_날짜 - INTERVAL '3' YEAR);
END;

CREATE OR REPLACE PROCEDURE DELETE_POSITION_DEADLINE()
AS
    현재_날짜 DATE := SYSDATE;
BEGIN
    DELETE FROM 포지션_제안 WHERE 마감기한 < 현재_날짜;
END;

CREATE OR REPLACE PROCEDURE DELETE_JOB_VACANCY()
AS
    현재_날짜 DATE := SYSDATE;
BEGIN
    DELETE FROM WHERE 마감일 < 현재_날짜;
END;
-----------------------------------------------------------------------------------------------
------------------------------------------- 스케줄러 -------------------------------------------
-----------------------------------------------------------------------------------------------
BEGIN
    DBMS_SCHEDULER.CREATE_JOB(
        -- 
        JOB_NAME => '매일_자정_이력서_삭제',
        JOB_TYPE => 'PLSQL_BLOCK',
        JOB_ACTION => 'BEGIN DELETE_RESUME_DEADLINE; END;',
        -- START_DATE : 스케쥴이 작동을 시작 할 시각. 입력한 시점부터 스케쥴러가 시작된다. AM 00시로 설정함
        START_DATE => TRUNC(SYSDATE) + INTERVAL '1' DAY,
        -- REPEAT_INTERVAL => 스케쥴이 작동하는 주기. 하루 한번 돌게 설정.
        REPEAT_INTERVAL => 'FREQ=DAILY; BYHOUR=0; BYMINUTE=0; BYSECOND=0',
        END_DATE => NULL,
        ENABLED => TRUE
    );
    
    DBMS_SCHEDULER.CREATE_JOB(
        -- 
        JOB_NAME => '매일_자정_포지션_제안_삭제',
        JOB_TYPE => 'PLSQL_BLOCK',
        JOB_ACTION => 'BEGIN DELETE_POSITION_DEADLINE; END;',
        -- START_DATE : 스케쥴이 작동을 시작 할 시각. 입력한 시점부터 스케쥴러가 시작된다. AM 00시로 설정함
        START_DATE => TRUNC(SYSDATE) + INTERVAL '1' DAY,
        -- REPEAT_INTERVAL => 스케쥴이 작동하는 주기. 하루 한번 돌게 설정.
        REPEAT_INTERVAL => 'FREQ=DAILY; BYHOUR=0; BYMINUTE=0; BYSECOND=0',
        END_DATE => NULL,
        ENABLED => TRUE
    );
    
    DBMS_SCHEDULER.CREATE_JOB(
        -- 
        JOB_NAME => '매일_자정_채용_게시글_삭제',
        JOB_TYPE => 'PLSQL_BLOCK',
        JOB_ACTION => 'BEGIN DELETE_JOB_VACANCY; END;',
        -- START_DATE : 스케쥴이 작동을 시작 할 시각. 입력한 시점부터 스케쥴러가 시작된다. AM 00시로 설정함
        START_DATE => TRUNC(SYSDATE) + INTERVAL '1' DAY,
        -- REPEAT_INTERVAL => 스케쥴이 작동하는 주기. 하루 한번 돌게 설정.
        REPEAT_INTERVAL => 'FREQ=DAILY; BYHOUR=0; BYMINUTE=0; BYSECOND=0',
        END_DATE => NULL,
        ENABLED => TRUE
    );
END;