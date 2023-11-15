package SWEA.Basic.Day02;

import java.util.Scanner;

/**
 * 1210) Ladder1
 */
public class D002_1210 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 번호)
			int t = sc.nextInt();
            // ladder(사다리 숫자 저장 2차원 배열 (100 x 100 만큼))
            int[][] ladder = new int[100][100];
            // x, y(도착 위치)
            int x = 0, y = 0;
            // 사다리 숫자 저장
            for (int i = 0; i < ladder.length; i++) {
                for (int j = 0; j < ladder.length; j++) {
                    ladder[i][j] = sc.nextInt();
                    // 도착 위치 저장
                    if (ladder[i][j] == 2) {
                        x = i;
                        y = j;
                    }
                }
            }
            // dx, dy(왼쪽, 오른쪽, 위 방향)
            int[] dx = { 0, 0, -1 };
            int[] dy = { -1, 1, 0 };
            // 도착점에서부터 위로 올라가며 사다리 시작점을 찾음
            while (x != 0) {
                for (int j = 0; j < 3; j++) {
                    // nx, ny(왼쪽, 오른쪽, 위 방향에 따른 x, y 위치)
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    // 사다리를 벗어나지 않는지
                    if (nx >= 0 && nx < ladder.length && ny >= 0 && ny < ladder.length) {
                        // 이동할 수 있다면
                        if (ladder[nx][ny] == 1) {
                            // 갔던 곳을 다시 가지 않도록 0으로 변경
                            ladder[nx][ny] = 0;
                            // x, y를 이동 위치로 갱신
                            x = nx;
                            y = ny;
                        }
                    }
                }
            }
            // #T와 출발점의 y 반환
            System.out.println("#" + t + " " + y);
        }
    }
}