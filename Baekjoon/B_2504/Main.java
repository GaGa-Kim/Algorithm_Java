package Baekjoon.B_2504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 문제 분석하기
 * : 분배법칙을 사용하여 구현하도록 함
 *   (()[[]])([])는 2 * (2 + (3 * 3)) + 2 * 3이며
 *   '(' temp = 1 * 2 = 2, answer = 0
 *   '(' temp = 2 * 2 = 4, answer = 0
 *   ')' temp = 4 / 2 = 2, answer = 0 + 4 = 4
 *   '[' temp = 2 * 3 = 6, answer = 4
 *   '[' temp = 6 * 3 = 18, answer = 4 
 *   ']' answer = 4 + 18 = 22, temp = 18 / 3 = 6
 *   ']' temp = 6 / 3 = 2, answer = 22
 *   ')' temp = 2 / 2 = 1, answer = 22
 *   '(' temp = 1 * 2 = 2, answer = 22
 *   '[' temp = 2 * 3 = 6, answer = 22
 *   ']' answer = 22 + 6 = 28, temp = 6 / 3 = 2
 *   ')' temp = 2 / 2 = 1, answer = 28
 *   괄호가 닫힐 때마다 현재까지의 곱을 곱해주도록 함
 *   그리고 괄호가 닫힌 후에 저장해두었던 곱셈 값들을 정리해주도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. '('을 만나면 '('을 스택에 넣고 temp에 2를 곱함
 * 2. '['을 만나면 '['을 스택에 넣고 temp에 3을 곱함
 * 3. ')'을 만나면
 *     이때 스택이 비어있거나 스택의 최상단이 '('이 아니면 올바른 괄호가 아니므로 0을 출력
 *     바로 이전 값이 '('이면 temp를 answer에 저장하고, 괄호가 닫힐 때마다 저장해둔 값들을 /2로 정리
 * 4. ']'을 만나면
 *     이때 스택이 비어있거나 스택의 최상단이 '['이 아니면 올바른 괄호가 아니므로 0을 출력
 *     바로 이전 값이 '['이면 temp를 answer에 저장하고, 괄호가 닫힐 때마다 저장해둔 값들을 /3으로 정리
 * 5. 결과값을 출력하도록 함
 */

/*
 * 2504) 괄호의_값
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[] brackets; // brackets(괄호값들)
    static Stack<Character> stack; // stack(괄호열의 값을 담을 스택)
    static int answer = 0; // answer(괄호값에 따른 결과값)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        brackets = br.readLine().toCharArray();
    }

    /*
     * 괄호값 계산하기
     */
    static void calculate() {
        stack = new Stack<Character>();

        int temp = 1;
        for (int i = 0; i < brackets.length; i++) {
            char c = brackets[i];

            if (c == '(') {
                stack.push(c);
                temp *= 2;
            } else if (c == '[') {
                stack.push(c);
                temp *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') { // 입력이 올바르지 못한 괄호열이면 0을 출력
                    answer = 0;
                    break;
                } else if (brackets[i - 1] == '(') {
                    answer += temp;
                }
                stack.pop();
                temp /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') { // 입력이 올바르지 못한 괄호열이면 0을 출력
                    answer = 0;
                    break;
                } else if (brackets[i - 1] == '[') {
                    answer += temp;
                }
                stack.pop();
                temp /= 3;
            }
        }
    }

    /*
     * 명령어들의 출력 결과 출력하기
     */
    static void print() {
        if (!stack.isEmpty()) { // 입력이 올바르지 못한 괄호열이면 0을 출력
            answer = 0;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        calculate();
        print();
    }
}