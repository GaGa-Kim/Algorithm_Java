package Baekjoon.B_9084;

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
 * 1. 1원부터 M원까지를 만들 때, 하나의 동전을 사용할 수 있는 경우와 여러 동전을 사용해야 하는 경우에 따른 경우의 수를 세도록 함
 * 2. 금액 M을 만드는 모든 방법의 수를 출력
 */

/*
 * 9084) 동전
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[] coins; // coins(동전들을 담은 배열)
    static int[] D; // D(각 동전을 만들 수 있는 방법의 수)

    /*
     * 금액 만들기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        coins = new int[N];
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(st.nextToken());
            coins[i] = coin;
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
    }

    /*
     * 금액 만들기
     */
    static void make() {
        D = new int[M + 1];
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= M; j++) {
                if (j - coins[i] == 0) { // 하나의 동전 종류로만 금액을 만들 수 있다면
                    D[j]++;
                } else if (j - coins[i] > 0) { // 다른 동전 종류와 함께 금액을 만들어야 한다면
                    D[j] = D[j] + D[j - coins[i]];
                }
            }
        }
    }

    /*
     * 금액 M을 만드는 모든 방법의 수를 출력
     */
    static void print() {
        System.out.println(D[M]);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            init(st);
            make();
            print();
        }
    }
}