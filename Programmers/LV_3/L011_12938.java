package Programmers.LV_3;

import java.util.Arrays;

/**
 * 12938) 최고의_집합
 */
public class L011_12938 {
    // n(집합의 원소의 개수)
    // s(모든 원소들의 합)
    public int[] solution(int n, int s) {
        // n이 s보다 크다면
        if (n > s)
            // 최고의 집합을 만들 수 없으므로 -1을 담은 배열 반환
            return new int[] { -1 };
        // answer(최고의 집합)
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            // s / n을 answer[i]에 저장
            answer[i] = s / n;
        }
        for (int i = 0; i < s % n; i++) {
            // s를 n으로 나눈 나머지만큼 answer[i]를 1 증가
            answer[i]++;
        }
        // answer을 오름차순 정렬
        Arrays.sort(answer);
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L011_12938 solution = new L011_12938();

        int n = 2;
        int s = 9;

        int[] result = solution.solution(n, s);

        System.out.println(Arrays.toString(result));
    }
}
