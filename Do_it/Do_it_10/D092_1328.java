package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 1328) 고층_빌딩
 */
public class D092_1328 {
    static int N, L, R;
    static long D[][][];
    static long mod = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(빌딩의 개수)
        N = sc.nextInt();
        // L(가장 왼쪽에서 봤을 때 보이는 빌딩의 수)
        L = sc.nextInt();
        // R(가장 오른쪽에서 봤을 때 보이는 빌딩의 수)
        R = sc.nextInt();
        // D[N][L][R] (빌딩 N개를 왼쪽에서 L개, 오른쪽에서 R개가 보이도록 배치할 수 있는 모든 경우의 수)
        D = new long[101][101][101];
        // 건물이 1개일 때 배치될 경우의 수는 1개
        D[1][1][1] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                for (int k = 1; k <= R; k++) {
                    D[i][j][k] = (D[i - 1][j - 1][k] + D[i - 1][j][k - 1] + D[i - 1][j][k] * (i - 2)) % mod;
                }
            }
        }
        // D[N][L][R]값 출력하기
        System.out.println(D[N][L][R]);
    }
}
