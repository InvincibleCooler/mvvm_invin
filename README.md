# mvvm_invin

1차 개발 조건

MVVM + 리파지토리 패턴 사용 (리파지토리는 remote + room을 기준으로 한다.)
UI 레이어, 데이터 레이어를 분리한다.
화면은 5분후 리프레시 된다.
네트워크는 retrofit을 사용한다.

리스트에 데이터가 없는 경우
목록이 없습니다.
목록이 있는 경우 아래와 같이 목록이 보여진다.
더보기가 있는 경우 더보기 처리를 한다.

필요한 경우 
FastAdapter(https://github.com/mikepenz/FastAdapter), 
paging3 (https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=ko)
를 사용해도 좋다.

![스크린샷 2022-06-16 오후 4 21 50](https://user-images.githubusercontent.com/39255829/174028510-6c254b04-5d8b-483f-8e9a-f3877087ff1a.png)


2차 개발 조건

Hilt 사용해서 refactoring
