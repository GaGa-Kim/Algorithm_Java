package Baekjoon.B_1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 크루스칼 알고리즘을 이용해 각 집들을 연결하는 최소 신장 트리를 찾고 두 마을로 분리하여 최소 유지비의 합을 찾도록 함
 *   이 집들을 두 개의 마을로 분리할 때, 마지막 집을 기준으로 두 개의 마을로 분리하여 가장 큰 유지비를 제외하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 크루스칼 알고리즘을 이용해 사용하지 않은 엣지 중 가중치가 가장 적은 간선을 선택
 * 2. 선택한 간선을 연결했을 때 사이클이 발생하는지 판단
 * 3. 간선을 더한 횟수가 정점의 개수 -1이 될 때까지 반복
 * 4. 마지막 유지비의 경우, 두 마을로 분리하면 사라지게 되므로 이는 합산하지 않음
 * 4. 두 마을로 분리하며 집들을 연결하는 길의 최소 유지비의 합을 출력 
 */

/*
 * 1647) 도시_분할_계획
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static int[] parent; // parent(각 마을의 대표 노드 저장 배열)
    static PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); // pq(간선을 담을 최소값 우선순위 큐)
    static int answer = 0; // answer(두 마을로 분리하며 집들을 연결하는 길의 최소 유지비의 합)

    /*
     * Edge(두 마을을 연결하는 길의 정보를 담을 간선 클래스)
     */
    static class Edge implements Comparable<Edge> {
        int A, B, C;

        public Edge(int A, int B, int C) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.C, other.C);
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pq.add(new Edge(A, B, C));
        }
    }

    /*
     * 크루스칼 알고리즘 수행
     */
    static void kruskal() {
        int usedEdge = 0; // usedEdge(사용한 간선의 개수)
        int max_value = Integer.MIN_VALUE; // max_value(가장 큰 유지비)
        while (usedEdge < N - 1) {
            Edge now = pq.poll();
            if (find(now.A) != find(now.B)) { // 사이클이 발생하지 않을 경우 연결
                union(now.A, now.B);
                answer += now.C;
                max_value = Math.max(max_value, now.C);
                usedEdge++;
            }
        }
        answer -= max_value; // 마지막 집 사이의 유지비는 두 마을로 분리하면 사라지게 되므로 이는 합산하지 않음
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
     * 두 마을로 분리하며 집들을 연결하는 길의 최소 유지비의 합 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        kruskal();
        print();
    }
}