package Baekjoon.B_20207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 각 날마다의 일정 개수를 센 후, 일정이 없는 날을 기준으로 하여 
 *   각 구간의 최대 일정 개수 * 구간의 수를 하여 코팅지 면적을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 각 날마다의 일정의 개수 세기
 * 2. 일정이 없는 날을 기준으로 각 구간의 최대 일정 개수 * 구간의 수를 하여 코팅지 면적 구하기
 * 3. 전체 코팅지 면적 출력하기
 */

/*
 * 20207) 달력 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N; // N(일정의 개수)
    static int[] calendar = new int[366]; // calendar(일정을 표시할 달력)
    static int answer = 0; // answer(자르는 코팅지의 면적)

    /*
     * 각 날마다의 일정의 개수 세기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); // S(일정 시작 날짜)
            int E = Integer.parseInt(st.nextToken()); // E(일정 종료 날짜)
            for (int j = S; j <= E; j++) {
                calendar[j]++;
            }
        }
    }

    /*
     * 일정이 없는 날을 기준으로 코팅지 면적 구하기
     */
    static void calculate() {
        int height = 0; // height(각 구간의 최대 일정 개수 = 코팅지의 세로 길이)
        int width = 0; // width(구간의 수 = 코팅지의 가로 길이)
        for (int i = 1; i <= 365; i++) {
            if (calendar[i] == 0) { // 일정이 없는 날을 기준으로 각 구간의 최대 일정 개수 * 구간의 수 구하기
                answer += height * width;
                height = 0;
                width = 0;
                continue;
            }
            width++;
            height = Math.max(height, calendar[i]);
        }
        answer += height * width; // 마지막 구간의 최대 일정 개수 * 구간의 수 구하기
    }

    /*
     * 전체 코팅지 면적 출력
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        calculate();
        print();
    }
}
