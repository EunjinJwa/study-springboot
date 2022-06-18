# Spring Security

## basic
* auth : 기본 회원 인증
* google oauth : 구글 인증 로그인

google oauth
* `Oauth2UserRequest`
  * 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code를 리턴(Oauth-Client 라이브러리 -> AccessToken 요청
* 로그인한 유저의 프로필 정보를 조회 => `loadUser()`
  * `DefaultOauth2UserService` 를 상속(extendes) 받은 클래스에서 loadUser() 함수를 통해 구글 유저의 프로필정보를 받아올 수 있음





