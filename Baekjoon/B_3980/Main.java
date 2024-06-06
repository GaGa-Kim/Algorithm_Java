package Baekjoon.B_3980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 각 선수별로 포지션을 배치하는 경우의 수를 모두 구하여 능력치의 합이 최대가 되는 것을 찾도록 함
 *   이때 각 포지션에 대한 능력이 0이라면 그 포지션에 적합하지 않으므로 배치하지 않도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 각 선수에 대한 능력을 저장
 * 2. 선수들에 따라 만들 수 있는 포지션 배치 경우를 모두 살펴가며 능력치의 합을 구하도록 함
 *    이때 이미 포지션이 정해진 선수와 그 포지션에 대해 능력이 0인 선수는 배치할 수 없음
 * 3. 이를 반복하여 능력치의 합의 최댓값을 구하여 출력하도록 함
 */

/*
 * 3980) 선발_명단  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] player; // player(각 선수에 대한 포지션 능력)
    static boolean[] confirmed; // confirmed (포지션 확정 여부 배열)
    static int answer; // answer(능력치의 합의 최댓값)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        player = new int[11][11];
        confirmed = new boolean[11];
        answer = Integer.MIN_VALUE;

        StringTokenizer st;
        for (int i = 0; i < 11; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 11; j++) {
                player[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
     * 포지션에 따라 선수들을 배치하는 경우의 수를 구해 능력치의 합 구하기
     */
    static void backtracking(int position, int sum) {
        if (position == 11) { // 11번째 포지션까지 선수를 배치했다면
            answer = Math.max(answer, sum);
        }

        for (int i = 0; i < 11; i++) { // 포지션에 따른 선수 배치하기
            if (!confirmed[i] && player[i][position] != 0) { // 이미 포지션이 정해진 선수 또는 그 포지션에 대해 능력이 0인 선수는 배치할 수 없음
                confirmed[i] = true;
                backtracking(position + 1, sum + player[i][position]);
                confirmed[i] = false;
            }
        }
    }

    /*
     * 능력치의 합의 최댓값 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            backtracking(0, 0);
            print();
        }
    }
}