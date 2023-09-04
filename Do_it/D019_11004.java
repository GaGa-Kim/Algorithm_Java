package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11004) K번째 수
 */
public class D019_11004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(숫자의 개수)
        int N = Integer.parseInt(st.nextToken());
        // K(K번째 수)
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // A(숫자 데이터 저장 배열)
        int[] A = new int[N];
        // A 배열 저장하기
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        // 퀵 소트 실행하기
        quickSort(A, 0, N - 1, K - 1);
        // K번째 데이터 출력하기
        System.out.println(A[K - 1]);
    }

    // 퀵 소트 함수
    public static void quickSort(int[] A, int S, int E, int K) {
        if (S < E) {
            // 피벗 구하기
            int pivot = partition(A, S, E);
            // 종료
            if (pivot == K) {
                return;
            }
            // 퀵 소트 수행하기(시작, 피벗 - 1, K)
            else if (K < pivot) {
                quickSort(A, S, pivot - 1, K);
            }
            // 퀵 소트 수행하기(피벗 + 1, 종료, K)
            else {
                quickSort(A, pivot + 1, E, K);
            }
        }
    }

    // 피벗 구하기 함수
    public static int partition(int[] A, int S, int E) {
        // 데이터가 2개인 경우는 바로 비교하여 정렬
        if (S + 1 == E) {
            if (A[S] > A[E]) {
                swap(A, S, E);
            }
            return E;
        }
        // M(중앙값)
        int M = (S + E) / 2;
        // 중앙값을 시작 위치와 swap
        swap(A, S, M);
        // 피벗을 시작 위치 값 A[S]로 저장
        int pivot = A[S];
        // i(시작점)
        int i = S + 1;
        // j(종료점)
        int j = E;
        while (i <= j) {
            // 피벗보다 작은 수가 나올 때까지 j--
            while (j >= S + 1 && pivot < A[j]) {
                j--;
            }
            // 피벗보다 큰 수가 나올 때까지 i++
            while (i <= E && pivot > A[i]) {
                i++;
            }
            // 찾은 i와 j 데이터를 swap
            if (i <= j) {
                swap(A, i++, j--);
            }
        }
        // 피벗 데이터를 나눠진 두 그룹의 경계 index에 저장하기
        A[S] = A[j];
        A[j] = pivot;
        // 경계 index 리턴
        return j;
    }

    // swap 함수
    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
