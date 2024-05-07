package Baekjoon.B_22862;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 투 포인터를 사용해 범위를 정한 후, 최대 K번 삭제하여 가장 긴 짝수 부분 수열을 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 투 포인터를 사용해 범위 정하기
 * 2. 짝수가 아닐 경우 K번만큼 삭제
 * 3. 가장 긴 짝수 부분 수열의 길이를 출력
 */

/*
 * 22862) 가장_긴_짝수_연속한_부분_수열_(large)
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] numbers; // numbers(수열을 구성하는 수 정보)
    static int length; // length(짝수로 이루어져 있는 연속한 부분 수열 중 가장 긴 길이)

    /*
     * 수열 저장하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) { // 각 숫자에 대해 2로 나눈 값을 저장 (0일 경우 짝수, 1일 경우 홀수)
            numbers[i] = Integer.parseInt(st.nextToken()) % 2;
        }
    }

    /*
     * 가장 긴 짝수 부분 수열의 길이 찾기
     */
    static void find() {
        int start = 0; // start, end(부분 수열의 범위를 나타내는 시작, 끝 포인터)
        int end = 0;
        int count = 0; // count(삭제할 홀수의 개수)
        while (end < N) {
            if (count < K) { // K번보다 적게 삭제했을 경우, 계속해서 end 이동
                if (numbers[end] == 1) {
                    count++;
                }
                end++;
                length = Math.max(length, end - start - count);
            } else if (count == K && numbers[end] == 0) { // K번만큼 삭제했으며 현재 수가 짝수일 경우, 계속해서 end 이동
                end++;
                length = Math.max(length, end - start - count);
            } else if (count == K && numbers[end] == 1) { // K번만큼 삭제했으며 현재 수가 홀수일 경우, end를 이동할 수 없으므로 start 이동
                if (numbers[start] == 1) {
                    count--;
                }
                start++;
            }
        }
    }

    /*
     * 가장 긴 짝수 부분 수열의 길이 출력하기
     */
    static void print() {
        System.out.println(length);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        find();
        print();
    }
}