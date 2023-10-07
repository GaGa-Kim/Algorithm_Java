package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 14501) 퇴사
 */
public class D085_14501 {
    static int N;
    static int D[];
    static int T[];
    static int P[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        // D(퇴사일까지 벌 수 있는 최대 수입을 저장하기)
        D = new int[N + 2];
        // T(상담에 필요한 일 수 저장 배열)
        T = new int[N + 1];
        // p(상담을 완료했을 때 받는 수입 저장 배열)
        P = new int[N + 1];
        // T와 P 배열 입력받기
        for (int i = 1; i <= N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }
        for (int i = N; i > 0; i--) {
            // i번째 상담을 퇴사일까지 끝낼 수 없을 때
            if (i + T[i] > N + 1) {
                // i + 1일 ~ 퇴사일까지 벌 수 있는 최대 수입
                D[i] = D[i + 1];
            }
            // i번째 상담을 퇴사일까지 끝낼 수 있을 때
            else {
                // MAX(i + 1일 ~ 퇴사일에 벌 수 있는 최대 수입과 i번째 상담이 끝난 다음 날부터 퇴사일까지의 최대 수입 + i번째 상담 비용)
                D[i] = Math.max(D[i + 1], D[i + T[i]] + P[i]);
            }
        }
        // D[1](1일부터 퇴사일까지 벌 수 있는 최대 수입) 출력하기
        System.out.println(D[1]);
    }
}
