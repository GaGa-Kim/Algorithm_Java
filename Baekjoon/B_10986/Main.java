package Baekjoon.B_10986;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 수들끼리의 누적합을 구한 후, 이들이 M으로 나누어 떨어지는지 확인하여 구간의 개수를 증가시키도록 함
 *   또한 나머지가 같은 구간을 이용해 M으로 나누어 떨어지는지 확인하도록 함
 *   예) A = {1, 2, 3, 1, 2}, M = 3
 *      S[1] = 1 + 2 = 3 -> M으로 나누어 떨어짐
 *      S[0] = 1 -> 나머지 1이 발생 
 *      S[3] = 1 + 2 + 3 + 1 = 7 -> 나머지 1이 발생
 *      그러므로 S[1] ~ S[3] = 2 + 3 + 1 -> M으로 나누어 떨어짐 
 */

/* 
 * 손으로 풀어보기
 * 1. 수들끼리의 누적합을 구함
 * 2. 누적합을 확인해 M으로 나누어 떨어지는지 확인하여 구간의 개수를 증가
 * 3. 나머지가 같은 구간을 이용해 M으로 나누어 떨어지도록 하여 구간의 개수를 증가
 * 4. 구간의 개수를 출력
 */

/*
 * 10986) 나머지_합 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static long[] prefix_sum; // prefix_sum(누적합 배열)
    static long[] remains; // remains(M으로 나눈 나머지인 0, 1, ... M - 1에 대한 개수를 담은 배열)
    static long answer = 0; // answer(연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        prefix_sum = new long[N + 1]; // 누적합을 구해 저장하기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefix_sum[i] = prefix_sum[i - 1] + Integer.parseInt(st.nextToken());
        }
    }

    /*
     * 구간의 개수 찾기
     */
    static void find() {
        remains = new long[M];

        for (int i = 1; i <= N; i++) { // 누적합 배열의 모든 값에 대해 M으로 나누어 떨어지는지 확인
            int remain = (int) (prefix_sum[i] % M);
            if (remain == 0) {
                answer++;
            }
            remains[remain]++; // 나누어 떨어지는 나머지에 대한 개수를 저장
        }

        for (int i = 0; i < M; i++) { // 나머지가 같은 구간을 2개를 뽑아 M으로 나누어 떨어지도록 함
            if (remains[i] > 1) {
                answer += (remains[i] * (remains[i] - 1) / 2);
            }
        }
    }

    /*
     * 구간의 개수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        find();
        print();
    }
}