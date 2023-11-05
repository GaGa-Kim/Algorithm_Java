package Programmers.Kit.Sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 42746) 가장_큰_수
 */
public class K002_42746 {
    // numbers(0 또는 양의 정수가 담긴 배열)
    public String solution(int[] numbers) {
        // arr(numbers를 담을 문자열 배열)
        String[] arr = new String[numbers.length];
        // arr에 numbers 저장
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        // arr을 앞뒤로 붙여가며 더 큰 순서대로 정렬하도록 커스텀
        // Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
        // arr의 첫 번째가 0이라면
        if (arr[0].equals("0")) {
            // 0 반환
            return "0";
        }
        // arr을 합쳐서 반환
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            answer.append(arr[i]);
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        K002_42746 solution = new K002_42746();

        int[] numbers = { 6, 10, 2 };

        String result = solution.solution(numbers);

        System.out.println(result);
    }
}
