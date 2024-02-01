package Programmers.LV_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 118667) 두_큐_합_같게_만들기
 */
public class L077_118667 {
    // queue1, queue2(길이가 같은 두 개의 큐)
    public int solution(int[] queue1, int[] queue2) {
        // answer(각 큐의 원소 합을 같게 만들기 위해 필요한 작업의 최소 횟수)
        int answer = 0;
        // sum1, sum2(queue1의 합계, queue2의 합계) 구하기
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        // q1, q2(queue1, queue2를 담을 큐)
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        // q1, q2에 queue1, queue2 담기
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        // sum1이 sum2와 다를 동안
        while (sum1 != sum2) {
            // answer가 (queue1의 길이 + queue2의 길이) * 2보다 크다면
            if (answer > (queue1.length + queue2.length) * 2)
                // 두 큐를 같게 만들 수 없으므로 return -1
                return -1;
            // sum1이 sum2보다 작다면
            if (sum1 < sum2) {
                // q2.poll() 값을 value(이동할 값)에 저장
                int value = q2.poll();
                // q1에 value 저장
                q1.add(value);
                // sum1에 value를 더해주고, sum2에 value를 빼줌
                sum1 += value;
                sum2 -= value;
            }
            // sum1이 sum2보다 크다면
            else if (sum1 > sum2) {
                // q1.poll() 값을 value(이동할 값)에 저장
                int value = q1.poll();
                // q2에 value 저장
                q2.add(value);
                // sum2에 value를 더해주고, sum1에 value를 빼줌
                sum2 += value;
                sum1 -= value;
            }
            // answer 증가
            answer++;
        }
        // sum1과 sum2가 같으므로 answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L077_118667 solution = new L077_118667();

        int[] queue1 = { 3, 2, 7, 2 };
        int[] queue2 = { 4, 6, 5, 1 };

        int result = solution.solution(queue1, queue2);

        System.out.println(result);
    }
}
