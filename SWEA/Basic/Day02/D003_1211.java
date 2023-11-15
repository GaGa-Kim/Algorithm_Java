package SWEA.Basic.Day02;

import java.util.Scanner;

/**
 * 1211) Ladder2
 */
public class D003_1211 {
    private static int[][] ladder;
    private static int answer;
    private static int min;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 번호)
            int t = sc.nextInt();
            // ladder(사다리 숫자 저장 2차원 배열 (100 x 100 만큼))
            ladder = new int[100][100];
            // 사다리 숫자 저장
            for (int i = 0; i < ladder.length; i++) {
                for (int j = 0; j < ladder.length; j++) {
                    ladder[i][j] = sc.nextInt();
                }
            }
            // answer(출발점의 y)
            answer = 0;
            // min(최소 이동 횟수)
            min = Integer.MAX_VALUE;
            // 도착 위치 저장
            for (int i = 0; i < ladder.length; i++) {
                if (ladder[99][i] == 1) {
                    // x = 99, y = i(도착 위치)
                    int x = 99;
                    int y = i;
                    // move 실행
                    move(x, y);
                }
            }
            // #T와 출발점의 answer 반환
            System.out.println("#" + t + " " + answer);
        }
    }

    private static void move(int x, int y) {
        // // dx, dy(왼쪽, 오른쪽, 위 방향)
        int[] dx = { 0, 0, -1 };
        int[] dy = { -1, 1, 0 };
        // visited(방문 배열)
        boolean[][] visited = new boolean[100][100];
        // count(이동 횟수)
        int count = 0;
        while (x != 0) {
            for (int j = 0; j < 3; j++) {
                // nx, ny(왼쪽, 오른쪽, 위 방향에 따른 x, y 위치)
                int nx = x + dx[j];
                int ny = y + dy[j];
                // 사다리를 벗어나지 않는지
                if (nx >= 0 && nx < ladder.length && ny >= 0 && ny < ladder.length) {
                    // 이동할 수 있다면
                    if (ladder[nx][ny] == 1 && visited[nx][ny] != true) {
                        // 방문 배열 true로 변경
                        visited[nx][ny] = true;
                        // count 증가
                        count++;
                        // x, y를 이동 위치로 갱신
                        x = nx;
                        y = ny;
                    }
                }
            }
        }
        // 최소 이동 횟수가 현재 이동 횟수보다 크다면
        if (min > count) {
            // 최소 이동 횟수 갱신
            min = count;
            // 출발점의 y 갱신
            answer = y;
        }
    }
}