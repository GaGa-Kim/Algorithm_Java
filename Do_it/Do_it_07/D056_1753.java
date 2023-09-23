package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1753) 최단경로
 */
public class D056_1753 {
    static int V, E, K;
    // 최단 거리 저장
    static int distance[];
    // 노드 사용 여부 저장
    static boolean visited[];
    // 그래프 정보 저장
    static ArrayList<Edge> list[];
    // 우선순위 큐
    static PriorityQueue<Edge> q = new PriorityQueue<Edge>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // V(노드 개수)
        V = Integer.parseInt(st.nextToken());
        // E(엣지 개수)
        E = Integer.parseInt(st.nextToken());
        // K(출발 노드)
        K = Integer.parseInt(br.readLine());
        // 방문 배열 초기화하기
        visited = new boolean[V + 1];
        // 그래프 정보를 저장하는 인접 리스트 초기화하기
        list = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            list[i] = new ArrayList<Edge>();
        }
        // 거리 배열은 충분히 큰 수로 초기화하기
        distance = new int[V + 1];
        for (int i = 0; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        // 인접 리스트 배열에 엣지 정보를 저장하기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Edge(v, w));
        }
        // 출발 노드는 우선순위 큐에 넣고 시작하기
        q.add(new Edge(K, 0));
        distance[K] = 0;
        while (!q.isEmpty()) {
            Edge now = q.poll();
            int now_vertex = now.vertex;
            // 현재 선택된 노드가 방문된 적이 있는지 확인하기
            if (visited[now_vertex])
                continue; // 이미 방문한 적이 있다면 다시 큐에 넣지 않음
            // 현재 노드를 방문 노드로 업데이트하기
            visited[now_vertex] = true;
            // 현재 선택 노드의 엣지
            for (int i = 0; i < list[now_vertex].size(); i++) {
                Edge next = list[now_vertex].get(i);
                int next_vertex = next.vertex;
                int next_value = next.value;
                // 현재 선택 노드 최단 거리 + 비용 < 타깃 노드의 최단 거리
                if (distance[next_vertex] > distance[now_vertex] + next_value) {
                    // 타깃 노드 최단 거리 업데이트하기
                    distance[next_vertex] = next_value + distance[now_vertex];
                    // 우선순위 큐에 타깃 노드 추가하기
                    q.add(new Edge(next_vertex, distance[next_vertex]));
                }
            }
        }
        // 거리 배열 출력하기
        for (int i = 1; i <= V; i++) {
            // 경로가 존재할 경우
            if (visited[i])
                // 최단 경로의 경로값 출력
                System.out.println(distance[i]);
            // 경로가 존재하지 않을 경우
            else
                System.out.println("INF");
        }
    }
}

class Edge implements Comparable<Edge> {
    // vertex(가리키는 노드)
    int vertex;
    // value(엣지의 가중치)
    int value;

    Edge(int vertex, int value) {
        this.vertex = vertex;
        this.value = value;
    }

    // 우선순위 큐 정렬 기준을 위해 compareTo 함수 구현하기
    public int compareTo(Edge e) {
        if (this.value > e.value)
            return 1;
        else
            return -1;
    }
}