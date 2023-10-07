package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 2193) 이친수
 */
public class D086_2193 {
    static int N;
    static long D[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(자릿수)
        N = sc.nextInt();
        // D(i 길이에서 끝이 0, 1로 끝나는 이친수의 개수 점화식 배열)
        D = new long[N + 1][2];
        // D 배열 초기화
        D[1][1] = 1; // 1
        D[1][0] = 0; // 0
        for (int i = 2; i <= N; i++) {
            // i번째 0으로 끝나는 개수 = i - 1에서 0으로 끝나는 개수 + i - 1에서 1로 끝나는 개수
            D[i][0] = D[i - 1][0] + D[i - 1][1];
            // i번째 1로 끝나는 개수 = i - 1에서 0으로 끝나는 개수
            D[i][1] = D[i - 1][0];
        }
        // N번째에서 0으로 끝나는 개수 + N번째에서 1로 끝나는 개수 출력하기
        System.out.print(D[N][0] + D[N][1]);
    }
}
