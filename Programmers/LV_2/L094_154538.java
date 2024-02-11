package Programmers.LV_2;

import java.util.Arrays;

/**
 * 154538) 숫자_변환하기
 */
public class L094_154538 {
    // x, y, n(자연수)
    public int solution(int x, int y, int n) {
        // D(x를 i로 변환하기 위한 최소 연산 횟수)
        int[] D = new int[y + 1];
        // D 배열을 y + 1로 모두 갱신
        Arrays.fill(D, y + 1);
        // x를 x로 변환하기 위한 최소 연산 횟수인 0으로 갱신
        D[x] = 0;
        for (int i = x; i <= y; i++) {
            // D[i]가 y + 1이라면
            if (D[i] == y + 1) {
                // 변환할 수 없는 값이므로 D[i] = -1
                D[i] = -1;
                continue;
            }
            if (i + n <= y)
                // D[i + n]을 Math.min(D[i + n], D[i] + 1)로 갱신
                D[i + n] = Math.min(D[i + n], D[i] + 1);
            if (i * 2 <= y)
                // D[i * 2]를 Math.min(D[i * 2], D[i] + 1)로 갱신
                D[i * 2] = Math.min(D[i * 2], D[i] + 1);
            if (i * 3 <= y)
                // D[i * 3]을 Math.min(D[i * 3], D[i] + 1)로 갱신
                D[i * 3] = Math.min(D[i * 3], D[i] + 1);
        }
        // D[y] 반환
        return D[y];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L094_154538 solution = new L094_154538();

        int x = 10;
        int y = 40;
        int n = 5;

        int result = solution.solution(x, y, n);

        System.out.println(result);
    }
}
