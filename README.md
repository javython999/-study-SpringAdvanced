# 스프링 핵심 원리 - 고급편
## ThreadLocal
쓰레드 로컬은 해당 쓰레드만 접근할 수 있는 특별한 저장소를 말한다.
쓰레드A와 쓰레드B의 데이터를 각각 저장하고 쓰레드에 따라 저장한 데이터를 구별해준다.
쓰레드 로컬을 사용하면 동시성 문제를 해결할 수 있다.
```java
// ThreadLocal<T> 변수명 = new ThreadLocal<>();
ThreadLocal<String> userName = new ThreadLocal<>();

// 데이터 set
userName.set("홍길동");

// 데이터 get
userName.get();

// 쓰레드 로컬 제거
userName.remove();
```
쓰레드 로컬은 사용후 ``remove()``를 호출해서 제거해주어야 한다.
다 사용한 뒤에 제거를 해주지 않으면 메모리 누수가 발생 할 수 있다.
***