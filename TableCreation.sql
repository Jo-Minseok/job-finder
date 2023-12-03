-----------------------------------------------------------------------------------------------
------------------------------------------- 데이터 삭제 -----------------------------------------
-----------------------------------------------------------------------------------------------
TRUNCATE TABLE 지역;
TRUNCATE TABLE 연봉_평균_계산;
TRUNCATE TABLE 기업_회원_정보_변경;
TRUNCATE TABLE 개인_회원_정보_변경;
TRUNCATE TABLE 기업_포인트_수정_내역;
TRUNCATE TABLE 개인_포인트_수정_내역;
TRUNCATE TABLE 게시글수;
TRUNCATE TABLE 지원;
TRUNCATE TABLE 포지션_제안;
TRUNCATE TABLE 이력서_경력;
TRUNCATE TABLE 이력서_자격증;
TRUNCATE TABLE 채용_설명회;
TRUNCATE TABLE 채용_게시글;
TRUNCATE TABLE 이력서;
TRUNCATE TABLE 기업회원;
TRUNCATE TABLE 개인회원;
TRUNCATE TABLE 기업;
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
-- 통계 및 변경, 기타 데이터 테이블
DROP TABLE 게시글수 CASCADE CONSTRAINTS;
DROP TABLE 개인_포인트_수정_내역 CASCADE CONSTRAINTS;
DROP TABLE 기업_포인트_수정_내역 CASCADE CONSTRAINTS;
DROP TABLE 개인_회원_정보_변경 CASCADE CONSTRAINTS;
DROP TABLE 기업_회원_정보_변경 CASCADE CONSTRAINTS;
DROP TABLE 연봉_평균_계산 CASCADE CONSTRAINTS;
DROP TABLE 지역;
-----------------------------------------------------------------------------------------------
------------------------------------------- 테이블 생성 -----------------------------------------
-----------------------------------------------------------------------------------------------

-- 강성 개체
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
    CONSTRAINT UK_개인회원 UNIQUE (휴대폰),
    CONSTRAINT FK_개인회원 FOREIGN KEY (기업_이름) REFERENCES 기업(이름) ON DELETE SET NULL
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
    CONSTRAINT FK_기업회원 FOREIGN KEY (기업명) REFERENCES 기업(이름) ON DELETE CASCADE
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
    CONSTRAINT FK_이력서 FOREIGN KEY(작성자ID) REFERENCES 개인회원(회원ID) ON DELETE CASCADE
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
    경쟁률 NUMBER(3,0),
    CONSTRAINT PK_채용_게시글 PRIMARY KEY(게시글_번호),
    CONSTRAINT FK_채용_게시글 FOREIGN KEY (작성자ID) REFERENCES 기업회원(회원ID) ON DELETE CASCADE
);

CREATE TABLE 채용_설명회(
    기업명 NVARCHAR2(60),
    설명회명 NVARCHAR2(60),
    회차 NUMBER(3,0),
    일시 DATE,
    장소 NVARCHAR2(60),
    CONSTRAINT PK_채용_설명회 PRIMARY KEY(기업명, 설명회명, 회차),
    CONSTRAINT FK_채용_설명회 FOREIGN KEY (기업명) REFERENCES 기업(이름) ON DELETE CASCADE
);

-- 다중값 테이블
CREATE TABLE 이력서_자격증(
    이력서_작성자 NVARCHAR2(32),
    이력서명 NVARCHAR2(40),
    자격증명 NVARCHAR2(32),
    CONSTRAINT PK_이력서_자격증 PRIMARY KEY(이력서_작성자,이력서명,자격증명),
    CONSTRAINT FK_이력서_자격증 FOREIGN KEY(이력서_작성자,이력서명) REFERENCES 이력서(작성자ID,이력서명) ON DELETE CASCADE
);

CREATE TABLE 이력서_경력(
    회원ID NVARCHAR2(32),
    이력서명 NVARCHAR2(40),
    경력_위치 NVARCHAR2(60),
    년수 NUMBER(2,0),
    직급 NCHAR(6),
    연봉 NUMBER(11,0),
    CONSTRAINT PK_이력서_경력 PRIMARY KEY (회원ID,이력서명,경력_위치),
    CONSTRAINT FK_이력서_경력 FOREIGN KEY (회원ID, 이력서명) REFERENCES 이력서(작성자ID, 이력서명) ON DELETE CASCADE
);

-- 관계 테이블
CREATE TABLE 포지션_제안(
    제안자 NVARCHAR2(32),
    이력서_작성자 NVARCHAR2(32),
    이력서명 NVARCHAR2(40),
    마감기한 DATE,
    CONSTRAINT FK_포지션_제안_제안자 FOREIGN KEY(제안자) REFERENCES 기업회원(회원ID) ON DELETE CASCADE,
    CONSTRAINT FK_포지션_제안_이력서 FOREIGN KEY(이력서_작성자, 이력서명) REFERENCES 이력서(작성자ID, 이력서명) ON DELETE CASCADE,
    CONSTRAINT PK_포지션_제안 PRIMARY KEY(제안자, 이력서_작성자,이력서명)
);

CREATE TABLE 지원(
    게시글_번호 NUMBER(38,0),
    지원자 NVARCHAR2 (32),
    이력서명 NVARCHAR2(40),
    일시정보 DATE,
    합격_여부 CHAR(1),
    CONSTRAINT FK_지원_채용게시글 FOREIGN KEY (게시글_번호) REFERENCES 채용_게시글(게시글_번호) ON DELETE CASCADE,
    CONSTRAINT FK_지원_이력서 FOREIGN KEY (지원자, 이력서명) REFERENCES 이력서(작성자ID, 이력서명) ON DELETE CASCADE,
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
    CONSTRAINT FK_개인_포인트수정내역 FOREIGN KEY (회원ID) REFERENCES 개인회원(회원ID) ON DELETE CASCADE
);

CREATE TABLE 기업_포인트_수정_내역(
    회원ID NVARCHAR2(32),
    내역 NCHAR(40),
    포인트 NUMBER(38,0),
    CONSTRAINT FK_기업_포인트수정내역 FOREIGN KEY (회원ID) REFERENCES 기업회원(회원ID) ON DELETE CASCADE
);

CREATE TABLE 개인_회원_정보_변경(
    회원ID NVARCHAR2(32),
    변경_열 NCHAR(40),
    변경_데이터 NVARCHAR2(76)
);

CREATE TABLE 기업_회원_정보_변경(
    회원ID NVARCHAR2(32),
    변경_열 NCHAR(40),
    변경_데이터 NVARCHAR2(76)
);

CREATE TABLE 연봉_평균_계산(
    기업명 NVARCHAR2(60),
    직책 NCHAR(6),
    평균 NUMBER(38,0),
    CONSTRAINT FK_연봉_평균_계산 FOREIGN KEY (기업명) REFERENCES 기업(이름) ON DELETE CASCADE,
    CONSTRAINT PK_연봉_평균_계산 PRIMARY KEY (기업명, 직책)
);

CREATE TABLE 지역(
    지역명 NCHAR(4)
);