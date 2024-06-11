package Baekjoon.B_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 세대가 거듭할 수록 이전 세대의 좌표들을 기준으로 반시계 방향으로 90도 회전시킨 방향으로 드래곤 커브가 그려지는 것을 볼 수 있음
 *   그러므로 이를 이용해 드래곤 커브의 세대만큼 반복하여 선분의 방향을 구하도록 함
 *   
 */

/* 
 * 손으로 풀어보기
 * 1. 입력받은 드래콘 커브의 정보에 따라 시작점부터 시작해 이동하는 방향을 모두 구하기
 *    이때 1세대부터 시작해 g세대까지 반복하며 이전 세대를 바탕으로 선분의 방향을 구하도록 함
 *    선분의 방향은 이전 세대의 좌표를 기준으로 반시계 방향으로 90도 회전시킨 방향
 * 2. 구한 방향에 따라 드래곤 커브를 그림
 * 3. 모든 드래곤 커브를 그린 후, 좌표를 살펴가며 1x1의 정사각형의 점이 모두 그려진 개수를 찾아 출력 
 */

/*
 * 15685) 드래곤_커브  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static boolean[][] board = new boolean[101][101]; // board(격자의 좌표)
    static int[] dx = { 1, 0, -1, 0 }; // dx, dy(이동할 방향에 따른 좌표)
    static int[] dy = { 0, -1, 0, 1 };

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(x, y, d, g);
        }
    }

    /*
     * 드래곤 커브 그리기
     */
    static void draw(int x, int y, int d, int g) {
        board[x][y] = true;

        List<Integer> directions = getDirections(d, g);
        for (int direction : directions) { // 방향에 따라 드래곤 커브 그리기
            x += dx[direction];
            y += dy[direction];
            board[x][y] = true;
        }
    }

    /*
     * 이동 방향 구하기
     */
    static List<Integer> getDirections(int d, int g) {
        List<Integer> directions = new ArrayList<Integer>();
        directions.add(d);

        for (int i = 1; i <= g; i++) { // 이전 세대의 좌표를 가지고 세대만큼 반복하며 드래곤 커브의 방향 구하기
            for (int j = directions.size() - 1; j >= 0; j--) {
                int direction = (directions.get(j) + 1) % 4; // 이전 세대의 좌표에서 반시계 방향으로 90도 회전시킨 방향
                directions.add(direction);
            }
        }
        return directions;
    }

    /*
     * 정사각형의 개수를 구해 출력하기
     */
    static void print() {
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1]) { // 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부일 경우
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        print();
    }
}