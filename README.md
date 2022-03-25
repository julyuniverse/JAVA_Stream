# 스트림(Stream)

## 스트림이란?

- 자료의 대상과 관계없이 동일한 연산을 수행
  배열, 컬렉션을 대상으로 연산을 수행함.
  일관성 있는 연산으로 자료의 처리를 쉽고 간단하게 함
  자료 처리에 대한 추상화가 구현되었다고 함
- 한번 생성하고 사용한 스트림은 재사용할 수 없음.
  자료에 대한 스트림을 생성하여 연산을 수행하면 스트림은 소모됨.
  다른 연산을 수행하기 위해서는 스트림을 다시 생성해야 함.
- 스트림 연산은 기존 자료를 변경하지 않음.
  자료에 대한 스트림을 생성하면 스트림이 사용하는 메모리 공간은 별도로 생성되므로 연산이 수행돼도 기존 자료에 대한 변경은 발생하지 않음.
- 스트림 연산은 중간 연산과 최종 연산으로 구분됨.
  스트림에 대해 중간 연산은 여러 개의 연산이 적용될 수 있지만 최종 연산은 마지막에 한 번만 적용됨.
  최종 연산이 호출되어야 중간 연산에 대한 수행이 이루어지고 그 결과가 만들어짐
  따라서 중간 연산에 대한 결과를 연산 중에 알 수 없음.
  이를 ‘지연 연산’이라 함.

## 스트림 생성하고 사용하기

- 정수 배열에 스트림 생성하여 연산을 수행하는 예

```java
import java.util.Arrays;
import java.util.stream.IntStream;

public class IntArrayStreamTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        for (int num : arr) {
            System.out.println(num);
        }

        // 결과
        // 1
        // 2
        // 3

        System.out.println();

        IntStream intStream = Arrays.stream(arr);
        intStream.forEach(n -> System.out.println(n)); // 한번 사용한 스트림은 재사용 불가
        // intStream.forEach(n -> System.out.println(n)); // 오류

        // 결과
        // 1
        // 2
        // 3

        System.out.println();

        // 스트림을 또 사용하려면 다시 재 선언해서 사용
        int sum = Arrays.stream(arr).sum();
        System.out.println(sum);

        // 결과
        // 6

        List<Integer> integerList = new ArrayList<>();
        integerList.add(11);
        integerList.add(44);
        integerList.add(12);
        integerList.add(63);
        integerList.add(22);
        int sum2 = integerList.stream().mapToInt(s -> s.intValue()).sum();
        System.out.println(sum2);
        
        // 결과
        // 152
    }
}
```

## 중간 연산과 최종 연산

- 중간 연산의 예 - filter(), map(), sorted() 등
  조건에 맞는 요소를 추출(filter)하거나 요소를 변환(map)함.
- 최종 연산이 호출될 때 중간 연산이 수행되고 결과가 생성됨.
- 문자열 리스트에서 문자열의 길이가 5 이상인 요소만 출력하기

```java
sList.stream().filter(s -> s.length() >= 5).forEach(s -> System.out.println(s));
```

filter()는 중간 연산이고, forEach()는 최종 연산임.

- 고객 클래스 배열에서 고객 이름만 가져오기

```java
customerList.stream().map(c -> c.getName()).forEach(s -> System.out.println(s));
```

map()은 중간 연산이고, forEach()는 최종 연산임.

- 중간 연산과 최종 연산에 대한 구현은 람다식을 활용함.
- 최종 연산의 예 - forEach(), count(), sum() 등
  스트림이 관리하는 자료를 하나씩 소모해가며 연산이 수행됨.
  최종 연산 후에 스트림은 더 이상 다른 연산을 적용할 수 없음.
  forEach(): 요소를 하나씩 꺼내 옴.
  count(): 요소의 개수
  sum(): 요소들의 합

## ArrayList 객체에 스트림 생성하고 사용하기

- ArrayList에 문자열 자료(이름)을 넣고 이에 대한 여러 연산을 수행해 보기

```java
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListStreamTest {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("Lee");
        stringList.add("Kim");
        stringList.add("Park");
        stringList.add("Cheon");

        stringList.stream().filter(s -> s.length() >= 4).forEach(s -> System.out.println(s)); // 문자열이 길이가 4보다 크거나 같을 때 출력
        System.out.println();

        // 결과
        // Park
        // Cheon

        Stream<String> stringStream = stringList.stream(); // 스트림 생성 후 문자열 출력
        stringStream.forEach(s -> System.out.print(s + "\t"));
        System.out.println();

        stringList.stream().map(s -> s.length()).forEach(n -> System.out.print(n + "\t")); // 문자열 길이 출력
        System.out.println();
        System.out.println();

        // 결과
        // Lee	Kim	Park	Cheon
        // 3	3	4	5

        stringList.stream().sorted().forEach(s -> System.out.println(s)); // sorted() 오름차순
        System.out.println();

        // 결과
        // Cheon
        // Kim
        // Lee
        // Park

        stringList.stream().sorted(Comparator.reverseOrder()).forEach(s -> System.out.println(s)); // sorted() 내림차순
        System.out.println();

        // 결과
        // Park
        // Lee
        // Kim
        // Cheon
    }
}
```

- 새로운 연산을 수행하기 위해서는 기존의 스트림은 재사용할 수 없고 stream() 메서드를 호출하여 스트림을 다시 생성해야 함.