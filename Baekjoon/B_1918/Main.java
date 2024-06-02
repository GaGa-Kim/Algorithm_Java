package Baekjoon.B_1918;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 문제 분석하기
 * : 연산자 우선 순위에 맞춰 후위 표기식으로 변환하도록 조건식을 세우도록 함 
 */

/* 
 * 손으로 풀어보기
 * 1. 연산자 우선 순위에 맞춰 후위 표기식으로 변환하도록 조건식으로 세우도록 함
 *    피연산자일 경우, 바뀐 식에 추가하도록 함
 *    연산자일 경우, '('라면 스택에 추가 
 *                '*, /'라면 자신과 우선순위가 같으며 앞에 있는 연산자를 꺼내 바뀐 식에 추가하도록 함
 *                '+, -'라면 자신과 우선순위가 크거나 같으며 괄호 사이에 있는 연산자를 꺼내 바뀐 식에 추가하도록 함
 *                ')'라면 괄호 사이에 있는 연산자를 꺼내 바뀐 식에 추가하도록 함
 *    이후 스택에 남은 모든 연산자를 꺼내 바뀐 식에 추가하도록 함
 * 2. 후위 표기식으로 바뀐 식을 출력
 */

/*
 * 1918) 후위_표기식  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String s;
    static Stack<Character> stack = new Stack<Character>(); // stack(연산자들을 담을 스택)
    static StringBuilder answer = new StringBuilder(); // answer(후위 표기식으로 바뀐 식)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        s = br.readLine();
    }

    /*
     * 후위 표기식으로 변환하기
     */
    static void postfix() {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isAlphabetic(c)) { // 피연산자일 경우
                answer.append(c);
            }

            else { // 연산자일 경우
                if (c == '(') {
                    stack.add(c);
                } else if (c == '*' || c == '/') { // 현재 연산자와 우선순위가 같은 연산자인 *, /가 스택의 앞에 있다면 꺼낸 후, 현재 연산자를 스택에 추가함
                    while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        answer.append(stack.pop());
                    }
                    stack.add(c);
                } else if (c == '+' || c == '-') { // 현재 연산자의 우선순위보다 높은 연산자인 *, / 또는 우선순위가 같은 +, -가 스택의 앞에 있다면 꺼낸 후, 현재 연산자를 스택에 추가함
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        answer.append(stack.pop()); 
                    }
                    stack.add(c); 
                } else if (c == ')') { // 괄호 사이에 있는 연산자를 스택에서 모두 꺼낸 후, ( 괄호를 스택에서 제거함
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        answer.append(stack.pop());
                    }
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) { // 스택에 남은 연산자들을 모두 꺼내도록 함
            answer.append(stack.pop());
        }
    }

    /*
     * 후위 표기식으로 바뀐 식 출력하기
     */
    static void print() {
        System.out.println(answer.toString());
    }

    public static void main(String[] args) throws IOException {
        init();
        postfix();
        print();
    }
}