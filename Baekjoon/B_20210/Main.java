package Baekjoon.B_20210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
 * 문제 분석하기
 * : 정렬 규칙에 맞춰 조건을 세워 정렬하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 문자열의 문자를 비교해 둘 다 숫자일 경우, 수를 비교해 더 작은 것이 앞에 오도록 함
 *    이때 두 수가 같다면 0의 개수가 적은 것이 앞에 오도록 함
 * 2. 둘 다 문자일 경우, 알파벳의 순서대로 더 작은 것이 앞에 오도록 함
 *    이때 두 알파벳이 같은 알파벳이라면 대문자가 앞에 오도록 함
 * 3. 하나는 문자, 하나는 숫자일 경우, 숫자가 앞에 오도록 함
 * 4. 두 문자열을 비교한 후 뒤에 문자가 더 붙어있는 문자열이 있다면, 뒤에 오도록 함
 */

/*
 * 20210) 파일탐색기 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static String[] strings; // strings(정렬할 문자열들)
    static StringBuilder answer = new StringBuilder(); // answer(문자열을 정렬한 결과)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        strings = new String[N];
        for (int i = 0; i < N; i++) {
            strings[i] = br.readLine();
        }
    }

    /*
     * 조건에 맞추어 정렬하기
     */
    static void natural_sort() {
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int s1_index = 0, s2_index = 0; // s1_index, s2_index(두 문자열의 위치 인덱스)

                for (; s1_index < s1.length() && s2_index < s2.length(); s1_index++, s2_index++) {
                    char c1 = s1.charAt(s1_index), c2 = s2.charAt(s2_index); // c1, c2(문자열의 글자)
                    boolean is_num1 = isNumber(c1), is_num2 = isNumber(c2); // is_num1, is_num2(글자가 숫자인지 여부)

                    /*
                     * 두 문자열의 글자가 모두 숫자일 때
                     */
                    if (is_num1 && is_num2) {
                        // 1. 숫자 앞 0의 개수 세기
                        int zero1 = 0, zero2 = 0; // zero1, zero2(0의 개수)
                        while (s1_index < s1.length() && s1.charAt(s1_index) == '0') {
                            zero1++;
                            s1_index++;
                        }
                        while (s2_index < s2.length() && s2.charAt(s2_index) == '0') {
                            zero2++;
                            s2_index++;
                        }
                        s1_index = Math.min(s1_index, s1.length()); // 위치 인덱스가 문자열의 총 길이를 초과할 수도 있으므로 갱신
                        s2_index = Math.min(s2_index, s2.length());

                        // 2. 0이 아닌 수 세기
                        StringBuilder nonzero1 = new StringBuilder(), nonzero2 = new StringBuilder(); // nonzero1, nonzero2(0이 아닌 수)
                        while (s1_index < s1.length() && isNumber(s1.charAt(s1_index))) {
                            if (isNumber(s1.charAt(s1_index))) {
                                nonzero1.append(s1.charAt(s1_index));
                            }
                            s1_index++;
                        }
                        while (s2_index < s2.length() && isNumber(s2.charAt(s2_index))) {
                            if (isNumber(s2.charAt(s2_index))) {
                                nonzero2.append(s2.charAt(s2_index));
                            }
                            s2_index++;
                        }
                        s1_index--;
                        s2_index--;

                        // 3. 0을 제외한 숫자의 길이를 비교하여, 더 짧은 것이 더 작은 것이므로 앞에 오게 됨
                        if (nonzero1.length() > nonzero2.length()) {
                            return 1;
                        }
                        if (nonzero2.length() > nonzero1.length()) {
                            return -1;
                        }

                        // 4. 숫자의 길이가 같다면, 두 수를 비교해 더 작은 것이 앞에 오게 됨
                        String num1 = nonzero1.toString(), num2 = nonzero2.toString();
                        for (int k = 0; k < num1.length(); k++) {
                            if (num1.charAt(k) > num2.charAt(k)) {
                                return 1;
                            } else if (num1.charAt(k) < num2.charAt(k)) {
                                return -1;
                            }
                        }

                        // 5. 같은 값을 가지는 숫자열일 경우, 앞에 따라붙는 0의 개수가 적은 것이 앞에 오게 됨
                        if (zero1 != zero2) {
                            return zero1 - zero2;
                        }
                    }

                    /*
                     * 두 문자열의 글자가 모두 문자일 때
                     */
                    if (!is_num1 && !is_num2) {
                        // 1. 완전히 같은 문자라면 넘어감
                        if (c1 == c2) {
                            continue;
                        }

                        // 2. 두 문자의 아스키 코드 구하기
                        boolean is_upper1 = isUpperCase(c1), is_upper2 = isUpperCase(c2); // is_upper1, is_upper2(문자가 대문자인지 여부)
                        int alphabet1 = 0, alphabet2 = 0; // alphabet1, alphabet2(문자의 아스키 코드 값)
                        if (is_upper1) {
                            alphabet1 = c1 - 'A';
                        } else {
                            alphabet1 = c1 - 'a';
                        }
                        if (is_upper2) {
                            alphabet2 = c2 - 'A';
                        } else {
                            alphabet2 = c2 - 'a';
                        }

                        // 3. 두 문자가 모두 대문자거나 소문자일 경우, 아스키코드가 더 작은 것이 앞에 오게 됨
                        if ((is_upper1 && is_upper2) || (!is_upper1 && !is_upper2)) {
                            return alphabet1 - alphabet2;
                        }

                        // 4. 두 문자 중 한 문자가 소문자일 경우, 아스키코드가 더 작거나 대문자가 앞에 오게 됨
                        if (!is_upper1 && is_upper2) {
                            if (alphabet1 == alphabet2) {
                                return 1;
                            }
                            return alphabet1 - alphabet2;
                        }
                        if (is_upper1 && !is_upper2) {
                            if (alphabet1 == alphabet2) {
                                return -1;
                            }
                            return alphabet1 - alphabet2;
                        }
                    }

                    /*
                     * 두 문자열의 글자가 하나는 문자, 하나는 숫자일 때
                     */
                    if (!is_num1 && is_num2) {
                        // 숫자가 문자보다 앞에 오게 됨
                        return 1;
                    }
                    if (is_num1 && !is_num2) {
                        // 숫자가 문자보다 앞에 오게 됨
                        return -1;
                    }
                }
                /*
                 * 같은 문자열이지만 뒤에 더 문자가 있다면 길이가 짧은 문자열이 앞에 오게 됨
                 */
                if (s1.length() != s1_index) {
                    return 1;
                }
                if (s2.length() != s2_index) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /*
     * 해당 문자가 숫자인지 여부
     */
    static boolean isNumber(char c) {
        if (Character.isDigit(c)) {
            return true;
        }
        return false;
    }

    /*
     * 해당 문자가 대문자인지 여부
     */
    static boolean isUpperCase(char c) {
        if (Character.isUpperCase(c)) {
            return true;
        }
        return false;
    }

    /*
     * 정렬한 결과를 출력하기
     */
    static void print() {
        for (String s : strings) {
            answer.append(s).append("\n");
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        natural_sort();
        print();
    }
}