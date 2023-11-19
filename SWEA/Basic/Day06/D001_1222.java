package SWEA.Basic.Day06;

import java.util.Scanner;
import java.util.Stack;

/**
 * 1222) 계산기1
 */
public class D001_1222 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // n(테스트 케이스의 길이)
            int n = sc.nextInt();
            // s = 문자열 저장
            String s = sc.next();
            // result(연산 저장 문자열) = 후위 표기식 변환 함수(s)
            String result = postfix(s);
            // answer(계산 결과) = 연산 계산 함수(result)
            int answer = calculate(result);
            // #T와 answer 반환
            System.out.println("#" + test_case + " " + answer);
        }
    }

    // 후위 표기식 변환 함수
    private static String postfix(String expression) {
        // result(후기표기식 결과)
        String result = "";
        // stack(연산자 저장 스택)
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            // c(i번째 문자)
            char c = expression.charAt(i);
            // 피연산자라면
            if (c - '0' >= 0 && c - '0' <= 9) {
                // 피연산자를 후위표기식 결과에 저장
                result += String.valueOf(c);
            }
            // 연산자라면
            else {
                // stack이 비었다면
                if (stack.isEmpty()) {
                    // 스택에 연산자 저장
                    stack.push(String.valueOf(c));
                }
                // stack이 비어있지 않다면
                else {
                    // 현재 스택에 있는 연산자를 후위표기식 결과에 저장
                    result += stack.pop();
                    // 스택에 연산자 저장
                    stack.push(String.valueOf(c));
                }
            }
        }
        // 스택이 비어있지 않다면
        while (!stack.isEmpty()) {
            // 남은 연산자를 후위표기식 결과에 저장
            result += stack.pop();
        }
        return result;
    }

    // 계산 함수
    private static int calculate(String expression) {
        // result(계산 결과)
        int result = 0;
        // stack(연산 계산 스택)
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            // c(i번째 문자)
            char c = expression.charAt(i);
            // 피연산자라면
            if (c - '0' >= 0 && c - '0' <= 9) {
                // 피연산자를 스택에 저장
                stack.push(c - '0');
            }
            // 연산자라면
            else {
                // 피연산자 두 개를 스택에서 꺼내와 연산
                int num1 = stack.pop();
                int num2 = stack.pop();
                // 연산한 결과 피연산자를 다시 스택에 저장
                stack.push(num1 + num2);
            }
        }
        // 마지막 결과를 스택에서 꺼내 계산 결과로 반환
        result = stack.pop();
        return result;
    }
}
