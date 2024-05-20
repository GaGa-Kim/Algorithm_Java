package Baekjoon.B_20164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 분석하기
 * : 수가 주어졌을 때 자리 조건에 맞춰 분할과 합을 반복하며 만들어가면 볼 수 있는 홀수의 개수에 대한 최솟값과 최댓값을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 수가 주어졌을 때 자리 조건에 맞춰 분할과 합을 반복
 *    수가 한 자리이면 종료, 
 *    수가 두 자리이면 2개로 나눠서 합 구하기, 
 *    수가 세 자리 이상이면 임의의 위치에서 끊어서 3개로 나눠서 합 구하기
 * 2. 이를 통해 만들어가면 볼 수 있는 홀수의 개수에 대한 최솟값과 최댓값을 구하도록 함

/*
 * 20164) 홀수_홀릭_호석
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    /*
     * 수 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    /*
     * 연산하기
     */
    static void find(int number, int count) {
        if (number < 10) { // 수가 한 자리이면 종료
            max = Math.max(max, count);
            min = Math.min(min, count);
        } else if (number < 100) { // 수가 두 자리이면 2개로 나눠서 합 구하기
            int sum = (number / 10) + (number % 10);
            find(sum, count + count_odd(sum));
        } else { // 수가 세 자리 이상이면 임의의 위치에서 끊어서 3개로 나눠서 합 구하기
            String num = Integer.toString(number);
            for (int i = 0; i <= num.length() - 3; i++) {
                for (int j = i + 1; j <= num.length() - 2; j++) {
                    String num1 = num.substring(0, i + 1);
                    String num2 = num.substring(i + 1, j + 1);
                    String num3 = num.substring(j + 1, num.length());
                    int sum = Integer.parseInt(num1) + Integer.parseInt(num2) + Integer.parseInt(num3);
                    find(sum, count + count_odd(sum));
                }
            }
        }
    }

    /*
     * 홀수 개수 세기
     */
    static int count_odd(int number) {
        int count = 0;
        while (number > 0) {
            int now = number % 10;
            if (now % 2 == 1) {
                count++;
            }
            number /= 10;
        }
        return count;
    }

    /*
     * 홀수의 개수에 대한 최솟값과 최댓값 출력하기
     */
    static void print() {
        System.out.println(min + " " + max);
    }

    public static void main(String[] args) throws IOException {
        init();
        find(N, count_odd(N));
        print();
    }
}
