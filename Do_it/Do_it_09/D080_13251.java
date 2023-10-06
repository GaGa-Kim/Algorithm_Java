package Do_it.Do_it_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 13251) 조약돌_꺼내기
 */
public class D080_13251 {
    static int T, M, K;
    // probability(색깔별 확률 저장하기 배열)
    static double probability[] = new double[51];
    // D(각 색깔별 조약돌 개수 저장하기 배열)
    static int D[] = new int[51];
    static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // M(색의 종류)
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // T(전체 조약돌 개수)
        T = 0;
        for (int i = 0; i < M; i++) {
            // D 배열에 각 조약돌 개수 저장하기
            D[i] = Integer.parseInt(st.nextToken());
            // T 변수에 조약돌의 개수 더하기
            T += D[i];
        }
        st = new StringTokenizer(br.readLine());
        // K(선택 조약돌 개수)
        K = Integer.parseInt(st.nextToken());
        answer = 0.0;
        for (int i = 0; i < M; i++) {
            // 현재 색깔의 조약돌의 개수가 선택해야 할 개수보다 크면
            if (D[i] >= K) {
                probability[i] = 1.0;
                for (int k = 0; k < K; k++) {
                    // i 색깔을 뽑을 확률 = i 색깔을 뽑을 확률 * 현재 색깔 개수 - K / 전체 색깔 개수 - K;
                    probability[i] *= (double) (D[i] - k) / (T - k);
                }
                // 정답에 현재 색깔을 모두 뽑을 확률 더하기
                answer += probability[i];
            }
        }
        // 정답 출력하기
        System.out.println(answer);
    }
}
