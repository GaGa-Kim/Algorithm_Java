package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1253) 좋다
 */
public class D008_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(수의 개수)
        int N = Integer.parseInt(br.readLine());
        int Result = 0;
        // A(수 데이터 저장 배열)
        long A[] = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        // A 배열에 데이터 저장하기
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        // A 배열 정렬하기
        Arrays.sort(A);
        for (int k = 0; k < N; k++) {
            // 찾고자 하는 값 find
            long find = A[k];
            // 투 포인터
            int i = 0;
            int j = N - 1;
            while (i < j) {
                if (A[i] + A[j] == find) {
                    // 두 포인터 i, j가 k가 아닐 때만 결괏값에 반영 및 while문 종료하기
                    if (i != k && j != k) {
                        Result++;
                        break;
                    } else if (i == k) {
                        i++;
                    } else if (j == k) {
                        j--;
                    }
                } else if (A[i] + A[j] < find) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        System.out.println(Result);
        br.close();
    }
}
