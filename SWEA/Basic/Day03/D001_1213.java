package SWEA.Basic.Day03;

import java.util.Scanner;

/**
 * 1213) String
 */
public class D001_1213 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 번호)
            int t = sc.nextInt();
            // sstring = 문자열 저장
            String sstring = sc.next();
            // sentence = 영어 문장 저장
            String sentence = sc.next();
            // count(횟수)
            int count = 0;
            // 영어 문장의 길이 - 문자열의 길이까지
            for (int i = 0; i <= sentence.length() - sstring.length(); i++) {
                // sstring과 sentence.substring(i, i + 문자열의 길이)가 같다면
                if (sstring.equals(sentence.substring(i, i + sstring.length()))) {
                    // count 증가
                    count++;
                }
            }
            // #T와 count 반환
            System.out.println("#" + t + " " + count);
        }
    }
}
