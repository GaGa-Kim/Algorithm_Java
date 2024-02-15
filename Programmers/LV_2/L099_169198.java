package Programmers.LV_2;

import java.util.Arrays;

/**
 * 169198) 당구_연습
 */
public class L099_169198 {
    // m, n(당구대의 가로, 세로 길이)
    // startX, startY(머쓱이가 쳐야 하는 공이 놓인 위치 좌표)
    // balls(매 회마다 목표로 해야하는 공들의 위치 좌표)
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        // answer(각 회마다 머쓱이가 친 공이 굴러간 거리의 최솟값의 제곱)
        int[] answer = new int[balls.length];
        for (int i = 0; i < balls.length; i++) {
            // targetX, targetY(목표로 해야하는 공들의 위치)
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            // min(각 회의 최소 거리)
            int min = Integer.MAX_VALUE;
            // now(방향에 따른 거리)
            int now = Integer.MAX_VALUE;
            // 상방향 대칭 거리 계산 (두 점이 평행한 경우, 벽보다 다른 공이 먼저 맞게 될 경우는 제외)
            if (!(startX == targetX && startY <= targetY)) {
                now = calculateDistance(startX, startY, targetX, n + (n - targetY));
                min = Math.min(min, now);
            }
            // 하방향 대칭 거리 계산 (두 점이 평행한 경우, 벽보다 다른 공이 먼저 맞게 될 경우는 제외)
            if (!(startX == targetX && startY >= targetY)) {
                now = calculateDistance(startX, startY, targetX, targetY * (-1));
                min = Math.min(min, now);
            }
            // 좌방향 대칭 거리 계산 (두 점이 평행한 경우, 벽보다 다른 공이 먼저 맞게 될 경우는 제외)
            if (!(startY == targetY && startX >= targetX)) {
                now = calculateDistance(startX, startY, targetX * (-1), targetY);
                min = Math.min(min, now);
            }
            // 우방향 대칭 거리 계산 (두 점이 평행한 경우, 벽보다 다른 공이 먼저 맞게 될 경우는 제외)
            if (!(startY == targetY && startX <= targetX)) {
                now = calculateDistance(startX, startY, m + (m - targetX), targetY);
                min = Math.min(min, now);
            }
            // min으로 갱신
            answer[i] = min;
        }
        // answer 반환
        return answer;
    }

    // 거리 계산하기 함수
    private int calculateDistance(int startX, int startY, int targetX, int targetY) {
        return (int) (Math.pow(startX - targetX, 2) + Math.pow(startY - targetY, 2));
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L099_169198 solution = new L099_169198();

        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = { { 7, 7 }, { 2, 7 }, { 7, 3 } };

        int[] result = solution.solution(m, n, startX, startY, balls);

        System.out.println(Arrays.toString(result));
    }
}
