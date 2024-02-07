package Programmers.LV_2;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 142085) 디펜스_게임
 */
public class L087_142085 {
    // n(준호가 처음 가지고 있는 병사의 수)
    // k(사용 가능한 무적권의 횟수)
    // enemy(매 라운드마다 공격해오는 적의 수)
    public int solution(int n, int k, int[] enemy) {
        // answer(준호가 막을 수 있는 라운드)
        int answer = 0;
        // k가 enemy의 길이와 같다면
        if (k == enemy.length)
            // 모두 막을 수 있으므로 enemy의 길이 반환
            return enemy.length;
        // queue(현재까지 진행한 라운드 중 가장 많은 수의 적이 포함된 라운드의 적의 개수를 저장하는 최댓값 우선순위 큐)
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int round = 0; round < enemy.length; round++) {
            // queue에 enemy[round] 저장
            queue.add(enemy[round]);
            // n에서 enemy[round]만큼 병사 감소
            n -= enemy[round];
            // n이 0보다 작다면
            if (n < 0) {
                // k가 0보다 크면서 queue가 비어있지 않다면 무적권을 쓸 수 있으므로
                if (k > 0 && !queue.isEmpty()) {
                    // n에 queue.poll만큼 병사 증가
                    n += queue.poll();
                    // k 감소
                    k--;
                }
                // k가 0보다 작아 무적권을 쓸 수 없다면
                else {
                    break;
                }
            }
            // 다음 라운드로 넘어갈 수 있으므로 answer 증가
            answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L087_142085 solution = new L087_142085();

        int n = 7;
        int k = 3;
        int[] enemy = { 4, 2, 4, 5, 3, 3, 1 };

        int result = solution.solution(n, k, enemy);

        System.out.println(result);
    }
}
