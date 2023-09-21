package Do_it.Do_it_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 11724) 연결_요소의_개수
 */
public class D023_11724 {
    static ArrayList<Integer>[] A;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // n(노드 개수) m(에지 개수)
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        // A(그래프 데이터 저장 인접 리스트)
        A = new ArrayList[n + 1];
        // visited(방문 기록 저장 배열)
        visited = new boolean[n + 1];
        // A 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 1; i < n + 1; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e); // 양방향 엣지이므로 양쪽에 엣지를 더하기
            A[e].add(s);
        }
        int count = 0;
        for (int i = 1; i < n + 1; i++) {
            // 방문하지 않은 노드가 있다면
            if (!visited[i]) {
                // 연결 요소 개수++
                count++;
                // DFS 실행하기
                DFS(i);
            }
        }
        // 연결 요소의 개수를 출력하기
        System.out.println(count);
    }

    static void DFS(int v) {
        // 현재노드 == 방문노드
        if (visited[v]) {
            return;
        }
        // visited 배열에 현재 노드 방문 기록하기
        visited[v] = true;
        // 현재 노드의 연결 노드 중 방문하지 않은 노드로 DFS 실행하기 (재귀 함수 형태)
        for (int i : A[v]) {
            if (visited[i] == false) {
                DFS(i);
            }
        }
    }
}