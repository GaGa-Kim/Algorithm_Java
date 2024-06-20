package Baekjoon.B_16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 문제 분석하기
 * : 괄호 안에 하나의 연산자만 들어가도록 하여 식의 결과의 최댓값을 구해야하므로 
 *   이전까지의 결과 값에 이번 연산을 할 때 괄호를 넣은 것과 넣지 않은 것의 결과를 구하도록 반복하여 최댓값을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 괄호를 넣지 않을 경우 이전까지의 계산 값과 현재의 수에 대해 계산하도록 함
 * 2. 괄호를 넣을 경우 현재의 수와 다음 수에 대해 먼저 계산한 후 이를 이전까지의 계산 값과 계산하도록 함
 * 3. 모든 연산자를 살펴봤다면 식의 결과의 최댓값을 갱신하도록 함
 * 4. 이를 반복하여 괄호를 넣거나 넣지 않는 경우에 대해 모두 반복하여 최댓값을 찾아 출력함
 */

/*
 * 16637) 괄호_추가하기 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    static List<Character> operators = new ArrayList<Character>(); // operators(연산자들)
    static List<Integer> operands = new ArrayList<Integer>(); // operands(피연산자들)

    static int answer = Integer.MIN_VALUE; // answer(식의 결과의 최댓값)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        operators = new ArrayList<Character>();
        operands = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                operators.add(c);
                continue;
            }
            operands.add(Character.getNumericValue(c));
        }
    }

    /*
     * 괄호 추가하기
     */
    static void add_bracket(int index, int result) {
        if (index >= operators.size()) { // 모든 연산자의 개수만큼 살펴봤다면 식의 결과의 최댓값 갱신
            answer = Math.max(answer, result);
            return;
        }

        int cal1 = calculate(result, operands.get(index + 1), operators.get(index)); // 괄호를 사용하지 않을 경우, 현재까지의 계산 값과 다음 수에 대해 계산
        add_bracket(index + 1, cal1);

        if (index + 1 < operators.size()) { // 괄호를 사용할 경우, 다음 두 개의 수에 대해 먼저 계산한 후 현재까지의 계산 값과 계산
            int cal2 = calculate(operands.get(index + 1), operands.get(index + 2), operators.get(index + 1));
            cal2 = calculate(result, cal2, operators.get(index));
            add_bracket(index + 2, cal2);
        }
    }

    /*
     * 연산자에 따라 계산하기
     */
    static int calculate(int num1, int num2, char operator) {
        if (operator == '+') {
            return num1 + num2;
        } else if (operator == '-') {
            return num1 - num2;
        } else if (operator == '*') {
            return num1 * num2;
        }
        return -1;
    }

    /*
     * 식의 결과의 최댓값 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        add_bracket(0, operands.get(0));
        print();
    }
}