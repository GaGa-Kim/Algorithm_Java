package Do_it.Do_it_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 11438) LCA_2
 */
public class D075_11438 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int kmax;
    static int[][] parent;
    static boolean[] visited;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(수의 개수)
        int N = Integer.parseInt(br.readLine());
        // tree(인접 리스트 자료구조)
        tree = new ArrayList[N + 1];
        // tree 인접 리스트의 각 ArrayList 초기화
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        // tree 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            tree[s].add(e);
            tree[e].add(s);
        }
        // depth(노드 깊이 배열)
        depth = new int[N + 1];
        // visited(방문 여부 저장하기 배열)
        visited = new boolean[N + 1];
        // kmax(최대 가능 높이) 구하기
        int temp = 1;
        kmax = 0;
        while (temp <= N) {
            temp <<= 1; // (1, 2, 4, 8, ... 2^k)
            kmax++;
        }
        // parent(노드 조상 배열)
        parent = new int[kmax + 1][N + 1]; // 2^k까지의 부모 노드 저장하기
        // depth 구하기
        BFS(1);
        // 점화식을 이용해 parent 구성하기
        for (int k = 1; k <= kmax; k++) {
            for (int n = 1; n <= N; n++) {
                parent[k][n] = parent[k - 1][parent[k - 1][n]];
            }
        }
        // M(질의 개수)
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // a(1번 노드)
            int a = Integer.parseInt(st.nextToken());
            // b(2번 노드)
            int b = Integer.parseInt(st.nextToken());
            // executeLCA(a와 b의 LCA를 구하는 함수 호출 및 결괏값 출력하기)
            int LCA = executeLCA(a, b);
            System.out.println(LCA);
        }
    }

    // Depth와 부모 노드 구하기
    private static void BFS(int node) {
        // 큐 자료구조에 출발 노드 더하기
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        // visited 배열에 현재 노드 방문 기록하기
        visited[node] = true;
        int level = 1;
        int now_size = 1;
        int count = 0;
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기
            int now = queue.poll();
            // 현재 노드의 연결 노드 중 방문하지 않은 노드로 반복하기
            for (int next : tree[now]) {
                if (!visited[next]) {
                    // 큐에 데이터 삽입하고 visited 배열에 방문 거리 기록하기
                    visited[next] = true;
                    queue.add(next);
                    // parent 배열에 자신의 부모 노드 저장하기
                    parent[0][next] = now;
                    // depth 배열에 현재 높이 저장하기
                    depth[next] = level;
                }
            }
            count++;
            // 이번 높이에 해당하는 모든 노드를 방문했을 때
            if (count == now_size) {
                count = 0;
                now_size = queue.size();
                // 현재 배열의 depth를 1개 증가
                level++;
            }
        }
    }

    // 공통 조상 구하기
    private static int executeLCA(int a, int b) {
        // 1번 노드가 depth가 더 작으면 1번 노드와 2번 노드 swap
        if (depth[a] > depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        // 두 노드의 depth를 동일하게 맞추기
        for (int k = kmax; k >= 0; k--) { // depth 빠르게 맞추기 (2의 제곱수 이동)
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[parent[k][b]]) {
                    b = parent[k][b];
                }
            }
        }
        // 두 노드가 같은 조상이 나올 때까지 각 노드를 부모 노드로 변경하는 작업을 반복하기
        for (int k = kmax; k >= 0; k--) { // 조상 빠르게 찾기 (2의 제곱수 이동)
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }
        int LCA = a;
        // 반복문이 종료된 후 이동한 두 노드가 같은 노드라면 해당 노드가 최소 공통 조상
        // 다른 노드라면 바로 위의 부모 노드가 최소 공통 조상
        if (a != b)
            LCA = parent[0][LCA];
        // 최소 공통 조상 리턴하기
        return LCA;
    }
}
