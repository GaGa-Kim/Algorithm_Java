package SWEA.Basic.Day10;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 1238) Contact
 */
public class D002_1238 {
    // A(그래프 데이터 저장 인접 리스트)
    static ArrayList<Integer>[] A;
    // visited(방문 거리 저장 배열)
    static int visited[];
    // depth(가장 먼 거리)
    static int depth;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // N(입력 받는 데이터의 길이)
            int N = sc.nextInt();
            // S(시작점)
            int S = sc.nextInt();
            // A 인접 리스트의 각 ArrayList 초기화하기
            A = new ArrayList[101];
            for (int i = 0; i <= 100; i++) {
                A[i] = new ArrayList<Integer>();
            }
            // A 인접 리스트에 그래프 데이터 저장하기
            for (int i = 0; i < N / 2; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                A[start].add(end);
            }
            // visited 배열 초기화하기
            visited = new int[101];
            for (int i = 0; i <= 100; i++) {
                visited[i] = -1;
            }
            // depth 초기화하기
            depth = 1;
            // BFS(S) 실행하기
            BFS(S);
            // answer(가장 큰 숫자)
            int answer = 0;
            for (int i = 0; i <= 100; i++) {
                // 가장 먼 거리와 동일하다면
                if (visited[i] == depth) {
                    // 가장 큰 숫자로 갱신
                    answer = Math.max(answer, i);
                }
            }
            // #T와 answer 반환
            System.out.println("#" + test_case + " " + answer);
        }
    }

    // BFS
    private static void BFS(int Node) {
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
}
