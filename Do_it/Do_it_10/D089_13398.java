package Do_it.Do_it_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 13398) 연속합2
 */
public class D089_13398 {
    static int N;
    static int[] A;
    static int[] L;
    static int[] R;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(배열 크기)
        N = Integer.parseInt(br.readLine());
        // A(수열 데이터 저장하기 배열)
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        // A 배열 저장하기
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        // L(왼쪽에서 오른쪽 방향으로 index를 포함한 최대 연속 합을 나타내는 배열)
        L = new int[N];
        L[0] = A[0];
        result = L[0];
        // 오른쪽 방향으로 index를 포함한 최대 연속 합 구하기
        for (int i = 1; i < N; i++) {
            L[i] = Math.max(A[i], L[i - 1] + A[i]);
            // L 배열의 최댓값을 정답 변수에 저장하기
            result = Math.max(result, L[i]); // 1개도 삭제하지 않았을 때 최댓값
        }
        // R(오른쪽에서 왼쪽 방향으로 index를 포함한 최대 연속 합을 나타내는 배열)
        R = new int[N];
        R[N - 1] = A[N - 1];
        // 왼쪽 방향으로 index를 포함한 최대 연속 합 구하기
        for (int i = N - 2; i >= 0; i--) {
            R[i] = Math.max(A[i], R[i + 1] + A[i]);
        }
        // L[i - 1] + R[i + 1] 2개의 구간 합 배열을 더하면 i번쩨 값을 제거한 효과
        for (int i = 1; i < N - 1; i++) {
            // 기존 정답 변수에 L[i - 1] + R[i + 1]로 계산한 값 중 최댓값
            int temp = L[i - 1] + R[i + 1];
            // 삭제하지 않았을 때와 삭제했을 때의 최댓값 비교
            result = Math.max(result, temp);
        }
        // 최댓값 출력하기
        System.out.println(result);
    }
}
