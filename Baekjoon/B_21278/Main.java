package Baekjoon.B_21278;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 플로이드 워셜을 이용해 각 건물 지점에서 다른 지점까지 가는 모든 경로의 시간을 구한 후, 
 *   건물을 두 개 짓는 모든 경우의 수에 대하여 가장 적은 시간을 갖는 건물 두 개를 고르도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 플로이드 워셜을 이용해 각 건물 지점에서 다른 지점까지 가는 모든 경로의 시간을 구하도록 함
 * 2. 건물 중 두 개를 골라 치킨집을 정할 경우의 왕복 시간의 총합을 구하도록 함
 *    이때 두 치킨집 중 더 작은 거리를 선택해야 함
 * 3. 이를 반복하여 최단 시간을 구해 출력하도록 함
 */

/*
 * 21278) 호석이_두_마리_치킨 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] times; // times(건물 사이의 왕복 시간)
    static boolean[] visited; // visited(방문 유무 배열)
    static int first = Integer.MAX_VALUE; // first, second(건물 2개가 지어질 건물 번호)
    static int second = Integer.MAX_VALUE;
    static int min_time = Integer.MAX_VALUE; // min_time(건물부터 모든 도시에서의 왕복 최단 시간의 합)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        times = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                times[i][j] = 101;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            times[A][B] = 1;
            times[B][A] = 1;
        }

        visited = new boolean[N + 1];
    }

    /*
     * 각 건물 사이의 왕복 거리 구하기
     */
    static void floyd_warshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (times[i][j] > times[i][k] + times[k][j]) {
                        times[i][j] = times[i][k] + times[k][j];
                    }
                }
            }
        }
    }

    /*
     * 치킨집을 지을 두 개의 건물 조합 찾기
     */
    static void find(int start, int count, int[] selected) {
        if (count == 2) { // 치킨집을 지을 두 개의 건물을 골랐다면
            int time = 0; // time(치킨집으로부터 모든 도시에서의 왕복 시간의 합)
            for (int i = 1; i <= N; i++) {
                if (visited[i]) {
                    continue;
                }
                int min = times[i][selected[0]]; // 두 치킨집 중 더 짧은 왕복 거리를 찾도록 함
                min = Math.min(times[i][selected[1]], min);
                time += min * 2;
            }

            if (min_time > time) {
                min_time = time;
                first = selected[0];
                second = selected[1];
            }
            return;
        }

        for (int i = start; i <= N; i++) { // 두 개의 치킨집 조합 선택하기
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            selected[count] = i;
            find(i + 1, count + 1, selected);
            visited[i] = false;
        }
    }

    /*
     * 건물 번호와 왕복 시간의 합 출력하기
     */
    static void print() {
        System.out.println(first + " " + second + " " + min_time);
    }

    public static void main(String[] args) throws IOException {
        init();
        floyd_warshall();
        find(1, 0, new int[2]);
        print();
    }
}