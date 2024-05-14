package Baekjoon.B_1749;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 행렬에 따라 누적합 배열을 만든 후, 모든 부분 행렬에 따라 최대값을 갱신
 */

/* 
 * 손으로 풀어보기
 * 1. 누적합 배열 생성
 * 2. 원하는 모든 부분 행렬에 따라 최대값을 갱신
 * 3. 정수의 합이 최대가 되는 부분행렬의 합 출력
 */

/*
 * 1749) 점수따먹기 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] prefix_sum; // prefix_sum(누적합 배열)
    static int answer = Integer.MIN_VALUE; // answer(정수의 합이 최대가 되는 부분행렬의 합)

    /*
     * 누적합 배열 생성하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        prefix_sum = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                prefix_sum[i][j] = prefix_sum[i][j - 1] + prefix_sum[i - 1][j] - prefix_sum[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
     * 부분 행렬에 따른 최대값 찾기
     */
    static void find() {
        for (int width = 1; width <= N; width++) {
            for (int height = 1; height <= M; height++) {
                for (int row = width; row <= N; row++) {
                    for (int col = height; col <= M; col++) {
                        int value = prefix_sum[row][col] - (prefix_sum[row - width][col] + prefix_sum[row][col - height]) + prefix_sum[row - width][col - height];
                        answer = Math.max(answer, value);
                    }
                }
            }
        }
    }

    /*
     * 정수의 합이 최대가 되는 부분행렬의 합 출력
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        find();
        print();
    }
}