package Baekjoon.B_2447;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 가운데를 비워두고 나머지 부분을 N/3의 패턴으로 둘러싸도록 재귀형태로 구현하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 패턴이 N일 때, 크기 N/3의 패턴으로 둘러싼 형태가 되므로 패턴 N은 크기 N/3의 패턴으로 9조각으로 나누어지게 됨
 * 2. (1, 2, 3, 4, 6, 7, 8, 9)번째 조각일 경우 재귀 형태로 N/3의 패턴으로 채워줌
 * 3. (5)번째 조각일 경우 공백으로 채워줌
 * 4. 모든 칸을 조건에 맞게 채운 후, 이를 출력
 */

/*
 * 2447) 별_찍기_-_10 
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

        board = new char[N][N];
    }

    /*
     * 별 찍기
     */
    static void star(int r, int c, int n, boolean isBlank) {
        if (isBlank) { // 공백일 경우
            for (int i = r; i < r + n; i++) {
                for (int j = c; j < c + n; j++) {
                    board[i][j] = ' ';
                }
            }
            return;
        }

        if (n == 1) { // n이 1이 될 때 공백인 부분이 아니라면 별을 찍어줌
            board[r][c] = '*';
            return;
        }

        int size = n / 3; // size(패턴이 n일 때, 이를 둘러쌀 n/3 패턴 크기)
        int count = 1; // count(조각의 번호)
        for (int i = r; i < r + n; i += size) {
            for (int j = c; j < c + n; j += size) {
                if (count++ == 5) { // (5)번째 조각일 경우 공백으로 채워줌
                    star(i, j, size, true);
                } else { // (1, 2, 3, 4, 6, 7, 8, 9)번째 조각일 경우 재귀 형태로 n/3의 패턴으로 채워줌
                    star(i, j, size, false);
                }
            }
        }
    }

    /*
     * 별 찍기 출력하기
     */
    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        star(0, 0, N, false);
        print();
    }
}