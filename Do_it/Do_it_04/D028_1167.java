package Do_it.Do_it_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 1167) 트리의_지름
 */
public class D028_1167 {
    // visited(방문 기록 저장 배열)
    static boolean visited[];
    // distance(거리 저장 배열)
    static int[] distance;
    // A(그래프 데이터 저장 인접 리스트)
    static ArrayList<Edge>[] A;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 개수)
        int N = sc.nextInt();
        // A 인접 리스트의 각 ArrayList 초기화하기
        A = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Edge>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < N; i++) {
            int S = sc.nextInt();
            while (true) {
                int E = sc.nextInt();
                if (E == -1)
                    break;
                int V = sc.nextInt();
                A[S].add(new Edge(E, V));
            }
        }
        // visited 배열 초기화하기
        visited = new boolean[N + 1];
        // distance 배열 초기화하기
        distance = new int[N + 1];
        // BFS(임의의 점을 시작점으로) 실행하기
        BFS(1);
        // distance 배열에서 가장 큰 값을 지니고 있는 노드를 시작점으로 지정하기
        int Max = 1;
        for (int i = 2; i <= N; i++) {
            if (distance[Max] < distance[i])
                Max = i;
        }
        // visited 배열 초기화하기
        visited = new boolean[N + 1];
        // distance 배열 초기화하기
        distance = new int[N + 1];
        // BFS(새로운 시작점으로) 실행하기
        BFS(Max);
        // distance 배열에서 가장 큰 수를 정답으로 출력하기
        Arrays.sort(distance);
        System.out.println(distance[N]);
    }

    public static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        // 큐 자료구조에 시작 노드 삽입하기
        queue.add(index);
        // visited 배열에 현재 노드 방문 기록하기
        visited[index] = true;
        while (!queue.isEmpty()) {
            // 큐에 노드 데이터를 가져오기
            int now = queue.poll();
            // 현재 노드의 연결 노드 중 방문하지 않은 노드로
            for (Edge i : A[now]) {
                int e = i.e;
                int v = i.value;
                // 큐에 데이터 삽입하고 visited 배열에 방문 기록하기
                if (!visited[e]) {
                    visited[e] = true;
                    queue.add(e);
                    // 현재 노드의 거리 + 엣지의 가중치로 방문하지 않은 노드 거리 배열 업데이트하기
                    distance[e] = distance[now] + v;
                }
            }
        }
    }
}

class Edge {
    // e(목적지 노드)
    int e;
    // value(엣지의 가중치)
    int value;

    public Edge(int e, int value) {
        this.e = e;
        this.value = value;
    }
}
