package Do_it;

import java.util.Scanner;

/**
 * 11726) 2xn_타일링
 */
public class D087_11726 {
    static long mod = 10007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // D[N](길이가 2 x N인 직사각형이 2 x 1, 1 x 2 타일을 붙일 수 있는 경우의 수)
        long D[] = new long[10001];
        // 길이가 2 x 1일 때 타일의 경우의 수
        D[1] = 1;
        // 길이가 2 x 2일 때 타일의 경우의 수
        D[2] = 2;
        for (int i = 3; i <= N; i++) {
            // 나온 결과를 10007 나머지 연산 수행하기
            D[i] = (D[i - 1] + D[i - 2]) % mod;
        }
        // D[N]값 출력하기
        System.out.println(D[N]);
    }
}