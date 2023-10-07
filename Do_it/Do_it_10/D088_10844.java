package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 10844) 쉬운_계단_수
 */
public class D088_10844 {
    static int N;
    static long mod = 1000000000;
    static long D[][];
    static long sum;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        // D[N][H](길이가 N일 때 높이 H로 끝나는 계단 수의 모든 경우의 수)
        D = new long[N + 1][11];
        // 길이가 1일 때 만드는 높이 H로 끝나는 계단 수의 모든 경우의 수는 1
        for (int i = 1; i <= 9; i++) {
            D[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            // N에서 높이가 0이면 N - 1에서 높이가 1이어야 계단 수가 가능
            D[i][0] = D[i - 1][1];
            // N에서 높이가 9이면 N - 1에서 높이가 8이어야 계단 수가 가능
            D[i][9] = D[i - 1][8];
            // 높이가 1 ~ 8이면서 N - 1에서 자신보다 한 단계 위 또는 한 단계 아래 높이에서 올 수 있음
            for (int j = 1; j <= 8; j++) {
                D[i][j] = (D[i - 1][j - 1] + D[i - 1][j + 1]) % mod;
            }
        }
        // sum(결괏값)
        sum = 0;
        // sum에 D[N][i]의 값을 모두 더하기
        for (int i = 0; i < 10; i++) {
            sum = (sum + D[N][i]) % mod;
        }
        // sum 출력하기
        System.out.println(sum);
    }
}
