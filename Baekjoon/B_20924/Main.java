package Baekjoon.B_20924;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : DFS 탐색을 통해 루트 노드부터 기가 노드까지의 길이와 기가 노드부터 리프 노드의 길이들을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 루트 노드부터 시작해서 탐색하면서 
 *    처음 자식 노드가 2개 이상인 노드(기가 노드)를 만나거나
 *    리프 노드가 단 1개인 노드를 만나거나
 *    루트 노드가 동시에 기가 노드인 경우라면 간선 길이를 구하도록 함
 * 2. 이후 기가 노드부터 시작해 리프 노드까지 탐색하며 가장 긴 간선 길이의 합을 구하도록 함
 * 3. 구한 트리의 기둥과 가장 긴 가지의 길이를 출력함
 */

/*
 * 20924) 트리의_기둥과_가지 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, R;
    static ArrayList<Edge>[] tree; // tree(트리 정보를 담은 리스트)
    static boolean[] visited; // visited(방문 유무 배열)
    static int giga = 0; // giga(기가 노드의 번호)
    static int[] answer = new int[2]; // answer(트리의 기둥과 가장 긴 가지의 길이)

    /*
     * Edge(간선 정보를 담을 클래스)
     */
    static class Edge {
        int index;
        int distance;

        public Edge(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Edge>();
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tree[a].add(new Edge(b, d));
            tree[b].add(new Edge(a, d));
        }
    }

    /*
     * 트리의 기둥 찾기
     */
    static void findPillar(int start, int sum) {
        if (tree[start].size() > 2 // 처음 자식 노드가 2개 이상인 노드를 만나거나
                || (tree[start].size() == 1 && start != R) // 리프 노드가 단 1개인 노드를 만나거나
                || (tree[start].size() == 2 && start == R)) { // 루트 노드가 동시에 기가 노드인 경우라면
            answer[0] = sum; // 트리의 기둥 길이와 기가 노드 갱신
            giga = start;
            return;
        }

        for (int i = 0; i < tree[start].size(); i++) {
            Edge next = tree[start].get(i);
            if (visited[next.index]) {
                continue;
            }
            visited[next.index] = true;
            findPillar(next.index, sum + next.distance);
        }

    }

    /*
     * 트리의 가지 찾기
     */
    static void findBranch(int start, int sum) {
        if (tree[start].size() == 1) { // 리프 노드라면 가장 긴 가지의 길이로 갱신
            answer[1] = Math.max(answer[1], sum);
            return;
        }

        for (int i = 0; i < tree[start].size(); i++) {
            Edge next = tree[start].get(i);
            if (visited[next.index]) {
                continue;
            }
            visited[start] = true;
            sum += next.distance;
            findBranch(next.index, sum);
            sum -= next.distance;
            visited[start] = false;
        }
    }

    /*
     * 트리의 기둥과 가장 긴 가지의 길이 출력하기
     */
    static void print() {
        System.out.println(answer[0] + " " + answer[1]);
    }

    public static void main(String[] args) throws IOException {
        init();
        findPillar(R, 0);
        findBranch(giga, 0);
        print();
    }
}