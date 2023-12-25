package Programmers.LV_1;

import java.util.Arrays;
import java.util.Stack;

/**
 * 12906) 같은_숫자는_싫어
 */
public class L004_12906 {
    // arr(숫자 0부터 9까지로 이루어져 있는 원소들)
    public int[] solution(int[] arr) {
        // stack(원소를 담을 stack)
        Stack<Integer> stack = new Stack<>();
        for (int element : arr) {
            // stack에 원소가 존재하지 않을 경우
            if (stack.isEmpty() || stack.peek() != element)
                // stack에 원소 추가
                stack.add(element);
        }
        // stack 리턴
        int[] answer = new int[stack.size()];
        int index = 0;
        for (int element : stack) {
            answer[index++] = element;
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L004_12906 solution = new L004_12906();

        int[] arr = { 1, 1, 3, 3, 0, 1, 1 };

        int[] result = solution.solution(arr);

        System.out.println(Arrays.toString(result));
    }
}