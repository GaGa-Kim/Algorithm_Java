package SWEA.Basic.Day03;

import java.util.Scanner;

/**
 * 1216) 회문2
 */
public class D003_1216 {
    static char[][] arr;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 번호)
            int t = sc.nextInt();
            // arr(글자 저장 2차원 배열 (8 x 8 만큼))
            arr = new char[100][100];
            // 글자 저장
            for (int i = 0; i < arr.length; i++) {
                String str = sc.next();
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = str.charAt(j);
                }
            }
            // answer(최대 회문의 길이)
            int answer = 0;
            // 회문의 길이를 증가시키면서 확인
            for (int len = 0; len < arr.length; len++) {
                // 가로
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr.length - len + 1; j++) {
                        // sb(len 길이만큼의 문자)
                        StringBuffer sb = new StringBuffer();
                        for (int k = 0; k < len; k++) {
                            sb.append(arr[i][j + k]);
                        }
                        // 대칭인지 확인
                        if (isPalindrome(sb)) {
                            // 대칭이라면 최대 회문의 길이 갱신
                            answer = len;
                        }
                    }
                }
                // 세로
                for (int i = 0; i < arr.length - len + 1; i++) {
                    for (int j = 0; j < arr.length; j++) {
                        // sb(len 길이만큼의 문자)
                        StringBuffer sb = new StringBuffer();
                        for (int k = 0; k < len; k++) {
                            sb.append(arr[i + k][j]);
                        }
                        // 대칭인지 확인
                        if (isPalindrome(sb)) {
                            // 대칭이라면 최대 회문의 길이 갱신
                            answer = len;
                        }
                    }
                }
            }
            // #T와 answer 반환
            System.out.println("#" + t + " " + answer);
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
