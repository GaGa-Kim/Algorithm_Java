package Baekjoon.B_15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 현재 상담을 했을 때 얻을 수 있는 수익과 현재 상담을 하지 않았을 경우에 얻을 수 있는 현재 상담의 종료일 시점의 상담 수익 중 더 큰 것을 찾도록 함
 *   즉, 현재 상담을 할 것인지, 하지 않을 것인지에 따라 각 일자의 최대 상담 수익을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 현재 상담을 했을 경우, 얻을 수 있는 수익을 찾음
 * 2. 현재 상담을 하지 않았을 경우에 얻을 수 있는 현재 상담의 종료일 시점의 상담 수익을 찾음
 * 3. 더 큰 수익으로 갱신해가며 가장 큰 상담 수익을 찾도록 함
 * 4. 최대 이익을 출력하기
 */

/*
 * 15486) 퇴사_2 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Counsel[] counsels; // counsels(상담 기간과 금액을 담은 배열)
    static int[] D; // D(각 날짜의 최대 이익을 담은 DP 테이블)

    /*
     * Counsel(상담 정보를 담은 클래스)
     */
    static class Counsel {
        int T; // T(상담을 완료하는데 걸리는 기간)
        int P; // P(상담을 했을 때 받을 수 있는 금액)

        public Counsel(int T, int P) {
            this.T = T;
            this.P = P;
        }
    }

    /*
     * 상담 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        counsels = new Counsel[N + 2];
        for (int i = 1; i <= N; i++) { // 1 ~ N일 동안의 상담 정보 저장
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            counsels[i] = new Counsel(T, P);
        }
        counsels[N + 1] = new Counsel(0, 0); // 퇴사 일(N + 1)의 상담 정보 저장
    }

    /*
     * 상담 선택하기
     */
    static void select() {
        D = new int[N + 2];
        int money = 0;
        for (int i = 1; i <= N + 1; i++) {
            money = Math.max(money, D[i]); // money(i일까지의 최대 수익)
            int next = i + counsels[i].T; // next(현재 상담을 선택할 경우의 끝나는 날)
            if (next < N + 2) { // 상담이 끝나는 날이 퇴사 이후가 아니라면
                D[next] = Math.max(D[next], money + counsels[i].P); // 현재 상담 종료일 시점의 상담 수익, 이전까지의 수익 + 현재 상담 수익 중 더 큰 것
            }
        }
    }

    /*
     * 최대 이익 출력하기
     */
    static void print() {
        System.out.println(D[N + 1]);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        select();
        print();
    }
}