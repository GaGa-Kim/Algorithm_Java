package Baekjoon.B_13164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 원생끼리의 키 차이를 구한 후, N명의 학생들을 K개의 그룹으로 나눈다고 하였으므로
 *   N - K개의 가장 큰 키 차이 값을 무시하여 제외하고 작은 키 차이 값들을 그룹의 개수에 합쳐 주도록 함
 *   예) 1, 3, 5, 6, 10 (2, 2, 1, 4의 키 차이) → 2, 4의 키 차이 값 제외 → 1,3 / 5,6 / 10
 */

/* 
 * 손으로 풀어보기
 * 1. 원생끼리의 키 차이를 구하기
 * 2. N - K개의 가장 큰 키 차이 값을 무시
 * 3. 나머지 키 차이 값들을 합쳐서 출력하기
 */

/*
 * 13164) 행복_유치원 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] heights; // heights(원생들의 키)
    static int[] diff; // diff(원생들의 키 차이)
    static int cost; // cost(티셔츠를 만드는 비용)

    /*
     * 티셔츠 만들기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
    }

    /*
     * 원생들의 키 차이 값 구하기
     */
    static void diffHeight() {
        diff = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = heights[i + 1] - heights[i];
        }
    }

    /*
     * 티셔츠 만드는 비용 구하기
     */
    static void calculate() {
        Arrays.sort(diff);
        for (int i = 0; i < N - K; i++) { // N - K개의 가장 큰 키 차이 값을 제외하고 합산
            cost += diff[i];
        }
    }

    /*
     * 티셔츠 만드는 비용 출력하기
     */
    static void print() {
        System.out.println(cost);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        diffHeight();
        calculate();
        print();
    }
}