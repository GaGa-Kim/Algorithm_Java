package Programmers.LV_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 72413) 합승_택시_요금
 */
public class L043_72413 {
    static ArrayList<Node>[] list;
    static int MAX = 20000001;

    // n(지점의 개수)
    // s, a, b(출발지점, A의 도착지점, B의 도착지점)
    // fares(지점 사이의 예상 택시요금)
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // answer(최저 예상 택시요금)
        int answer = MAX;
        // 자료구조 선언하기(그래프 정보 저장, 최단 거리 저장)
        list = new ArrayList[n + 1];
        // 그래프 정보를 저장하는 인접 리스트 초기화하기
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<Node>();
        }
        // 인접 리스트 배열에 엣지 정보를 저장하기
        for (int i = 0; i < fares.length; i++) {
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];
            list[c].add(new Node(d, f));
            list[d].add(new Node(c, f));
        }
        // distanceS(출발지점에 따른 거리 배열)
        int[] distanceS = dijkstra(n, s); // s, a, b를 기준으로 다익스트라 수행
        // distanceA(A의 도착지점에 따른 거리 배열)
        int[] distanceA = dijkstra(n, a);
        // distanceB(B의 도착지점에 따른 거리 배열)
        int[] distanceB = dijkstra(n, b);
        // i까지 합승한 후 i부터 A와 B가 나뉘어 혼자 택시를 이용하는 택시요금이 더 작을 경우 answer을 갱신
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, distanceS[i] + distanceA[i] + distanceB[i]);
        }
        // answer 반환
        return answer;
    }

    // 다익스트라 함수
    private int[] dijkstra(int n, int start) {
        // 거리 배열을 충분히 큰 수로 초기화하기
        int[] distance = new int[n + 1];
        Arrays.fill(distance, MAX);
        // 시작점을 오름차순 우선순위 큐에 넣고 시작
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;
        // 큐가 빌 때까지
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            // 현재 선택 노드와 연결된 엣지 개수만큼 탐색
            for (Node next : list[now.targetNode]) {
                // 현재 선택 노드 최단 거리 + 비용 < 타깃 노드의 최단 거리라면
                if (distance[next.targetNode] > distance[now.targetNode] + next.cost) {
                    // 타깃 노드 최단 거리 업데이트하기
                    distance[next.targetNode] = distance[now.targetNode] + next.cost;
                    // 우선순위 큐에 타깃 노드 추가하기
                    pq.add(new Node(next.targetNode, distance[next.targetNode]));
                }
            }
        }
        return distance;
    }

    class Node implements Comparable<Node> {
        // targetNode(가리키는 노드)
        int targetNode;
        // cost(두 지점 간 예상 택시요금)
        int cost;

        Node(int targetNode, int cost) {
            this.targetNode = targetNode;
            this.cost = cost;
        }

        // 우선순위 큐 정렬 기준을 위해 compareTo 함수 구현하기
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L043_72413 solution = new L043_72413();

        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = { { 4, 1, 10 }, { 3, 5, 24 }, { 5, 6, 2 }, { 3, 1, 41 }, { 5, 1, 24 }, { 4, 6, 50 },
                { 2, 4, 66 }, { 2, 3, 22 }, { 1, 6, 25 } };

        int result = solution.solution(n, s, a, b, fares);

        System.out.println(result);
    }
}