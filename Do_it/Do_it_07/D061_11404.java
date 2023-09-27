package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11404) 플로이드
 */
public class D061_11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(도시 개수)
        int N = Integer.parseInt(br.readLine());
        // M(노선 개수)
        int M = Integer.parseInt(br.readLine());
        // distance(노선 데이터를 저장하는 인접 행렬)
        int distance[][] = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 시작 도시와 종료 도시가 같으면 0
                if (i == j)
                    distance[i][j] = 0;
                // 아니면 충분히 큰 수로 저장하기
                else
                    distance[i][j] = 10000001;
            }
        }
        // 노선 데이터를 distance 행렬에 저장하기
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (distance[s][e] > v)
                distance[s][e] = v;
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // distance([i][j])에 distance[i][k] + distance[k][j] 값들 중 최솟값 넣기
                    // Math.min(D[I][J], D[I][K] + D[K][J])
                    if (distance[i][j] > distance[i][k] + distance[k][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
                }
            }
        }
        // 정답 배열 출력하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == 10000001)
                    System.out.print("0 ");
                else
                    System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }
}
