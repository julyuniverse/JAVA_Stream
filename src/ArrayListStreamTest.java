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
