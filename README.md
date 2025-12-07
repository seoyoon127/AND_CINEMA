# AND CINEMA
## 프로젝트 소개
영화 목록을 확인하고, 좋아요 및 예매를 할 수 있는 안드로이도 영화 예매 사이트입니다.   
개발 기간 : 2025.11.27 ~ 2025.12.5

## IA
<img width="604" height="476" alt="image" src="https://github.com/user-attachments/assets/91120526-64ee-4c73-af67-3dba0bf94b7a" />


## 사용 기술
- 언어: Java
- 개발 환경: Android Studio
- 영화 api: tmdb
- db: SQLite

## 주요 기능
### 회원가입/로그인
<img width="242.5" height="505" alt="image" src="https://github.com/user-attachments/assets/476a46c4-0903-4ba5-96c0-08c3b1f1205a" />

- 검증: 중복되지 않은 이메일만 회원가입 가능
- sharedPreference에 userId 저장하여 로그인 여부 확인

### 홈화면 - 정렬
<img width="449" height="430" alt="image" src="https://github.com/user-attachments/assets/aa3c0e5d-016b-4176-a528-c554e01e3edb" />

- 팝업 메뉴를 통한 정렬
- UseMovieApi를 통해 4가지 카테코리의 데이터 불러옴
  

### 영화 상세
<img width="240" height="515" alt="image" src="https://github.com/user-attachments/assets/6475cec4-aebc-41c2-be67-fbf0cfed2429" />

### 캐스팅 정보
<img width="449" height="430" alt="image" src="https://github.com/user-attachments/assets/4c422f11-f5b4-484f-90e9-8cf1279b37a1" />

### 영화 예매 - 극장 선택
<img width="449" height="428" alt="image" src="https://github.com/user-attachments/assets/d4289518-6aa7-412a-8538-477eedd60d13" />

- 극장 선택 - 모달 사용
- 모든 옵션을 선택하지 않고 다음 버튼을 누를 시 토스트 메세지 발생 & 페이지 이동 방지

### 영화 예매 - 인원 선택
<img width="235" height="510" alt="image" src="https://github.com/user-attachments/assets/003c4840-8d58-48e0-97b9-fd34afec3b18" />

### 영화 예매 - 좌석 선택
<img width="235" height="510" alt="image" src="https://github.com/user-attachments/assets/e43cbce5-9438-4707-9692-3a8d70e4f3db" />

- 좌석 선택 시 버튼 색 변경
- 앞서 선택한 인원과 상이할 시 토스트 메세지 발생

### 결제
<img width="449" height="428" alt="image" src="https://github.com/user-attachments/assets/cf310abf-9b55-41a3-a6f9-dca4f38b6c45" />

- 영화 예매 페이지에서 선택한 항목들 모두 표기
- 최종 금액 합산하여 표기
- 실제로 결제되진 않음

### 마이페이지
<img width="235" height="510" alt="image" src="https://github.com/user-attachments/assets/19e74295-fa0d-464c-959b-447281f3779b" />


### 예매 내역
<img width="462" height="457" alt="image" src="https://github.com/user-attachments/assets/2bcb6f02-87b0-48de-bf4d-dc851d72467f" />


- 영화 제목, 극장, 상영 일시, 좌석, 결제금액 표기
- 최근 항목: 오늘 혹은 그 이후 영화 목록
- 이전 항목: 만료된 티켓


### 좋아요 목록
<img width="235" height="510" alt="image" src="https://github.com/user-attachments/assets/a7bf707d-bf50-43dd-ae4b-1cc51a6eab89" />

- 좋아요한 영화 사진 정렬
- 사진 클릭 시 영화 상세로 이동

## 실행 영상


https://github.com/user-attachments/assets/5823f34e-ce42-42db-9238-50bd36f73482




