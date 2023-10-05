package Do_it.Do_it_09;

import java.util.Scanner;

/**
 * 1010) 다리_놓기
 */
public class D079_1010 {
    static int T, N, M;
    static long[][] D;

    public static void main(String[] args) {
        // D(DP 배열)
        D = new long[31][31];
        // D 배열 초기화하기
        for (int i = 0; i <= 30; i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }
        for (int i = 2; i <= 30; i++) {
            // 고르는 수의 개수가 전체 개수를 넘을 수 없음
            for (int j = 1; j < i; j++) {
                // 조합 점화식
                D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
            }
        }
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스)
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            // N(서쪽)
            N = sc.nextInt();
            // M(동쪽)
            M = sc.nextInt();
            // D[M][N] 출력하기
            System.out.println(D[M][N]);
        }
    }
}
