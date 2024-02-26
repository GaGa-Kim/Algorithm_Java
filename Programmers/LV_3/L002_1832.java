package Programmers.LV_3;

/**
 * 1832) 보행자_천국
 */
public class L002_1832 {
    int MOD = 20170805;

    // m, n(도시의 크기)
    // cityMap(지도)
    public int solution(int m, int n, int[][] cityMap) {
        // D(i, j번째 칸에 도달할 수 있는 경우의 수)
        int[][][] D = new int[m + 1][n + 1][2];
        // D[1][1][0], D[1][1][1]은 1
        D[1][1][0] = D[1][1][1] = 1;
        for (int i = 1; i <= m; i++) {
            // cityMap[i - 1][j - 1]이 0이라면 자동차가 자유롭게 지나갈 수 있으므로
            for (int j = 1; j <= n; j++) {
                if (cityMap[i - 1][j - 1] == 0) {
                    // D[i][j][0]에 (D[i - 1][j][0] + D[i][j - 1][1]) % MOD를 추가
                    D[i][j][0] += (D[i - 1][j][0] + D[i][j - 1][1]) % MOD;
                    // D[i][j][1]에 (D[i - 1][j][0] + D[i][j - 1][1]) % MOD를 추가
                    D[i][j][1] += (D[i - 1][j][0] + D[i][j - 1][1]) % MOD;
                }
                // cityMap[i - 1][j - 1]이 1이라면 자동차 통행이 금지되므로
                else if (cityMap[i - 1][j - 1] == 1) {
                    // D[i][j][0], D[i][j][1]은 0
                    D[i][j][0] = D[i][j][1] = 0;
                }
                // cityMap[i - 1][j - 1]이 2라면 보행자 안전을 위해 좌회전이나 우회전이 금지되므로
                else {
                    // D[i][j][0]은 D[i - 1][j][0]
                    D[i][j][0] = D[i - 1][j][0];
                    // D[i][j][1]은 D[i][j - 1][1]
                    D[i][j][1] = D[i][j - 1][1];
                }
            }
        }
        // D[m][n][0] 반환
        return D[m][n][0];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L002_1832 solution = new L002_1832();

        int m = 3;
        int n = 6;
        int[][] cityMap = { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } };

        int result = solution.solution(m, n, cityMap);

        System.out.println(result);
    }
}
