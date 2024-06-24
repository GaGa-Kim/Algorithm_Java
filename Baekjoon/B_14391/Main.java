package Baekjoon.B_14391;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 현재의 위치에서 자기 자신과 가로 칸을 추가해 자르거나, 
 *   자기 자신과 세로 칸을 추가해 자르거나 
 *   둘 다 추가하지 않고 자기 자신만 자르는 것을 반복하여 완전 탐색하며 
 *   얻을 수 있는 가장 큰 점수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 종이 조각의 크기에 따라 종이 조각의 수를 저장
 * 2. 현재 위치의 종이 조각을 이미 사용했을 경우 넘어가도록 함
 * 3. 현재 위치의 종이 조각을 사용하지 않았을 경우,
 *    자기 자신만 잘라 조각을 만들거나,
 *    자기 자신과 가로 조각을 붙여 조각을 만들거나,
 *    자기 자신과 세로 조각을 붙여 조각을 만드는 것을 반복하여 조각의 합을 최대로 갱신함
 * 4. 조각의 최대 점수 합을 출력함
 */

/*
 * 14391) 종이_조각 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;

    static int[][] board; // board(종이 정보 배열)
    static boolean[][] visited; // visited(칸 사용 여부 배열)

    static int answer = 0; // answer(얻을 수 있는 점수의 최댓값)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        visited = new boolean[N][M];
    }

    /*
     * 조각 만들기
     */
    static void dfs(int index, int number, int sum) {
        if (index == N * M) { // 모든 칸을 확인했다면 얻을 수 있는 점수 갱신
            answer = Math.max(answer, sum);
            return;
        }

        int r = index / M;
        int c = index % M;

        if (visited[r][c]) { // 이미 사용한 칸이라면
            dfs(index + 1, number, sum);
        } else {
            visited[r][c] = true; // 자기 자신만 자르기
            number = number * 10 + board[r][c];
            dfs(index + 1, 0, sum + number);

            int end;
            int temp_number = number;
            for (end = r + 1; end < N; end++) { // 자기 자신과 가로 칸 자르기
                if (visited[end][c]) {
                    break;
                }
                visited[end][c] = true;
                temp_number = temp_number * 10 + board[end][c];
                dfs(index + 1, 0, sum + temp_number);
            }
            for (int i = r + 1; i < end; i++) {
                visited[i][c] = false;
            }

            temp_number = number;
            for (end = c + 1; end < M; end++) { // 자기 자신과 세로 칸 자르기
                if (visited[r][end]) {
                    break;
                }
                visited[r][end] = true;
                temp_number = temp_number * 10 + board[r][end];
                dfs(index + end - c + 1, 0, sum + temp_number);
            }
            for (int i = c + 1; i < end; i++) {
                visited[r][i] = false;
            }

            visited[r][c] = false;
        }
    }

    /*
     * 조각의 최대 점수 합 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, 0, 0);
        print();
    }
}
