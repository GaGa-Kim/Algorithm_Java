package Do_it.Do_it_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 14003) 가장_긴_증가하는_부분_수열_5
 */
public class D096_14003 {
    // // N(수열 개수)
    static int N;
    // maxLength(최대 길이 저장 변수)
    static int maxLength;
    // A[](수열 데이터)
    static int A[] = new int[1000001];
    // ans[](정답 수열)
    static int answer[] = new int[1000001];
    // D[](0 ~ i까지 i를 포함하는 최장 증가 수열의 길이)
    static int D[] = new int[1000001];
    // B[](현재 가장 유리한 증가 수열)
    static int B[] = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(수열 개수 입력받기)
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 수열 데이터를 입력받아 A 배열에 값 저장하기
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int index;
        // B[1] = A[1]
        B[++maxLength] = A[1];
        // D[1] = 1
        D[1] = 1;
        for (int i = 2; i <= N; i++) {
            // 가장 마지막 수열보다 현재 수열이 클 때
            if (B[maxLength] < A[i]) {
                // B 배열의 끝에 A[i]값 추가하기
                B[++maxLength] = A[i]; // maxLength = maxLength + 1로 변경
                // D 배열에 maxLength를 저장
                D[i] = maxLength;
            } else {
                // 바이너리 서치를 이용해 현재 수열이 들어갈 index 찾기
                index = binarysearch(1, maxLength, A[i]);
                // B[index] = 현재 수열의 값을 저장
                B[index] = A[i];
                // D[i] = index
                D[i] = index;
            }
        }
        // 가장 긴 증가하는 부분 수열 길이 출력하기
        System.out.println(maxLength);
        index = maxLength;
        int x = B[maxLength] + 1;
        // 뒤에서부터 탐색하면서 정답 수열 저장하기
        for (int i = N; i >= 1; i--) {
            // 최초 maxLength와 같은 값을 지니고 있는 D 배열 index를 찾아 이 수열을 정답 배열에 저장하기
            if (D[i] == index && A[i] < x) {
                // ans[](정답 수열 저장하기)
                answer[index] = A[i];
                x = A[i];
                // index(maxLength) 값을 1 감소
                index--;
            }
        }
        // 저장된 정답 배열 출력하기
        for (int i = 1; i <= maxLength; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    // 현재 수열이 들어갈 수 있는 위치를 빠르게 찾아 주기 위한 바이너리 서치 함수
    private static int binarysearch(int l, int r, int row) {
        int mid;
        // l이 r보다 작을 때까지 반복하기
        while (l < r) {
            // 중앙값 = l + r / 2
            mid = (l + r) / 2;
            // B[중앙값]이 row보다 작으면 l값을 중앙값 + 1로 변경
            if (B[mid] < row)
                l = mid + 1;
            // B[중앙값]이 row보다 크거나 같으면 r값을 중앙값으로 변경
            else
                r = mid;
        }
        // l값을 리턴하기
        return l;
    }
}
