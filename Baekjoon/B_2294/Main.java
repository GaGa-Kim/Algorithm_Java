package Baekjoon.B_2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 주어진 동전을 조합하여 K원을 만드는데 필요한 최소 개수를 찾도록 함 
 */

/* 
 * 손으로 풀어보기
 * 1. K원까지를 만들 때, 하나의 동전을 사용할 수 있는 경우와 여러 동전을 사용해야 하는 경우에 따른 경우의 수를 세도록 함
 * 2. 금액 K를 만드는 방법의 최소 개수를 출력
 */

/*
 * 2294) 동전 2
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] coins; // coins(동전들을 담은 배열)
    static int[] D; // D(각 동전을 만드는 최소 동전의 개수)

    /*
     * 금액 만들기 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    }

    /*
     * 금액 만들기3 15
1
5
12
     */
    static void make() {
        D = new int[K + 1];
        Arrays.fill(D, 10001);
        D[0] = 0;

        for (int i = 0; i < coins.length; i++) { // 동전의 모든 가치를 가지고 K원까지 만들 수 있는 방법의 수를 찾도록 함
            for (int j = coins[i]; j <= K; j++) {
                D[j] = Math.min(D[j], (D[j - coins[i]] + 1));
            }
        }
    }

    /*
     * 금액 K를 만드는 모든 방법의 최소 개수를 출력
     */
    static void print() {
        if (D[K] == 10001) {
            System.out.println(-1);
            return;
        } 
        System.out.println(D[K]);
    }

    public static void main(String[] args) throws IOException {
        init();
        make();
        print();
    }
}
