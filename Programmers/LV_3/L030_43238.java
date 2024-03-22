package Programmers.LV_3;

import java.util.Arrays;

/**
 * 43238) 입국심사
 */
public class L030_43238 {
    // n(입국 인원수)
    // times(심사관마다 걸리는 시간)
    public long solution(int n, int[] times) {
        // 범위가 100000000이므로 int형 대신 long형 사용
        // answer(모든 사람이 심사를 받는데 걸리는 시간의 최솟값)
        long answer = 0;
        // times 정렬
        Arrays.sort(times);
        // start(이진 탐색 시작 인덱스) = 0
        long start = 0;
        // end(이진 탐색 종료 인덱스) = 가장 길게 걸리는 심사관의 시간 * n명
        long end = (long) times[times.length - 1] * n;
        // 이진 탐색 수행
        while (start <= end) {
            // mid(중간 인덱스)
            long mid = (start + end) / 2;
            // count(입국 가능한 인원수)
            long count = 0;
            // 심사관마다 입국 처리
            for (int i = 0; i < times.length; i++) {
                count += mid / times[i];
            }
            // 중간 인덱스 값으로 모든 인원 입국 불가능하다면
            if (count < n) {
                // 시작 인덱스 = 중앙 인덱스 + 1 (시간 추가)
                start = mid + 1;
            }
            // 중간 인덱스 값으로 모든 인원 입국 가능하다면
            else {
                // 종료 인덱스 = 중앙 인덱스 - 1 (시간 감소)
                end = mid - 1;
                // answer = mid 갱신
                answer = mid;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L030_43238 solution = new L030_43238();

        int n = 6;
        int[] times = { 7, 10 };

        long result = solution.solution(n, times);

        System.out.println(result);
    }
}
