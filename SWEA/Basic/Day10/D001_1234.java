package SWEA.Basic.Day10;

import java.util.Scanner;
import java.util.Stack;

/**
 * 1234) 비밀번호
 */
public class D001_1234 {
    static int N;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // N(문자열이 포함하는 문자의 총 수)
            N = sc.nextInt();
            // s(번호 문자열)
            String s = sc.next();
            // answer = 비밀번호 만들기(s)
            String answer = makePassword(s);
            // #T와 answer 반환
            System.out.println("#" + test_case + " " + answer);
        }
    }

    // 비밀번호 만들기
    private static String makePassword(String s) {
        // stack(비밀번호를 저장할 스택)
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            // 스택이 비어 있지 않으면서 스택의 top이 현재 비밀번호와 같다면
            if (!stack.isEmpty() && stack.peek() == s.charAt(i)) {
                // 소거
                stack.pop();
            }
            // 스택에 현재 비밀번호 추가
            else {
                stack.push(s.charAt(i));
            }
        }
        // 스택에 남아 있는 비밀번호 꺼내서 저장
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        return sb.toString();
    }
}
