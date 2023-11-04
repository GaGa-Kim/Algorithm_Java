package Programmers.Kit.Stack;

import java.util.Stack;

/**
 * 12909) 올바른_괄호
 */
public class K002_12909 {
    // s('(' 또는 ')'로만 이루어진 문자열)
    boolean solution(String s) {
        boolean answer = true;
        // stack(괄호를 담을 stack)
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // '('라면
            if (s.charAt(i) == '(') {
                // stack에 저장
                stack.add("(");
            }
            // ')'라면
            if (s.charAt(i) == ')') {
                // stack의 크기가 0이라면
                if (stack.size() == 0) {
                    return false;
                }
                // stack에서 짝이 맞는 '(' 제거
                stack.pop();
            }
        }
        // stack의 크기가 0이 아니라면
        if (stack.size() != 0) {
            answer = false;
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K002_12909 solution = new K002_12909();

        String s = "()()";
        boolean result = solution.solution(s);

        System.out.println(result);
    }
}
