package Baekjoon.B_18430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 나무의 재료에 따라 부메랑을 만들 수 있는 조합을 모두 찾은 후, 부메랑들의 강도의 합의 최댓값을 찾도록 함
 *   
 */

/* 
 * 손으로 풀어보기
 * 1. 부메랑의 중심이 될 좌표를 하나 선정하여 하나의 부메랑을 만들어 부메랑의 강도의 합을 구함
 * 2. 만들어진 부메랑을 기준으로 만들 수 있는 부메랑 조합(┑, ┙, ┕, ┍, 만들지 않음)을 계속해서 만들어나가 부메랑들의 강도의 합에 더해주도록 함
 * 3. 이를 반복하여 만들어질 수 있는 부메랑들의 조합에 따른 부메랑들의 강도의 합의 최댓값을 찾아 출력
 */

/*
 * 18430) 무기_공학 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] board; // board(나무 재료의 각 위치의 강도)
    static boolean[][] visited; // visited(각 위치의 나무 재료 사용 유무)
    static int answer = 0; // answer(부메랑들의 강도의 합의 최댓값)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
    }

    /*
     * 부메랑의 중심이 될 좌표를 하나 선정하고 이에 따라 만들 수 있는 부메랑을 계속해서 만들기
     */
    static void find(int position, int sum) {
        if (position == N * M) { // 모든 위치를 탐색했다면 부메랑들의 강도의 합을 갱신
            answer = Math.max(answer, sum);
            return;
        }

        int r = position / M; // r, c(부메랑의 중심 좌표)
        int c = position % M;

        if (!visited[r][c]) { // 중심 좌표를 기준으로 부메랑을 만들 수 있는지 확인
            if (c - 1 >= 0 && !visited[r][c - 1] && r + 1 < N && !visited[r + 1][c]) { // ┑ 부메랑 만들기
                visited[r][c - 1] = true;
                visited[r][c] = true;
                visited[r + 1][c] = true;
                find(position + 1, sum + board[r][c - 1] + (2 * board[r][c]) + board[r + 1][c]);
                visited[r][c - 1] = false;
                visited[r][c] = false;
                visited[r + 1][c] = false;
            }
            if (c - 1 >= 0 && !visited[r][c - 1] && r - 1 >= 0 && !visited[r - 1][c]) { // ┙ 부메랑 만들기
                visited[r][c - 1] = true;
                visited[r][c] = true;
                visited[r - 1][c] = true;
                find(position + 1, sum + board[r][c - 1] + (2 * board[r][c]) + board[r - 1][c]);
                visited[r][c - 1] = false;
                visited[r][c] = false;
                visited[r - 1][c] = false;
            }
            if (c + 1 < M && !visited[r][c + 1] && r - 1 >= 0 && !visited[r - 1][c]) { // ┕ 부메랑 만들기
                visited[r][c + 1] = true;
                visited[r][c] = true;
                visited[r - 1][c] = true;
                find(position + 1, sum + board[r][c + 1] + (2 * board[r][c]) + board[r - 1][c]);
                visited[r][c + 1] = false;
                visited[r][c] = false;
                visited[r - 1][c] = false;
            }
            if (c + 1 < M && !visited[r][c + 1] && r + 1 < N && !visited[r + 1][c]) { // ┍ 부메랑 만들기
                visited[r][c + 1] = true;
                visited[r][c] = true;
                visited[r + 1][c] = true;
                find(position + 1, sum + board[r][c + 1] + (2 * board[r][c]) + board[r + 1][c]);
                visited[r][c + 1] = false;
                visited[r][c] = false;
                visited[r + 1][c] = false;
            }
        }
        find(position + 1, sum); // 중심 좌표의 나무 재료를 사용하지 않도록 해 부메랑을 만들지 않음
    }

    /*
     * 부메랑들의 강도의 합의 최댓값 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        find(0, 0);
        print();
    }
}