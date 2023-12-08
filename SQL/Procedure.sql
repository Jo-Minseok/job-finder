-----------------------------------------------------------------------------------------------
------------------------------------------- 프로시저 -------------------------------------------
-----------------------------------------------------------------------------------------------
DROP PROCEDURE MAIN_FIND;
DROP PROCEDURE PASSWORD_PROTECTION_PERSONAL;
DROP PROCEDURE PASSWORD_PROTECTION_BUSINESS;
DROP PROCEDURE CREATE_ACCOUNT_PERSONAL;
DROP PROCEDURE CREATE_ACCOUNT_BUSINESS;
DROP PROCEDURE RECALCULATE;
DROP PROCEDURE COUNT_TREND_START;
DROP PROCEDURE COUNT_TREND_UPDATE;

DBMS_SCHEDULER.DROP_JOB('매일_자정_이력서_삭제');
DBMS_SCHEDULER.DROP_JOB('매일_자정_포지션_제안_삭제');
DBMS_SCHEDULER.DROP_JOB('매일_자정_채용_게시글_삭제');
DROP PROCEDURE DELETE_RESUME_DEADLINE;
DROP PROCEDURE DELETE_POSITION_DEADLINE;
DROP PROCEDURE DELETE_JOB_VACANCY;
------------------------------------- [Edit_Info] ----------------------------------------------
CREATE OR REPLACE PROCEDURE RECALCULATE(
최근_회사 IN NVARCHAR2,
옛날_회사 IN NVARCHAR2,
최근_직책 IN NVARCHAR2,
옛날_직책 IN NVARCHAR2,
최근_연봉 IN NUMBER,
옛날_연봉 IN NUMBER)
AS
TMP_연봉 NUMBER;
BEGIN
        SELECT AVG(연봉) INTO TMP_연봉 FROM 개인회원 WHERE 기업_이름 = 최근_회사;
        UPDATE 연봉_평균_계산 SET 평균 = TMP_연봉 WHERE 기업명 = 최근_회사 AND 직책= 최근_직책;
        SELECT AVG(연봉) INTO TMP_연봉 FROM 개인회원 WHERE 기업_이름= 옛날_회사 AND 직책= 옛날_직책;
        UPDATE 연봉_평균_계산 SET 평균 = TMP_연봉 WHERE 기업명 = 옛날_회사 AND 직책= 옛날_직책;
END;

CREATE OR REPLACE PROCEDURE CALCULATE_SALARY
AS
CURSOR CURSOR_COMPANY IS SELECT 이름 FROM 기업;
기업이름 기업.이름%TYPE;
TMP_연봉 NUMBER;
TYPE StringArray IS TABLE OF VARCHAR2(50);
직책_배열 StringArray:= StringArray('파트장','대리','사원','인턴','팀장','부장','차장','과장');
BEGIN
    OPEN CURSOR_COMPANY;
    LOOP
        FETCH CURSOR_COMPANY INTO 기업이름;
        EXIT WHEN CURSOR_COMPANY%NOTFOUND;
        FOR i IN 1..직책_배열.COUNT LOOP
            SELECT AVG(연봉) INTO TMP_연봉 FROM 개인회원 WHERE 기업_이름 = 기업이름 AND 직책 = 직책_배열(i);
            INSERT INTO 연봉_평균_계산 VALUES (기업이름,직책_배열(i), TMP_연봉);
        END LOOP;
    END LOOP;
    CLOSE CURSOR_COMPANY;
END;
EXEC CALCULATE_SALARY;
-------------------------------------- [Main Form] ---------------------------------------------
CREATE OR REPLACE PROCEDURE MAIN_FIND(
모드 IN NVARCHAR2,
고객ID IN 개인회원.회원ID%TYPE,
현재일 IN DATE,
출력_이름 OUT 개인회원.이름%TYPE,
출력_포인트 OUT NUMBER,
출력_게시글_작성수 OUT NUMBER,
제안_개수 OUT NUMBER,
현재_게시글 OUT NUMBER)
AS
BEGIN
    IF 모드 = '개인' THEN
        BEGIN
            SELECT 이름, 포인트, 이력서_작성수 INTO 출력_이름, 출력_포인트, 출력_게시글_작성수 FROM 개인회원 WHERE 회원ID = 고객ID;
            SELECT COUNT(*) INTO 제안_개수 FROM 포지션_제안 WHERE 이력서_작성자 = 고객ID;
        END;
    ELSE
        BEGIN
            SELECT 이름, 포인트, 게시글_작성수 INTO 출력_이름, 출력_포인트, 출력_게시글_작성수 FROM 기업회원 WHERE 회원ID = 고객ID;
            SELECT COUNT(*) INTO 제안_개수 FROM 지원 WHERE 게시글_번호 IN (SELECT 게시글_번호 FROM 채용_게시글 WHERE 작성자ID = 고객ID);
        END;
    END IF;
    SELECT COUNT(*) INTO 현재_게시글 FROM 채용_게시글 WHERE 마감일 > 현재일;
END;

------------------------------------- [ID/PW 찾기 FROM] -----------------------------------------
-- 개인회원
CREATE OR REPLACE PROCEDURE PASSWORD_PROTECTION_PERSONAL(고객ID IN 개인회원.회원ID%TYPE, 부분비밀번호 OUT NVARCHAR2)
AS
BEGIN
    SELECT RPAD(SUBSTR(비밀번호,1,3),LENGTH(비밀번호),'*') INTO 부분비밀번호 FROM 개인회원 WHERE 회원ID = 고객ID;
    EXCEPTION WHEN NO_DATA_FOUND THEN
        부분비밀번호 := '아이디가 존재하지 않습니다!';
END;
-- 기업회원
CREATE OR REPLACE PROCEDURE PASSWORD_PROTECTION_BUSINESS(고객ID IN 개인회원.회원ID%TYPE, 부분비밀번호 OUT NVARCHAR2)
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
    입력회원ID IN 개인회원.회원ID%TYPE,
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
    ROW_COUNT NUMBER;
BEGIN
    SELECT COUNT(회원ID) INTO ROW_COUNT FROM 개인_회원_정보_변경 WHERE 개인_회원_정보_변경.회원ID = 입력회원ID AND 개인_회원_정보_변경.변경_열 = '탈퇴';
    IF (ROW_COUNT!=0) THEN
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
    
    IF REGEXP_LIKE(입력회원ID,'\s') OR LENGTH(입력회원ID) <6 OR NOT REGEXP_LIKE(입력회원ID,'[[:alpha:]]') OR NOT REGEXP_LIKE(입력회원ID,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20002, '회원ID의 형식이 올바르지 않거나 ID 길이가 6자를 넘지 않습니다.');
    END IF;
    
     IF REGEXP_LIKE(비밀번호,'\s') OR LENGTH(비밀번호) <8 OR NOT REGEXP_LIKE(비밀번호,'[[:alpha:]]') OR NOT REGEXP_LIKE(비밀번호,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20003, '비밀번호의 형식이 올바르지 않거나 길이가 8자를 넘지 않습니다.');
    END IF;
    
    IF EXTRACT(YEAR FROM 생년월일) > EXTRACT(YEAR FROM SYSDATE) - 20 THEN
        RAISE_APPLICATION_ERROR(-20004, '미성년자는 가입이 불가능합니다. '|| TO_CHAR(EXTRACT(YEAR FROM SYSDATE)-20) ||' 이전 년생 부터 가입 가능.');
    END IF;
    
    IF NOT (성별='남' OR 성별='여') THEN 
        RAISE_APPLICATION_ERROR(-20005, '성별이 올바르지 않습니다.(''남'',''여''로 입력하세요.)');
    END IF;
    INSERT INTO 개인회원 VALUES (입력회원ID, 비밀번호, 회원이름, 생년월일, 성별, 변환된_휴대폰, 거주_지역, 개인정보_유효기간, SYSDATE, 0, 0, 기업_이름, 연봉,직책);
    INSERT INTO 개인_회원_정보_변경 VALUES (입력회원ID,'생성',NULL,NULL);
    완료:='회원가입을 성공했습니다.';
END;
-- 기업 회원
CREATE OR REPLACE PROCEDURE CREATE_ACCOUNT_BUSINESS(
    기업명 IN 기업회원.기업명%TYPE,
    회원이름 IN 기업회원.이름%TYPE,
    휴대폰 IN 기업회원.휴대폰%TYPE,
    입력회원ID IN 기업회원.회원ID%TYPE,
    비밀번호 IN 기업회원.비밀번호%TYPE,
    개인정보유효기간 IN 기업회원.개인정보_유효기간%TYPE,
    사업자등록번호 IN 기업회원.사업자등록번호%TYPE,
    완료 OUT NVARCHAR2)
AS
    변환된_휴대폰 기업회원.휴대폰%TYPE;
    ROW_COUNT NUMBER;
BEGIN   
    SELECT COUNT(회원ID) INTO ROW_COUNT FROM 기업_회원_정보_변경 WHERE 기업_회원_정보_변경.변경_열 = '탈퇴' AND 기업_회원_정보_변경.회원ID = 입력회원ID;
    IF(ROW_COUNT <> 0) THEN
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
    
    IF REGEXP_LIKE(입력회원ID,'\s') OR LENGTH(입력회원ID) <=6 OR NOT REGEXP_LIKE(입력회원ID,'[[:alpha:]]') OR NOT REGEXP_LIKE(입력회원ID,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20002, '회원ID의 형식이 올바르지 않거나 ID 길이가 6자를 넘지 않습니다.');
    END IF;
    
     IF REGEXP_LIKE(비밀번호,'\s') OR LENGTH(비밀번호) <=8 OR NOT REGEXP_LIKE(비밀번호,'[[:alpha:]]') OR NOT REGEXP_LIKE(비밀번호,'[[:digit:]]') THEN
        RAISE_APPLICATION_ERROR(-20003, '비밀번호의 형식이 올바르지 않거나 길이가 8자를 넘지 않습니다.');
    END IF;
    INSERT INTO 기업회원 VALUES (기업명, 입력회원ID, 비밀번호, 회원이름, 사업자등록번호, 변환된_휴대폰, 개인정보유효기간, SYSDATE, 0, 0);
    INSERT INTO 기업_회원_정보_변경 VALUES (입력회원ID,'생성',NULL,NULL);
    완료:='회원가입을 성공했습니다.';
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
        UPDATE 채용_게시글 SET 경쟁률 = APPLY_COUNT/(SELECT 모집인원 FROM 채용_게시글 WHERE 게시글_번호 = POST_NUMBER) * 100 WHERE 게시글_번호 = POST_NUMBER;
    END LOOP;
    CLOSE CURSOR_COMPITION_RATE;
END;

CREATE OR REPLACE PROCEDURE POST_COUNT_PERSONAL
AS
    TMP_ID 개인회원.회원ID%TYPE;
    CURSOR CURSOR_PERSONAL IS SELECT 회원ID FROM 개인회원;
BEGIN
    OPEN CURSOR_PERSONAL;
    LOOP
        FETCH CURSOR_PERSONAL INTO TMP_ID;
        EXIT WHEN CURSOR_PERSONAL%NOTFOUND;
        UPDATE 개인회원 SET 이력서_작성수 = (SELECT COUNT(*) FROM 이력서 WHERE 작성자ID = TMP_ID) WHERE 회원ID = TMP_ID;
    END LOOP;
    CLOSE CURSOR_PERSONAL;
END;

CREATE OR REPLACE PROCEDURE POST_COUNT_BUSINESS
AS
    TMP_ID 기업회원.회원ID%TYPE;
    CURSOR CURSOR_PERSONAL IS SELECT 회원ID FROM 기업회원;
BEGIN
    OPEN CURSOR_PERSONAL;
    LOOP
        FETCH CURSOR_PERSONAL INTO TMP_ID;
        EXIT WHEN CURSOR_PERSONAL%NOTFOUND;
        UPDATE 기업회원 SET 게시글_작성수 = (SELECT COUNT(*) FROM 채용_게시글 WHERE 작성자ID = TMP_ID) WHERE 회원ID = TMP_ID;
    END LOOP;
    CLOSE CURSOR_PERSONAL;
END;

EXEC COMPITITION_RATE;
EXEC POST_COUNT_PERSONAL;
EXEC POST_COUNT_BUSINESS;

-------------------------------------- [채용 시장 FROM] ------------------------------------------

CREATE OR REPLACE PROCEDURE COUNT_TREND_START
AS
BEGIN
    FOR TMP_TABLE IN (SELECT 기업.기업구분, COUNT(기업.기업구분)  AS 게시글_개수 FROM 채용_게시글,기업,기업회원 WHERE 채용_게시글.작성자ID= 기업회원.회원ID AND 기업.이름 = 기업회원.기업명 GROUP BY(기업.기업구분))
    LOOP
        INSERT INTO 게시글수 VALUES (TMP_TABLE.기업구분, TMP_TABLE.게시글_개수);
    END LOOP;
    
    FOR TMP_TABLE IN (SELECT 채용_게시글.지역, COUNT(채용_게시글.지역) AS 게시글_개수 FROM 채용_게시글,기업,기업회원 WHERE 채용_게시글.작성자ID= 기업회원.회원ID AND 기업.이름 = 기업회원.기업명 GROUP BY(채용_게시글.지역))
    LOOP
        INSERT INTO 게시글수 VALUES (TMP_TABLE.지역, TMP_TABLE.게시글_개수);
    END LOOP;
    
    FOR TMP_TABLE IN (SELECT 채용_게시글.고용형태, COUNT(채용_게시글.고용형태) AS 게시글_개수 FROM 채용_게시글,기업,기업회원 WHERE 채용_게시글.작성자ID= 기업회원.회원ID AND 기업.이름 = 기업회원.기업명 GROUP BY(채용_게시글.고용형태))
    LOOP
        INSERT INTO 게시글수 VALUES (TMP_TABLE.고용형태, TMP_TABLE.게시글_개수);
    END LOOP;
END;

CREATE OR REPLACE PROCEDURE COUNT_TREND_UPDATE
AS
BEGIN
    FOR TMP_TABLE IN (SELECT 기업.기업구분, COUNT(기업.기업구분)  AS 게시글_개수 FROM 채용_게시글,기업,기업회원 WHERE 채용_게시글.작성자ID= 기업회원.회원ID AND 기업.이름 = 기업회원.기업명 GROUP BY(기업.기업구분))
    LOOP
        UPDATE 게시글수 SET 게시글수 = TMP_TABLE.게시글_개수 WHERE 분류 = TMP_TABLE.기업구분;
    END LOOP;
    
    FOR TMP_TABLE IN (SELECT 채용_게시글.지역, COUNT(채용_게시글.지역) AS 게시글_개수 FROM 채용_게시글,기업,기업회원 WHERE 채용_게시글.작성자ID= 기업회원.회원ID AND 기업.이름 = 기업회원.기업명 GROUP BY(채용_게시글.지역))
    LOOP
        UPDATE 게시글수 SET 게시글수 = TMP_TABLE.게시글_개수 WHERE 분류 = TMP_TABLE.지역;
    END LOOP;
    
    FOR TMP_TABLE IN (SELECT 채용_게시글.고용형태, COUNT(채용_게시글.고용형태) AS 게시글_개수 FROM 채용_게시글,기업,기업회원 WHERE 채용_게시글.작성자ID= 기업회원.회원ID AND 기업.이름 = 기업회원.기업명 GROUP BY(채용_게시글.고용형태))
    LOOP
        UPDATE 게시글수 SET 게시글수 = TMP_TABLE.게시글_개수 WHERE 분류 = TMP_TABLE.고용형태;
    END LOOP;
END;

CREATE OR REPLACE PROCEDURE COUNT_TREND_PROGRAM(
기업이름IN IN NVARCHAR2,
시작날짜IN IN DATE,
마감날짜IN IN DATE,
날짜상관없는게시글수 OUT NUMBER,
매출액OUT OUT NUMBER,
남성 OUT NUMBER,
전체인원 OUT NUMBER,
평균연봉OUT OUT NUMBER,
날짜채용게시글수 OUT NUMBER,
날짜평균연봉 OUT NUMBER,
날짜평균근무시간 OUT NUMBER,
날짜경쟁률 OUT NUMBER)
AS
BEGIN
    IF 기업이름IN IS NULL THEN
    BEGIN
        IF 시작날짜IN IS NULL THEN
        BEGIN
            SELECT COUNT(*) INTO 날짜상관없는게시글수 FROM 채용_게시글;
            SELECT AVG(매출액) INTO 매출액OUT FROM 기업;
            SELECT COUNT(성별) INTO 남성 FROM 개인회원 WHERE 성별 = '남';
            SELECT COUNT(성별) INTO 전체인원 FROM 개인회원;
            SELECT AVG(대졸초임) INTO 평균연봉OUT FROM 기업;
            SELECT COUNT(*) INTO 날짜채용게시글수 FROM 채용_게시글;
            SELECT AVG(급여 * 12) INTO 날짜평균연봉 FROM 채용_게시글;
            SELECT AVG(근무시간) INTO 날짜평균근무시간 FROM 채용_게시글;
            SELECT AVG(경쟁률) INTO 날짜경쟁률 FROM 채용_게시글;
        END;
        
        ELSE
        BEGIN
            SELECT COUNT(*) INTO 날짜상관없는게시글수 FROM 채용_게시글;
            SELECT AVG(매출액) INTO 매출액OUT FROM 기업;
            SELECT COUNT(성별) INTO 남성 FROM 개인회원 WHERE 성별 = '남';
            SELECT COUNT(성별) INTO 전체인원 FROM 개인회원;
            SELECT AVG(대졸초임) INTO 평균연봉OUT FROM 기업;
            SELECT COUNT(*) INTO 날짜채용게시글수 FROM 채용_게시글 WHERE 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
            SELECT AVG(급여 * 12) INTO 날짜평균연봉 FROM 채용_게시글 WHERE 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
            SELECT AVG(근무시간) INTO 날짜평균근무시간 FROM 채용_게시글 WHERE 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
            SELECT AVG(경쟁률) INTO 날짜경쟁률 FROM 채용_게시글 WHERE 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
        END;
        END IF;
    END;
    
    ELSE
    BEGIN
        IF 시작날짜IN IS NULL THEN
        BEGIN
            SELECT COUNT(*) INTO 날짜상관없는게시글수 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN;
            SELECT AVG(매출액) INTO 매출액OUT FROM 기업 WHERE 기업.이름 = 기업이름IN;
            SELECT COUNT(성별) INTO 남성 FROM 개인회원 WHERE 성별 = '남' AND 개인회원.기업_이름 = 기업이름IN;
            SELECT COUNT(성별) INTO 전체인원 FROM 개인회원 WHERE 개인회원.기업_이름 = 기업이름IN;
            SELECT AVG(대졸초임) INTO 평균연봉OUT FROM 기업 WHERE 기업.이름 = 기업이름IN;
            SELECT COUNT(*) INTO 날짜채용게시글수 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN;
            SELECT AVG(급여 * 12) INTO 날짜평균연봉 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN;
            SELECT AVG(근무시간) INTO 날짜평균근무시간 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN;
            SELECT AVG(경쟁률) INTO 날짜경쟁률 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN;
        END;
        
        ELSE
        BEGIN
            SELECT COUNT(*) INTO 날짜상관없는게시글수 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN;
            SELECT AVG(매출액) INTO 매출액OUT FROM 기업 WHERE 기업.이름 = 기업이름IN;
            SELECT COUNT(성별) INTO 남성 FROM 개인회원 WHERE 성별 = '남' AND 개인회원.기업_이름 = 기업이름IN;
            SELECT COUNT(성별) INTO 전체인원 FROM 개인회원 WHERE 개인회원.기업_이름 = 기업이름IN;
            SELECT AVG(대졸초임) INTO 평균연봉OUT FROM 기업 WHERE 기업.이름 = 기업이름IN;
            SELECT COUNT(*) INTO 날짜채용게시글수 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN AND 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
            SELECT AVG(급여 * 12) INTO 날짜평균연봉 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN AND 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
            SELECT AVG(근무시간) INTO 날짜평균근무시간 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN AND 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
            SELECT AVG(경쟁률) INTO 날짜경쟁률 FROM 채용_게시글,기업회원 WHERE 채용_게시글.작성자ID = 기업회원.회원ID AND 기업회원.기업명 = 기업이름IN AND 시작날짜IN < 마감일 AND 마감일 < 마감날짜IN;
        END;
        END IF;
    END;
    
    END IF;
END;

EXEC COUNT_TREND_START;
COMMIT;
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