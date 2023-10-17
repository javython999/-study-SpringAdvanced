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
## TemplateMethod Pattern
> 작업에서 알고리즘의 골격을 정의하고 일부 단계를 하위 클래스로 연기합니다. 템플릿 메서드를 사용하면 하위 클래스가 알고리즘의 구조를 변경하지 않고도 알고리즘의 특정 단계를 재정의할 수 있습니다.
> -GoF-

부모 클래스에 알고리즘의 골격인 템플릿을 정의하고, 일부 변경되는 로직은 자식 클래스에 정의하는 것이다.
이렇게 하면 자식 클래스가 알고리즘의 전체 구조를 변경하지 않고, 특정 부분만 재정의할 수 있다. 상속과 오버라이딩을 통한
다형성으로 문제를 해결하는 것이다.

"하지만"

상속을 사용하기 때문에 상속의 단점을 그대로 안고간다. 자식 클래스가 부모 클래스와 컴파일 시점에 강하게 결합되는 문제가 있다.
자식 클래스의 입장에서는 부모 클래스의 기능을 사용하지 않지만 템플릿 메서드 패턴을 구현하기 위해 부모 클래스를 상속받는다.
부모 클래스에 새로운 메서드가 추가 되면 모든 자식 클래스들은 사용하지 않아도 새로 추가된 메서드를 오버라이딩 해야 한다.
***
## Strategy Pattern
> 알고리즘 제품군을 정의하고 각각을 캡슐화하여 상호 교환 가능하게 만들자. 전략을 사용하면 알고리즘을 사용하는 클라이언트와 독립적으로 알고리즘을 변경할 수 있다.
> -GoF-
> 
변하지 않는 부분을 `context`라는 곳에 두고, 변하는 부분을 `strategy`라는 인터페이스를 만들고
각각의 전략들은 `strategy`인터페이스를 구현하도록해 문제를 해결한다. 템플릿 메서드 패턴과 다르게 상속이 아니라 위임으로 문제를 해결한다.

전략 패턴은 2가지로 나눌 수 있다.
1. `context`와 `strategy`를 조립후 실행하는 방법
2. `context`를 실행 할 때 `strategy`를 전달 받아 실행하는 방법이 있다.

1의 경우는 이미 조립이 끝났기 떄문에 전략을 신경쓰지 않고 실행에 초점을 둔다 하지만 전략을 변경하는데 유연하지 않다.
2의 경우 실행직전 전략을 받아 실행하기 때문에 전략을 변경하기 매우 쉽지만 실행할 때 마다 전략을 지정해주어야 한다.
***
## Proxy
클라이언트는 서버에 필요한 것을 요청하고, 서버는 클라이언트의 요청을 처리한다.
이 개념을 객체에 도입하면 요청하는 객체는 클라이언트, 요청을 처리하는 객체는 서버가 된다.

일반적으로 클라이언트가 서버에게 요청을 하고 처리 결과를 직접 받는다. 이것을 직접호출이라고 한다.
클라이언트가 서버에게 직접 요청을 하는것이 아니라 어떤 대리자를 통해 간접적으로 서버에게 요청을 할 수 있다.
여기서 대리자를 Proxy라고 한다.

Proxy는 클라이언트의 요청뿐만 접근제어, 캐싱, 부가 기능추가등 다양한 동작을 할 수 있고
클라언트는 요청이후의 과정에 대해서는 알필요가 없다.

### Proxy의 역할
> 대체 가능

객체에서 프록시가 되려면, 클라이언트는 서버에게 요청을 한 것인지 프록시에게 요청한 것인지 조차 몰라야 한다.
서버와 클라이언트는 같은 인터페이스를 사용해야 한다.
클라이언트가 사용하는 서버 객체를 프록시 객체로 변경해도 클라이언트 코드를 변경하지 않고 동작할 수 있어야 한다.

> 접근 제어

권한에 따른 접근 허용/차단, 캐싱, 지연로딩

> 부가 기능 추가

원래 서버가 제공하는 기능에 더해서 부가 기능을 수행한다.

예)
* 요청값이나 응답 값을 중간에 변형
* 실행시간 측정
***
## Reflection
리플렉션 기술을 사용하면, 클래스나 메서드의 메타정보를 동적으로 획득하고, 동적으로 호출 할 수 있다.

### JDK 동적 프록시
동적 프록시 기술을 사용하면 개발자가 직접 프록시 클래스를 만들지 않아도 된다.(런타임에 개발자 대신 만들어준다.)
> 주의 <br>
> JDK 동적 프록시는 인터페이스를 기반으로 프록시를 동적으로 만들어준다. 따라서 인터페이스가 필수이다.
