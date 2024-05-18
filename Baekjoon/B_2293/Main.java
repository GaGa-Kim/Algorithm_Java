package Baekjoon.B_2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 주어진 금액을 만들기 위해 동전들을 조합해 만드는 방법을 모두 세도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 1원부터 K원까지를 만들 때, 하나의 동전을 사용할 수 있는 경우와 여러 동전을 사용해야 하는 경우에 따른 경우의 수를 세도록 함
 * 2. 금액 K를 만드는 모든 방법의 수를 출력
 */

/*
 * 2293) 동전_1 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] coins; // coins(동전들을 담은 배열)
    static int[] D; // D(각 동전을 만들 수 있는 방법의 수)

    /*
     * 금액 만들기 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new int[N]; // 동전 가치 저장하기
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
    }

    /*
     * 금액 만들기
     */
    static void make() {
        D = new int[K + 1];
        for (int i = 0; i < coins.length; i++) { // 동전의 모든 가치를 가지고 1원부터 K원까지를 만들 수 있는 방법의 수를 찾도록 함
            for (int j = 1; j <= K; j++) {
                if (j - coins[i] == 0) { // 하나의 동전 가치로만 금액을 만들 수 있다면
                    D[j]++;
                } else if (j - coins[i] > 0) { // 다른 동전 가치와 함께 금액을 만들어야 한다면
                    D[j] = D[j] + D[j - coins[i]];
                }
            }
        }
    }

    /*
     * 금액 K를 만드는 모든 방법의 수를 출력
     */
    static void print() {
        System.out.println(D[K]);
    }

    public static void main(String[] args) throws IOException {
        init();
        make();
        print();
    }
}