package Programmers.LV_2;

import java.util.Arrays;

/**
 * 42842) 카펫
 */
public class L046_42842 {
    // brown(갈색 격자의 수)
    // yellow(노란색 격자의 수)
    public int[] solution(int brown, int yellow) {
        // all(격자의 전체 수) = brown + yellow
        int all = brown + yellow;
        // width(가로 길이), height(세로 길이)
        int width = 0, height = 0;
        for (int i = 3; i < all; i++) {
            // 약수일 경우
            if (all % i == 0) {
                height = i;
                width = all / i;
                // 구한 약수의 짝에서 각 -2씩을 하여 곱한 값이 yellow의 갯수와 같은지 확인
                if ((height - 2) * (width - 2) == yellow) {
                    break;
                }
            }
        }
        // answer에 height와 width 저장하여 반환
        int[] answer = new int[2];
        answer[0] = width;
        answer[1] = height;
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L046_42842 solution = new L046_42842();

        int brown = 10;
        int yellow = 2;

        int[] result = solution.solution(brown, yellow);

        System.out.println(Arrays.toString(result));
    }
}