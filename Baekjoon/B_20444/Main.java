package Baekjoon.B_20444;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 가로로 색종이를 자르는 횟수를 이분 탐색하며 잘리는 색종이의 개수를 구하도록 함
 *   이를 반복하여 K개의 색종이 조각을 만들 수 있는지 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 가로로 색종이를 자르는 횟수를 0 ~ N / 2로 정하여 이분 탐색
 * 2. 이분 탐색하며 가로로 색종이를 자르는 횟수에 따라 잘리는 색종이의 개수를 구하도록 함
 * 3. 이를 반복하여 K개의 색종이 조각을 만들 수 있는지 찾도록 함

/*
 * 20444) 색종이와_가위
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long N, K;

    /*
     * 데이터 저장하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
    }

    /*
     * 가로로 자르는 횟수를 기준으로 이분 탐색
     */
    static void binary_search() {
        long start = 0;
        long end = N / 2;

        while (start <= end) {
            long row = (start + end) / 2; // row, col(가로로, 세로로 자르는 횟수)
            long col = N - row;

            long count = cut(row, col);

            if (count == K) { // K개의 색종이 조각이 만들어진다면, YES 출력
                print("YES");
                return;
            } else if (count > K) { // K개의 색종이 조각보다 많이 만들어진다면, 가로로 자르는 횟수를 줄여 색종이 조각의 개수를 줄이도록 함
                end = row - 1;
            } else if (count < K) { // K개의 색종이 조각보다 적게 만들어진다면, 가로로 자르는 횟수를 늘려 색종이 조각의 개수를 늘리도록 함
                start = row + 1;
            }
        }
        print("NO"); // 가로로, 세로로 자르는 횟수를 조정해도 K개의 색종이 조각을 만들 수 없다면, NO 출력
    }

    /*
     * 가로와 세로로 자르는 횟수에 따라 만들어지는 색종이 조각의 개수
     */
    static long cut(long row, long col) {
        return (row + 1) * (col + 1);
    }

    /*
     * N번의 가위질로 K개의 색종이 조각을 만들 수 있는지 여부 출력하기
     */
    static void print(String mention) {
        System.out.println(mention);
    }

    public static void main(String[] args) throws IOException {
        init();
        binary_search();
    }
}