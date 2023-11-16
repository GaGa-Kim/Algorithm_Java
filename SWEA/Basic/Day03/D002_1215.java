package SWEA.Basic.Day03;

import java.util.Scanner;

/**
 * 1215) 회문1
 */
public class D002_1215 {
    static char[][] arr;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // n(찾아야 하는 회문의 길이)
            int n = sc.nextInt();
            // arr(글자 저장 2차원 배열 (8 x 8 만큼))
            arr = new char[8][8];
            // 글자 저장
            for (int i = 0; i < arr.length; i++) {
                String str = sc.next();
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
            // count(횟수)
            int count = 0;
            // 가로
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length - n + 1; j++) {
                    // sb(n 길이만큼의 문자)
                    StringBuffer sb = new StringBuffer();
                    for (int k = 0; k < n; k++) {
                        sb.append(arr[i][j + k]);
                    }
                    // 대칭인지 확인
                    if (isPalindrome(sb)) {
                        // 대칭이라면 count 증가
                        count++;
                    }
                }
            }
            // 세로
            for (int i = 0; i < arr.length - n + 1; i++) {
                for (int j = 0; j < arr.length; j++) {
                    // sb(n 길이만큼의 문자)
                    StringBuffer sb = new StringBuffer();
                    for (int k = 0; k < n; k++) {
                        sb.append(arr[i + k][j]);
                    }
                    // 대칭인지 확인
                    if (isPalindrome(sb)) {
                        // 대칭이라면 count 증가
                        count++;
                    }
                }
            }
            // #T와 count 반환
            System.out.println("#" + test_case + " " + count);
        }
    }

    // 팰린드롬 함수 구현하기
    private static boolean isPalindrome(StringBuffer sb) {
        // str(원본 문자열)
        String str = sb.toString();
        // rstr(원본 문자열을 뒤집은 문자열)
        String rstr = sb.reverse().toString();
        // 동일하다면
        if (str.equals(rstr)) {
            return true;
        }
        return false;
    }
}
