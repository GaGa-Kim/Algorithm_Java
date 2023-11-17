package SWEA.Basic.Day04;

import java.util.Scanner;
import java.util.Stack;

/**
 * 1218) 괄호_짝짓기
 */
public class D002_1218 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // n(테스트케이스의 길이)
            int n = sc.nextInt();
            // s(문자열)
            String s = sc.next();
            // answer(문자열이 유효한지 유무)
            int answer = 0;
            // stack(괄호를 담을 stack)
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < n; i++) {
                // ch(문자열[i])
                char ch = s.charAt(i);
                // ch가 ')'이면서 stack.peek가 '('라면
                if (ch == ')' && stack.peek() == '(') {
                    stack.pop();
                }
                // ch가 ']'이면서 stack.peek가 '['라면
                else if (ch == ']' && stack.peek() == '[') {
                    stack.pop();
                }
                // ch가 '}'이면서 stack.peek가 '{'라면
                else if (ch == '}' && stack.peek() == '{') {
                    stack.pop();
                }
                // ch가 '>'이면서 stack.peek가 '<'라면
                else if (ch == '>' && stack.peek() == '<') {
                    stack.pop();
                }
                // ch가 '(', '[', '{', '<'라면
                else {
                    stack.push(ch);
                }
            }
            // stack의 크기가 0이라면
            if (stack.size() == 0) {
                answer = 1;
            }
            // #T와 answer 반환
            System.out.println("#" + test_case + " " + answer);
        }
    }
}