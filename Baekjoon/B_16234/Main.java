package Baekjoon.B_16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : BFS를 통해 이동하며 연합을 만든 후, 연합을 해지하는 것을 반복하며 인구 이동이 일어날 수 있을 때까지 이를 반복
 */

/* 
 * 손으로 풀어보기
 * 1. BFS를 통해 이동하며 연합을 생성
 * 2. 연합을 이루는 각 칸의 인구수를 조정
 * 3. 더 이상 연합이 만들어지지 않을 때까지 반복
 */

/*
 * 16234) 인구_이동 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, L, R;
    static int[][] board; // board(나라 인구를 담은 배열)
    static boolean[][] visited; // visited(방문 유무 배열)
    static List<Country> unionCountries; // unionCountries(연합한 나라들)
    static int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 이동 좌표)
    static int[] dc = { 0, 0, -1, 1 };
    static int T = 0; // T(인구 이동 일 수)

    /*
     * Country(나라 정보를 나타내는 클래스)
     */
    static class Country {
        int r, c;

        public Country(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    /*
     * 인구 이동 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
     * 인구 이동하기
     */
    static void move() {
        while (true) { // 더 이상 연합이 만들어지지 않을 때까지 반복
            boolean canMakeUnion = false;
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!visited[r][c]) {
                        int sum = makeUnion(r, c);
                        if (unionCountries.size() > 1) { // 연합한 나라가 2개 이상일 때
                            adjustCount(sum);
                            canMakeUnion = true;
                        }
                    }
                }
            }
            if (!canMakeUnion) {
                break;
            }
            T++;
        }
    }

    /*
     * 연합 생성하기
     */
    static int makeUnion(int r, int c) {
        Queue<Country> queue = new LinkedList<Country>();
        queue.add(new Country(r, c));
        visited[r][c] = true;
        unionCountries = new ArrayList<Country>();
        unionCountries.add(new Country(r, c));

        int sum = board[r][c]; // sum(연합한 나라들의 인구 수)
        while (!queue.isEmpty()) { // 상하좌우로 이동하며 연합을 구함
            Country now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) {
                    continue;
                }
                int diff = Math.abs(board[now.r][now.c] - board[nr][nc]);
                if (L <= diff && diff <= R) {
                    queue.add(new Country(nr, nc));
                    visited[nr][nc] = true;
                    unionCountries.add(new Country(nr, nc));
                    sum += board[nr][nc];
                }
            }
        }
        return sum;
    }

    /*
     * 연합에 따라 인구 수 조정하기
     */
    static void adjustCount(int sum) {
        int average = sum / unionCountries.size();
        for (Country country : unionCountries) {
            board[country.r][country.c] = average;
        }
    }

    /*
     * 인구 이동 일 수 출력하기
     */
    static void print() {
        System.out.println(T);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        move();
        print();
    }
}