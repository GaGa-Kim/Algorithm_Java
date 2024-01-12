package Programmers.LV_2;

import java.util.Stack;

/**
 * 12973) 짝지어_제거하기
 */
public class L023_12973 {
    // s(문자열)
    public int solution(String s) {
        // answer(짝지어 제거하기를 성공적으로 수행할 수 있는지 여부)
        int answer = 0;
        // stack(문자를 넣을 스택)
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // c(s의 i번째 문자)
            char c = s.charAt(i);
            // stack이 비어있다면
            if (stack.isEmpty())
                // stack에 c 저장
                stack.push(c);
            // stack의 top이 c와 동일하다면
            else if (stack.peek() == c)
                stack.pop();
            // stack의 top이 c와 동일하지 않다면
            else
                // stack에 c 저장
                stack.push(c);
        }
        // stack이 비어있다면
        if (stack.isEmpty())
            // 성공이므로 1로 변경
            answer = 1;
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L023_12973 solution = new L023_12973();

        String s = "baabaa";

        int result = solution.solution(s);

        System.out.println(result);
    }
}
