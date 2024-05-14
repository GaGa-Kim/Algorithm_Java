package Baekjoon.B_20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 분석하기
 * : 문자열에 따른 알파벳의 갯수를 저장한 후, K개 이상을 가진 알파벳일 때 이 문자부터 시작해서 K개를 포함하는 문자열을 찾아 최소, 최대 갱신 
 */

/* 
 * 손으로 풀어보기
 * 1. 문자열에 따른 알파벳의 갯수를 저장
 * 2. K개 이상을 가진 알파벳일 때 이 문자부터 시작해서 K개를 포함하는 문자열을 찾기
 * 3. 최소, 최대 갱신
 * 4. 만족하는 연속 문자열이 없을 경우 -1, 만족하는 문자열이 있을 경우 최대, 최소 출력하기
 */

/*
 * 20437) 문자열_게임_2 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T;

    /*
     * 문자열 길이 구하기 준비하기
     */
    static void init() throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                print(1, 1);
            } else {
                find(W, K);
            }
        }
    }

    /*
     * 문자열 길이 구하기
     */
    static void find(String W, int K) {
        int[] alphabet = new int[26]; // 알파벳 갯수 저장하기
        for (int i = 0; i < W.length(); i++) {
            alphabet[W.charAt(i) - 'a']++;
        }

        int min = Integer.MAX_VALUE; // min(가장 짧은 연속 문자열의 길이)
        int max = Integer.MIN_VALUE; // max(가장 긴 연속 문자열의 길이)
        for (int i = 0; i < W.length(); i++) {
            if (alphabet[W.charAt(i) - 'a'] < K) { // 현재 문자가 K개 보다 적은 알파벳이라면 넘어감
                continue;
            }
            int count = 1; // count(해당 문자의 개수)
            for (int j = i + 1; j < W.length(); j++) {
                if (W.charAt(i) == W.charAt(j)) { // 같은 문자라면 증가
                    count++;
                }
                if (count == K) { // K개만큼 같은 문자를 포함한다면 더 짧은 길이, 더 긴 길이로 갱신
                    min = Math.min(min, j - i + 1);
                    max = Math.max(max, j - i + 1);
                    break;
                }
            }
        }
        if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) { // 만족하는 연속 문자열이 없을 경우
            print();
        } else { // 만족하는 문자열이 있을 경우
            print(min, max);
        }
    }

    /*
     * 가장 짧은 연속 문자열의 길이와 가장 긴 연속 문자열의 길이 출력하기
     */
    static void print(int min, int max) {
        System.out.println(min + " " + max);
    }

    /*
     * 만족하는 연속 문자열이 없을 시 -1 출력하기
     */
    static void print() {
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        init();
    }
}