package Baekjoon.B_7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 익은 토마토의 상하좌우를 탐색해 익지 않은 토마토가 있을 경우 익을 수 있도록 해줌
 */

/* 
 * 손으로 풀어보기
 * 1. 익은 토마토의 상하좌우를 살펴보며 익지 않은 토마토가 있을 경우 익을 수 있도록 해줌
 * 2. 토마토가 모두 익을 때까지의 최소 날짜를 출력
 */

/*
 * 7576) 토마토 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int M, N;
    static int[][] tomatoes; // tomatoes(토마토의 정보를 저장할 배열)
    static Queue<Tomato> queue = new LinkedList<Tomato>(); // queue(익은 토마토를 담을 큐)
    static int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 좌표)
    static int[] dc = { 0, 0, -1, 1 };
    static int day = 0; // day(토마토가 모두 익을 때까지의 최소 날짜)

    /*
     * Tomato(토마토 정보를 담을 클래스)
     */
    static class Tomato {
        int r, c;
        int day;

        public Tomato(int r, int c, int day) {
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

        tomatoes = new int[N][M];
        for (int r = 0; r < N; r++) { // 토마토 저장하기
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                tomatoes[r][c] = Integer.parseInt(st.nextToken());
                if (tomatoes[r][c] == 1) {
                    queue.add(new Tomato(r, c, 0));
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
            day = now.day;
            for (int d = 0; d < 4; d++) { // 익은 토마토의 상하좌우를 살펴보며 익지 않은 토마토가 있을 경우 익히기
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M || tomatoes[nr][nc] == -1) {
                    continue;
                }
                if (tomatoes[nr][nc] == 0) {
                    tomatoes[nr][nc] = 1;
                    queue.add(new Tomato(nr, nc, now.day + 1));
                }
            }
        }

        if (!isAllRipen()) { // 모두 익지 않았을 경우, -1 출력
            day = -1;
        }
    }

    /*
     * 모든 토마토가 익어있는지 확인하기
     */
    static boolean isAllRipen() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (tomatoes[r][c] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * 토마토가 모두 익을 때까지의 최소 날짜 출력하기
     */
    static void print() {
        System.out.println(day);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        ripen();
        print();
    }
}