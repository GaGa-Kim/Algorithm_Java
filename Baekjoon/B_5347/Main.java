package Baekjoon.B_5347;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 두 수의 최대 공약수를 구해 최대 공배수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 두 수의 최대 공약수를 구하도록 함
 * 2. 이를 이용해 두 수의 곱을 최대 공약수로 나눠 최대 공배수를 구하도록 함
 * 3. 최소 공배수를 출력함
 */

/*
 * 5347) LCM 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static long a, b;
    static long answer = 0; // answer(두 수의 최소공배수)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
    }

    /*
     * 최소 공배수 구하기
     */
    static void lcm() {
        long gcd = gcd(a, b);
        answer = (a * b) / gcd;
    }

    /*
     * 최대 공약수 구하기
     */
    static long gcd(long a, long b) {
        long n = Math.max(a, b);
        long m = Math.min(a, b);
        if (m == 0) {
            return n;
        } else {
            return gcd(m, n % m);
        }
    }

    /*
     * 최소 공배수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            lcm();
            print();
        }
    }
}