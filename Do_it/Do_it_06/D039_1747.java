package Do_it.Do_it_06;

import java.util.Scanner;

/**
 * 1747) 소수&팰린드롬
 */
public class D039_1747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(어떤 수)
        int N = sc.nextInt();
        int[] A = new int[10000001];
        // A 배열 초기화하기
        for (int i = 2; i < A.length; i++) {
            A[i] = i;
        }
        for (int i = 2; i <= Math.sqrt(A.length); i++) {
            // 소수가 아니면 넘어감
            if (A[i] == 0) {
                continue;
            }
            // 소수의 배숫값을 10000000까지 탐색하기
            for (int j = i + i; j < A.length; j = j + i) {
                // 이 수가 소수가 아니라는 것을 표시하기 (배수 지우기)
                A[j] = 0;
            }
        }
        int i = N;
        while (true) {
            // A 배열에서 소수인 값일 때
            if (A[i] != 0) {
                // 현재 소수
                int result = A[i];
                // N부터 값을 1씩 증가시키면서 해당 값이 소수이면서 팰린드롬 수인지 판별하기
                if (isPalindrome(result)) {
                    // 맞으면 반복문 종료하기
                    System.out.println(result);
                    break;
                }
            }
            i++;
        }
    }

    // 팰린드롬 수 판별 함수
    public static boolean isPalindrome(int target) {
        // Integer값을 char 배열로 변환하기
        char temp[] = String.valueOf(target).toCharArray();
        // s(시작 인덱스)
        int s = 0;
        // e(끝 인덱스)
        int e = temp.length - 1;
        while (s < e) {
            // 만약 시작과 끝 인덱스에 해당하는 값이 다르면
            if (temp[s] != temp[e])
                return false;
            s++;
            e--;
        }
        // 반복문을 다 돌았으면
        return true;
    }
}
