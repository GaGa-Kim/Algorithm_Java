package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1916) 최소비용_구하기
 */
public class D057_1916 {
    static int N, M;
    // 그래프 정보 저장
    static ArrayList<Node>[] list;
    // 최단 거리 저장
    static int[] distance;
    // 노드 사용 여부 저장
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // N(노드 수)
        N = Integer.parseInt(br.readLine());
        // M(엣지 수)
        M = Integer.parseInt(br.readLine());
        // 방문 배열 초기화하기
        visited = new boolean[N + 1];
        // 그래프 정보를 저장하는 인접 리스트 초기화하기
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Node>();
        }
        // 거리 배열은 충분히 큰 수로 초기화하기
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        // 인접 리스트 배열에 엣지 정보를 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
        }
        st = new StringTokenizer(br.readLine());
        // startIndex(시작점)
        int startIndex = Integer.parseInt(st.nextToken());
        // endIndex(도착점)
        int endIndex = Integer.parseInt(st.nextToken());
        // 시작점을 기준으로 다익스트라 수행
        bw.write(dijkstra(startIndex, endIndex) + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // 다익스트라 알고리즘
    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작점을 오름차순 우선순위 큐에 넣고 시작하기
        pq.add(new Node(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int now_node = now.targetNode;
            // 현재 선택된 노드가 방문된 적이 있는지 확인하기
            if (!visited[now_node]) {
                // 현재 노드를 방문 노드로 업데이트하기
                visited[now_node] = true;
                // 현재 선택 노드의 엣지
                for (Node next : list[now_node]) {
                    // 타깃 노드 방문 전 && 현재 선택 노드 최단 거리 + 비용 < 타깃 노드의 최단 거리
                    if (!visited[next.targetNode] && distance[next.targetNode] > distance[now_node] + next.value) {
                        // 타깃 노드 최단 거리 업데이트하기
                        distance[next.targetNode] = distance[now.targetNode] + next.value;
                        // 우선순위 큐에 타깃 노드 추가하기
                        pq.add(new Node(next.targetNode, distance[next.targetNode]));
                    }
                }
            }
        }
        return distance[end];
    }
}

class Node implements Comparable<Node> {
    // targetNode(가리키는 노드)
    int targetNode;
    // value(엣지의 가중치)
    int value;

    Node(int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }

    // 우선순위 큐 정렬 기준을 위해 compareTo 함수 구현하기
    public int compareTo(Node o) {
        if (this.value > o.value)
            return 1;
        else
            return -1;
    }
}