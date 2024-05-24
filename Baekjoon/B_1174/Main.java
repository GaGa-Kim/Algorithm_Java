package Baekjoon.B_1174;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 문제 분석하기
 * : 백트래킹을 이용해 재귀하며 9 ~ 0의 숫자에 대해 해당 수를 선택할지 말지 여부를 찾아 조합하도록 함
 *   이때 10개의 수에 대해 모두 재귀했다면 멈추도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 백트래킹을 이용해 재귀하며 9 ~ 0의 숫자에 대해 해당 수를 선택할지 말지 여부를 찾아 조합
 * 2. 이를 정렬하여 N번째 작은 줄어드는 수를 출력

/*
 * 1174) 줄어드는_수 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] digit = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }; // digit(줄어드는 수를 만들기 위해 사용할 수 있는 수)
    static List<Long> list = new ArrayList<Long>(); // list(줄어드는 수들을 담은 리스트)

    /*
     * 데이터 저장하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    /*
     * 줄어드는 수 모두 찾기
     */
    static void makeNum(long number, int depth) {
        if (depth == 10) { // 10개의 수에 대해 모두 재귀했다면
            if (!list.contains(number)) { // 중복되지 않을 경우 저장
                list.add(number);
            }
            return;
        }

        makeNum(number, depth + 1); // 이번 수를 선택하지 않을 경우
        makeNum(number * 10 + digit[depth], depth + 1); // 이번 수를 선택할 경우
    }

    /*
     * N번째로 작은 줄어드는 수 출력하기
     */
    static void print() {
        if (N > list.size()) { // 줄어드는 수의 전체 개수보다 N이 크다면
            System.out.println(-1); // 그러한 수가 없으므로 -1 출력
            return;
        }

        Collections.sort(list); // 줄어드는 수를 정렬해 N번째 작은 줄어드는 수 출력
        System.out.println(list.get(N - 1));
    }

    public static void main(String[] args) throws IOException {
        init();
        makeNum(0, 0);
        print();
    }
}