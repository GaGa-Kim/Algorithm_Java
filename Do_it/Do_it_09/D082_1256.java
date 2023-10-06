package Do_it.Do_it_09;

import java.util.Scanner;

/**
 * 1256) 사전
 */
public class D082_1256 {
    static int N, M, K;
    static int[][] D = new int[202][202];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(a 문자 개수)
        N = sc.nextInt();
        // M(z 문자 개수)
        M = sc.nextInt();
        // K(순번)
        K = sc.nextInt();
        // 조합 테이블 초기화
        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    D[i][j] = 1;
                } else {
                    D[i][j] = D[i - 1][j] + D[i - 1][j - 1];
                    // K 범위가 넘어가면 범위의 최댓값
                    if (D[i][j] > 1000000000) {
                        D[i][j] = 1000000001;
                    }
                }
            }
        }
        // 주어진 자릿수로 만들 수 없는 K번째 수이면
        if (D[N + M][M] < K) {
            System.out.println("-1");
        } else {
            while (!(N == 0 && M == 0)) {
                // a 문자를 선택했을 때 남은 문자들로 만들 수 있는 모든 경우의 수가 K보다 크면
                if (D[N - 1 + M][M] >= K) {
                    // a 출력하기
                    System.out.print("a");
                    // a 문자 개수 감소
                    N--;
                }
                // a 문자를 선택했을 때 남은 문자들로 만들 수 있는 모든 경우의 수가 K보다 작으면
                else {
                    // z 출력하기
                    System.out.print("z");
                    // K값 업데이트 (계산한 모든 경우의 수를 뺀 값으로 저장)
                    K = K - D[N - 1 + M][M];
                    // z 문자 개수 감소
                    M--;
                }
            }
        }
    }
}
