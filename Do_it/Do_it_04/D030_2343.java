package Do_it.Do_it_04;

import java.util.Scanner;

/**
 * 2343) 기타_레슨
 */
public class D030_2343 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(레슨 개수)
        int N = sc.nextInt();
        // M(블루레이 개수)
        int M = sc.nextInt();
        // A(기타 레슨 데이터 저장 배열)
        int[] A = new int[N];
        // start(이진 탐색 시작 인덱스)
        int start = 0;
        // end(이진 탐색 종료 인덱스)
        int end = 0;
        for (int i = 0; i < N; i++) {
            // A 배열 저장하기
            A[i] = sc.nextInt();
            // 시작 인덱스 저장(A 배열 중 최솟값)
            if (start < A[i])
                start = A[i];
            // 종료 인덱스 저장(A 배열의 총합)
            end = end + A[i];
        }
        while (start <= end) {
            // middle(중간 인덱스)
            int middle = (start + end) / 2;
            // sum(레슨 합)
            int sum = 0;
            // count(현재 사용한 블루레이 개수)
            int count = 0;
            for (int i = 0; i < N; i++) {
                // 만약 sum + 현재 레슨 시간 > 중간 인덱스이면
                if (sum + A[i] > middle) {
                    // count 값을 올리고 sum을 0으로 리셋하기 (다음 블루레이에 저장하기 위해서)
                    count++;
                    sum = 0;
                }
                // sum에 현재 레슨 시간값 더하기
                sum = sum + A[i];
            }
            // sum이 0이 아니면 마지막 블루레이가 필요하므로 count값 올리기
            if (sum != 0)
                count++;
            // 중간 인덱스 값으로 모든 레슨 저장 불가능
            if (count > M)
                start = middle + 1;
            // 중앙 인덱스값으로 모든 레슨 저장 가능
            else
                end = middle - 1;
        }
        // 시작 인덱스 출력하기
        System.out.println(start);
    }
}
