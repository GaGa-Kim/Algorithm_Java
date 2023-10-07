package Do_it.Do_it_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 9252) LCS_2
 */
public class D090_9252 {
    static long[][] D;
    static ArrayList<Character> Path;
    static char[] A;
    static char[] B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // A(1번째 문자열)
        A = br.readLine().toCharArray();
        // B(2번째 문자열)
        B = br.readLine().toCharArray();
        // D(각 위치 인덱스를 마지막 문자로 하는 두 문자열의 최장 공통 수열의 길이를 나타내는 2차원 점화식 배열)
        D = new long[A.length + 1][B.length + 1];
        // Path(LCS 저장하기 리스트)
        Path = new ArrayList<Character>();
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                // A[i]와 B[i]가 같으면 D[i][j]의 값을 왼쪽 대각선값 + 1로 저장하기
                if (A[i - 1] == B[j - 1]) {
                    D[i][j] = D[i - 1][j - 1] + 1;
                }
                // 다른 경우에는 왼쪽의 값과 위의 값 중 큰 값으로 D[i][j] 채우기
                else {
                    D[i][j] = Math.max(D[i - 1][j], D[i][j - 1]);
                }
            }
        }
        // D의 마지막 값을 출력하기(LCS 길이)
        System.out.println(D[A.length][B.length]);
        // getText 함수를 이용해 LCS 문자열 출력하기
        getText(A.length, B.length);
        for (int i = Path.size() - 1; i >= 0; i--) {
            System.out.print(Path.get(i));
        }
        System.out.println();
    }

    // LCS 출력하기 함수
    private static void getText(int row, int column) {
        if (row == 0 || column == 0)
            return;
        // A[row]와 B[column]가 같으면
        if (A[row - 1] == B[column - 1]) {
            // LCS에 기록
            Path.add(A[row - 1]);
            // 대각선 왼쪽 위로 이동
            getText(row - 1, column - 1);
        }
        // 다른 경우
        else {
            // 왼쪽 값과 위의 값 중 값이 더 큰 쪽으로 이동하기
            if (D[row - 1][column] > D[row][column - 1])
                getText(row - 1, column);
            else
                getText(row, column - 1);
        }
    }
}
