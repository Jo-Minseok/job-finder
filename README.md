# 💼 잡 파인더 소프트웨어 (Job-Finder Software)

<div align="center">
<a href="https://hits.seeyoufarm.com"><img width = "15%" src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FJo-Minseok%2Fjob-finder&count_bg=%23DE6700&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false"/></a>
</div>

## CONTENTS

- [TEAM](#TEAM)
  - [Intro](#Intro)
  - [Composition](#Composition)
- [PROJECT INFORMATION](#PROJECT-INFORMATION)
  - [Mean](#Mean)
  - [Goal](#Goal)
  - [Explain](#Explain)
- [STACKS](#STACKS)
  - [Collaboration](#collaboration)
  - [Development](#Development)
  - [Database](#Database)
  - [Language](#Language)
  - [Function](#Function)
- [SOURCE](#source)

## TEAM

### Intro

- NAME : <strong><em>5조</em></strong>

### Composition

<table align="center">
    <th>역할</th>
    <th>이름</th>
    <th>Github</th>
    <th>수행 내용</th>
    <tr>
        <td>팀장</td>
        <td>조민석 (3학년)</td>
        <td><a href="https://github.com/Jo-Minseok">@Jo-Minseok</a></td>
        <td>
          <ul>
            <li>Trigger & Sequence</li>
              <ul>
                <li>POST_NUMBER_SEQ</li>
                <li>POST_NUMBER_TRIG</li>
                <li>채용게시글_TRIG</li>
                <li>채용설명회_TRIG</li>
              </ul>
            <li>Procedure</li>
              <ul>
                <li>RECALCULATE</li>
                <li>CALCULATE_SALARY</li>
                <li>MAIN_FIND</li>
                <li>COUNT_TREND_START</li>
                <li>COUNT_TREND_UPDATE</li>
                <li>COUNT_TREND_PROGRAM</li>
                <li>DELETE_RESUME_DEADLINE</li>
                <li>DELETE_POSITION_DEADLINE</li>
                <li>DELETE_JOB_VACANCY</li>
              </ul>
            <li>Scheduler</li>
              <ul>
                <li>매일 자정 만료 이력서 자동 삭제</li>
                <li>매일 자정 마감 포지션 제안 자동 삭제</li>
                <li>매일 자정 마감 채용 게시글 자동 삭제</li>
              </ul>
            <li>UI Function</li>
              <ul>
                <li>채용 설명회 조회 (Job_description.java)</li>
                <li>채용 게시글 오픈 (Job_posting.java)</li>
                <li>메인 페이지 (Mainpage.java)</li>
                <li>기업 조회 (Corporate_inquiry.java)</li>
                <li>로그인 (Login.java)</li>
                <li>채용 게시글 작성 (Upload_recruit.java)</li>
                <li>설명회 게시글 작성 (Upload_briefing.java)</li>
                <li>기업 전체 조회 (CompanyFullView.java)</li>
                <li>시장 동향 (Market_trends.java)</li>
                <li>지원 (Resume_Apply.java)</li>
              </ul>
          </ul>
        </td>
    </tr>
    <tr>
      <td>팀원</td>
      <td>서아론 (3학년)</td>
      <td><a href="https://github.com/Seo-aron">@Seo-aron</a></td>
      <td>
        <ul>
          <li>Trigger</li>
            <ul>
              <li>기업 회원정보수정 TRIG</li>
            </ul>
          <li>Procedure</li>
            <ul>
              <li>CREATE_ACCOUNT_PERSONAL
              <li>CREATE_ACCOUNT_BUSINESS
              <li>COMPITITION_RATE
              <li>POST_COUNT_PERSONAL
              <li>POST_COUNT_BUSINESS
            </ul>
          <li>UI Function</li>
            <ul>
              <li>회원가입 (Register.java)
              <li>회원 정보 수정 (Edit_info.java)
              <li>이력서 작성 (Resume.java)
              <li>경력 (Career.java)
              <li>자격증 (Certificate.java)
              <li>기업 전체 조회 (CompanyFullView.java)
            </ul>
        </ul>
      </td>
    </tr>
  <tr>
    <td>팀원</td>
    <td>한창훈 (3학년)</td>
    <td><a href="https://github.com/khanbii">@khanbii</a></td>
    <td>
      <ul>
      <li>Trigger</li>
        <ul>
          <li>개인_회원정보수정_TRIG_BEFORE</li>
          <li>기업_포인트_변경_TRIG 작성</li>
          <li>개인_포인트_변경_TRIG 작성</li>
        </ul>
      <li>Procedure</li>
        <ul>
          <li>PASSWORD_PROTECTION_PERSONAL</li>
          <li>PASSWORD_PROTECTION_BUSINESS</li>
        </ul>
      <li>UI Function</li>
        <ul>
          <li>ID/PW 찾기(Find_ID_PW.java)</li>
          <li>회원 탈퇴(Resign.java)</li>
          <li>포인트(Point.java)</li>
          <li>이력서 조회(Resume_inquiry.java)</li>
        </ul>
      </ul>
    </td>
  </tr>

</table>

## PROJECT INFORMATION

> 동의대학교 2023학년도 데이터베이스 프로그래밍 팀 프로젝트 </br>
> PERIOD: 2023.11.09 ~ 2023.12.11</br>

### Mean

📃 잡코리아를 모티브로 만든 프로그램으로, 직장을 잘 찾자라는 의미로 '잡 파인더'라고 작명했음

### Goal

🥇 위드 코로나 시대로 인해 코로나 19때 축소되었던 취업 시장과 금리 인하로 인한 경기 상황 호전을 예상하여 구직자들의 활발한 구직 활동과 기업에서의 구인 활동을 적극적으로 할 수 있도록 도움

### Explain

📃 잡코리아 웹사이트에 영감을 받아 프로그램으로 만들어봤습니다.<br>
트랜잭션, 스케쥴러, 프로시저, 트리거를 활용하였고 처리 서버 없이 RDBMS만을 이용하여 데이터 처리를 진행하였습니다.<br>
※ 소스코드내 드라이버를 통해 데이터베이스와 연결되는 <b>IP/ID/PW/SID</b>는 지워놨습니다.<br>

## STACKS

### Collaboration

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/discord-5865F2?style=for-the-badge&logo=discord&logoColor=white">

### Development

<img src="https://img.shields.io/badge/eclipse%20ide-2C2255?style=for-the-badge&logo=eclipseide&logoColor=white">

### Database

<img src="https://img.shields.io/badge/oracle-F80000?style=for-the-badge&logo=oracle&logoColor=white">

### Language

<img src="https://img.shields.io/badge/JAVA-2F2625?style=for-the-badge&logo=coffeescript&logoColor=white"> <img src="https://img.shields.io/badge/PL/SQL-FFFFFF?style=for-the-badge&&logoColor=white">

### Function

<details>
    <summary><strong>💡 기업 평균 연봉 자동 계산</strong></summary>
    <ul>
        <li>회원 정보 수정에서 현재 본인의 연봉을 수정할 경우, 본인이 속한 회사의 평균 연봉을 재연산</li>
    </ul>
</details>
<details>
    <summary><strong>💡 채용 게시글 경쟁률</strong></summary>
    <ul>
        <li>채용 게시글에 지원할 경우, 모집 인원 대비 지원자에 대한 비율을 재연산</li>
    </ul>
</details>
<details>
    <summary><strong>💡 채용 트렌드 게시글 수</strong></summary>
    <ul>
        <li>분류별(기업 규모, 업계 등) 마다의 채용 게시글 수를 연산</li>
    </ul>
</details>
<details>
    <summary><strong>💡 포인트 충전 및 사용</strong></summary>
    <ul>
        <li>포인트 충전, 사용(게시글 조회), 출금 기능</li>
    </ul>
</details>
<details>
    <summary><strong>💡 채용 시장 동향 통계</strong></summary>
    <ul>
        <li>특정 기간 범위에 채용 게시글 수를 판단하여 시장 동향을 조회</li>
    </ul>
</details>
<details>
    <summary><strong>💡 채용 게시글, 이력서 작성</strong></summary>
    <ul>
        <li>채용 게시글(기업 회원)과 이력서(개인 회원)를 작성할 수 있으며, 작성한 이력서로 채용 게시글에 지원 가능</li>
    </ul>
</details>

## SOURCE

<ul>
  <li>잡코리아 : <a href="https://www.jobkorea.co.kr/">https://www.jobkorea.co.kr/</a></li>
</ul>
