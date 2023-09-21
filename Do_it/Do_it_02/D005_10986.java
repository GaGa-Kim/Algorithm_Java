package Do_it.Do_it_02;

import java.util.Scanner;

/**
 * 10986) 나머지_합
 */
public class D005_10986 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N 입력받기(수열의 개수)
        int N = sc.nextInt();
        // M 입력받기 (나누어떨어져야 하는 수)
        int M = sc.nextInt();
        // S 선언하기 (합 배열)
        long[] S = new long[N];
        // C 선언하기 (같은 나머지의 인덱스를 카운트하는 배열)
        long[] C = new long[M];
        long answer = 0;
        // 수열 합 배열 만들기
        S[0] = sc.nextInt();
        for (int i = 1; i < N; i++) {
            S[i] = S[i - 1] + sc.nextInt();
        }
        // 합 배열의 모든 값에 % 연산 수행하기
        for (int i = 0; i < N; i++) {
            int remainder = (int) (S[i] % M);
            // 구간 합 자체가 0일 때 정답에 더하기
            if (remainder == 0)
                answer++;
            // 나머지가 같은 인덱스의 개수 카운팅하기
            C[remainder]++;
        }
        for (int i = 0; i < M; i++) {
            // 나머지가 같은 인덱스 중 2개를 뽑는 경우의 수를 더하기
            if (C[i] > 1)
                answer = answer + (C[i] * (C[i] - 1) / 2);
        }
        System.out.println(answer);
    }
}
