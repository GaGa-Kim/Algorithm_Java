package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 1463) 1로_만들기
 */
public class D084_1463 {
    static int N;
    static int D[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(구하고자 하는 수)
        N = sc.nextInt();
        // D[N](N을 1로 만들 수 있는 연산의 경우의 수)
        D = new int[N + 1];
        // 1일 때 연산 불필요
        D[1] = 0;
        for (int i = 2; i <= N; i++) {
            // -1 연산
            D[i] = D[i - 1] + 1;
            // 나누기 2 연산
            if (i % 2 == 0)
                D[i] = Math.min(D[i], D[i / 2] + 1);
            // 나누기 3 연산
            if (i % 3 == 0)
                D[i] = Math.min(D[i], D[i / 3] + 1);
        }
        // D[N] 출력하기
        System.out.println(D[N]);
    }
}
