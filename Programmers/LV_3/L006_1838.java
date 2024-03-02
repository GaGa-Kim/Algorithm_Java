package Programmers.LV_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 1838) 몸짱_트레이너_라이언의_고민
 */
public class L006_1838 {
    // n(정사각형 한 변의 길이)
    // m(손님수)
    // timetable(각 손님의 예약된 입실/퇴실 시간)
    public int solution(int n, int m, int[][] timetable) {
        // preSum(600 ~ 1321분 간의 손님의 수를 담은 구간 합 배열)
        int[] preSum = new int[722];
        for (int i = 0; i < m; i++) {
            // 입실 시간에 손님 증가
            preSum[timetable[i][0] - 600]++;
            // 퇴실 시간에 손님 감소
            preSum[timetable[i][1] - 600 + 1]--;
        }
        // maxClient(최대로 겹치는 손님의 수)
        int maxClient = 0;
        for (int i = 1; i <= 720; i++) {
            // 구간 합 배열 저장
            preSum[i] += preSum[i - 1];
            // maxClient 갱신
            maxClient = Math.max(maxClient, preSum[i]);
        }
        // maxClient가 1보다 작거나 같다면
        if (maxClient <= 1)
            // 손님 간에 이용 시간이 한 번도 겹치지 않으므로 0을 반환
            return 0;
        // 한 변의 길이가 n인 맵에 대해 2개를 배치할 때 최대 거리인 2 * (n - 1)부터 크기를 점점 줄여가며 배치할 수 있는지 판별
        for (int distance = 2 * (n - 1); distance >= 1; distance--) {
            // i, j 위치에 첫 번째 손님 배치
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // people(배치할 수 있는 손님의 락커 리스트)
                    List<int[]> people = new ArrayList<>();
                    people.add(new int[] { i, j });
                    // y, x 위치에 다음 손님들 배치
                    for (int y = i; y < n; y++) {
                        for (int x = 0; x < n; x++) {
                            // 만약 y 좌표가 i와 같고, x 좌표가 j 이하라면
                            if (y == i && x <= j)
                                // 이미 첫 번째 손님이 위치한 곳이므로 넘어감
                                continue;
                            // 배치될 수 있다면
                            if (canPlace(new int[] { y, x }, distance, people))
                                // people에 해당 위치 손님의 락커 추가
                                people.add(new int[] { y, x });
                            // 배치할 수 있는 락커 수가 maxClient와 일치한다면
                            if (people.size() == maxClient)
                                // 이를 최대 거리로 반환
                                return distance;
                        }
                    }
                }
            }
        }
        return 0;
    }

    // 배치될 수 있는지
    private boolean canPlace(int[] coord, int maxDistance, List<int[]> people) {
        for (int[] person : people) {
            // 다른 손님의 락커와의 거리가 maxDistance보다 작다면
            if (getDistance(person, coord) < maxDistance) {
                return false;
            }
        }
        return true;
    }

    // 거리 계산
    private int getDistance(int[] person, int[] coord) {
        // 두 좌표의 거리를 절댓값을 이용해 구함
        return Math.abs(person[0] - coord[0]) + Math.abs(person[1] - coord[1]);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L006_1838 solution = new L006_1838();

        int n = 3;
        int m = 2;
        int[][] timetable = { { 1170, 1210 }, { 1200, 1260 } };

        int result = solution.solution(n, m, timetable);

        System.out.println(result);
    }
}
