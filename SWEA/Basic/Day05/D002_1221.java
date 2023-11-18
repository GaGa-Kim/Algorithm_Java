package SWEA.Basic.Day05;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1221) GNS
 */
public class D002_1221 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수)
        int T = sc.nextInt();
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(# 기호와 테스트 케이스의 번호)
            String t = sc.next();
            // n(테스트 케이스의 길이)
            int n = sc.nextInt();
            // numbers(각 숫자의 값 단어를 저장하는 문자열 배열)
            String[] numbers = { "ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN" };
            // arr(문자들의 인덱스를 저장하는 배열)
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                // string(문자)
                String string = sc.next();
                // numbers를 돌면서
                for (int j = 0; j < numbers.length; j++) {
                    // numbers의 문자와 같다면
                    if (numbers[j].equals(string)) {
                        // 문자의 인덱스 저장
                        arr[i] = j;
                    }
                }
            }
            // arr 오름차순 정렬
            Arrays.sort(arr);
            StringBuilder sb = new StringBuilder();
            // arr[i]를 numbers의 인덱스에 맞는 문자로 변경하여 sb에 공백과 함께 추가
            for (int i = 0; i < arr.length; i++) {
                int num = arr[i];
                sb.append(numbers[num] + " ");
            }
            // #T와 sb 반환
            System.out.println(t + " " + sb);
        }
    }
}