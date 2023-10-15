package Do_it.Do_it_11;

import java.util.Scanner;

/**
 * 2166) 다각형의_면적
 */
public class D100_2166 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(점의 개수)
        int N = sc.nextInt();
        // x[](x 좌표 저장하기)
        long[] x = new long[N + 1];
        // y[](y 좌표 저장하기)
        long[] y = new long[N + 1];
        // 배열에 x, y 좌표 저장하기
        for (int i = 0; i < N; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        // 배열의 마지막에 처음 점 다시 넣기
        // 마지막 점과 처음 점도 CCW 계산에 포함되도록 함
        x[N] = x[0];
        y[N] = y[0];
        double result = 0;
        // 원점, i, i + 1 세 점과 관련된 CCW 값을 구해 result에 더하기
        for (int i = 0; i < N; i++) {
            result += (x[i] * y[i + 1]) - (x[i + 1] * y[i]);
        }
        // result값을 2로 나누고 둘째 자리 반올림해 출력하기
        String answer = String.format("%.1f", Math.abs(result) / 2.0);
        System.out.println(answer);
    }
}
