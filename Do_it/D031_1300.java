package Do_it;

import java.util.Scanner;

/**
 * 1300) K번째_수
 */
public class D031_1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(배열의 크기)
        int N = sc.nextInt();
        // K(구하고자 하는 index)
        int K = sc.nextInt();
        // start(시작 인덱스 = 1)
        long start = 1;
        // end(종료 인덱스 = K)
        long end = K;
        long answer = 0;
        while (start <= end) {
            // middle(중간 인덱스)
            long middle = (start + end) / 2;
            // cnt(중앙값보다 작은 수)
            long cnt = 0;
            for (int i = 1; i <= N; i++) {
                // cnt에 중앙 인덱스를 i로 나눈 값과 N중 작은 값을 더함
                cnt += Math.min(middle / i, N);
            }
            // 현재 중앙값보다 작은 수의 개수가 K보다 작음
            if (cnt < K) {
                start = middle + 1;
            }
            // 현재 중앙값보다 작은 수의 개수가 K보다 크거나 작음
            else {
                end = middle - 1;
                answer = middle;
            }
        }
        System.out.println(answer);
    }
}
