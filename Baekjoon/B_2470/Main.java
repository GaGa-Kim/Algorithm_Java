package Baekjoon.B_2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 반복문 2개로 풀이할 경우 시간 초과가 발생하게 됨
 *   그러므로 투 포인터를 이용해 두 용액을 혼합한 용액의 특성값 중 가장 0에 가까운 용액을 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 투 포인터를 이용해 두 용액을 혼합한 용액의 특성값을 찾음
 * 2. 특성값 중 가장 0에 가까운 용액으로 갱신
 * 3. 두 용액을 오름차순으로 출력 
 */

/*
 * 2470) 두_용액 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] waters; // waters(용액들을 담은 배열)
    static int[] answer; // answer(두 용액을 담은 배열)

    /*
     * 특성값 찾기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        waters = new int[N];
        for (int i = 0; i < N; i++) {
            waters[i] = Integer.parseInt(st.nextToken());
        }
    }

    /*
     * 특정값 계산하기
     */
    static void calculate() {
        answer = new int[2];

        Arrays.sort(waters);

        int minValue = Integer.MAX_VALUE; // minValue(가장 0에 가까운 특성값)
        int start = 0; // start(가장 작은 특성값부터 시작하는 포인터)
        int end = N - 1; // end(가장 큰 특성값부터 시작하는 포인터)

        while (start < end) { // 투 포인터를 변경하며 가장 0에 가까운 특성값을 찾도록 함
            int value = waters[start] + waters[end];
            if (Math.abs(value) < minValue) { // 현재 특성값보다 더 작은 특성값이라면
                minValue = Math.abs(value);
                answer[0] = waters[start];
                answer[1] = waters[end];
                if (minValue == 0) { // 특성값이 0이라면 종료
                    break;
                }
            }
            if (value < 0) {
                start++; // 특성값이 0보다 작다면, 0에 가까워지기 위해서는 작은 특성값을 증가시켜줘야 함
            } else {
                end--; // 특성값이 0보다 크다면, 0에 가까워지기 위해서는 큰 특성값을 감소시켜줘야 함
            }
        }
    }

    /*
     * 두 용액을 오름차순으로 출력
     */
    static void print() {
        System.out.println(answer[0] + " " + answer[1]);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        calculate();
        print();
    }
}
