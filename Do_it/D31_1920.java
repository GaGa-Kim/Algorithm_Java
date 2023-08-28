package Do_it;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1920) 수_찾기
 */
public class D31_1920 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(정렬할 수 개수)
        int N = sc.nextInt();
        // A(정렬할 배열 선언하기)
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            // A 배열 저장하기
            A[i] = sc.nextInt();
        }
        // A 배열 정렬하기
        Arrays.sort(A);
        // M(탐색할 숫자의 개수)
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            boolean find = false;
            // target(찾아야 하는 수)
            int target = sc.nextInt();
            // start(시작 인덱스)
            int start = 0;
            // end(종료 인덱스)
            int end = A.length - 1;
            while (start <= end) {
                // midi(중간 인덱스)
                int midi = (start + end) / 2;
                int midV = A[midi];
                if (midV > target) {
                    end = midi - 1;
                } else if (midV < target) {
                    start = midi + 1;
                } else { // 찾았으므로 반복문 종료하기
                    find = true;
                    break;
                }
            }
            // if(찾았음) 1 출력
            if (find) {
                System.out.println(1);
            } else { // else 0 출력
                System.out.println(0);
            }
        }
    }
}