package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12940) 최대공약수와_최소공배수
 */
public class L024_12940 {
    // n, m(두 자연수)
    public int[] solution(int n, int m) {
        // answer(두 수의 최대 공약수와 최소 공배수를 담은 배열)
        int[] answer = new int[2];
        // answer[0] = 최대 공약수 함수
        answer[0] = gcd(n, m);
        // answer[1] = n * m / answer[0]
        answer[1] = n * m / answer[0];
        // answer 반환
        return answer;
    }

    // 최대 공약수 함수
    private int gcd(int n, int m) {
        // a(n과 m 중 큰 수)
        int a = Math.max(n, m);
        // b(n과 m 중 작은 수)
        int b = Math.min(n, m);
        // b가 0이라면
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // 최대 공약수 함수(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L024_12940 solution = new L024_12940();

        int n = 3;
        int m = 12;

        int[] result = solution.solution(n, m);

        System.out.println(Arrays.toString(result));
    }
}
