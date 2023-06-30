import java.util.Scanner;

/**
 * 1427) 소트인사이드
 */
public class 소트인사이드 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // str(정렬할 수)
        String str = sc.next();
        // A(자릿수별로 구분해 저장한 배열)
        int[] A = new int[str.length()];
        // str의 길이만큼 반복하기
        for (int i = 0; i < str.length(); i++) {
            // A 배열 저장 → str.substring 사용하기
            A[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        // 0 ~ str의 길이만큼 반복하기
        for (int i = 0; i < str.length(); i++) {
            int Max = i;
            // i + 1 ~ str의 길이만큼 반복하기
            for (int j = i + 1; j < str.length(); j++) {
                // 현재 범위에서 Max값 찾기
                // 내림차순이므로 최댓값을 찾음
                if (A[j] > A[Max]) {
                    Max = j;
                }
            }
            // 현재 i의 값과 Max값 중 Max값이 더 크면 swap 수행하기
            if (A[i] < A[Max]) {
                int temp = A[i];
                A[i] = A[Max];
                A[Max] = temp;
            }
        }
        // A 배열 출력하기
        for (int i = 0; i < str.length(); i++) {
            System.out.print(A[i]);
        }
    }
}