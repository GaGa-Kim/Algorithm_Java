package Programmers.LV_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 12910) 나누어_떨어지는_숫자_배열
 */
public class L005_12910 {
    // arr(자연수를 담은 배열)
    // divisor(자연수)
    public int[] solution(int[] arr, int divisor) {
        // answer(정답 리스트)
        List<Integer> answer = new ArrayList<>();
        for (int i : arr) {
            // i가 divisor로 나누어 떨어진다면
            if (i % divisor == 0)
                // answer에 i 저장
                answer.add(i);
        }
        // answer 오름차순 정렬
        Collections.sort(answer);
        // answer이 비어있다면
        if (answer.isEmpty())
            // 배열에 -1을 담아 반환
            return new int[] { -1 };
        // answer이 비어있지 않다면
        else
            // answer을 배열로 변환하여 반환
            return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L005_12910 solution = new L005_12910();

        int[] arr = { 5, 9, 7, 10 };
        int divisor = 5;

        int[] result = solution.solution(arr, divisor);

        System.out.println(Arrays.toString(result));
    }
}
