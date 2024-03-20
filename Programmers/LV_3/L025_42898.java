package Programmers.LV_3;

/**
 * 42898) 등굣길
 */
public class L025_42898 {
    // m, n(격자의 크기)
    // puddles(물이 잠긴 지역의 좌표)
    public int solution(int m, int n, int[][] puddles) {
        // mod(1000000007)
        int mod = 1000000007;
        // D(D[i][j]는 집에서부터 i번째 줄의 j번째까지의 최단 경로 횟수)
        int[][] D = new int[n + 1][m + 1];
        // 물이 잠긴 지역은 지나갈 수 없으므로 최단 경로 횟수는 -1
        for (int i = 0; i < puddles.length; i++) {
            D[puddles[i][1]][puddles[i][0]] = -1;
        }
        // 집 위치 1
        D[1][1] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                // 물이 담긴 지역이라면
                if (D[i][j] == -1) {
                    continue;
                }
                // 왼쪽의 최단경로 횟수가 0보다 크다면
                if (D[i - 1][j] > 0) {
                    // 오른쪽으로 이동
                    D[i][j] += D[i - 1][j] % mod;
                }
                // 위쪽의 최단경로 횟수가 0보다 크다면
                if (D[i][j - 1] > 0) {
                    // 아래로 이동
                    D[i][j] += D[i][j - 1] % mod;
                }
            }
        }
        // D[n][m] % mod 반환
        return D[n][m] % mod;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L025_42898 solution = new L025_42898();

        int m = 4;
        int n = 3;
        int[][] puddles = { { 2, 2 } };

        int result = solution.solution(m, n, puddles);

        System.out.println(result);
    }
}