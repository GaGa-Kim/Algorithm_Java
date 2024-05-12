package Baekjoon.B_21610;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 비바라기 마법을 시전한 칸에서 명령에 따라 이동한 후, 물복사버그 마법을 시전해 바구니의 물의 양을 변화시킴
 *   이를 명령에 맞춰 M번 반복한 후, 바구니에 들어있는 물의 양의 합을 구해 출력함  
 */

/* 
 * 손으로 풀어보기
 * 1. 비바라기 마법을 시전한 칸에서 명령에 따라 구름을 이동
 * 2. 이동한 칸의 물의 양을 1 증가
 * 3. 물복사버그 마법을 시전해 이동한 칸의 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수를 세서 물의 양을 증가
 * 4. 이전에 구름이 생긴 칸을 제외하고 바구니에 저장된 물의 양이 2 이상인 칸에 구름이 생성되고 물의 양이 2 줄어듦
 * 5. 이를 명령에 맞춰 M번 반복한 후, 바구니에 들어있는 물의 양의 합을 구해 출력함
 */

/*
 * 21610) 마법사_상어와_비바라기 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[][] board; // board(격자 정보를 담을 배열)
    static boolean[][] visited; // visited(방문 유무를 담을 배열)
    static Queue<Cloud> queue = new LinkedList<Cloud>(); // queue(생성된 구름 정보를 담은 큐)
    static int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 }; // dr, dc(구름의 이동 방향)
    static int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };

    /*
     * Cloud(구름 정보를 담을 클래스)
     */
    static class Cloud {
        int r, c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    /*
     * 마법 시전 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N]; // 격자 정보 저장하기
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rainAttack();
        for (int i = 0; i < M; i++) { // M번 동안 명령에 맞춰 이동하기
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            move(d, s);
            waterAttack();
            makeCloud();
        }
    }

    /*
     * 비바라기를 시전해 비구름 생성하기
     */
    static void rainAttack() {
        queue.add(new Cloud(N - 1, 0));
        queue.add(new Cloud(N - 1, 1));
        queue.add(new Cloud(N - 2, 0));
        queue.add(new Cloud(N - 2, 1));
    }

    /*
     * 모든 구름을 이동 후 물의 양 1 증가
     */
    static void move(int d, int s) {
        for (Cloud cloud : queue) {
            cloud.r = (N + cloud.r + dr[d] * (s % N)) % N;
            cloud.c = (N + cloud.c + dc[d] * (s % N)) % N;
            board[cloud.r][cloud.c]++;
        }
    }

    /*
     * 물복사버그 마법을 시전해 바구니의 물 양 증가하기
     */
    static void waterAttack() {
        while (!queue.isEmpty()) {
            Cloud cloud = queue.poll();
            visited[cloud.r][cloud.c] = true;

            int count = 0; // count(대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수)
            for (int d = 1; d <= 7; d += 2) {
                int nr = cloud.r + dr[d];
                int nc = cloud.c + dc[d];
                if (nr >= 0 && nr < N && nc >= 0 && nc < N && board[nr][nc] >= 1) {
                    count++;
                }
            }
            board[cloud.r][cloud.c] += count;
        }
    }

    /*
     * 바구니에 저장된 물의 양에 따라 구름 생성하기
     */
    static void makeCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && board[i][j] >= 2) {
                    board[i][j] -= 2;
                    queue.add(new Cloud(i, j));
                }
            }
        }
        visited = new boolean[N][N]; // 한 번의 명령을 끝낸 후, 초기화
    }

    /*
     * 바구니에 들어있는 물의 양의 합을 구해 출력하기
     */
    static void print() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += board[i][j];
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        print();
    }
}