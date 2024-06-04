package Baekjoon.B_2960;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 에라토스네테스의 체를 활용해 N까지의 수에 대한 소수를 구하며, K번째 지우는 수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 구하고자 하는 소수의 범위만큼 1차원 배열을 생성
 * 2. 1은 소수가 아니므로 2부터 시작하여 현재 선택된 숫자와 그의 배수에 해당하는 수를 지워나감
 * 3. 이를 반복하며 K번째 지워지는 수를 구하도록 함
 */

/*
 * 2960) 에라토스네테스의_체
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int answer = 0; // answer(K번째 지우는 수)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    /*
     * 에라토스테네스의 체를 이용해 소수 구하기
     */
    static void prime_number() {
        int[] arr = new int[N + 1]; // arr(소수 배열)
        for (int i = 2; i <= N; i++) { // 소수 배열 초기화
            arr[i] = i;
        }

        for (int P = 2; P <= N; P++) { // 아직 지우지 않은 수 중 가장 작은 수인 P를 찾음
            if (arr[P] == 0) {
                continue;
            }
            for (int i = P; i <= N; i += P) { // P를 지우고, 아직 지우지 않은 P의 배수 지우기
                if (arr[i] != 0) {
                    arr[i] = 0;
                    K--;
                    if (K == 0) { // K번째 지우는 수라면
                        answer = i;
                        return;
                    }
                }
            }
        }
    }

    /*
     * K번째로 지워지는 수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        prime_number();
        print();
    }
}