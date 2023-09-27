package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1389) 케빈_베이컨의_6단계_법칙
 */
public class D063_1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(유저 수)
        int N = Integer.parseInt(st.nextToken());
        // M(친구 관계 수)
        int M = Integer.parseInt(st.nextToken());
        // distance(친구 관계 데이터를 저장하는 인접 행렬)
        int distance[][] = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 시작 친구와 종료 친구가 같으면(자기 자신이면) 0
                if (i == j)
                    distance[i][j] = 0;
                // 아니면 충분히 큰 수로 저장하기
                else
                    distance[i][j] = 10000001;
            }
        }
        // 친구 관계 데이터를 distance 행렬에 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            // 친구 관계는 서로 관계를 맺는 것이므로 양방향 엣지로 저장하고 가중치를 1로 함
            distance[s][e] = 1;
            distance[e][s] = 1;
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
        // MIN(충분히 큰 수로 초기화)
        int Min = Integer.MAX_VALUE;
        int Answer = -1;
        // 각 배열의 값을 합치기 (i의 케빈 베이컨의 수)
        for (int i = 1; i <= N; i++) {
            int temp = 0;
            for (int j = 1; j <= N; j++) {
                temp = temp + distance[i][j];
            }
            if (Min > temp) {
                // MIN값을 i의 케빈 베이컨의 수로 저장하기
                Min = temp;
                Answer = i;
            }
        }
        // 가장 작은 케빈 베이컨의 수를 지니고 있는 i 출력하기
        System.out.println(Answer);
    }
}
