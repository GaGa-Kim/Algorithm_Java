package Programmers.LV_2;

import java.util.Arrays;
import java.util.Stack;

/**
 * 42584) 주식가격
 */
public class L038_42584 {
    // prices(주식가격이 담긴 배열)
    public int[] solution(int[] prices) {
        // answer(가격이 떨어지지 않은 기간)
        int[] answer = new int[prices.length];
        // stack(주식을 담을 스택)
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            // 스택이 비어있지 않으면서 주식이 떨어지는 동안
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                // answer에 떨어지지 않은 기간 넣기
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            // 주식이 동일하거나 증가하는 경우 stack에 인덱스를 넣기
            stack.push(i);
        }
        // 모든 주식을 돈 후
        // 스택이 빌 때까지
        while (!stack.isEmpty()) {
            // 끝까지 주식이 떨어지지 않은 경우
            // answer에 떨어지지 않은 기간 넣기
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L038_42584 solution = new L038_42584();

        int[] prices = { 1, 2, 3, 2, 3 };
        int[] result = solution.solution(prices);

        System.out.println(Arrays.toString(result));
    }
}