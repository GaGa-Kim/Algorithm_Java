package Programmers.Kit.Greedy;

import java.util.Stack;

/**
 * 42883) 큰_수_만들기
 */
public class K003_42883 {
    // number(문자열 형식의 숫자)
    // k(제거할 수의 개수)
    public String solution(String number, int k) {
        // answer = 가장 큰 수 만들기 함수(number, k)
        String answer = makeBNumber(number, k);
        // answer 반환
        return answer;
    }

    // 가장 큰 수 만들기 함수
    private String makeBNumber(String number, int k) {
        // stack(숫자 문자열을 담는 스택)
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < number.length(); i++) {
            // 스택이 비어있지 않으면서 현재 숫자가 스택의 앞 숫자보다 크다면, k가 0이 되지 않을 때까지 제거
            while (!stack.isEmpty() && stack.peek() < number.charAt(i) && k-- > 0) {
                stack.pop();
            }
            // 현재 숫자 삽입
            stack.push(number.charAt(i));
        }
        // k개 만큼 제거하지 못했다면 끝에서부터 남은 k개 만큼 제거
        if (k != 0) {
            for (int i = 0; i < k; i++) {
                stack.pop();
            }
        }
        // 스택에 있는 숫자 문자열 반환
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K003_42883 solution = new K003_42883();

        String number = "4177252841";
        int k = 4;

        String result = solution.solution(number, k);

        System.out.println(result);
    }
}
