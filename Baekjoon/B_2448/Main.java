package Baekjoon.B_2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 삼각형을 계속해서 2로 나눠가며 쪼개가며 가장 작은 삼각형을 만들도록 재귀형태로 구현하도록 해 가장 작은 삼각형이 된 부분만 별로 채움
 */

/* 
 * 손으로 풀어보기
 * 1. n이 3이 될 때 가장 작은 삼각형이 되므로 이때만 별을 찍어주도록 함
 * 2. 가장 작은 삼각형이 되도록 2로 나눠가며 쪼개기
 * 3. 모든 칸을 조건에 맞게 채운 후, 이를 출력
 */

/*
 * 2448) 별_찍기_-_11 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static char[][] board; // board(별을 담을 배열)

    /*
     * 별 찍기 준비하기
     */
    static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());

        board = new char[N][N * 2 - 1];
        for (int i = 0; i < N; i++) { // 공백으로 채움
            Arrays.fill(board[i], ' '); 
        }
    }

    /*
     * 별 찍기
     */
    static void star(int r, int c, int n) {
        if (n == 3) { // n이 3일 경우 가장 작은 삼각형이므로 별 찍기
            board[r][c] = '*'; // 삼각형 첫 번째 줄
            board[r + 1][c - 1] = board[r + 1][c + 1] = '*'; // 삼각형 두 번째 줄
            board[r + 2][c - 2] = board[r + 2][c - 1] = board[r + 2][c] = board[r + 2][c + 1] = board[r + 2][c + 2] = '*'; // 삼각형 세 번째 줄
            return;
        } else { // n이 3이 아닐 경우 가장 작은 삼각형이 아니므로 삼각형을 계속해서 n / 2 사이즈로 쪼개기
            int size = n / 2;
            star(r, c, size); // 삼각형을 n / 2 사이즈로 쪼갠 첫 번째 좌표
            star(r + size, c - size, size); // 삼각형을 n / 2 사이즈로 쪼갠 두 번째 좌표
            star(r + size, c + size, size); // 삼각형을 n / 2 사이즈로 쪼갠 세 번째 좌표
        }
    }

    /*
     * 별 찍기 출력하기
     */
    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N * 2 - 1; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        star(0, N - 1, N);
        print();
    }
}