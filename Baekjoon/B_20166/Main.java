package Baekjoon.B_20166;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 상하좌우, 대각선으로 탐색하며 문자열을 만들 수 있는지 확인하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 상하좌우, 대각선으로 탐색하며 문자열을 탐색하고 개수를 증가시키도록 함
 * 2. 문자열이 만들어지는 경우의 수를 순서대로 출력
 */

/*
 * 20166) 문자열_지옥에_빠진_호석 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, K;
    static char[][] board; // board(알파벳을 담은 격자 정보)
    static String[] question; // question(신이 좋아하는 문자열 정보)
    static Map<String, Integer> map = new HashMap<String, Integer>(); // map(문자열과 문자열이 만들어지는 경우의 수를 담은 해시맵)
    static int[] dr = { -1, 1, 0, 0, -1, 1, 1, -1 }; // dr, dc (상하좌우, 대각선 좌표)
    static int[] dc = { 0, 0, -1, 1, 1, -1, 1, -1 };

    /*
     * Point(좌표 정보를 담은 클래스)
     */
    static class Point {
        int r, c;
        String s;

        public Point(int r, int c, String s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    /*
     * 문자열을 만들 수 있는 경우의 수 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        question = new String[K];
        for (int i = 0; i < K; i++) {
            question[i] = br.readLine();
        }
    }

    /*
     * 문자열 탐색하기
     */
    static void find() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                Queue<Point> queue = new LinkedList<Point>();
                String s = board[r][c] + "";
                queue.add(new Point(r, c, s));
                map.put(s, map.getOrDefault(s, 0) + 1);

                while (!queue.isEmpty()) { // 모든 위치 탐색
                    Point now = queue.poll();
                    if (now.s.length() >= 5) { // 문자열이 5보다 크거나 같다면 종료
                        continue;
                    }
                    for (int d = 0; d < 8; d++) {
                        int nr = (now.r + dr[d]) % N; // 반대 방향 처리
                        int nc = (now.c + dc[d]) % M;
                        if (nr < 0) { // 대각선 방향 처리
                            nr += N;
                        }
                        if (nc < 0) {
                            nc += M;
                        }
                        String ns = now.s + board[nr][nc];
                        queue.add(new Point(nr, nc, ns));
                        map.put(ns, map.getOrDefault(ns, 0) + 1);
                    }
                }
            }
        }
    }

    /*
     * 문자열이 만들어지는 경우의 수를 순서대로 출력하기
     */
    static void print() {
        for (String s : question) {
            if (map.containsKey(s)) {
                System.out.println(map.get(s));
            } else {
                System.out.println(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        find();
        print();
    }
}