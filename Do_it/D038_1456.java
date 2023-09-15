package Do_it;

import java.util.Scanner;

/**
 * 1456) 거의_소수
 */
public class D038_1456 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Min(시작 수)
        long Min = sc.nextLong();
        // Max(종료 수)
        long Max = sc.nextLong();
        // A(소수 배열)
        long[] A = new long[10000001];
        // A 배열 초기화하기
        for (int i = 2; i < A.length; i++) {
            A[i] = i;
        }
        for (int i = 2; i <= Math.sqrt(A.length); i++) {
            // 소수가 아니면 넘어감
            if (A[i] == 0) {
                continue;
            }
            // 소수의 배숫값을 10000000까지 탐색하기
            for (int j = i + i; j < A.length; j = j + i) {
                // 이 수가 소수가 아니라는 것을 표시하기 (배수 지우기)
                A[j] = 0;
            }
        }
        int count = 0;
        for (int i = 2; i < 10000001; i++) {
            // A 배열에서 소수인 값일 때
            if (A[i] != 0) {
                // temp = 현재 소수
                long temp = A[i];
                while ((double) A[i] <= (double) Max / (double) temp) {
                    if ((double) A[i] >= (double) Min / (double) temp) {
                        // 정답값 증가
                        count++;
                    }
                    // temp = temp * 현재 소수
                    temp = temp * A[i];
                }
            }
        }
        // 정답 출력하기
        System.out.println(count);
    }
}
