package Baekjoon.B_15681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 트리를 DFS로 재귀적으로 탐색하면서 각 정점에 대한 서브트리 수를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 트리를 DFS로 재귀적으로 탐색하면서 각 정점에 대한 서브트리 수를 구하도록 함
 * 2. 쿼리에 따라 서브트리에 속한 정점의 수를 출력
 */

/*
 * 15681) 트리와_쿼리  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, R, Q;
    static ArrayList<Integer>[] tree; // tree(트리 정보)
    static int[] query; // query(쿼리 정보)
    static int count[]; // count(각 정점에 대한 서브트리에 속한 정점의 수)

    /*
     * 각 정점에 대한 서브트리 수 구하기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1]; // 트리 정보 저장하기
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            tree[U].add(V);
            tree[V].add(U);
        }

        query = new int[Q]; // 쿼리 정보 저장하기
        for (int i = 0; i < Q; i++) {
            query[i] = Integer.parseInt(br.readLine());
        }

        count = new int[N + 1];
    }

    /*
     * 각 정점에 대한 서브트리 수 구하기
     */
    static void find(int now) {
        count[now] = 1; // 현재 정점이므로 정점의 수 1 증가
        for (int next : tree[now]) { // 현재 정점과 연결된 다른 자식 정점을 재귀적으로 방문
            if (count[next] == 0) { // 아직 방문하지 않은 정점이라면 탐색
                find(next);
                count[now] += count[next];
            }
        }
    }

    /*
     * 쿼리에 따라 각 정점에 대한 서브트리 수 출력하기
     */
    static void print() {
        for (int q : query) {
            System.out.println(count[q]);
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        find(R);
        print();
    }
}