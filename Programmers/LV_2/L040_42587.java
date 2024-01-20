package Programmers.LV_2;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 42587) 프로세스
 */
public class L040_42587 {
    // priorities(프로세스의 중요도가 순서대로 담긴 배열)
    // location(알고싶은 프로세스의 위치)
    public int solution(int[] priorities, int location) {
        // priorityQueue(프로세스 중요도를 담을 최댓값 우선순위 큐)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        // 중요도 담기
        for (int i = 0; i < priorities.length; i++) {
            priorityQueue.offer(priorities[i]);
        }
        // answer(실행 순서)
        int answer = 0;
        while (!priorityQueue.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                // priorities[i]가 가장 우선순위가 크다면
                if (priorityQueue.peek() == priorities[i]) {
                    // 프로세스 꺼내기
                    priorityQueue.poll();
                    // 실행되므로 answer 증가
                    answer++;
                    // 실행되는 프로세스가 location과 같다면
                    if (i == location) {
                        // 실행 순서 반환
                        return answer;
                    }
                }
            }
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L040_42587 solution = new L040_42587();

        int[] priorities = { 2, 1, 3, 2 };
        int location = 2;
        int result = solution.solution(priorities, location);

        System.out.println(result);
    }
}