package Baekjoon.B_14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 각 테트로미노를 회전, 대칭시키며 종이 위에 올린 후, 가장 큰 수들의 합을 찾는 것이므로
 *   한 칸을 정한 후, 이 위치에서 상하좌우로 탐색하며 테트로미노의 크기인 4만큼이 되었을 때 수를 갱신하도록 함
 *   이때 보라색 테트로미노를 제외하고 나머지 테트로미노는 상하좌우 탐색을 통해 만들 수 있지만
 *   보라색 테트로미노는 탐색 시 2번째 칸에서 3번째 칸으로 이동하는 것이 아니라 2번째 칸에서 다시 한 번 탐색을 하도록 함
 *   
 *   파란색 : 우우우 (좌좌좌, 하하하, 상상상 ...)
 *   노란색 : 하우상 (우하좌, 상우하, 좌하우 ...)
 *   주황색 : 하하우 (우우상, 우우하, 좌좌하, 좌좌상, 상상좌, 상상우 ...)
 *   초록색 : 하우하 (우상우, 우하우, 좌하좌, 좌상좌, 상좌상, 상우상 ...)
 *   보라색 : 우/하/우 (좌/상/좌, 상/우/상, 하/좌/하 ...)
 *   4 * 4 * 4 = 64개만큼의 테트로미노가 존재하게 됨
 */

/* 
 * 손으로 풀어보기
 * 1. 한 칸을 정한 후, 이 위치에서 상하좌우로 탐색
 * 2. 테트로미노의 크기인 4만큼이 되었을 때 가장 큰 수들의 합을 갱신
 * 3. 이때 테트로미노의 크기가 2일 때, 
 *    보라색 테트로미노를 위해 2번째 칸에서 3번째 칸으로 이동하는 것이 아니라 2번째 칸에서 다시 한 번 탐색을 하도록 함
 * 4. 가장 큰 수들의 합을 출력함
 */

/*
 * 14500) 테트로미노 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;

    static int[][] paper; // paper(종이에 쓰여 있는 수들 배열)
    static boolean[][] visited; // visited(사용된 칸들 유무 배열)

    static int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 좌표)
    static int[] dc = { 0, 0, -1, 1 };

    static int answer = 0; // answer(가장 큰 수들의 합)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                tetromino(i, j, 1, paper[i][j]);
                visited[i][j] = false;
            }
        }
    }

    /*
     * 테트로미노 놓기
     */
    static void tetromino(int startR, int startC, int count, int sum) {
        if (count == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = startR + dr[d];
            int nc = startC + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) {
                continue;
            }

            if (count == 2) { // 보라색 테트로미노의 경우, 2번째 칸에서 다시 한 번 탐색
                visited[nr][nc] = true;
                tetromino(startR, startC, count + 1, sum + paper[nr][nc]);
                visited[nr][nc] = false;
            }

            visited[nr][nc] = true;
            tetromino(nr, nc, count + 1, sum + paper[nr][nc]);
            visited[nr][nc] = false;
        }
    }

    /*
     * 가장 큰 수들의 합 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        print();
    }
}