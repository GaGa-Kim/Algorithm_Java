package 그리디;

import java.util.Scanner;

/**
 * 11047) 동전_0
 */
public class 동전_0 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(동전 개수) K(목표 금액)
        int N = sc.nextInt();
        int K = sc.nextInt();
        // A(동전 데이터 배열)
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            // A 배열 저장하기
            A[i] = sc.nextInt();
        }
        int count = 0;
        // 가치가 큰 동전부터 선택
        for (int i = N - 1; i >= 0; i--) {
            // 현재 K보다 동전 가치가 작으면
            if (A[i] <= K) {
                // 동전 수 += 목표 금액 / 현재 동전 가치
                count += (K / A[i]);
                // 목표 금액 = 목표 금액 % 현재 동전 가치
                K = K % A[i];
            }
        }
        // 누적된 동전 수 출력하기
        System.out.println(count);
    }
}