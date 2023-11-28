package Programmers.Kit.Queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 42583) 다리를_지나는_트럭
 */
public class K005_42583 {
    // bridge_length(다리에 올라갈 수 있는 트럭 수)
    // weight(다리가 견딜 수 있는 무게)
    // truck_weights(트럭 별 무게)
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // answer(모든 트럭이 다리를 건너는 최소 시간)
        int answer = 0;
        // sum(다리 위 트럭의 무게 합)
        int sum = 0;
        // queue(트럭이 지나가는 다리)
        Queue<Integer> queue = new LinkedList<>();
        for (int truck : truck_weights) {
            while (true) {
                // queue가 비어있다면
                if (queue.isEmpty()) {
                    // 트럭 하나를 다리에 올린 후, 다음 트럭으로
                    queue.add(truck);
                    sum += truck;
                    answer++;
                    break;
                }
                // queue가 꽉 찼다면
                else if (queue.size() == bridge_length) {
                    // 다리의 가장 앞의 트럭을 다리에서 뺌
                    sum -= queue.poll();
                } else {
                    // 다리에 트럭 하나를 더 올릴 수 있다면
                    if (sum + truck <= weight) {
                        // 트럭 하나를 다리에 올린 후, 다음 트럭으로
                        queue.add(truck);
                        sum += truck;
                        answer++;
                        break;
                    } else {
                        // 올릴 수 있는 트럭이 없다면 0을 넣어, 앞의 트럭이 앞으로 이동하도록 함
                        queue.add(0);
                        answer++;
                    }
                }
            }
        }
        // answer에 마지막 트럭이 건너는 시간인 bridge_length를 합하여 반환
        return answer + bridge_length;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K005_42583 solution = new K005_42583();

        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = { 7, 4, 5, 6 };
        int result = solution.solution(bridge_length, weight, truck_weights);

        System.out.println(result);
    }
}
