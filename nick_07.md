## item07 다 쓴 객체 참조를 해제하라

JVM    
: 운영체제의 메모리 영역에 접근하여 메모리를 관리하는 프로그램.  
-> 메모리 관리, Garbage Collector 수행

Garbage Collector   
: 동적으로 할당한 메모리 영역 중 사용하지 않는 영역을 탐지하여 해지하는 기능   

Stack: 정적으로 할당한 메모리 영역   
-> 원시 타입의 데이터가 값과 함께 할당. Heap영역에 생성된 Object 타입의 데이터의 참조 값 할당.   

Heap: 동적으로 할당한 메모리 영역.   
모든 Object 타입의 데이터가 할당. Heap영역의 Object를 가리키는 참조 변수가 Stack에 할당.   

<정적>   
<img width="286" alt="image" src="https://user-images.githubusercontent.com/62540133/167291998-ed570240-b551-4df5-a114-16f2ff314e5d.png">
   
<동적>
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listArgument = new ArrayList<>();
        listArgument.add("yaboong");
        listArgument.add("github");

        print(listArgument);
    }
```   
<img width="657" alt="image" src="https://user-images.githubusercontent.com/62540133/167292051-7cf161d4-e91b-4437-947e-d99e7e7960c9.png">.  

(unreachable object).  
<img width="637" alt="image" src="https://user-images.githubusercontent.com/62540133/167292323-df419a61-e344-44f4-a028-d2ec1e528de6.png">.    

(책의 stack 예제)   
```java
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    ...
    
    public Object pop() {
        if(size == 0){
            throw new EmpthStackException();
        }
        
        return elements[--size]; // 메모리 누수: elements[size] = null을 해 주어야
    }
}
```
  
  
