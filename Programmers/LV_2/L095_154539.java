package Programmers.LV_2;

import java.util.Arrays;
import java.util.Stack;

/**
 * 154539) 뒤에_있는_큰_수_찾기
 */
public class L095_154539 {
    // numbers(정수 배열)
    public int[] solution(int[] numbers) {
        // answer(모든 원소에 대한 뒷 큰수들을 차례로 담은 배열)
        int[] answer = new int[numbers.length];
        // stack(정수를 담을 스택)
        Stack<Integer> stack = new Stack<>();
        // stack에 첫 번째 정수의 인덱스인 0 저장
        stack.push(0);
        for (int i = 1; i < numbers.length; i++) {
            // 스택이 비어있지 않으면서 현재 스택이 바라보고 있는 값보다 numbers의 값이 크다면
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i])
                // answer[stack.pop]에 numbers[i] 저장
                answer[stack.pop()] = numbers[i];
            // stack에 정수의 인덱스인 i 저장
            stack.push(i);
        }
        // stack이 비어 있지 않는 동안
        while (!stack.isEmpty())
            // answer[stack.pop]에 -1 저장
            answer[stack.pop()] = -1;
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L095_154539 solution = new L095_154539();

        int[] numbers = { 2, 3, 3, 5 };

        int[] result = solution.solution(numbers);

        System.out.println(Arrays.toString(result));
    }
}
