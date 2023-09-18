package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1325) 효율적인_해킹
 */
public class D047_1325 {
    static int N, M;
    static boolean visited[];
    static int answer[];
    static ArrayList<Integer> A[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(노드 개수)
        N = Integer.parseInt(st.nextToken());
        // M(엣지 개수)
        M = Integer.parseInt(st.nextToken());
        // A(그래프 데이터 저장 인접 리스트)
        A = new ArrayList[N + 1];
        // answer(정답 배열)
        answer = new int[N + 1];
        // A 인접 리스트의 각 ArrayList 초기화하기
        for (int i = 1; i <= N; i++) {
            A[i] = new ArrayList<>();
        }
        // A 인접 리스트에 그래프 데이터 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(E);
        }
        for (int i = 1; i <= N; i++) {
            // visited(방문 유무 저장 배열) 초기화하기
            visited = new boolean[N + 1];
            // BFS(i) 실행하기
            BFS(i);
        }
        // answer 배열에서 가장 큰 수 찾기
        int maxVal = 0;
        for (int i = 1; i <= N; i++) {
            maxVal = Math.max(maxVal, answer[i]);
        }
        // answer 배열에서 maxVal와 같은 값을 가진 index를 정답으로 출력하기
        for (int i = 1; i <= N; i++) {
            if (answer[i] == maxVal) {
                System.out.print(i + " ");
            }
        }
    }

    // BFS 구현하기
    public static void BFS(int index) {
        // 큐 자료구조에 출발 노드 더하기
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        // visited 배열에 현재 노드 방문 기록하기
        visited[index] = true;
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기
            int now = queue.poll();
            // 현재 노드의 연결 노드 중 방문하지 않은 노드로
            for (int i : A[now]) {
                if (visited[i] == false) {
                    // visited 배열에 방문 기록하기
                    visited[i] = true;
                    // 신규 노드 인덱스의 정답 배열 값을 증가시키기
                    answer[i]++;
                    // 큐에 데이터 삽입
                    queue.add(i);
                }
            }
        }
    }
}
