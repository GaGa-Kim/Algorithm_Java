package Do_it.Do_it_09;

import java.util.Scanner;

/**
 * 1947) 선물_전달
 */
public class D083_1947 {
    static int N;
    static long mod = 1000000000;
    // D(N명일 때 선물을 교환할 수 있는 모든 경우의 수 배열)
    static long D[] = new long[1000001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(학생의 수)
        N = sc.nextInt();
        D[1] = 0;
        D[2] = 1;
        // i명이 교환할 수 있는 모든 경우의 수
        for (int i = 3; i <= N; i++) {
            D[i] = (i - 1) * (D[i - 1] + D[i - 2]) % mod;
        }
        // D[N]값 출력하기
        System.out.println(D[N]);
    }
}
