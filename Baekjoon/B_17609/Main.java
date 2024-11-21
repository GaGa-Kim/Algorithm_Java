package Baekjoon.B_17609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 분석하기
 * : 원래 문자열과 뒤집은 문자열을 비교하며 회문, 유사회문, 그 외인지 찾아내도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 원래 문자를 뒤집은 문자와 비교
 * 2. 다른 문자가 나올 경우, 이를 삭제하고 비교를 계속 하도록 함
 * 3. 삭제하지 않고 회문일 경우 0, 하나만 삭제하고 회문일 경우 1, 하나를 삭제해도 회문이 아닐 경우 2를 출력함
 */

/*
 * 17609) 회문
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T;
    static StringBuilder answer = new StringBuilder(); // answer(회문 유뮤들을 담은 정답 문자열)

    /*
     * 회문 준비하기
     */
    static void init() throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) { // 회문 판단
            String s = br.readLine();
            answer.append(palindrome(s)).append('\n');
        }
    }

    /*
     * 회문 판단하기
     */
    static int palindrome(String s) {
        int result = 2;

        String rs = new StringBuilder(s).reverse().toString();
        if (s.equals(rs)) { // 원래 문자를 뒤집은 문자와 비교해 같을 경우 회문이므로 0
            result = 0;
        }

        else { // 다른 문자가 나올 경우 이를 삭제하고 뒤집은 문자와 비교해 같을 경우 유사회문이므로 1
            int left = 0;
            int right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    StringBuilder ns1 = new StringBuilder(s).deleteCharAt(left);
                    StringBuilder ns2 = new StringBuilder(s).deleteCharAt(right);
                    if (ns1.toString().equals(ns1.reverse().toString())
                            || ns2.toString().equals(ns2.reverse().toString())) {
                        result = 1;
                    }
                    break;
                }
                left++;
                right--;
            }
        }

        return result; // 둘다 아니라면 그 외는 2
    }

    /*
     * 문자열 결과 출력하기
     */
    static void print() {
        System.out.println(answer.toString());
    }

    public static void main(String[] args) throws IOException {
        init();
        print();
    }
}