package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 2342) Dance_Dance_Revolution
 */
public class D093_2342 {
    static int N, S;
    static int D[][][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // mp(한 발을 이동할 때 드는 힘을 미리 저장하기)
        // {0에서 0, 1, 2, 3, 4로 이동}
        // {1에서 0, 1, 2, 3, 4로 이동}
        // {2에서 0, 1, 2, 3, 4로 이동}
        // {3에서 0, 1, 2, 3, 4로 이동}
        // {4에서 0, 1, 2, 3, 4로 이동}
        int mp[][] = { { 0, 2, 2, 2, 2 }, { 2, 1, 3, 4, 3 }, { 2, 3, 1, 3, 4 }, { 2, 4, 3, 1, 3 }, { 2, 3, 4, 3, 1 } };
        // D[N][L][R] (N개의 인풋까지 수행했고, 왼쪽 다리가 L, 오른쪽 다리가 R에 있을 때 힘의 최솟값)
        D = new int[100001][5][5];
        // D를 충분히 큰 수로 초기화
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 100001; k++) {
                    D[k][i][j] = 100001 * 4;
                }
            }
        }
        // D[0][0][0]을 0으로 초기화 (처음에는 아무 힘도 들지 않은 상태로 시작)
        D[0][0][0] = 0;
        // N(현재 수열의 숫자)
        N = 0;
        // S(현재 수열의 갯수)
        S = 1;
        while (true) {
            // 현재 수열의 숫자 입력
            N = sc.nextInt();
            // 입력의 마지막이면 종료
            if (N == 0)
                break;
            // 오른발(j)을 이동해 현재 발 위치로 만들 수 있는 경우의 수
            for (int i = 0; i < 5; i++) { // D[S][i][j]
                // 두 발이 같은 자리에 있을 수 없음 (이미 왼발이 현재 수열이 숫자에 위치할 경우)
                if (N == i)
                    continue;
                for (int j = 0; j < 5; j++) {
                    // D 배열에 오른발 이동에 필요한 힘 합산 값 중 최솟값 저장하기
                    // 오른발을 옮겨 현재 모습이 됐을 때 최소의 힘
                    D[S][i][N] = Math.min(D[S - 1][i][j] + mp[j][N], D[S][i][N]);
                }
            }
            // 왼발(i)을 이동해 현재 발 위치로 만들 수 있는 경우의 수
            for (int j = 0; j < 5; j++) { // D[S][i][j]
                // 두 발이 같은 자리에 있을 수 없음 (이미 왼발이 현재 수열이 숫자에 위치할 경우)
                if (N == j)
                    continue;
                for (int i = 0; i < 5; i++) {
                    // D 배열에 왼발 이동에 필요한 힘 합산 값 중 최솟값 저장하기
                    // 왼발을 옮겨 현재 모습이 됐을 때 최소의 힘
                    D[S][N][j] = Math.min(D[S - 1][i][j] + mp[i][N], D[S][N][j]);
                }
            }
            S++;
        }
        S--;
        // 모두 수행했을 때 최솟값 찾기
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, D[S][i][j]);
            }
        }
        // 최솟값 출력하기
        System.out.println(min);
    }
}
