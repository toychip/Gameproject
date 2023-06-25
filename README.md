# Gameproject
플래시 게임 사이트 프로젝트 api

api 배포 url: http://54.180.13.188:8080/

|Method|URI|Description|비회원 접근 권한|JSON 변수 명|parameter|
|---|---|---|---|---|---|
|GET|/|게임 메인화면|O|name(optional)|-|
|GET|/game/{id}|게임 상세화면|O||-|
|POST|/auth/signup|회원가입|O|name, email, password|-|
|POST|/auth/login|로그인|O|username, password|-|
|GET|/mypage|마이페이지|X|-|-|
|POST|/posts|자유게시판-글 작성|X|title, content|-|
|GET|/posts|자유게시판-글 목록 조회|X|-|page(optional), size(optional), title(optional), content(optional), writtenBy(optional)|
|GET|/posts/{postId}|자유게시판-글 세부 조회|X|-|{postId}|
|PATCH|/posts/{postId}|자유게시판-글 수정|X, 작성자 외 수정 불가|title, content|{postId}|
|DELETE|/posts/{postId}|자유게시판-글 삭제|X, 작성자 외 삭제 불가|-|{postId}|

