package Programmers.Kit.Queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 12906) 같은_숫자는_싫어
 */
public class K001_12906 {
    // arr(숫자 0부터 9까지로 이루어져 있는 원소들)
    public int[] solution(int[] arr) {
        // queue(원소를 담을 queue - Deque 자료구조를 이용하여 구현)
        Deque<Integer> queue = new LinkedList<>();
        for (int element : arr) {
            // queue에 원소가 존재하지 않을 경우
            if (queue.isEmpty() || queue.getLast() != element)
                // queue에 원소 추가
                queue.add(element);
        }
        // queue 리턴
        int[] answer = new int[queue.size()];
        int index = 0;
        for (int element : queue) {
            answer[index++] = element;
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K001_12906 solution = new K001_12906();

        int[] arr = { 1, 1, 3, 3, 0, 1, 1 };

        int[] result = solution.solution(arr);

        System.out.println(Arrays.toString(result));
    }
}
