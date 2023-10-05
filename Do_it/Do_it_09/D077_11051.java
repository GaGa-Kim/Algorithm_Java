package Do_it.Do_it_09;

import java.util.Scanner;

/**
 * 11051) 이항_계수_2
 */
public class D077_11051 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(총 개수)
        int N = sc.nextInt();
        // K(선택 수)
        int K = sc.nextInt();
        // D(DP 배열)
        int[][] D = new int[N + 1][N + 1];
        // D 배열 초기화하기
        for (int i = 0; i <= N; i++) {
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            // 고르는 수의 개수가 전체 개수를 넘을 수 없음
            for (int j = 1; j < i; j++) {
                // 조합 점화식
                D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
                // D[i][j] 값을 10007로 mod 연산한 값으로 업데이트하기
                D[i][j] = D[i][j] % 10007;
            }
        }
        System.out.println(D[N][K]);
    }
}