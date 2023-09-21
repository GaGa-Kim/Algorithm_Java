package Do_it.Do_it_06;

import java.util.Scanner;

/**
 * 1934) 최소공배수
 */
public class D042_1934 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // t(테스트 케이스)
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            // a(1번째 수)
            int a = sc.nextInt();
            // b(2번째 수)
            int b = sc.nextInt();
            // 결괏값 = a * b / gcd(a, b)
            int result = a * b / gcd(a, b);
            // 결괏값 출력하기
            System.out.println(result);
        }
    }

    // 최대 공약수 함수 구현하기
    public static int gcd(int a, int b) {
        // if(b가 0이면)
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // gcd(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }
}
