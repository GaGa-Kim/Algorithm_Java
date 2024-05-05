package Baekjoon.B_1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 고객의 수에 따른 투자해야 하는 돈의 최소값들을 구해 이를 이용해 계속해서 다음 고객의 수에 따른 비용을 구함
 *   이때 적어도 C명이므로 최소 비용을 가지고 C명보다 많은 고객을 확보할 때도 가능하게 됨
 */

/* 
 * 손으로 풀어보기
 * 1. 고객의 수에 따른 비용 테이블을 생성
 * 2. 고객(적어도 C명에서 C + 100명)을 확보하는데 사용되는 최소 비용으로 갱신
 */

/*
 * 1106) 호텔 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int C, N;
    static City[] cities; // cities(도시 정보)
    static int[] dp; // dp(고객의 수에 따른 비용 테이블)

    /*
     * City(도시 정보를 담을 클래스)
     */
    static class City {
        int cost;
        int customer;

        public City(int cost, int customer) {
            this.cost = cost;
            this.customer = customer;
        }
    }

    /*
     * 비용 구하기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cities = new City[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());
            cities[i] = new City(cost, people);
        }
    }

    /*
     * 고객의 수에 따른 비용 구하기
     */
    static void calculate() {
        dp = new int[C + 101]; // 적어도 C명 늘리는 것이므로, 그보다 100명 더 늘어도 됨
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (City city : cities) { // 고객 수를 얻기 위해 각 도시에 따라 홍보할 때 드는 비용을 갱신
            for (int i = city.customer; i < C + 101; i++) {
                if (dp[i - city.customer] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - city.customer] + city.cost);
                }
            }
        }
    }

    /*
     * 고객을 확보하는데 사용되는 최소 비용 출력하기
     */
    static void print() {
        int answer = Integer.MAX_VALUE;
        for (int i = C; i < C + 101; i++) { // 가장 적은 비용으로 적어도 C명을 늘리는 경우를 찾기
            answer = Math.min(answer, dp[i]);
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        calculate();
        print();
    }
}