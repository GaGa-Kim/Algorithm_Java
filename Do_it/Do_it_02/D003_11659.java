package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11659) 구간_합_구하기_4
 */
public class D003_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // suNo(숫자 개수), quizNo(질의 개수) 저장하기
        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());
        long[] S = new long[suNo + 1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 숫자 개수만큼 반복하기
        for (int i = 1; i <= suNo; i++) {
            // 합 배열 생성하기(S[i] = S[i - 1] + A[i])
            S[i] = S[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
        }
        // 질의 개수만큼 반복하기
        for (int q = 0; q < quizNo; q++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            // 질의 범위 받기(i ~ j)
            int i = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(stringTokenizer.nextToken());
            // 구간 합 출력하기(S[j] - S[i - 1])
            System.out.println(S[j] - S[i - 1]);
        }
    }

}