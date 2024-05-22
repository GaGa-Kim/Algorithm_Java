package Baekjoon.B_1025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 등차수열로 이루어져야 하므로 조건에 따라 
 *   '왼쪽 아래 -> 위 / 왼쪽 위 -> 아래 / 오른쪽 아래 -> 위 / 오른쪽 위 -> 아래 / 행 -> 행 / 열 -> 열'로 이동하며 
 *   가장 큰 완전 제곱수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. '왼쪽 아래 -> 위 / 왼쪽 위 -> 아래 / 오른쪽 아래 -> 위 / 오른쪽 위 -> 아래 / 행 -> 행 / 열 -> 열'로 등차수열에 따라 이동
 * 2. 위를 반복하며 만들 수 있는 정수가 완전 제곱수이라면 더 큰 완전 제곱수로 갱신하여 가장 큰 완전 제곱수를 구하도록 함

/*
 * 1025) 제곱수_찾기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] board; // board(표 정보)
    static int answer = -1; // answer(가장 큰 완전 제곱수)

    /*
     * 데이터 저장하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(String.valueOf(s.charAt(j)));
            }
        }
    }

    /*
     * 완전 제곱수 찾기
     */
    static void find() {
        for (int i = 0; i < N; i++) { // 각 행과 열의 시작 지점 i, j
            for (int j = 0; j < M; j++) {
                for (int di = -N; di < N; di++) { // 등차수열의 공차 di, dj
                    for (int dj = -M; dj < M; dj++) {
                        if (di == 0 && dj == 0) {
                            continue;
                        }

                        int number = 0; // number(만들 수 있는 정수)
                        int nr = i; // nr, nc(시작 위치)
                        int nc = j;

                        while (nr >= 0 && nr < N && nc >= 0 && nc < M) { // 표 범위일 동안 등차수열 공차만큼 이동
                            number = number * 10 + board[nr][nc];
                            if (isPerfectSquare(number)) { // 만들 수 있는 정수가 완전 제곱수라면 갱신
                                answer = Math.max(answer, number);
                            }
                            nr += di;
                            nc += dj;
                        }
                    }
                }
            }
        }
    }

    /*
     * 완전 제곱수인지 확인
     */
    static boolean isPerfectSquare(int number) {
        int number_sqrt = (int) Math.sqrt(number);
        return number_sqrt * number_sqrt == number;
    }

    /*
     * 가장 큰 완전 제곱수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        find();
        print();
    }
}