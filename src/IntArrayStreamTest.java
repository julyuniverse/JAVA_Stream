import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        System.out.println();

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
