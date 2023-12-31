package Programmers.LV_1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 68644) 두_개_뽑아서_더하기
 */
public class L043_68644 {
    // numbers(정수 배열)
    public int[] solution(int[] numbers) {
        // set(서로 다른 인덱스에 있는 두 개의 수를 뽑아 연산한 숫자들)
        Set<Integer> set = new HashSet<>();
        // 인덱스가 다른 두 개의 정수 뽑기
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                // set에 numbers[i] + numbers[j] 추가
                set.add(numbers[i] + numbers[j]);
            }
        }
        // answer(서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서 만들 수 있는 모든 수의 배열)
        // answer에 set을 오름차순 정렬하면서 담기
        int[] answer = set.stream().sorted().mapToInt(Integer::intValue).toArray();
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L043_68644 solution = new L043_68644();

        int[] numbers = { 2, 1, 3, 4, 1 };

        int[] result = solution.solution(numbers);

        System.out.println(Arrays.toString(result));
    }
}
