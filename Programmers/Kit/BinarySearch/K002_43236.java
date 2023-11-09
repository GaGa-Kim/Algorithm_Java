package Programmers.Kit.BinarySearch;

import java.util.Arrays;

/**
 * 43246) 징검다리
 */
public class K002_43236 {
    // distance(출발지점부터 도착지점까지의 거리)
    // rocks(바위들이 있는 위치를 담은 배열)
    // n(제거할 바위의 수)
    public int solution(int distance, int[] rocks, int n) {
        // rocks 정렬
        Arrays.sort(rocks);
        // start(이진 탐색 시작 인덱스) = 1
        int start = 1;
        // end(이진 탐색 종료 인덱스) = distance
        int end = distance;
        // answer(바위를 n개를 제거한 뒤 각 지점 사이의 거리의 최솟값 중에서 가장 큰 값)
        int answer = 0;
        while (start <= end) {
            // mid(중간 인덱스)
            int mid = (start + end) / 2;
            // count(제거한 바위 갯수)
            int count = 0;
            // previous(이전 바위 위치)
            int previous = 0;
            // 각 지점 사이의 거리 비교
            for (int i = 0; i < rocks.length; i++) {
                // 각 지점 사이의 거리가 mid보다 작을 경우 바위를 제거
                if (rocks[i] - previous < mid)
                    // 현재 위치 바위 제거
                    count++;
                else
                    // 이전 바위 위치 갱신
                    previous = rocks[i];
            }
            if (distance - previous < mid)
                // 현재 위치 바위 제거
                count++;
            // 중앙값 크기로 바위 제거 횟수보다 적게 모든 바위를 건널 수 있다면
            if (count <= n) {
                // answer = mid 갱신
                answer = mid;
                // 시작 인덱스 = 중앙 인덱스 + 1
                start = mid + 1;
            }
            // 중앙값 크기로 바위 제거 횟수보다 적게 모든 바위를 건널 수 없다면
            else {
                // 종료 인덱스 = 중앙 인덱스 - 1
                end = mid - 1;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_43236 solution = new K002_43236();

        int distance = 25;
        int[] rocks = { 2, 14, 11, 21, 17 };
        int n = 2;

        int result = solution.solution(distance, rocks, n);

        System.out.println(result);
    }
}
