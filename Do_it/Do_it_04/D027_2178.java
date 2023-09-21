package Do_it.Do_it_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2178) 미로_탐색
 */
public class D027_2178 {
    // dx, dy (상하좌우를 탐색하기 위한 define값 정의 변수)
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    // visited(방문 기록 저장 배열)
    static boolean[][] visited;
    // A(데이터 저장 2차원 행렬)
    static int[][] A;
    // N(row), M(column)
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // A 배열 초기화하기
        A = new int[N][M];
        // visited 배열 초기화하기
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                // A 배열에 데이터 저장하기
                A[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }
        // BFS(0, 0) 실행하기
        BFS(0, 0);
        // A[N-1][M-1] 값 출력하기
        System.out.println(A[N - 1][M - 1]);
    }

    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        // 큐 자료구조에 시작 노드 삽입하기(add 연산)
        queue.offer(new int[] { i, j });
        // visited 배열에 현재 노드 방문 기록하기
        visited[i][j] = true;
        // 큐가 비어 있을 때까지
        while (!queue.isEmpty()) {
            // 큐에서 노드 데이터를 가져오기(poll 연산)
            int now[] = queue.poll();
            for (int k = 0; k < 4; k++) {
                // 상하좌우 탐색
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                // 유효한 좌표
                if (x >= 0 && y >= 0 && x < N && y < M) {
                    // 이동할 수 있는 칸이면서 방문하지 않은 노드
                    if (A[x][y] != 0 && !visited[x][y]) {
                        // visited 배열에 방문 기록하기
                        visited[x][y] = true;
                        // A 배열에 depth를 현재 노드의 depth + 1로 업데이트하기
                        A[x][y] = A[now[0]][now[1]] + 1;
                        // 큐에 데이터 삽입하기(add 연산)
                        queue.add(new int[] { x, y });
                    }
                }
            }
        }
    }
}