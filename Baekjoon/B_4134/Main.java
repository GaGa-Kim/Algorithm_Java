package Baekjoon.B_4134;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 분석하기
 * : n보다 크거나 같은 수 중에서 가장 작은 소수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. n부터 시작하여 각 수가 1과 자기 자신만을 약수로 가지는지 확인하며 가장 작은 소수를 구하도록 함
 * 2. 각 케이스에 대해 한 줄에 하나씩 소수를 출력함

/*
 * 4134) 다음_소수 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T; // T(테스트 케이스의 개수)
    static long answer; // answer(가장 작은 소수)

    /*
     * 각 테스트 케이스에 대한 소수 찾기
     */
    static void find() throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) { // 테스트 케이스 개수만큼 소수 찾기
            long n = Long.parseLong(br.readLine());
            answer = findNextPrime(n);
            print();
        }
    }

    /*
     * n보다 크거나 같은 소수 찾기
     */
    static long findNextPrime(long n) {
        if (n == 0 || n == 1) { // n이 0 또는 1이라면 소수 2
            return 2;
        }
        while (true) {
            long count = 0; // count(n의 약수의 개수)
            for (long i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) { // i가 n의 약수라면 n의 약수의 개수 증가
                    count++;
                    break;
                }
            }
            if (count == 0) { // 1과 자신을 제외한 약수가 없다면 소수
                return n;
            }
            n++; // n이 소수가 아니라면 다음 수가 소수인지 확인
        }
    }

    /*
     * n보다 크거나 같은 소수 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        find();
    }
}
