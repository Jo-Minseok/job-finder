-----------------------------------------------------------------------------------------------
------------------------------------------- 프로시저 -------------------------------------------
-----------------------------------------------------------------------------------------------
DROP PROCEDURE PASSWORD_PROTECTION_PERSONAL;
DROP PROCEDURE PASSWORD_PROTECTION_BUSINESS;
DROP PROCEDURE CREATE_ACCOUNT_PERSONAL;
DROP PROCEDURE CREATE_ACCOUNT_BUSINESS;

DBMS_SCHEDULER.DROP_JOB('매일_자정_이력서_삭제');
DBMS_SCHEDULER.DROP_JOB('매일_자정_포지션_제안_삭제');
DBMS_SCHEDULER.DROP_JOB('매일_자정_채용_게시글_삭제');
DROP PROCEDURE DELETE_RESUME_DEADLINE;
DROP PROCEDURE DELETE_POSITION_DEADLINE;
DROP PROCEDURE DELETE_JOB_VACANCY;


------------------------------------- [ID/PW 찾기 FROM] -----------------------------------------
-- 개인회원
CREATE OR REPLACE PROCEDURE PASSWORD_PROTECTION_PERSONAL(고객ID IN 개인회원.회원ID%TYPE, 부분비밀번호 OUT CHAR)
AS
BEGIN
    SELECT RPAD(SUBSTR(비밀번호,1,3),LENGTH(비밀번호),'*') INTO 부분비밀번호 FROM 개인회원 WHERE 회원ID = 고객ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
        부분비밀번호 := '아이디가 존재하지 않습니다!';
END;
-- 기업회원
CREATE OR REPLACE PROCEDURE PASSWORD_PROTECTION_BUSINESS(고객ID IN 개인회원.회원ID%TYPE, 부분비밀번호 OUT CHAR)
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
    휴대폰 IN 개인회원.휴대폰%TYPE,
    회원ID IN 개인회원.회원ID%TYPE,
    비밀번호 IN 개인회원.비밀번호%TYPE,
    생년월일 IN 개인회원.생년월일%TYPE,
    성별 IN 개인회원.성별%TYPE,
    거주_지역 IN 개인회원.거주_지역%TYPE,
    개인정보_유효기간 IN 개인회원.개인정보_유효기간%TYPE,
    기업_이름 IN 개인회원.기업_이름%TYPE,
    연봉 IN 개인회원.연봉%TYPE,
    직책 IN 개인회원.직책%TYPE,
    완료 OUT NVARCHAR2)
AS
    변환된_휴대폰 개인회원.휴대폰%TYPE;
    삭제된_ID 개인회원.회원ID%TYPE;
BEGIN
    SELECT 회원ID INTO 삭제된_ID FROM 개인_회원_정보_변경  WHERE 개인_회원_정보_변경.변경_열 = '탈퇴' AND 개인_회원_정보_변경.회원ID = 회원ID;
    IF(NOT SQL%NOTFOUND) THEN
        RAISE_APPLICATION_ERROR(-20008, '이미 탈퇴한 회원입니다.');
    END IF;
    IF(LENGTH(휴대폰) = 11) THEN
        BEGIN
            변환된_휴대폰 := SUBSTR(휴대폰, 1,3) || '-' || SUBSTR(휴대폰, 4,4) || '-' || SUBSTR(휴대폰, 8,4);
        END;
    ELSIF(LENGTH(휴대폰) = 13) THEN
        BEGIN
            변환된_휴대폰 := 휴대폰;
        END;
    ELSE
        BEGIN
            RAISE_APPLICATION_ERROR(-20001, '전화번호의 입력이 올바르지 않습니다.');
        END;
    END IF;
    
    IF REGEXP_LIKE(회원ID,'\s') OR LENGTH(회원ID) <=6 OR NOT REGEXP_LIKE(회원ID,'[[:alpha:]]') OR NOT REGEXP_LIKE(회원ID,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20002, '회원ID의 형식이 올바르지 않거나 ID 길이가 6자를 넘지 않습니다.');
    END IF;
    
     IF REGEXP_LIKE(회원ID,'\s') OR LENGTH(회원ID) <=8 OR NOT REGEXP_LIKE(회원ID,'[[:alpha:]]') OR NOT REGEXP_LIKE(회원ID,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20003, '비밀번호의 형식이 올바르지 않거나 길이가 8자를 넘지 않습니다.');
    END IF;
    
    IF EXTRACT(YEAR FROM 생년월일) < EXTRACT(YEAR FROM SYSDATE) - 20 THEN
        RAISE_APPLICATION_ERROR(-20004, '미성년자는 가입이 불가능합니다.'|| TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-20) ||' 이전 년생 부터 가입 가능.');
    END IF;
    
    IF NOT (성별='남' OR 성별='여') THEN
        RAISE_APPLICATION_ERROR(-20005, '성별이 올바르지 않습니다.(''남'',''여''로 입력하세요.)');
    END IF;
    INSERT INTO 개인회원 VALUES (회원ID, 비밀번호, 회원이름, 생년월일, 성별, 변환된_휴대폰, 거주_지역, 개인정보_유효기간, SYSDATE, 0, 0, 기업_이름, 연봉,직책);
    INSERT INTO 개인_회원_정보_변경 VALUES (회원ID,'생성',NULL);
    완료:='회원가입을 성공했습니다.';
END;
-- 기업 회원
CREATE OR REPLACE PROCEDURE CREATE_ACCOUNT_BUSINESS(
    기업명 IN 기업회원.기업명%TYPE,
    회원이름 IN 기업회원.이름%TYPE,
    휴대폰 IN 기업회원.휴대폰%TYPE,
    회원ID IN 기업회원.회원ID%TYPE,
    비밀번호 IN 기업회원.비밀번호%TYPE,
    개인정보유효기간 IN 기업회원.개인정보_유효기간%TYPE)
AS
    변환된_휴대폰 기업회원.휴대폰%TYPE;
    삭제된_ID 기업회원.회원ID%TYPE;
BEGIN   
    SELECT 회원ID INTO 삭제된_ID FROM 기업_회원_정보_변경 WHERE 기업_회원_정보_변경.변경_열 = '탈퇴' AND 기업_회원_정보_변경.회원ID = 회원ID;
    IF(NOT SQL%NOTFOUND) THEN
        RAISE_APPLICATION_ERROR(-20008, '이미 탈퇴한 회원입니다.');
    END IF;
    IF(LENGTH(휴대폰) = 11) THEN
        BEGIN
            변환된_휴대폰 := SUBSTR(휴대폰, 1,3) || '-' || SUBSTR(휴대폰, 4,4) || '-' || SUBSTR(휴대폰, 8,4);
        END;
    ELSIF(LENGTH(휴대폰) = 13) THEN
        BEGIN
            변환된_휴대폰 := 휴대폰;
        END;
    ELSE
        BEGIN
            RAISE_APPLICATION_ERROR(-20001, '전화번호의 입력이 올바르지 않습니다.');
        END;
    END IF;
    
    IF REGEXP_LIKE(회원ID,'\s') OR LENGTH(회원ID) <=6 OR NOT REGEXP_LIKE(회원ID,'[[:alpha:]]') OR NOT REGEXP_LIKE(회원ID,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20002, '회원ID의 형식이 올바르지 않거나 ID 길이가 6자를 넘지 않습니다.');
    END IF;
    
     IF REGEXP_LIKE(회원ID,'\s') OR LENGTH(회원ID) <=8 OR NOT REGEXP_LIKE(회원ID,'[[:alpha:]]') OR NOT REGEXP_LIKE(회원ID,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20003, '비밀번호의 형식이 올바르지 않거나 길이가 8자를 넘지 않습니다.');
    END IF;
END;
-------------------------------------- [이력서 조회 FROM] ------------------------------------------
CREATE OR REPLACE PROCEDURE COMPITITION_RATE
AS
    POST_NUMBER 채용_게시글.게시글_번호%TYPE;
    APPLY_COUNT NUMBER(38,0);
    CURSOR CURSOR_COMPITION_RATE IS SELECT 게시글_번호, COUNT(게시글_번호) FROM 지원 GROUP BY 게시글_번호;
BEGIN
    OPEN CURSOR_COMPITION_RATE;
    LOOP
        FETCH CURSOR_COMPITION_RATE INTO POST_NUMBER, APPLY_COUNT;
        EXIT WHEN CURSOR_COMPITION_RATE%NOTFOUND;
        UPDATE 채용_게시글 SET 경쟁률 = (SELECT 모집인원 FROM 채용_게시글 WHERE 게시글_번호 = POST_NUMBER)/APPLY_COUNT * 100 WHERE 게시글_번호 = POST_NUMBER;
    END LOOP;
    CLOSE CURSOR_COMPITION_RATE;
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

------------------------------------ [스케쥴러 + 프로시저] ------------------------------------------
-- 이력서 삭제 프로시저
CREATE OR REPLACE PROCEDURE DELETE_RESUME_DEADLINE
AS
    현재_날짜 DATE := SYSDATE;
BEGIN
    DELETE FROM 이력서 WHERE 작성일자 < (현재_날짜 - INTERVAL '3' YEAR);
END;

-- 포지션 제안 삭제 프로시저
CREATE OR REPLACE PROCEDURE DELETE_POSITION_DEADLINE
AS
    현재_날짜 DATE := SYSDATE;
BEGIN
    DELETE FROM 포지션_제안 WHERE 마감기한 < 현재_날짜;
END;

-- 채용 게시글 삭제 프로시저
CREATE OR REPLACE PROCEDURE DELETE_JOB_VACANCY
AS
    현재_날짜 DATE := SYSDATE;
BEGIN
    DELETE FROM 채용_게시글 WHERE 마감일 < 현재_날짜;
END;