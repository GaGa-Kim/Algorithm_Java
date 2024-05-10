package Baekjoon.B_7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 익은 토마토의 상, 하, 좌, 우, 위, 아래를 탐색해 익지 않은 토마토가 있을 경우 익을 수 있도록 해줌
 */

/* 
 * 손으로 풀어보기
 * 1. 익은 토마토의 상, 하, 좌, 우, 위, 아래를 살펴보며 익지 않은 토마토가 있을 경우 익을 수 있도록 해줌
 * 2. 토마토가 모두 익을 때까지의 최소 날짜를 출력
 */

/*
 * 7569) 토마토 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int M, N, H;
    static int[][][] tomatoes; // tomatoes(토마토의 정보를 저장할 배열)
    static Queue<Tomato> queue = new LinkedList<Tomato>(); // queue(익은 토마토를 담을 큐)
    static int[] dr = { -1, 1, 0, 0, 0, 0 }; // dr, dc, dh(상, 하, 좌, 우, 위, 아래 좌표)
    static int[] dc = { 0, 0, -1, 1, 0, 0 };
    static int[] dh = { 0, 0, 0, 0, 1, -1 };
    static int allDay = 0; // allDay(토마토가 모두 익을 때까지의 최소 날짜)

    /*
     * Tomato(토마토 정보를 담을 클래스)
     */
    static class Tomato {
        int h, r, c;
        int day;

        public Tomato(int h, int r, int c, int day) {
            this.h = h;
            this.r = r;
            this.c = c;
            this.day = day;
        }
    }

    /*
     * 토마토 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomatoes = new int[H][N][M]; // 각 층에 토마토 저장하기
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    tomatoes[h][r][c] = Integer.parseInt(st.nextToken());
                    if (tomatoes[h][r][c] == 1) {
                        queue.add(new Tomato(h, r, c, 0));
                    }
                }
            }
        }
    }

    /*
     * 토마토 익히기
     */
    static void ripen() {
        while (!queue.isEmpty()) {
            Tomato now = queue.poll();
            allDay = now.day;
            for (int d = 0; d < 6; d++) { // 익은 토마토의 상하좌우를 살펴보며 익지 않은 토마토가 있을 경우 익히기
                int nh = now.h + dh[d];
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M || tomatoes[nh][nr][nc] == -1) {
                    continue;
                }
                if (tomatoes[nh][nr][nc] == 0) {
                    tomatoes[nh][nr][nc] = 1;
                    queue.add(new Tomato(nh, nr, nc, now.day + 1));
                }
            }
        }

        if (!isAllRipen()) { // 모두 익지 않았을 경우, -1 출력
            allDay = -1;
        }
    }

    /*
     * 모든 토마토가 익어있는지 확인하기
     */
    static boolean isAllRipen() {
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (tomatoes[h][r][c] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * 토마토가 모두 익을 때까지의 최소 날짜 출력하기
     */
    static void print() {
        System.out.println(allDay);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        ripen();
        print();
    }
}