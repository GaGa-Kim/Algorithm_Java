package Programmers.LV_1;

import java.util.Arrays;

/**
 * 17681) 비밀지도
 */
public class L034_17681 {
    // n(지도의 한 변 크기)
    // arr1, arr2(정수 배열)
    public String[] solution(int n, int[] arr1, int[] arr2) {
        // answer(해독된 비밀지도)
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            // c1(arr1[i]의 값을 이진수로 변환한 문자열)
            String c1 = convert(arr1[i], n);
            // c2(arr2[i]의 값을 이진수로 변환한 문자열)
            String c2 = convert(arr2[i], n);
            // s(answer의 한 줄 문자열)
            String s = "";
            for (int j = 0; j < n; j++) {
                // 1의 j 또는 c2의 j가 1이라면
                if (c1.charAt(j) == '1' || c2.charAt(j) == '1')
                    // s에 # 추가
                    s += "#";
                // 1의 j 또는 c2의 j가 0이라면
                else
                    // s에 공백 추가
                    s += " ";
            }
            // s 저장
            answer[i] = s;
        }
        // answer 반환
        return answer;
    }

    // 이진수 변환 함수
    // 또는 Integer.toBinaryString() 사용
    private String convert(int num, int n) {
        // s(이진수 변환 문자열)
        String s = "";
        // 현재 값(num)이 0이 아닐 동안
        while (num != 0) {
            s = num % 2 + s;
            num = num / 2;
        }
        // s의 길이가 n이 아니라면
        if (s.length() != n) {
            // "0"를 n - s의 길이만큼 반복하여 자릿수를 채워줌
            s = "0".repeat(n - s.length()) + s;
        }
        // s 반환
        return s;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L034_17681 solution = new L034_17681();

        int n = 5;
        int[] arr1 = { 9, 20, 28, 18, 11 };
        int[] arr2 = { 30, 1, 21, 17, 28 };

        String[] result = solution.solution(n, arr1, arr2);

        System.out.println(Arrays.toString(result));
    }
}
