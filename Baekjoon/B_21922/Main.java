package Baekjoon.B_21922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 물건의 종류에 따라서 바람의 방향을 바꿔가며 이동할 수 있는 모든 위치의 개수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 에어컨의 위치에서부터 상하좌우로 이동하도록 함
 * 2. 물건 1일 때, 바람 방향이 상 또는 하라면 같은 방향으로 이동할 수 있음 (좌 또는 우라면 이동 불가)
 * 3. 물건 2일 때, 바람 방향이 좌 또는 우라면 같은 방향으로 이동할 수 있음 (상 또는 하라면 이동 불가)
 * 4. 물건 3일 때, 바람 방향이 상우하좌라면 우상좌하 방향으로 이동할 수 있음
 * 5. 물건 4일 때, 바람 방향이 상우하좌라면 좌하우상 방향으로 이동할 수 있음
 * 6. 물건의 종류에 따라서 바람의 방향을 바꿔가며 이동할 수 있는 모든 위치의 개수를 출력
 */

/*
 * 21922) 학부_연구생_민상
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M; // N(연구실의 세로 크기), M(연구실의 가로 크기)
    static int[][] map; // map(연구실 내부 구조)
    static boolean[][][] visited; // visited(방문 유무 배열)
    static Queue<Wind> queue; // queue(에어컨 바람이 이동할 큐)
    static int[] dr = {-1, 0, 1, 0}; // dr, dc(상우하좌 이동 방향)
    static int[] dc = {0, 1, 0, -1};
    
    /*
     * Wind(에어컨의 이동 바람)
     */
    static class Wind {
        int r, c, dir; // r, c(바람 위치), dir(바람 방향)

        public Wind(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    /*
     * 에어컨 바람 이동 준비
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][4];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[i][j] = tmp;
                if (tmp == 9) { // 에어컨이 있는 위치라면 이 위치부터 상하좌우로 바람 이동
                    for (int d = 0; d < 4; d++) {
                        visited[i][j][d] = true;
                        queue.add(new Wind(i, j, d));
                    }
                }
            }
        }
    }
    
    /*
     * 에어컨 바람 이동하기
     */
    static void move() {
        while (!queue.isEmpty()) {
            Wind wind = queue.poll();
            int nr = wind.r + dr[wind.dir]; // nr, nc(바람이 이동할 다음 위치)
            int nc = wind.c + dc[wind.dir];
            
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) { // 연구실을 벗어났다면
                continue;
            }
            if (visited[nr][nc][wind.dir]) { // 이미 방문한 위치라면
                continue;
            }

            visited[nr][nc][wind.dir] = true;
            switch (map[nr][nc]) {
                case 1: // 물건의 종류가 1이면서 우, 좌 이동이라면 이동 종료
                    if (wind.dir == 1 || wind.dir == 3) {
                        continue;
                    }
                    break;
                case 2: // 물건의 종류가 2이면서 상, 하 이동이라면 이동 종료
                    if (wind.dir == 0 || wind.dir == 2) {
                        continue;
                    }
                    break;
                case 3: // 물건의 종류가 3이면서 상우하좌 이동이라면 우상좌하 방향으로 이동
                    if (wind.dir == 0) {
                        wind.dir = 1;
                    } else if (wind.dir == 1) {
                        wind.dir = 0;
                    } else if (wind.dir == 2) {
                        wind.dir = 3;
                    } else if (wind.dir == 3) {
                        wind.dir = 2;
                    }
                    break;
                case 4: // 물건의 종류가 4이면서 상우하좌 이동이라면 좌하우상 방향으로 이동
                    if (wind.dir == 0) {
                        wind.dir = 3;
                    } else if (wind.dir == 1) {
                        wind.dir = 2;
                    } else if (wind.dir == 2) {
                        wind.dir = 1;
                    } else if (wind.dir == 3) {
                        wind.dir = 0;
                    }
                    break;
            }
            queue.add(new Wind(nr, nc, wind.dir));
        }
    }

    /*
     * 원하는 자리 개수 출력하기
     */
    static void print() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int d = 0; d < 4; d++) {
                    if (visited[i][j][d]) {
                        count++;
                        break; // 네 모앵 중 하나라도 이동하여 방문했다면 중복 제외
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        move();
        print();
    }
}