package Baekjoon.B_2668;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 문제 분석하기
 * : 첫째 줄에서 수를 선택한 후, 그 수의 바로 밑의 둘째 줄에 해당하는 수를 첫째 줄에서 찾아 
 *   두 수가 일치하는지 확인하는 것을 반복하여 최대 정수의 개수를 구하도록 함
 *   예) 1 -> 3 -> 1 / 5 -> 5
 */

/* 
 * 손으로 풀어보기
 * 1. 첫째 줄에서 수를 선택한 후, 
 *    DFS 탐색을 통해 숫자 -> numbers[숫자] -> numbers[numbers[숫자]]로 싸이클이 발생하는지 확인하도록 함
 * 2. 이를 반복하여 싸이클이 발생하는 수를 찾아 그 개수와 숫자들을 출력하도록 함 
 */

/*
 * 2668) 숫자고르기  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] numbers; // numbers(표의 둘째 줄에 들어가는 정수들)
    static boolean[] visited; // visited(정수 방문 유무 배열)
    static List<Integer> answer = new ArrayList<Integer>(); // answer(뽑힌 정수들)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];
    }

    /*
     * 두 집합이 일치하도록 정수 뽑기
     */
    static void select() {
        for (int i = 1; i <= N; i++) {
            visited[i] = true;
            dfs(i, i);
            visited[i] = false;
        }
    }

    /*
     * DFS 탐색을 통해 싸이클 발생 확인하기
     */
    static void dfs(int first, int second) {
        if (numbers[first] == second) { // 첫째 줄에서 뽑은 정수와 둘째 줄에서 뽑은 정수가 일치한다면
            answer.add(first);
        }
        if (visited[numbers[first]] == false) { // 아직 뽑히지 않은 정수라면 뽑을 수 있는지 탐색
            visited[numbers[first]] = true;
            dfs(numbers[first], second);
            visited[numbers[first]] = false;
        }
    }

    /*
     * 뽑힌 정수들의 개수와 뽑힌 정수들을 출력하기
     */
    static void print() {
        System.out.println(answer.size());
        Collections.sort(answer);
        for (int i : answer) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        select();
        print();
    }
}