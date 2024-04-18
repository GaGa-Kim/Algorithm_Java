package Baekjoon.B_22943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 소수들을 찾아, 두 소수가 조건을 만족하는지 확인하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. K가지의 숫자를 가지고 만들 수 있는 가장 큰 수까지의 소수 찾기
 * 2. 소수 쌍을 가지고 조건 1과 조건 2의 경우의 수 만들기
 * 3. K가지의 숫자를 한 번씩만 사용하여 만들 수 있는 수 선택해 조건1, 2 확인하기
 * 4. 조건을 만족하는 수의 개수 출력
 */

/*
 * 22943) 수
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int K, M;
    static int limit; // limit(K개의 서로 다른 숫자로 만들 수 있는 수의 최대값)
    static ArrayList<Integer> primeNumberList; // primeNumberList(소수들 리스트)
    static HashSet<Integer> sumSet; // sumSet(조건 1을 만족하는 수들)
    static HashSet<Integer> multiSet; // multiSet(조건 2를 만족하는 수들)
    static boolean[] visited = new boolean[10]; // visited(수를 만들기 위한 방문 유무 배열)
    static int answer = 0; // answer(조건을 만족하는 수의 개수)

    /*
     * 소수 찾기 준비하기
     */
    static void init(StringTokenizer st) {
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String s = "";
        for (int i = 9; i >= 0; i--) {
            s += i;
            if (s.length() == K) {
                break;
            }
        }
        limit = Integer.parseInt(s);
    }

    /*
     * K가지의 숫자를 가지고 만들 수 있는 가장 큰 수까지의 소수 찾기
     */
    static void findPrimeNumber() {
        boolean[] arr = new boolean[limit + 1];
        for (int i = 2; i <= (int) Math.sqrt(limit); i++) { // 에라토스테네스 방법으로 소수 구하기
            if (arr[i]) {
                continue;
            }
            for (int j = i + i; j <= limit; j = j + i) {
                arr[j] = true;
            }
        }

        primeNumberList = new ArrayList<Integer>();
        for (int i = 2; i <= limit; i++) {
            if (!arr[i]) {
                primeNumberList.add(i);
            }
        }
    }

    /*
     * 소수 쌍을 가지고 조건 1과 조건 2의 경우의 수 만들기
     */
    static void findSumAndMulti() {
        sumSet = new HashSet<Integer>();
        multiSet = new HashSet<Integer>();

        for (int i = 0; i < primeNumberList.size(); i++) {
            for (int j = i; j < primeNumberList.size(); j++) {
                int pn1 = primeNumberList.get(i);
                int pn2 = primeNumberList.get(j);

                int sum = pn1 + pn2;
                if (pn1 != pn2) {
                    if (sum <= limit) {
                        sumSet.add(sum); // 조건 1을 만족하는 수들
                    }
                }

                long multi = 1L * pn1 * pn2;
                if (multi <= limit) {
                    multiSet.add((int) multi); // 조건 2를 만족하는 수들
                }
            }
        }
    }

    /*
     * K가지의 숫자를 한 번씩만 사용하여 만들 수 있는 수 선택하기
     */
    static void selectNumber(int depth, int number) {
        if (depth == K) {
            if (isValid(number)) {
                answer++;
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (i == 0 && depth == 0 || visited[i]) {
                continue; // 수의 맨 앞이 0이거나, 이미 사용한 수라면 넘어감
            }
            visited[i] = true;
            selectNumber(depth + 1, number * 10 + i);
            visited[i] = false;
        }
    }

    /*
     * 조건1, 2 확인하기
     */
    static boolean isValid(int number) {
        if (!sumSet.contains(number)) {
            return false; // 조건 1을 만족하지 않는다면
        }
        while (number % M == 0) {
            number /= M;
        }
        if (!multiSet.contains(number)) {
            return false; // 조건 2를 만족하지 않는다면
        }
        return true; // 조건 1, 2를 모두 만족한다면
    }

    /*
     * 조건을 만족하는 수의 개수 출력
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        findPrimeNumber();
        findSumAndMulti();
        selectNumber(0, 0);
        print();
    }
}