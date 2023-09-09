package Do_it;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 1260) DFS와_BFS
 */
public class D026_1260 {
    // visited(방문 기록 저장 배열)
    static boolean visited[];
    // A(그래프 데이터 저장 인접 리스트)
    static ArrayList<Integer>[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 개수)
        int N = sc.nextInt();
        // M(엣지 개수)
        int M = sc.nextInt();
        // Start(시작점)
        int Start = sc.nextInt();
        // A 인접 리스트의 각 ArrayList 초기화하기
        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < M; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
            A[E].add(S);
        }
        // 각 노드와 관련된 엣지를 정렬하기
        for (int i = 1; i <= N; i++) {
            Collections.sort(A[i]);
        }
        // visited 배열 초기화하기
        visited = new boolean[N + 1];
        // DFS(Start) 실행하기
        DFS(Start);
        System.out.println();
        // visited 배열 초기화하기
        visited = new boolean[N + 1];
        // BFS(Start) 실행하기
        BFS(Start);
        System.out.println();
    }

    public static void DFS(int Node) {
        // 현재 노드 출력하기
        System.out.print(Node + " ");
        // visited 배열에 현재 노드 방문 기록하기
        visited[Node] = true;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드로 DFS 실행하기
        for (int i : A[Node]) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    public static void BFS(int Node) {
        Queue<Integer> queue = new LinkedList<Integer>();
        // 큐 자료구조에 시작 노드 삽입하기
        queue.add(Node);
        // visited 배열에 현재 노드 방문 기록하기
        visited[Node] = true;
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기
            int now = queue.poll();
            // 가져온 노드 출력하기
            System.out.print(now + " ");
            // 현재 노드의 연결 노드 중 방문하지 않은 노드를 큐에 삽입하고 방문 배열에 기록하기
            for (int i : A[now]) {
                if (!visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
