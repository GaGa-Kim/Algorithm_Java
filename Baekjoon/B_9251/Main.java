package Baekjoon.B_9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 분석하기
 * : 점화식 배열을 이용해 각 위치 인덱스를 마지막 문자로 하는 최장 공통 수열의 길이를 구해 갱신하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 각 위치 인덱스를 마지막 문자로 하는 공통 수열의 길이를 구해 갱신
 * 2. 마지막 위치 인덱스까지 왔을 때의 최장 공통 수열의 길이를 출력
 */

/*
 * 9251) LCS 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[] A; // A(1번째 문자열)
    static char[] B; // B(2번째 문자열)
    static long[][] D; // D(각 위치 인덱스를 마지막 문자로 하는 두 문자열의 최장 공통 수열의 길이 점화식 배열)

    /*
     * 두 수열 저장하기
     */
    static void init() throws IOException {
        A = br.readLine().toCharArray();
        B = br.readLine().toCharArray();

        D = new long[A.length + 1][B.length + 1];
    }

    /*
     * 부분 공통 수열의 길이 찾기
     */
    static void find() {
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) { // 두 문자열이 같다면 이전 공통 수열의 길이 + 1로 갱신
                    D[i][j] = D[i - 1][j - 1] + 1;
                } else { // 두 문자열이 같지 않다면 이전 공통 수열의 길이로 갱신
                    D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
                }
            }
        }
    }

    /*
     * 최장 공통 수열의 길이 출력하기
     */
    static void print() {
        System.out.println(D[A.length][B.length]);
    }

    public static void main(String[] args) throws IOException {
        init();
        find();
        print();
    }
}