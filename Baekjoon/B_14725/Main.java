package Baekjoon.B_14725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 단어 정보에 따라 트라이를 구한 후, 개미굴 입구인 루트 노드를 제외하고 트리를 탐색하여 개미굴의 구조를 출력하도록 함
 *   예) APPLE, KIWI 중 APPLE → APPLE, BANANA 중 APPLE, BANANA 순 → KIWI
 *      KIWI → APPLE, BANANA 중 APPLE, BANANA 순
 *      그러므로
 *      APPLE
 *      --APPLE
 *      --BANANA
 *      ----KIWI
 *      KIWI
 *      --APPLE
 *      --BANANA
 */

/* 
 * 손으로 풀어보기
 * 1. 단어 정보에 따라 트라이 자료구조에 모든 먹이 정보 저장
 * 2. 저장해둔 트라이 자료구조를 탐색하여 개미굴의 구조를 출력
 */

/*
 * 14725) 개미굴 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static TNode trie = new TNode(); // trie(개미굴 먹이 정보를 저장할 트라이 자료구조)

    /*
     * TNode(개미굴의 먹이를 저장할 트라이 자료구조)
     */
    static class TNode {
        Map<String, TNode> cNode = new HashMap<String, TNode>(); // cNode(다음 자식 먹이 노드들)

        /*
         * 문자에 따라 각 트라이 노드에 먹이 저장
         */
        public void insert(String strs) {
            TNode tNode = this;
            String[] foods = strs.split(" ");
            for (String food : foods) { // 첫 먹이부터 마지막 먹이까지 노드를 내려가며 저장
                tNode.cNode.putIfAbsent(food, new TNode()); // 만약 자식 노드에 먹이가 없다면 추가한 후
                tNode = tNode.cNode.get(food); // 이를 다음 노드로 사용
            }
        }

        /*
         * 개미굴의 구조에 따른 먹이 정보 출력
         */
        public void print(TNode now, int depth) {
            TNode tNode = now;
            if (tNode.cNode != null) {
                List<String> foods = new ArrayList<String>(tNode.cNode.keySet()); // 자식 먹이 노드들
                Collections.sort(foods); // 같은 층에 여러 개의 방이 있을 때는 사전 순서가 앞서는 먹이 정보가 먼저 나오도록 함
                for (String food : foods) {
                    for (int i = 0; i < depth; i++) { // 깊이만큼 --을 추가하여 먹이 출력
                        System.out.print("--");
                    }
                    System.out.println(food);
                    print(tNode.cNode.get(food), depth + 1); // 먹이의 자식 먹이 노드들을 출력
                }
            }
        }
    }

    /*
     * 먹이 정보 저장 및 개미굴 구조 출력하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < K; j++) {
                sb.append(st.nextToken()).append(" ");
            }
            trie.insert(sb.toString()); // 먹이 정보 저장하기
        }
        trie.print(trie, 0); // 개미굴의 구조 출력하기
    }

    public static void main(String[] args) throws IOException {
        init();
    }
}