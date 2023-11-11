package Programmers.Kit.Heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 42628) 이중우선순위큐
 */
public class K003_42628 {
    // operations(이중 우선순위 큐가 할 연산)
    public int[] solution(String[] operations) {
        // minHeap(최솟값 우선순위 큐)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // maxHeap(최댓값 우선순위 큐)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            String x = st.nextToken();
            int y = Integer.parseInt(st.nextToken());
            // "I"일 경우
            if (x.equals("I")) {
                // minHeap, maxHeap에 숫자 저장
                minHeap.add(y);
                maxHeap.add(y);
            }
            // "D"일 경우
            if (x.equals("D")) {
                // 우선순위 큐가 비어 있다면
                if (minHeap.isEmpty()) {
                    // 해당 연산 무시
                    continue;
                }
                // 1일 경우
                if (y == 1) {
                    // maxHeap에서 최댓값 삭제 후 그 값을 minHeap에서도 삭제
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                }
                // -1일 경우
                if (y == -1) {
                    // minHeap에서 최솟값 삭제 후 그 값을 maxHeap에서도 삭제
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                }
            }
        }
        int[] answer = new int[2];
        // 우선순위 큐가 비어 있지 않다면
        if (!minHeap.isEmpty()) {
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K003_42628 solution = new K003_42628();

        String[] operations = { "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1" };
        int[] result = solution.solution(operations);

        System.out.println(Arrays.toString(result));
    }
}
