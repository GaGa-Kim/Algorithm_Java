package Programmers.Kit.BruteForce;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 86971) 전력망을_둘로_나누기
 */
public class K006_86971 {
    static int answer;
    static ArrayList<Integer>[] A;

    // n(송전탑의 개수)
    // wires(전선 정보)
    public int solution(int n, int[][] wires) {
        // answer(두 전력망이 가지고 있는 송전탑 개수의 차이)
        answer = Integer.MAX_VALUE;
        // A(그래프 데이터 저장 인접 리스트)
        A = new ArrayList[n + 1];
        // A 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 1; i <= n; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < wires.length; i++) {
            A[wires[i][0]].add(wires[i][1]);
            A[wires[i][1]].add(wires[i][0]);
        }
        // 전선들 중 하나를 삭제하며 BFS 탐색하기
        for (int i = 0; i < wires.length; i++) {
            // A[i] 제거
            A[wires[i][0]].remove(Integer.valueOf(wires[i][1]));
            A[wires[i][1]].remove(Integer.valueOf(wires[i][0]));
            // dfs(1)
            bfs(n, 1);
            // A[i] 추가
            A[wires[i][0]].add(wires[i][1]);
            A[wires[i][1]].add(wires[i][0]);
        }
        // answer 반환
        return answer;
    }

    // bfs
    private void bfs(int n, int node) {
        // count(지나온 송전탑의 개수)
        int count = 0;
        // visited(방문 유무 배열)
        boolean[] visited = new boolean[n + 1];
        // 큐 자료구조에 현재 노드 방문 기록하기
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;
        count++;
        // 큐가 빌 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기(poll)
            int now = queue.poll();
            // 현재 노드의 연결 노드 중
            for (int i : A[now]) {
                // 방문하지 않았다면
                if (visited[i] != true) {
                    // 큐에 데이터 삽입(add 연산)
                    queue.add(i);
                    // 방문 유무 갱신하기
                    visited[i] = true;
                    // count 증가
                    count++;
                }
            }
        }
        // 두 전력망의 차이가 가장 적을 때로 갱신
        answer = Math.min(answer, Math.abs(n - 2 * count));
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K006_86971 solution = new K006_86971();

        int n = 9;
        int[][] wires = { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } };
        int result = solution.solution(n, wires);

        System.out.println(result);
    }
}
