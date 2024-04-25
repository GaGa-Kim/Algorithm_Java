package Baekjoon.B_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 부분합 중 합이 K가 되기 위해서는 정수가 K일 때, 부분합 중의 합이 K가 될 때이므로 이 둘 중 하나가 될 경우를 찾아줌
 */

/* 
 * 손으로 풀어보기
 * 1. N개의 수에 대한 누적합 배열을 생성
 *    sum[i] = sum[i - 1] + A[i]
 * 2. 누적합이 K와 같다면 합이 K인 부분합의 개수 증가
 * 3. 누적합이 있을 때 누적합 - K인 수가 있다면, 처음부터 누적합 - K가 되는 위치까지는 제외시켜주면 K가 되므로 합이 K인 부분합의 개수 증가
 *    sum[j] - sum[i - 1] = K → sum[j] - K = sum[i - 1]
 * 4. 합이 K인 부분합의 개수 출력
 */

/*
 * 2015) 수들의_합_4 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] sum; // sum(누적합 배열)
    static Map<Integer, Integer> map; // map(부분합들을 담을 해시맵)
    static long answer; // answer(합이 K인 부분합의 개수)

    /*
     * 부분합 구하기 준비하기
     */
    static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    /*
     * 누적합 구하기
     */
    static void prefix_sum(StringTokenizer st) throws IOException {
        sum = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) { // 누적합 저장
            int A = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + A;
        }
    }

    /*
     * 합이 K인 부분합 찾기
     */
    static void findK() {
        map = new HashMap<Integer, Integer>();

        for (int i = 1; i <= N; i++) {
            if (sum[i] == K) { // 누적합이 K와 같다면 합이 K인 부분합의 개수 증가
                answer++;
            }
            answer += map.getOrDefault(sum[i] - K, 0); // 누적합이 있을 때 누적합 - K인 수가 있다면 합이 K인 부분합의 개수 증가
            map.put(sum[i], map.getOrDefault(sum[i], 0) + 1);
        }
    }

    /*
     * 합이 K인 부분합의 개수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        prefix_sum(st);
        findK();
        print();
    }
}