package Do_it;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 2751) 수_정렬하기_2
 */
public class D020_2751 {
    public static int[] A, tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // N(정렬할 수 개수)
        int N = Integer.parseInt(br.readLine());
        // A(정렬할 배열 선언하기)
        A = new int[N + 1];
        // tmp(정렬할 때 잠시 사용할 임시 배열 선언하기)
        tmp = new int[N + 1];
        // A 배열에 데이터 저장하기
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        // 병합 정렬 함수 수행하기
        merge_sort(1, N);
        // 결과값 출력하기
        for (int i = 1; i <= N; i++) {
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }

    // 병합 정렬 함수
    public static void merge_sort(int s, int e) { // s(시작점), e(종료점)
        if (e - s < 1)
            return;
        // m(중간점)
        int m = s + (e - s) / 2;
        // 그룹 분할
        // 병합정렬(s, m)
        merge_sort(s, m);
        // 병합정렬(m + 1, e)
        merge_sort(m + 1, e);
        // tmp 배열 저장하기
        for (int i = s; i <= e; i++) {
            tmp[i] = A[i];
        }
        int k = s;
        // 앞쪽 그룹 시작점
        int index1 = s;
        // 뒤쪽 그룹 시작점
        int index2 = m + 1;
        // 그룹 병합
        while (index1 <= m && index2 <= e) {
            // 양쪽 그룹의 index가 가리키는 값을 비교한 후 더 작은 수를 선택해 배열에 저장
            // 선택된 데이터의 index 값을 오른쪽으로 한 칸 이동하기
            if (tmp[index1] > tmp[index2]) {
                A[k] = tmp[index2];
                k++;
                index2++;
            } else {
                A[k] = tmp[index1];
                k++;
                index1++;
            }
        }
        // 반복문이 끝난 후 남아 있는 데이터 정리하기
        while (index1 <= m) {
            A[k] = tmp[index1];
            k++;
            index1++;
        }
        while (index2 <= e) {
            A[k] = tmp[index2];
            k++;
            index2++;
        }
    }
}
