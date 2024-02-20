package Programmers.LV_2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 181188) 요격_시스템
 */
public class L105_181188 {
    // targets(각 폭격 미사일의 x 좌표 범위 목록)
    public int solution(int[][] targets) {
        // answer(모든 폭격 미사일을 요격하기 위해 필요한 요격 미사일 수)
        int answer = 0;
        // e를 기준으로 오름차순 정렬, 같을 경우 s를 기준으로 정렬
        Arrays.sort(targets, new Comparator<int[]>() {
            public int compare(int[] t1, int[] t2) {
                if (t1[1] == t2[1]) {
                    return t1[0] - t2[0];
                }
                return t1[1] - t2[1];
            }
        });
        // end(요격 미사일의 위치)
        int end = targets[0][1]; // 첫 미사일의 e로 갱신
        // answer 증가
        answer++;
        for (int[] target : targets) {
            // target의 s가 end보다 크거나 같다면
            if (target[0] >= end) {
                // 같이 요격하지 못하므로 미사일을 추가해야 하므로 answer 증가
                answer++;
                // end를 e로 갱신
                end = target[1];
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L105_181188 solution = new L105_181188();

        int[][] targets = { { 4, 5 }, { 4, 8 }, { 10, 14 }, { 11, 13 }, { 5, 12 }, { 3, 7 }, { 1, 4 } };

        int result = solution.solution(targets);

        System.out.println(result);
    }
}
