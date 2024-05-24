package Baekjoon.B_4256;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 후위 순회에서는 왼쪽-오른쪽-루트 순서로 이동하므로, 전위 순회와 중위 순회 값을 이용해 순회하도록 함
 *   예) 전위 : 3, 6, 5, 4, 8, 7, 1, 2 / 중위 : 5, 6, 8, 4, 3, 1, 2, 7
 *      - 루트 노드 : 3 / 왼쪽 서브트리 : 5, 6, 8, 4 / 오른쪽 서브트리 : 1, 2, 7
 *      
 *      - 루트 노드 : 6 / 왼쪽 서브트리 : 5 / 오른쪽 서브트리 : 8, 4
 *      - 루트 노드 : 5 -> [5]
 *      - 루트 노드 : 4 / 왼쪽 서브트리 : 8 -> [5, 8, 4]
 *      - [5, 8, 4, 6]
 *      
 *      - 루트 노드 : 7 / 왼쪽 서브트리 : 1, 2
 *      - 루트 노드 : 1 / 오른쪽 서브트리 : 2 -> [5, 8, 4, 6, 2, 1]
 *      - [5, 8, 4, 6, 2, 1, 7]
 *      
 *      - [5, 8, 4, 6, 2, 1, 7, 3] 
 */

/* 
 * 손으로 풀어보기
 * 1. 전위 순회로 루트 노드를 찾도록 함
 * 2. 구한 루트 노드를 가지고 중위 순회 값을 이용해 
 *    루트 노드까지를 찾아 왼쪽 서브트리, 그 외는 오른쪽 서브 트리로 나누고 이를 반복해 순회하도록 함
 * 3. 마지막에는 루트 노드를 합쳐주도록 함

/*
 * 4256) 트리 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] pre_order; // pre_order(전위 순회 결과)
    static int[] in_order; // in_order(중위 순회 결과)
    static StringBuilder answer; // answer(후위 순회 결과)

    /*
     * 데이터 저장하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        pre_order = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pre_order[i] = Integer.parseInt(st.nextToken());
        }

        in_order = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            in_order[i] = Integer.parseInt(st.nextToken());
        }

        answer = new StringBuilder();
    }

    /*
     * 후위 순회하기
     */
    static void post_order(int rootIndex, int begin, int end) {
        if (rootIndex >= N) {
            return;
        }

        int rootValue = pre_order[rootIndex]; // 전위 순회로 루트 노드의 값을 찾음

        for (int i = begin; i <= end; i++) { // 중위 순회로 루트 노드까지를 찾아 왼쪽 서브트리, 그 외는 오른쪽 서브트리로 나눔
            if (rootValue == in_order[i]) {
                post_order(rootIndex + 1, begin, i); // 왼쪽 서브트리
                post_order(rootIndex + i + 1 - begin, i + 1, end); // 오른쪽 서브트리
                answer.append(rootValue + " "); // 마지막에는 루트 노드를 합쳐주도록 함
                return;
            }
        }
    }

    /*
     * 후위 순회 결과 출력하기
     */
    static void print() {
        answer.append("\n");
        System.out.print(answer);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            post_order(0, 0, N - 1);
            print();
        }
    }
}