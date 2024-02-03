package Programmers.LV_2;

import java.util.HashSet;
import java.util.Set;

/**
 * 131701) 연속_부분_수열_합의_개수
 */
public class L080_131701 {
    // elements(원형 수열의 모든 원소)
    public int solution(int[] elements) {
        // set(원형 수열의 연속 부분 수열 합을 저장하는 집합)
        Set<Integer> set = new HashSet<>();
        for (int size = 1; size <= elements.length; size++) {
            // 1부터 elements의 길이까지의 부분 수열의 합을 구함
            for (int i = 0; i < elements.length; i++) {
                // sum(연속 부분 수열 합)
                int sum = 0;
                for (int j = i; j < i + size; j++) {
                    // sum에 elements[j % elements의 길이] 추가
                    sum += elements[j % elements.length];
                }
                // set에 sum 저장
                set.add(sum);
            }
        }
        // set의 크기 반환
        return set.size();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L080_131701 solution = new L080_131701();

        int[] elements = { 7, 9, 1, 1, 4 };

        int result = solution.solution(elements);

        System.out.println(result);
    }
}
