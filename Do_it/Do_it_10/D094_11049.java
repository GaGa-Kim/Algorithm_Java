package Do_it.Do_it_10;

import java.util.Scanner;

/**
 * 11049) 행렬_곱셈_순서
 */
public class D094_11049 {
    static int N;
    static int[][] D;
    static Matrix[] M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(행렬 개수)
        N = sc.nextInt();
        // M(행렬 저장 클래스 배열)
        M = new Matrix[N + 1];
        // D[i][j] (i ~ j번째 행렬까지 최소 연산 횟수를 저장하는 배열)
        D = new int[N + 1][N + 1];
        // D 배열 초기화
        for (int i = 0; i < D.length; i++) {
            for (int j = 0; j < D.length; j++) {
                D[i][j] = -1;
            }
        }
        // 행렬 데이터 저장하기
        for (int i = 1; i <= N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            M[i] = new Matrix(x, y);
        }
        // 정답 출력하기
        System.out.println(execute(1, N));
    }

    private static int execute(int s, int e) {
        // result(결괏값)
        int result = Integer.MAX_VALUE;
        // 이미 계산한 구역일 때
        if (D[s][e] != -1)
            // D[i][j] 값 바로 리턴
            return D[s][e];
        // 1개 행렬일 때
        if (s == e)
            // 연산 횟수 0 리턴
            return 0;
        // 행렬 2개일 때
        if (s + 1 == e)
            // 2개 행렬 연산값 리턴
            return M[s].x * M[s].y * M[e].y;
        // 행렬 3개 이상일 때 (재귀 형태로 구현)
        for (int i = s; i < e; i++)
            // execute(s, i) + execute(i + 1, e) + 앞뒤 구간의 행렬을 합치기 위한 연산 횟수
            result = Math.min(result, M[s].x * M[i].y * M[e].y + execute(s, i) + execute(i + 1, e));
        return D[s][e] = result;
    }
}

// 행렬 정보 저장하기
class Matrix {
    // x(행의 개수)
    int x;
    // y(열의 개수)
    int y;

    Matrix(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
