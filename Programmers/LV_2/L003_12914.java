package Programmers.LV_2;

/**
 * 12914) 멀리_뛰기
 */
public class L003_12914 {
    // n(멀리뛰기에 사용될 칸의 수)
    public long solution(int n) {
        // D(i번째 칸에 도달할 수 있는 경우의 수)
        int[] D = new int[2001];
        D[1] = 1;
        D[2] = 2;
        for (int i = 3; i < 2001; i++) {
            // (이전 칸에서 1칸 뛸 때 + 이전 칸에서 2칸 뛸 때의 경우의 수) % 1234567
            D[i] = (D[i - 1] + D[i - 2]) % 1234567;
        }
        // D[n] 반환
        return D[n];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L003_12914 solution = new L003_12914();

        int n = 4;

        long result = solution.solution(n);

        System.out.println(result);
    }
}
