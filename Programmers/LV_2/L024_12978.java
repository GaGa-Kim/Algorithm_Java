package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 12978) 배달
 */
public class L024_12978 {
    // Node 클래스
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

    // 자료구조 선언하기(그래프 정보 저장, 최단 거리 저장, 노드 사용 여부 저장)
    static ArrayList<Node>[] list;
    static int[] distance;
    static boolean[] visited;

    // N(마을의 개수)
    // road(도로 정보의 개수)
    // K(음식 배달이 가능한 시간)
    public int solution(int N, int[][] road, int K) {
        // answer(배달이 가능한 마을의 개수)
        int answer = 0;
        // 그래프 정보를 저장하는 인접 리스트 초기화하기
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Node>();
        }
        // 인접 리스트 배열에 엣지 정보를 저장하기
        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }
        // 거리 배열은 충분히 큰 수로 초기화하기
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        // 방문 배열 초기화하기
        visited = new boolean[N + 1];
        // 시작점인 1번 마을을 기준으로 다익스트라 수행
        dijkstra(1);
        // 1번 마을부터 시작하여 N번 마을까지 배달 가능한지 확인
        for (int i = 1; i <= N; i++) {
            // 최단 거리 배열이 K보다 작거나 같다면
            if (distance[i] <= K)
                // answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 다익스트라 함수
    private void dijkstra(int start) {
        // 시작점을 오름차순 우선순위 큐에 넣고 시작
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;
        // 큐가 빌 때까지
        while (!pq.isEmpty()) {
            // now(현재 선택된 노드의 전체 정보)
            Node now = pq.poll();
            // now_node(현재 선택된 노드가 가리키는 노드 정보)
            int now_node = now.targetNode;
            // now_node를 방문한 적이 없다면
            if (!visited[now_node]) {
                // now_node를 방문 노드로 업데이트하기
                visited[now_node] = true;
                // now_node와 연결된 엣지 개수만큼 탐색
                for (Node next : list[now_node]) {
                    // next_node(now_node와 연결된 노드가 가리키는 노드 정보)
                    int next_node = next.targetNode;
                    // next_node가 방문 전이며
                    // now_node 최단 거리 + value < next_node의 최단 거리라면
                    if (!visited[next_node] && distance[next_node] > distance[now_node] + next.value) {
                        // next_node 최단 거리 업데이트하기
                        distance[next_node] = distance[now_node] + next.value;
                        // 우선순위 큐에 타깃 노드 추가하기
                        pq.add(new Node(next_node, distance[next_node]));
                    }
                }
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L024_12978 solution = new L024_12978();

        int N = 5;
        int[][] road = { { 1, 2, 1 }, { 2, 3, 3 }, { 5, 2, 2 }, { 1, 4, 2 }, { 5, 3, 1 }, { 5, 4, 2 } };
        int K = 3;
        int result = solution.solution(N, road, K);

        System.out.println(result);
    }
}
