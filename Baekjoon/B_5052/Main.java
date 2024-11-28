package Baekjoon.B_5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 문제 분석하기
 * : 트라이 자료구조를 사용해 모든 전화번호 목록을 저장한 후 접두사가 일치하는지 확인
 *   또는 문자열을 정렬한 후 완전탐색으로 모든 문자열을 비교
 */

/* 
 * 손으로 풀어보기
 * 1. 트라이 자료구조에 모든 전화번호 목록 저장
 * 2. 저장해둔 트라이 자료구조를 통해 각 전화번호를 입력하여 해당 전화번호가 다른 전화번호의 접두사로 포함되는지 확인
 * 3. 만약 현재 입력된 전화번호의 끝 부분이 다른 전화번호에 포함되는 부분이라면 일관성이 있지 않으므로 false를 출력 
 * 4. 모두 일관성 있다면 true를 출력
 */

/*
 * 5052) 전화번호_목록  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static List<String> numberList; // numberList(전화번호 목록)
    static TNode trie; // trie(전화번호 목록을 저장할 트라이 자료구조)

    /*
     * TNode(전화번호 목록의 문자를 저장할 트라이 자료구조)
     */
    static class TNode {
        Map<Character, TNode> CNode = new HashMap<Character, TNode>(); // CNode(다음 자식 문자 노드들)
        boolean isEnd; // isEnd(마지막 문자 여부 표시하기)

        /*
         * 문자열에 따라 각 트라이 노드에 문자 저장
         */
        public void insert(String word) {
            TNode tNode = this;
            for (int i = 0; i < word.length(); i++) { // 첫 문자부터 마지막 문자까지 노드를 내려가며 저장
                char c = word.charAt(i);

                tNode.CNode.putIfAbsent(c, new TNode()); // 만약 자식 노드에 c 문자가 없다면 추가
                tNode = tNode.CNode.get(c); // 이를 다음 노드로 사용

                if (i == word.length() - 1) { // 마지막 문자라면 마지막 문자열 여부 표시
                    tNode.isEnd = true;
                    return;
                }
            }
        }

        /*
         * 문자열 포함 여부 확인
         */
        public boolean contain(String word) {
            TNode tNode = this;
            for (int i = 0; i < word.length(); i++) { // 첫 문자부터 마지막 문자까지 노드를 내려가며 같은지 확인
                char c = word.charAt(i);

                TNode node = tNode.CNode.get(c);
                if (node == null) { // 중간에 같은 문자열이 없을 경우
                    return false;
                }
                tNode = node;
            }
            if (tNode.isEnd) { // 마지막까지 내려왔으나 같은 문자열이 아닐 경우
                if (tNode.CNode.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }

    /*
     * 전화번호 목록 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        numberList = new ArrayList<String>(); // 전화번호를 전화번호 목록과 트라이 자료구조에 저장
        trie = new TNode();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            numberList.add(s);
            trie.insert(s);
        }
    }

    /*
     * 일관성 있는 목록인지 확인하기
     */
    static void valid() {
        for (String number : numberList) {
            if (trie.contain(number)) {
                print(false);
                return;
            }
        }
        print(true);
    }

    /*
     * 일관성 있는 목록인지 출력하기
     */
    static void print(boolean isConsistent) {
        if (isConsistent) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            valid();
        }
    }
}