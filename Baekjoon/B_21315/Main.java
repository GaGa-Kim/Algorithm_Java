package Baekjoon.B_21315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 초기 상태부터 시작해 K가 될 수 있는 수를 두 개 뽑아 완전 탐색하여 알맞는 K를 찾도록 함
 *   예) 초기 상태 -> 1 2 3 4 5
 *   4개의 카드를 더미의 맨 위에 올림 -> 2 3 4 5 1
 *   2 <= i <= 3이므로
 *   i = 2일 때, 직전에 맨 위에 올린 카드(2, 3, 4, 5) 중 밑에서 2개의 카드 더미를 맨 위에 올림 -> 4, 5, 2, 3, 1  
 *   i = 3일 때, 직전에 맨 위에 올린 카드(4, 5) 중에서 밑에서 1개의 카드 더미를 맨 위에 올림 -> 5, 4, 2, 3, 1 
 */

/* 
 * 손으로 풀어보기
 * 1. K가 될 두 수를 뽑도록 함
 * 2. K에 따라 2번의 (2, K) 섞기를 하여 결과를 찾도록 함
 *    2^K개의 카드를 더미의 맨 위에 올린 후, 그 중에서 2^(K - i + 1)개의 카드를 더미의 맨 위에 올리는 것을 반복
 * 3. 구한 결과가 주어진 카드 결과와 같은지 확인하도록 하여 같다면 두 K를 출력함
 */

/*
 * 21315) 카드_섞기
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] target; // target(2번의 (2, K)를 한 결과)

    static int K1, K2; // K1, K2(각각의 K)
    static int[] arr; // arr(초기 상태)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        target = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        K1 = 0;
        K2 = 0;
        select();
    }

    /*
     * K 정하기
     */
    static void select() {
        for (int i = 1; Math.pow(2, i) < N; i++) { // 1 ≤ K, 2^K < N의 조건에 따라 K 두 개 선택
            for (int j = 1; Math.pow(2, j) < N; j++) {
                arr = new int[N]; // 초기 상태 만들기
                for (int k = 0; k < N; k++) {
                    arr[k] = k + 1;
                }

                mix(i); // K에 따라 2번의 (2, K) 섞기
                mix(j);

                boolean isAllEquals = true; // 구한 결과가 주어진 카드 결과와 같은지 확인
                for (int l = 0; l < N; l++) {
                    if (arr[l] != target[l]) {
                        isAllEquals = false;
                        break;
                    }
                }

                if (isAllEquals) {
                    K1 = i;
                    K2 = j;
                    return;
                }
            }
        }
    }

    /*
     * (2, K) 섞기
     */
    static void mix(int K) {
        int range = N; // range(직전에 맨 위로 올린 카드까지의 인덱스 범위)
        int count = 0; // count(더미의 맨 위로 올릴 카드 개수)
        for (int i = 1; i <= K + 1; i++) { // i = 1일 때는 2^K이므로 첫 번째 단계부터 K + 1번째 단계까지 반복
            count = (int) Math.pow(2, K - i + 1);
            step(range, count);
            range = count;
        }
    }

    /*
     * 섞기 단계
     */
    static void step(int range, int count) {
        List<Integer> tmp = new ArrayList<Integer>(); // tmp(임시 리스트)
        for (int i = range - count; i < range; i++) { // 직전에 맨 위로 올린 카드 중 밑에서 2^(K - i + 1)개의 카드를 임시 리스트에 저장
            tmp.add(arr[i]);
            arr[i] = 0;
        }
        for (int i = 0; i < N; i++) {
            if (arr[i] != 0) { // 임시 리스트에 존재하지 않는 수는 뽑힌 카드보다 뒤에 있어야 하므로, 리스트에 저장한 후 이후 배열의 뒤에 저장
                tmp.add(arr[i]);
            }
            arr[i] = tmp.get(i); // 임시 리스트에 존재하는 카드의 수를 더미의 맨 위에 저장
        }
    }

    /*
     * 두 K를 출력하기
     */
    static void print() {
        System.out.println(K1 + " " + K2);
    }

    public static void main(String[] args) throws IOException {
        init();
        select();
        print();
    }
}