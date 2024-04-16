package Baekjoon.B_2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

/*
 * 문제 분석하기
 * : 괄호의 쌍이 맞을 때 함께 제거하도록 하여 새로운 식을 만들어내도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 수식 저장하기
 * 2. 스택에 괄호의 인덱스를 순서대로 저장하며, 함께 제거될 수 있는 괄호의 인덱스를 찾도록 함
 * 3. 제거될 수 있는 괄호 쌍 사이의 조합을 찾아 제거된 괄호 식을 저장
 * 4. 저장한 식들을 반환
 */

/*
 * 2800) 괄호_제거
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[] expression; // expression(수식을 담은 문자 배열)
    static List<int[]> couple_indexes = new ArrayList<>(); // couple_indexes(괄호 쌍들의 인덱스를 담은 리스트)
    static Set<String> new_expressions = new HashSet<>(); // new_expressions(나올 수 있는 서로 다른 식들)
    static boolean[] visited; // visited(인덱스 쌍 방문 여부 배열)

    /*
     * 수식 저장하기
     */
    static void init() throws IOException {
        expression = br.readLine().toCharArray();
    }

    /*
     * 함께 제거될 수 있는 괄호 인덱스 찾기
     */
    static void findCouples() {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < expression.length; i++) {
            Character now = expression[i];
            if (now == '(') {
                stack.add(i);
            } else if (now == ')') {
                couple_indexes.add(new int[] { stack.pop(), i });
            }
        }

        visited = new boolean[couple_indexes.size()];
        makeExpressions(0);
    }

    /*
     * 제거될 수 있는 인덱스 조합으로 식 반환
     */
    static void makeExpressions(int depth) {
        if (depth == couple_indexes.size()) {
            boolean[] removeChars = new boolean[expression.length]; // removeChars(제거할 괄호 위치들)
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    int[] removeIndexes = couple_indexes.get(i);
                    removeChars[removeIndexes[0]] = true;
                    removeChars[removeIndexes[1]] = true;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < removeChars.length; i++) {
                if (!removeChars[i]) {
                    sb.append(expression[i]);
                }
            }
            if (sb.length() != expression.length) { // 처음 수식과 다를 때만 저장
                new_expressions.add(sb.toString());
            }
            return;
        }

        visited[depth] = true; // 조합에 이번 인덱스 조합을 사용
        makeExpressions(depth + 1);

        visited[depth] = false; // 조합에 이번 인덱스 조합을 사용하지 않음
        makeExpressions(depth + 1);
    }

    /*
     * 식을 사전 순으로 출력
     */
    static void print() {
        List<String> answer = new ArrayList<>(new_expressions);
        Collections.sort(answer);
        for (int i = 0; i < answer.size(); i++) {
            System.out.println(answer.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        findCouples();
        print();
    }
}