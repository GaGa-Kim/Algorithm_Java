package Do_it.Do_it_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2098) 외판원_순회
 */
public class D095_2098 {
    static int N;
    static int[][] W;
    static int[][] D;
    static final int INF = 1000000 * 16 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(도시 개수)
        N = Integer.parseInt(br.readLine());
        // W[i][j](i 도시에서 j 도시로 가는데 드는 비용을 저장하는 배열)
        W = new int[16][16];
        // D[c][v](현재 도시가 c, 현재까지 방문한 도시 리스트가 v일 때 앞으로 남은 모든 도시를 경유하는데 필요한 최소 비용)
        D = new int[16][1 << 16];
        // W 배열에 값 저장하기
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 정답 출력하기
        System.out.println(tsp(0, 1));
    }

    // 모든 경우의 수와 관련된 완전 탐색과 재귀 구현하기
    private static int tsp(int c, int v) {
        // 모든 도시를 방문할 때
        if (v == (1 << N) - 1)
            // 시작 도시로 돌아갈 수 있을 때 return W[c][시작 도시]
            // 시작 도시로 돌아갈 수 없을 때 return 무한대
            return W[c][0] == 0 ? INF : W[c][0];
        // 이미 계산한 적이 있을 때
        if (D[c][v] != 0)
            // 바로 리턴
            return D[c][v];
        int min = INF;
        for (int i = 0; i < N; i++) {
            // 방문한 적이 없고, 갈 수 있는 도시일 때
            if ((v & (1 << i)) == 0 && W[c][i] != 0) {
                min = Math.min(min, tsp(i, (v | (1 << i))) + W[c][i]);
            }
        }
        D[c][v] = min;
        return D[c][v];
    }
}
