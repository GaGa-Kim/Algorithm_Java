package Programmers.Kit.Heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 42627) 디스크_컨트롤러
 */
public class K002_42627 {
    // jobs(작업이 요청되는 시점, 작업의 소요시간을 담은 2차원 배열)
    public int solution(int[][] jobs) {
        // jobs을 작업이 요청되는 시점으로 오름차순 정렬
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] <= o2[0]) {
                    return -1;
                }
                return 1;
            }
        });
        // minHeap(작업을 담은 우선순위 큐)
        // minHeap을 작업의 소요시간으로 오름차순 정렬하도록 커스텀
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[1] <= o2[1]) {
                    return -1;
                }
                return 1;
            }
        });
        // end(작업이 끝나는 시간 = 현재까지의 소요 시간)
        int end = 0;
        // total(전체 소요 시간)
        int total = 0;
        // count(현재까지 완료한 작업 갯수)
        int count = 0;
        // index(jobs 배열의 인덱스)
        int index = 0;
        while (count < jobs.length) {
            // 모든 작업이 완료되기 전이며 jobs의 요청 시점이 end보다 작을 때까지
            while (index < jobs.length && jobs[index][0] <= end) {
                // 현재 작업이 끝난 후에 실행할 수 있는 작업이므로 minHeap에 저장
                minHeap.add(jobs[index++]);
            }
            // minHeap이 비어있다면
            if (minHeap.isEmpty()) {
                // 바로 실행할 수 있는 작업이 없으므로 요청의 처음으로 end를 갱신
                end = jobs[index][0];
            }
            // minHeap이 비어있지만 않다면 바로 실행할 작업이 있다는 것이므로
            else {
                // 우선순위 큐에서 이전 작업이 끝난 후 실행할 수 있는 작업 중에서 작업의 소요시간이 짧은 작업 꺼내기
                int[] now = minHeap.poll();
                // total += 현재 작업의 소요 시간 + end - 작업이 요청되는 시간
                total += now[1] + end - now[0];
                // 현재 작업의 소요 시간을 더하여 end 갱신
                end += now[1];
                // 완료한 작업 갯수 증가
                count++;
            }
        }
        // 평균 계산 반환
        return total / jobs.length;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_42627 solution = new K002_42627();

        int[][] jobs = { { 0, 3 }, { 1, 9 }, { 2, 6 } };

        int result = solution.solution(jobs);

        System.out.println(result);
    }
}
