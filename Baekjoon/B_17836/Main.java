package Baekjoon.B_17836;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 벽을 피해 상하좌우로 BFS 탐색하면서 T시간만에 공주님이 있는 곳에 도달할 수 있는지 확인
 *   이때 용사가 그람을 구할 경우 벽에 상관없이 이동할 수 있게 됨
 */

/* 
 * 손으로 풀어보기
 * 1. 벽을 피해 상하좌우로 BFS 탐색
 * 2. 용사가 그람을 구할 경우부터 벽에 상관없이 이동할 수 있게 됨
 * 3. T시간만에 공주님이 있는 곳에 도달할 수 있는지 확인

/*
 * 17836) 공주님을_구해라! 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, T;
    static int[][] board; // board(성의 구조)
    static int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 좌표)
    static int[] dc = { 0, 0, -1, 1 };
    static int answer = Integer.MAX_VALUE; // answer(공주에게 도달할 수 있는 최단 시간)

    /*
     * Warrior(용사의 위치와 그람 획득 여부 클래스)
     */
    static class Warrior {
        int r, c;
        boolean hasKnife;
        int time;

        public Warrior(int r, int c, boolean hasKnife, int time) {
            this.r = r;
            this.c = c;
            this.hasKnife = hasKnife;
            this.time = time;
        }
    }

    /*
     * 구출 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
     * 공주에게 도달하기
     */
    static void find() {
        Queue<Warrior> queue = new LinkedList<Warrior>();
        boolean[][][] visited = new boolean[N][M][2];
        queue.add(new Warrior(0, 0, false, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Warrior now = queue.poll();

            if (now.time > T) {
                continue;
            }
            if (now.r == N - 1 && now.c == M - 1) { // 공주가 있는 위치에 도착했다면 최단 시간으로 갱신
                answer = Math.min(answer, now.time);
                return;
            }

            for (int d = 0; d < 4; d++) { // 상하좌우로 탐색
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) { // 벽 밖이라면 넘어감
                    continue;
                }

                if (!now.hasKnife) { // 그람을 가지고 있지 않다면
                    if (board[nr][nc] == 0 && !visited[nr][nc][0]) { // 벽이 아니며 방문하지 않은 곳으로 이동
                        queue.add(new Warrior(nr, nc, false, now.time + 1));
                        visited[nr][nc][0] = true;
                    } else if (board[nr][nc] == 2 && !visited[nr][nc][0]) { // 그람이 있으며 방문하지 않은 곳으로 이동
                        queue.add(new Warrior(nr, nc, true, now.time + 1)); // 그람 유무 갱신
                        visited[nr][nc][0] = true;
                    }
                } else { // 그람을 가지고 있다면
                    if (!visited[nr][nc][1]) { // 방문하지 않은 곳으로 이동
                        queue.add(new Warrior(nr, nc, true, now.time + 1));
                        visited[nr][nc][1] = true;
                    }
                }
            }
        }
    }

    /*
     * 공주 구출 여부 출력하기
     */
    static void print() {
        if (answer <= T) {
            System.out.println(answer);
            return;
        }
        System.out.println("Fail");
    }

    public static void main(String[] args) throws IOException {
        init();
        find();
        print();
    }
}