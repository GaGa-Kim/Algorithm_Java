package Programmers.Kit.Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 42884) 단속카메라
 */
public class K006_42884 {
    // routes(차량의 경로)
    public int solution(int[][] routes) {
        // answer(카메라의 최소 설치 갯수)
        int answer = 0;
        // routes 나간 지점을 기준으로 오름차순 정렬
        // Arrays.sort(routes, (r1, r2) -> Integer.compare(r1[1], r2[1]));
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] route1, int[] route2) {
                return route1[1] - route2[1];
            }
        });
        // camera(현재 카메라 위치)
        int camera = Integer.MIN_VALUE;
        for (int[] route : routes) {
            // 현재 카메라의 위치가 route의 진입 지점보다 작으면
            if (camera < route[0]) {
                // 현재 카메라 위치를 새로운 카메라 위치로 변경
                camera = route[1];
                // 카메라 갯수 증가
                answer++;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K006_42884 solution = new K006_42884();

        int[][] routes = { { -20, -15 }, { -14, -5 }, { -18, -13 }, { -5, -3 } };

        int result = solution.solution(routes);

        System.out.println(result);
    }
}
