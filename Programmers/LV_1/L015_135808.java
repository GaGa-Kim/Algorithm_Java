package Programmers.LV_1;

import java.util.Arrays;

/**
 * 135808) 과일_장수
 */
public class L015_135808 {
    // k(사과의 최대 점수)
    // m(한 상자에 들어가는 사과의 수)
    // score(사과들의 점수)
    public int solution(int k, int m, int[] score) {
        // answer(최대 이익)
        int answer = 0;
        // score 내림차순 정렬
        Arrays.sort(score);
        // m개씩 끊어가며 사과 한 상자의 가격 계산
        for (int i = score.length; i >= m; i -= m) {
            answer += score[i - m] * m;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L015_135808 solution = new L015_135808();

        int k = 3;
        int m = 4;
        int[] score = { 1, 2, 3, 1, 2, 3, 1 };

        int result = solution.solution(k, m, score);

        System.out.println(result);
    }
}
