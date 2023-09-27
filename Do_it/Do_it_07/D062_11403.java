package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11403) 경로_찾기
 */
public class D062_11403 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(인접 행렬의 크기)
        int N = Integer.parseInt(br.readLine());
        // distance(노선 데이터를 저장하는 인접 행렬)
        int distance[][] = new int[N + 1][N + 1];
        // 인접 행렬 데이터를 distance 행렬에 그대로 저장하기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());
                distance[i][j] = v;
            }
        }
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 만약 distance[i][k] == 1이고, distance[k][j] == 1이면
                    if (distance[i][k] == 1 && distance[k][j] == 1)
                        // distance[i][j] = 1로 저장
                        distance[i][j] = 1;
                }
            }
        }
        // distance 배열 출력하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }
}
