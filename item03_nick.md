### private 생성자나 열거 타입으로 싱글턴임을 보증하라

#### 싱글턴
> 인스턴스를 오직 하나만 생성할 수 있는 클래스를 말한다. 

전형적인 예로는 무상태 객체나 설계상 유일해야 하는 시스템 컴포넌트가 있다. 
그렇다면 무상태 객체란 무엇일까?? + 시스템 컴포넌트??

싱글톤 단점 1. 클래스를 싱글턴으로 만들면 이를 사용하는 클라이언트를 테스트하기 어려워 질 수 있다. 
타입을 인터페이스로 정의한 다음 그 인터페이스를 구현해서 만든 싱글턴이 아니라면 싱글턴 인스턴스를 mock 구현으로 대체할 수 없기 때문이다. 

싱글턴을 만드는 방식은 보통 둘 중 하나이다. 
1. public static 멤버가 final 필드인 방식

``` java
public class Elvis {
	public static final Elvis INSTANCE = new Elvis();
	private Elvis() { ... }

	public void leaveTheBuilding()
}
```
한편, 이러한 방식은 예외가 한가지 있다. 리플랙션 API인 AccessibleObject.setAttribute를 사용해 private 생성자를 호출할 수 있다. 이러한 공격을 방어하려면 생성자를 수정하여
두 번째 객체가 생성되려 할 때, 예외를 던지게 하면 된다. 

```java
public class Elvis implements IElvis, Serializable {

    /**
     * 싱글톤 오브젝트
     */
    public static final Elvis INSTANCE = new Elvis();
    private static boolean created;

    private Elvis() {
        if (created) {
            throw new UnsupportedOperationException("can't be created by constructor.");
        }

        created = true;
    }
}
```
장점 1
해당 클래스가 싱글턴임이 API에 명백히 드러난다.
(public static 필드가 final이니 절대로 다른 객체를 참조할 수 없다.)
장점 2
간결하다 



싱글턴을 만드는 두 번째 방법에는 정적 팩터리 메서드를 public static 멤버로 제공한다. 
``` java
public class Elvis implements Singer {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis() { }
    public static Elvis getInstance() { return INSTANCE; }
```
-> Elvis.getInstance는 항상 같은 객체의 참조를 반환하므로 제 2의 인스턴스는 껼코 만들어 지지 않는다. 

장점 1
API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다. 
장점 2
원한다면 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다. 
장점 3
정적 팩토리의 메서드 참조를 공급자로 사용할 수 있다. 

직렬화 부분은 내일 아침에 봐보자

싱글턴을 만드는 세 번째 방법에는 원소가 하나인 열거 타입을 선언
```java
public enum Elvis {
    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("기다려 자기야, 지금 나갈께!");
    }
}
```

장점
더 간결하고 추가 노력 없이 직렬화 할 수 있다. 
아주 복잡한 직렬화 상황이나, 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다. 
대부분 상황에서는 원소가 하나뿐인 열거 타임이 싱글턴을 만드는 가장 좋은 방법이다. 
