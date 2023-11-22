package SWEA.Basic.Day09;

import java.util.Scanner;
import java.util.Stack;

/**
 * 1232) 사칙연산
 */
public class D002_1232 {
    static int N;
    static int[][] tree;
    static String[] value;
    static String expression;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // N(트리가 갖는 정점의 총 수)
            N = sc.nextInt();
            sc.nextLine();
            // tree(트리의 연결 정점 저장)
            tree = new int[N + 1][2];
            // value(트리의 값 저장)
            value = new String[N + 1];
            for (int i = 1; i <= N; i++) {
                String[] str = sc.nextLine().split(" ");
                // 트리의 값 저장
                value[i] = str[1];
                // 4개가 주어진다면 (연결 자식 정점 존재)
                if (str.length == 4) {
                    // tree의 연결 정점 저장
                    tree[i][0] = Integer.parseInt(str[2]);
                    tree[i][1] = Integer.parseInt(str[3]);
                }
                // 4개가 주어지지 않는다면 (연결 자식 정점 없음 - 리프 노드)
                else {
                    // tree의 연결 정점에 -1 저장
                    tree[i][0] = -1;
                    tree[i][1] = -1;
                }
            }
            // expression(후위 표기식 값)
            expression = "";
            // 후위순회(1)
            postOrder(1);
            // 후위 표기식 계산
            int answer = calculate(expression);
            // #T와 answer 반환
            System.out.println("#" + test_case + " " + answer);
        }
    }

    // 후위순회
    private static void postOrder(int now) {
        // 현재 값이 -1이면 리턴(자식 정점이 없으면)
        if (now == -1) {
            return;
        }
        // 왼쪽 자식 정점 탐색하기
        postOrder(tree[now][0]);
        // 오른쪽 자식 정점 탐색하기
        postOrder(tree[now][1]);
        // 현재 정점의 값을 공백과 함께 저장
        expression += value[now] + " ";
    }

    // 후위 표기식 계산
    private static int calculate(String expression) {
        // stack(연산 계산 스택)
        Stack<Double> stack = new Stack<>();
        // expressions(후위 표기식 값을 공백에 따라 나누어줌)
        String[] expressions = expression.split(" ");
        for (int i = 0; i < expressions.length; i++) {
            // exp(i번째 문자)
            String exp = expressions[i];
            // exp가 피연산자라면
            if (!exp.equals("+") && !exp.equals("-") && !exp.equals("*") && !exp.equals("/")) {
                // 스택에 피연산자를 실수로 저장
                stack.push(Double.parseDouble(exp));
            }
            // exp가 연산자라면
            else {
                // 피연산자 두 개를 꺼내와 실수 연산하고 다시 스택에 저장
                double num1 = stack.pop();
                double num2 = stack.pop();
                if (exp.equals("+")) {
                    stack.push(num2 + num1);
                } else if (exp.equals("-")) {
                    stack.push(num2 - num1);
                } else if (exp.equals("*")) {
                    stack.push(num2 * num1);
                } else if (exp.equals("/")) {
                    stack.push(num2 / num1);
                }
            }
        }
        // 연산 결과를 정수로 반환
        return stack.pop().intValue();
    }
}
