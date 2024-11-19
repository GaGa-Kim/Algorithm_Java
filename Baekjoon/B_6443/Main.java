package Baekjoon.B_6443;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제 분석하기
 * : 각 단어의 알파벳을 분리한 후, 이를 모두 사용하며 만들 수 있는 단어들을 찾도록 함
 *   이때 중복되지 않으면서 알파벳 순서로 출력해야 하므로 가장 앞에 있는 알파벳부터 시작하여 단어를 만들도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 각 단어의 알파벳을 분리
 * 2. 각 단어에 존재하는 알파벳의 개수를 세기
 * 3. 각 알파벳을 순서대로 탐색하며 중복되지 않으면서 만들 수 있는 단어들을 출력
 */

/*
 * 6443) 애너그램 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[] word; // word(단어의 알파벳을 담은 배열)
    static int[] alphabet_count; // alphabet_count(알파벳 등장 횟수)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        word = br.readLine().toCharArray();

        alphabet_count = new int[26];
        for (int i = 0; i < word.length; i++) {
            alphabet_count[word[i] - 'a']++;
        }
    }

    /*
     * 단어의 알파벳을 모두 탐색하여 만들 수 있는 단어찾기
     */
    static void backtracking(StringBuilder sb) {
        if (sb.length() == word.length) {
            print(sb.toString());
            return;
        }

        for (int i = 0; i < 26; i++) { // a부터 시작하여 탐색해 알파벳 순서에 따라 단어를 만들도록 함
            if (alphabet_count[i] > 0) {
                alphabet_count[i]--;
                sb.append((char) (i + 'a'));
                backtracking(sb);
                alphabet_count[i]++;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    /*
     * 만들어진 애너그램 출력하기
     */
    static void print(String word) {
        System.out.println(word);
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            init();
            backtracking(new StringBuilder());
        }
    }
}
