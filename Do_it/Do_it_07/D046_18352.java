package Do_it.Do_it_07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Queue;

/**
 * 18352) 특정_거리의_도시_찾기
 */
public class D046_18352 {
    static int visited[];
    static ArrayList<Integer>[] A;
    static int N, M, K, X;
    static List<Integer> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 개수)
        N = sc.nextInt();
        // M(엣지 개수)
        M = sc.nextInt();
        // K(목표 거리)
        K = sc.nextInt();
        // X(시작점)
        X = sc.nextInt();
        // A(그래프 데이터 저장 인접 리스트)
        A = new ArrayList[N + 1];
        // answer(정답 리스트)
        answer = new ArrayList<>();
        // A 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < M; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
        }
        // visited(방문 거리 저장 배열)
        visited = new int[N + 1];
        // visited 배열 초기화하기
        for (int i = 0; i <= N; i++) {
            visited[i] = -1;
        }
        // BFS(X) 실행하기
        BFS(X);
        // 방문 거리가 K인 노드의 숫자를 정답 배열에 더하기
        for (int i = 0; i <= N; i++) {
            if (visited[i] == K) {
                answer.add(i);
            }
        }
        // 정답 배열 오름차순 정렬해 출력하기
        if (answer.isEmpty()) {
            System.out.println("-1");
        } else {
            Collections.sort(answer);
            for (int temp : answer) {
                System.out.println(temp);
            }
        }
    }

    // BFS 구현하기
    public static void BFS(int Node) {
        // 큐 자료구조에 출발 노드 더하기
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(Node);
        // visited 배열에 현재 노드 방문 기록하기
        visited[Node]++;
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기
            int now = queue.poll();
            // 현재 노드의 연결 노드 중 방문하지 않은 노드로
            for (int i : A[now]) {
                if (visited[i] == -1) {
                    // visited 배열에 방문 거리 기록하기 (이전 노드의 방문 노드 거리 + 1)
                    visited[i] = visited[now] + 1;
                    // 큐에 데이터 삽입
                    queue.add(i);
                }
            }
        }
    }
}
