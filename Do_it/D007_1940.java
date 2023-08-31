package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1940) 주몽
 */
public class D007_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(재료의 개수) 저장하기
        int N = Integer.parseInt(br.readLine());
        // M(갑옷이 되는 번호) 저장하기
        int M = Integer.parseInt(br.readLine());
        // 재료 배열 저장하기
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        // 재료 배열 정렬하기
        Arrays.sort(A);
        int count = 0;
        int i = 0;
        int j = N - 1;
        while (i < j) {
            if (A[i] + A[j] < M) {
                // 작은 번호 재료를 한 칸 위로 변경하기
                i++;
            } else if (A[i] + A[j] > M) {
                // 큰 번호 재료를 한 칸 아래로 변경하기
                j--;
            } else {
                // 경우의 수 증가, 양쪽 index 각각 변경하기
                count++;
                i++;
                j--;
            }
        }
        // count 출력하기
        System.out.println(count);
        br.close();
    }
}
