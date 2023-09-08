package Do_it;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 13023) ABCDE
 */
public class D025_13023 {
    static boolean visited[];
    static ArrayList<Integer>[] A;
    static boolean arrive;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 개수)
        int N = sc.nextInt();
        // M(엣지 개수)
        int M = sc.nextInt();
        // A(그래프 데이터 저장 인접 리스트)
        A = new ArrayList[N];
        // visited(방문 기록 저장 배열)
        visited = new boolean[N];
        // arrive(도착 확인 변수)
        arrive = false;
        // A 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 0; i < N; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < M; i++) {
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
            A[E].add(S);
        }
        // 각 노드마다 DFS 실행하기
        for (int i = 0; i < N; i++) {
            DFS(i, 1);
            if (arrive)
                break;
        }
        if (arrive)
            System.out.println("1");
        else
            System.out.println("0");
    }

    public static void DFS(int now, int depth) {
        if (depth == 5 || arrive) {
            arrive = true;
            return;
        }
        // 방문 배열에 현재 노드 방문 기록하기
        visited[now] = true;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드로 DFS 실행하기
        for (int i : A[now]) {
            if (!visited[i]) {
                DFS(i, depth + 1);
            }
        }
        visited[now] = false;
    }
}
