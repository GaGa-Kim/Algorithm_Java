package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12982) 예산
 */
public class L033_12982 {
    // d(부서별로 신청한 금액이 들어있는 배열)
    // budget(전체 예산)
    public int solution(int[] d, int budget) {
        // answer(최대 지원 가능한 부서의 갯수)
        int answer = 0;
        // d 오름차순 정렬
        Arrays.sort(d);
        for (int i = 0; i < d.length; i++) {
            // budget에서 d[i] 빼주기
            budget -= d[i];
            // 현재 전체 예산이 0보다 작다면
            if (budget < 0)
                // 종료
                break;
            // answer 증가
            answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L033_12982 solution = new L033_12982();

        int[] d = { 1, 3, 2, 5, 4 };
        int budget = 9;

        int result = solution.solution(d, budget);

        System.out.println(result);
    }
}
