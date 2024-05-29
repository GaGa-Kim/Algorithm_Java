package Baekjoon.B_1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 다익스트라 알고리즘을 이용해 시작점에서 다른 모든 정점으로의 최단 경로를 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 인접 리스트에 정점에 대한 간선 정보를 저장
 * 2. 시작점을 큐에 넣고 다익스트라 알고리즘을 수행
 * 3. 각 정점까지 가는 최단 경로의 값을 출력
 *    경로가 존재하지 않을 경우 INF를 출력
 */

/*
 * 1753) 최단경로 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int V, E, K;
    static ArrayList<Edge>[] list; // list(그래프 정보를 담은 리스트)
    static boolean[] visited; // visited(정점 방문 유무 배열)
    static int[] distance; // distance(최단 거리 배열)
    static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); // pq(가중치 최소값 우선순위 큐)

    /*
     * Edge(간선 정보를 담을 클래스)
     */
    static class Edge implements Comparable<Edge> {
        int v;
        int w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.w, other.w);
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<Edge>();
        }

        visited = new boolean[V + 1];

        distance = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge(v, w));
        }
    }

    /*
     * 다익스트라 알고리즘 수행하기
     */
    static void dijkstra() {
        pq.add(new Edge(K, 0)); // 시작 정점 추가
        distance[K] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (visited[now.v]) {
                continue;
            }
            visited[now.v] = true;
            for (int i = 0; i < list[now.v].size(); i++) {
                Edge next = list[now.v].get(i);
                if (distance[next.v] > distance[now.v] + next.w) { // 해당 정점까지 가는 더 적은 가중치 값으로 갱신
                    distance[next.v] = distance[now.v] + next.w;
                    pq.add(new Edge(next.v, distance[next.v]));
                }
            }
        }
    }

    /*
     * 각 정점까지 가는 최단 경로의 값 출력하기
     */
    static void print() {
        for (int i = 1; i <= V; i++) {
            if (visited[i]) {
                System.out.println(distance[i]);
            } else {
                System.out.println("INF");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        print();
    }
}