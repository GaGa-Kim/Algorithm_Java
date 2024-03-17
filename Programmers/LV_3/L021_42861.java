package Programmers.LV_3;

import java.util.PriorityQueue;

/**
 * 42861) 섬_연결하기
 */
public class L021_42861 {
    class Edge implements Comparable<Edge> {
        // start(출발 노드)
        int start;
        // end(종료 노드)
        int end;
        // value(가중치)
        int value;

        Edge(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        // 가중치가 오름차순 정렬되도록 compareTo 함수 구현하기
        @Override
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }

    static int[] parent;
    static PriorityQueue<Edge> queue;

    // n(섬의 개수)
    // costs(n개의 섬 사이에 다리를 건설하는 비용)
    public int solution(int n, int[][] costs) {
        // parent(대표 노드 저장 배열)
        parent = new int[n + 1];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // queue(엣지 정보를 저장할 우선순위 큐)
        queue = new PriorityQueue<>();
        // queue에 엣지 정보 저장하기
        for (int i = 0; i < costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int value = costs[i][2];
            queue.add(new Edge(start, end, value));
        }
        // useEdge(사용한 엣지 수)
        int useEdge = 0;
        // answer(모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용)
        int answer = 0;
        // 사용한 엣지 수가 노드 - 1이 될 때까지
        while (useEdge < n - 1) {
            // 큐에서 엣지 정보 가져오기
            Edge now = queue.poll();
            // 엣지 시작점에서 끝점의 부모 노드가 다르면 (연결해도 사이클이 생기지 않으면)
            if (find(now.start) != find(now.end)) {
                // union 연산 수행하기
                union(now.start, now.end);
                // 엣지의 가중치를 answer에 더하기
                answer = answer + now.value;
                // 사용자 엣지 수 증가
                useEdge++;
            }
        }
        // answer 반환
        return answer;
    }

    // union 연산
    public static void union(int a, int b) {
        // a와 b의 대표 노드 찾기
        a = find(a);
        b = find(b);
        // 두 원소의 대표 노드끼리 연결하기
        if (a != b) {
            parent[b] = a;
        }
    }

    // find 연산
    public static int find(int a) {
        // a가 대표 노드면 리턴하기
        if (a == parent[a])
            return a;
        // 아니면 a의 대표 노드값을 find(parent[a]) 값으로 저장 (재귀 함수 형태)
        else
            return parent[a] = find(parent[a]);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L021_42861 solution = new L021_42861();

        int n = 4;
        int[][] costs = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };

        int result = solution.solution(n, costs);

        System.out.println(result);
    }
}