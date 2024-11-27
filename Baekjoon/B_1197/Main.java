package Baekjoon.B_1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 두 정점을 연결할 수 있는 가장 작은 가중치를 찾아 트리들을 모두 연결하는 최소 신장 트리를 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 크루스칼 알고리즘을 수행해 사용하지 않은 엣지 중 가중치가 가장 작은 간선을 선택
 * 2. 선택한 간선을 연결했을 때 사이클이 발생하는지 판단
 * 3. 간선을 더한 횟수가 정점의 개수 - 1이 될 때까지 이를 반복
 * 4. 간선의 가중치를 모두 더한 값을 출력
 */

/*
 * 1197) 최소_스패닝_트리
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int V, E;
    static int[] parent; // parent(각 정점의 대표 노드 저장 배열)
    static PriorityQueue<Edge> pq; // pq(간선을 담을 최소값 우선순위 큐)
    static int answer = 0; // answer(최소 스패닝 트리의 가중치)

    /*
     * Edge(간선을 정보를 담을 클래스)
     */
    static class Edge implements Comparable<Edge> {
        int start, end;
        int value;

        public Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Edge other) { // 가중치를 기준으로 오름차순 정렬
            return Integer.compare(this.value, other.value);
        }
    }

    /*
     * 최소 스패닝 트리 구하기 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1]; // 정점에 따른 대표 노드 초기화하기
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        pq = new PriorityQueue<Edge>(); // 간선 정보 저장하기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }
    }

    /*
     * 최소 스패닝 트리를 구하기 위한 크루스칼 알고리즘 수행하기
     */
    static void kruskal() {
        int usedEdge = 0; // usedEdge(사용한 간선의 개수)
        while (usedEdge < V - 1) {
            Edge now = pq.poll();
            if (find(now.start) != find(now.end)) { // 사이클이 발생하지 않을 경우 연결 (같은 집합이 아니라면)
                union(now.start, now.end);
                answer += now.value;
                usedEdge++;
            }
        }
    }

    /*
     * 대표 노드 찾기
     */
    static int find(int a) {
        if (a == parent[a]) {
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    /*
     * 합집합 만들기
     */
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    /*
     * 간선의 가중치를 모두 더한 값 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        kruskal();
        print();
    }
}
