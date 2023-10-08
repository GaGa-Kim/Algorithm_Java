package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 1915) 가장_큰_정사각형
 */
public class D091_1915 {
    static int N, M;
    static long[][] D;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(배열의 세로 길이)
        N = sc.nextInt();
        // M(배열의 가로 길이)
        M = sc.nextInt();
        // D[i][j] (i, j 위치에서 왼쪽 위로 만들 수 있는 최대 정사각형 길이를 저장하는 배열)
        D = new long[1001][1001];
        // max(최댓값 저장하기)
        long max = 0;
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                D[i][j] = Long.parseLong(String.valueOf(line.charAt(j)));
                // D[i][j]의 값이 1이면
                if (D[i][j] == 1 && i > 0 && j > 0) {
                    // 자신의 위, 왼쪽, 대각선 위의 값들 중 최솟값 + 1 값을 저장하기
                    D[i][j] = Math.min(D[i - 1][j - 1], Math.min(D[i][j - 1], D[i - 1][j])) + D[i][j];
                }
                // D[i][j]의 값이 최댓값보다 크다면
                if (max < D[i][j]) {
                    // 최댓값 업데이트
                    max = D[i][j];
                }
            }
        }
        // 정답(최댓값 * 최댓값) 출력하기
        System.out.println(max * max);
    }
}
