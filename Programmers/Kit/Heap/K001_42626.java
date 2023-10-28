package Programmers.Kit.Heap;

import java.util.PriorityQueue;

/**
 * 42626) 더_맵게
 */
public class K001_42626 {
    // scoville(음식의 스코빌 지수)
    // K(원하는 스코빌 지수)
    public int solution(int[] scoville, int K) {
        // minHeap(음식의 스코빌 지수를 담은 우선순위 큐)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // minHeap에 scoville 저장
        for (int food : scoville) {
            minHeap.add(food);
        }
        // answer(최소 횟수)
        int answer = 0;
        while (minHeap.peek() < K) {
            // 모든 음식을 스코빌 지수 K 이상으로 만들 수 없고 음식이 하나일 때
            if (minHeap.size() == 1)
                // -1 리턴
                return -1;
            // 새로운 음식을 만들고 저장
            minHeap.add(minHeap.poll() + minHeap.poll() * 2);
            // 최소 횟수 증가
            answer++;
        }
        // answer 리턴
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K001_42626 solution = new K001_42626();

        int[] scoville = { 1, 2, 3, 9, 10, 12 };
        int K = 7;

        int result = solution.solution(scoville, K);

        System.out.println(result);
    }
}
