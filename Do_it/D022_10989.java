package Do_it;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 10989) 수_정렬하기_3
 */
public class D022_10989 {
    public static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // N(정렬할 수 개수)
        int N = Integer.parseInt(br.readLine());
        // A(정렬할 배열 선언하기)
        A = new int[N];
        // A 배열 저장하기
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();
        // 기수 정렬 함수 수행하기
        Radix_Sort(A, 5);
        // 정렬된 A 배열 출력하기
        for (int i = 0; i < N; i++) {
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 기수 정렬 함수
    public static void Radix_Sort(int[] A, int max_size) {
        // output(임시 정렬을 위한 배열)
        int[] output = new int[A.length];
        // jarisu(현재 자릿수를 표현하는 수 -> 1, 10, 100, ...)
        int jarisu = 1;
        // 최대 자릿수만큼 반복하기
        int count = 0;
        while (count != max_size) {
            // bucket(현재 자릿수들의 분포를 합 배열의 형태로 알려 주는 배열 0 ~ 9)
            int[] bucket = new int[10];
            // 일의 자리부터 시작하기
            for (int i = 0; i < A.length; i++) {
                bucket[(A[i] / jarisu) % 10]++;
            }
            // 합 배열을 이용해 index 계산하기
            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }
            // 현재 자릿수를 기준으로 정렬하기
            for (int i = A.length - 1; i >= 0; i--) {
                output[bucket[A[i] / jarisu % 10] - 1] = A[i];
                bucket[(A[i] / jarisu) % 10]--;
            }
            // 다음 자릿수 이동을 위해 현재 자릿수 기준 정렬 데이터 저장하기
            for (int i = 0; i < A.length; i++) {
                A[i] = output[i];
            }
            // 자릿수 증가시키기
            jarisu = jarisu * 10;
            count++;
        }
    }
}
