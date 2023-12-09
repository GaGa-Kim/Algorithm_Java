package Programmers.LV_1;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 138477) 명예의_전당_(1)
 */
public class L013_138477 {
    // k(명예의 전당 목록의 점수의 개수)
    // score(1일부터 마지막 날까지 출연한 가수들의 점수)
    public int[] solution(int k, int[] score) {
        // answer(매일 발표된 명예의 전당의 최하위 점수들)
        int[] answer = new int[score.length];
        // queue(명예의 전당의 점수를 저장하는 최솟값 우선순위 큐)
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < score.length; i++) {
            // queue에 score[i] 집어넣기
            queue.add(score[i]);
            // 큐의 크기가 k보다 크다면
            if (queue.size() > k) {
                // 가장 낮은 점수(최솟값)를 삭제
                queue.poll();
            }
            // answer[i] = queue의 가장 낮은 점수(최솟값)를 저장
            answer[i] = queue.peek();
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L013_138477 solution = new L013_138477();

        int k = 4;
        int[] score = { 0, 300, 40, 300, 20, 70, 150, 50, 500, 1000 };

        int[] result = solution.solution(k, score);

        System.out.println(Arrays.toString(result));
    }
}
