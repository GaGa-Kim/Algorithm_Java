package Do_it.Do_it_06;

import java.util.Scanner;

/**
 * 1929) 소수_구하기
 */
public class D037_1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // M(시작 수) N(종료 수)
        int M = sc.nextInt();
        int N = sc.nextInt();
        // A(소수 배열)
        int[] A = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            // A 배열 초기화하기
            A[i] = i;
        }
        // N의 제곱근까지 반복하기
        for (int i = 2; i <= Math.sqrt(N); i++) {
            // 소수가 아니면 넘어감
            if (A[i] == 0) {
                continue;
            }
            // 소수의 배수 값을 N까지 반복하기
            for (int j = i + i; j <= N; j = j + i) {
                // 이 수가 소수가 아니라는 것을 표시하기
                A[j] = 0;
            }
        }
        // M ~ N까지 반복하기
        for (int i = M; i <= N; i++) {
            // A 배열에서 소수인 값 출력하기
            if (A[i] != 0) {
                System.out.println(A[i]);
            }
        }
    }
}