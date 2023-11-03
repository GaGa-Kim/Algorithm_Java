package Programmers.Kit.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 49189) 가장_먼_노드
 */
public class K001_49189 {
    // A(그래프 데이터 저장 인접 리스트)
    static ArrayList<Integer>[] A;
    // visited(방문 거리 저장 배열)
    static int visited[];
    // depth(가장 먼 거리)
    static int depth;

    // n(노드의 개수)
    // edge(간선의 정보가 담긴 2차원 배열)
    public int solution(int n, int[][] edge) {
        // A 인접 리스트의 각 ArrayList 초기화하기
        A = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < edge.length; i++) {
            A[edge[i][0]].add(edge[i][1]);
            A[edge[i][1]].add(edge[i][0]);
        }
        // visited 배열 초기화하기
        visited = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            visited[i] = -1;
        }
        // depth 초기화하기
        depth = 1;
        // BFS(X) 실행하기
        BFS(1);
        // answer(가장 멀리 떨어진 노드의 개수)
        int answer = 0;
        // 방문 거리가 depth인 노드가 있다면 answer 증가
        for (int i = 0; i <= n; i++) {
            if (visited[i] == depth) {
                answer += 1;
            }
        }
        // answer 리턴
        return answer;
    }

    // BFS
    private void BFS(int Node) {
        // 큐 자료구조에 출발 노드 더하기(add 연산)
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        // visited 배열에 현재 노드 방문 기록하기
        visited[Node]++;
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기(poll 연산)
            int now = queue.poll();
            // 현재 노드의 연결 노드 중 방문하지 않은 노드로
            for (int i : A[now]) {
                if (visited[i] == -1) {
                    // 이전 노드의 방문 노드 거리 + 1
                    visited[i] = visited[now] + 1;
                    // depth 갱신
                    depth = Math.max(depth, visited[i]);
                    // 큐에 데이터 삽입(add 연산)
                    queue.add(i);
                }
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K001_49189 solution = new K001_49189();

        int n = 6;
        int[][] edge = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };

        int result = solution.solution(n, edge);

        System.out.println(result);
    }
}
