package Programmers.LV_2;

import java.util.Stack;

/**
 * 76502) 괄호_회전하기
 */
public class L064_76502 {
    // s(대괄호, 중괄호, 그리고 소괄호로 이루어진 문자열)
    public int solution(String s) {
        // answer(s가 올바른 괄호 문자열이 되게 하는 x의 개수)
        int answer = 0;
        // sb(s를 저장하는 StringBuilder)
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++) {
            // isRight(sb.toString()이 맞다면
            if (isRight(sb.toString()))
                // answer 증가
                answer++;
            sb.append(sb.charAt(0)).deleteCharAt(0);
        }
        // answer 반환
        return answer;
    }

    // isRight
    private boolean isRight(String bracketStr) {
        // stack(괄호들을 저장할 스택)
        Stack<Character> stack = new Stack<>();
        for (char bracket : bracketStr.toCharArray()) {
            // bracket이 여는 괄호라면
            if (isOpenBracket(bracket)) {
                // stack에 bracket 저장
                stack.push(bracket);
            }
            // bracket이 닫는 괄호라면
            else {
                // stack이 비어있거나 stack의 상단 값이 bracket과 다르다면
                if (stack.isEmpty() || !isMatching(stack.pop(), bracket)) {
                    // false 반환
                    return false;
                }
            }
        }
        // 모든 괄호를 검사한 후 stack이 비어있다면 true 반환, 비어있지 않다면 false 반환
        return stack.isEmpty();
    }

    // bracket이 여는 괄호인지
    private static boolean isOpenBracket(char bracket) {
        return bracket == '(' || bracket == '[' || bracket == '{';
    }

    // stack의 상단 값이 bracket과 올바른 짝 괄호인지
    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L064_76502 solution = new L064_76502();

        String s = "[](){}";

        int result = solution.solution(s);

        System.out.println(result);
    }
}
