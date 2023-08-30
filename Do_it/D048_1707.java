package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 1707) 이분_그래프
 */
public class D048_1707 {
    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean[] visited;
    static boolean IsEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(테스트 케이스)
        int N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {
            String[] s = br.readLine().split(" ");
            // V(노드 개수)
            int V = Integer.parseInt(s[0]);
            // E(엣지 개수)
            int E = Integer.parseInt(s[1]);
            // A(그래프 데이터 저장 인접 리스트)
            A = new ArrayList[V + 1];
            // isited(방문 기록 저장 배열)
            visited = new boolean[V + 1];
            // check(이분 그래프 체크 배열)
            check = new int[V + 1];
            IsEven = true;
            for (int i = 1; i <= V; i++) {
                // A 인접 리스트의 각 ArrayList 초기화하기
                A[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < E; i++) {
                // 인접 리스트로 그래프 저장하기
                s = br.readLine().split(" ");
                int Start = Integer.parseInt(s[0]);
                int End = Integer.parseInt(s[1]);
                A[Start].add(End);
                A[End].add(Start);
            }
            // 각 노드에서 DFS 실행 → 결과가 이분 그래프가 아니면 반복 종료
            for (int i = 1; i <= V; i++) {
                if (IsEven) {
                    DFS(i);
                } else {
                    break;
                }
            }
            // 이분 그래프 여부를 정답으로 출력하기
            if (IsEven) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static void DFS(int node) {
        // visited 배열에 현재 노드 방문 기록하기
        visited[node] = true;
        for (int i : A[node]) {
            // 인접한 노드는 같은 집합이 아니므로 이전 노드와 다른 집합으로 처리하기 (0, 1)
            if (!visited[i]) {
                check[i] = (check[node] + 1) % 2;
                DFS(i);
            } // 이미 방문한 노드가 현재 내 노드와 같은 집합이면 이분 그래프가 아님
            else if (check[node] == check[i]) {
                IsEven = false;
            }
        }
    }
}
