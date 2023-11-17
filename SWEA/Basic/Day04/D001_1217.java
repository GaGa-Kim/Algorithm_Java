package SWEA.Basic.Day04;

import java.util.Scanner;

/**
 * 1217) 거듭 제곱
 */
public class D001_1217 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 번호)
            int t = sc.nextInt();
            // n, m 저장
            int n = sc.nextInt();
            int m = sc.nextInt();
            // answer(거듭제곱 값)
            int answer = 0;
            // answer = 거듭제곱 함수(n, m)
            answer = pow(n, m);
            // #T와 answer 반환
            System.out.println("#" + t + " " + answer);
        }
    }

    // 거듭제곱 함수 구현하기
    private static int pow(int n, int m) {
        if (m == 1) {
            return n;
        }
        return n * pow(n, m - 1);
    }
}