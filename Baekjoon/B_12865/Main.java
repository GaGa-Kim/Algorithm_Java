package Baekjoon.B_12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 배낭에 넣을 수 있는 각 무게에 따른 물건의 최대 가치를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 아이템과 무게에 따른 가치 테이블을 생성
 * 2. 가치의 최대값을 출력
 */

/*
 * 12865) 평범한_배낭  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static Item[] items; // items(물건들을 담은 배열)
    static int[][] dp; // dp(아이템과 무게에 따른 가치 테이블)

    /*
     * Item(물건 정보를 담을 클래스)
     */
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    /*
     * 물건 저장하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            items[i] = new Item(W, V);
        }
    }

    /*
     * 배낭에 넣을 수 있는 물건들의 가치 찾기
     */
    static void calculate() {
        dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) { // N개의 물품을 K개의 무게에 따른 가치로 갱신
            for (int j = 1; j <= K; j++) {
                Item item = items[i];
                if (item.weight > j) { // 무게가 커 N번째 물품을 담을 수 없는 경우
                    dp[i][j] = dp[i - 1][j];
                } else { // N번째 물품을 담을 수 있는 경우
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - item.weight] + item.value);
                }
            }
        }
    }

    /*
     * 가치의 최대값 출력하기
     */
    static void print() {
        System.out.println(dp[N][K]);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        calculate();
        print();
    }
}