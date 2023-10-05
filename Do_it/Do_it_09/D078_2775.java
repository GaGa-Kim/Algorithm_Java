package Do_it.Do_it_09;

import java.util.Scanner;

/**
 * 2775) 부녀회장이_될테야
 */
public class D078_2775 {
    static int T, K, N;
    static int[][] D;

    public static void main(String[] args) {
        // D(DP 배열)
        D = new int[15][15];
        // D 배열 초기화하기
        for (int i = 0; i < 15; i++) {
            D[i][1] = 1;
            D[0][i] = i;
        }
        // 점화식으로 D 배열 값 채우기
        for (int i = 1; i < 15; i++) {
            for (int j = 2; j < 15; j++) {
                D[i][j] = D[i][j - 1] + D[i - 1][j];
            }
        }
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스)
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            // K(층수)
            K = sc.nextInt();
            // N(호수)
            N = sc.nextInt();
            System.out.println(D[K][N]);
        }
    }
}
