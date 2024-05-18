package Baekjoon.B_17073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 더 이상 물이 움직이지 않을 때는 리프 노드에 모두 물이 쌓이게 되므로 
 *   리프 정점에 고인 물의 양을 리프 노드의 개수로 나누어주면 됨
 */

/* 
 * 손으로 풀어보기
 * 1. 트리를 탐색하며 리프 노드의 개수를 찾도록 함
 * 2. 리프 정점에 고인 물의 양을 리프 노드의 개수로 나누어주어 평균으로 출력
 */

/*
 * 17073) 나무_위의_빗물
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, W;
    static ArrayList<Integer>[] tree; // tree(트리 데이터 저장 인접 리스트)
    static int leaf_count = 0; // leaf_count(리프 노드 개수)

    /*
     * 리프 노드 찾기 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1]; // 트리 저장 인접 리스트 초기화
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < N - 1; i++) { // 트리 간선 정보 저장
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            tree[U].add(V);
            tree[V].add(U);
        }
    }

    /*
     * 리프 노드 개수 찾기
     */
    static void find_leaf() {
        for (int i = 2; i <= N; i++) {
            if (tree[i].size() == 1) { // 루트 노드를 제외하고, 연결된 간선이 하나인 노드라면 리프 노드
                leaf_count++;
            }
        }
    }

    /*
     * 0보다 큰 정점들에 고인 물들의 평균
     */
    static void print() {
        System.out.println((double) W / leaf_count);
    }

    public static void main(String[] args) throws IOException {
        init();
        find_leaf();
        print();
    }
}