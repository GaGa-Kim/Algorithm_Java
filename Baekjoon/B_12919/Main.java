package Baekjoon.B_12919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : T에서 두 조건에 따라 T를 변환해가며 S를 만들 수 있는지 확인함
 */

/* 
 * 손으로 풀어보기
 * 1. 변환한 T의 마지막 글자가 A일 경우, 마지막 글자를 빼도록 함
 * 2. 변환한 T의 첫 번째 글자가 B일 경우, 첫 번째 글자를 빼고 뒤집도록 함
 * 3. 이를 반복해 변환한 T가 S의 길이와 같아졌을 때 동일한 글자인지 확인
 * 4. S를 T로 만들 수 있다면 1, 없다면 0을 반환
 */

/*
 * 12919) A와_B_2
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static String S;
    static String T;
    static int canChanged = 0; // canChanged(문자열 변환 가능 유무 - 0은 불가능, 1은 가능)

    /*
     * 문자열 변환하기 준비
     */
    static void init(StringTokenizer st) throws IOException {
        S = st.nextToken();

        st = new StringTokenizer(br.readLine());
        T = st.nextToken();

        change(T);
    }

    /*
     * 문자열 변환하기
     */
    static void change(String word) {
        if (S.length() == word.length()) { // 변환한 T가 S의 길이와 같을 때 동일한 글자인지 확인
            if (S.equals(word)) {
                canChanged = 1;
            }
            return;
        }

        if (word.charAt(word.length() - 1) == 'A') { // 변환한 T의 마지막 글자가 A일 경우
            String aword = word.substring(0, word.length() - 1);
            change(aword);
        }

        if (word.charAt(0) == 'B') { // 변환한 T의 첫 번째 글자가 B일 경우
            String bsub = word.substring(1);
            String bword = new StringBuilder(bsub).reverse().toString();
            change(bword);
        }
    }

    /*
     * 문자열 변환 가능 유무 출력하기
     */
    static void print() {
        System.out.println(canChanged);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        print();
    }
}