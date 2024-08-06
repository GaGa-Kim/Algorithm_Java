package Baekjoon.B_1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : DFS 탐색을 통해 루트 노드에서부터 가장 먼 노드를 찾은 후, 그 노드로부터 가장 먼 노드를 찾아 지름을 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. DFS 탐색을 통해 루트 노드에서부터 가장 먼 노드 찾기
 * 2. 그 노드로부터 가장 먼 노드를 찾아 지름 출력
 */

/*
 * 1967) 트리의_지름 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static ArrayList<Node>[] tree; // tree(트리 데이터 저장 인접 리스트)
    static boolean[] visited; // visited(노드 방문 유무 배열)
    static int distant_node = 1; // node(루트 노드에서부터 가장 먼 노드)
    static int answer; // answer(트리의 지름)

    /*
     * Node(트리의 노드 정보를 담을 클래스)
     */
    static class Node {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            tree[node1].add(new Node(node2, cost));
            tree[node2].add(new Node(node1, cost));
        }

        answer = 0; // 가장 먼 노드 찾기
        visited = new boolean[n + 1];
        dfs(1, 0);

        answer = 0; // 그 노드로부터 가장 먼 노드를 찾아 지름 찾기
        visited = new boolean[n + 1];
        dfs(distant_node, 0);
    }

    /*
     * DFS 탐색
     */
    static void dfs(int node, int sum) {
        visited[node] = true;
        if (sum > answer) {
            answer = sum;
            distant_node = node;
        }
        for (Node next : tree[node]) {
            int n = next.node;
            int c = next.cost;
            if (!visited[n]) {
                dfs(n, sum + c);
            }
        }
    }

    /*
     * 지름 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        print();
    }
}