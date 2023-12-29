package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12954) x만큼_간격이_있는_n개의_숫자
 */
public class L030_12954 {
    // x(정수)
    // n(자연수)
    public long[] solution(int x, int n) {
        // answer(x부터 시작해 x씩 증가하는 숫자를 n개 지니는 리스트)
        long[] answer = new long[n];
        // answer의 첫 번째 배열에 x 저장
        answer[0] = x;
        // 이전 배열 값에 x를 합하여 저장
        for (int i = 1; i < answer.length; i++) {
            answer[i] = answer[i - 1] + x;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L030_12954 solution = new L030_12954();

        int x = 2;
        int n = 5;

        long[] result = solution.solution(x, n);

        System.out.println(Arrays.toString(result));
    }
}
