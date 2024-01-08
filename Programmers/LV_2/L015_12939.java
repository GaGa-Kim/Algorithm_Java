package Programmers.LV_2;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 12939) 최댓값과 최솟값
 */
public class L015_12939 {
    // s(공백으로 구분된 숫자들이 저장된 문자열)
    public String solution(String s) {
        // answer(최소값과 최대값)
        String answer = "";
        // arr(숫자들 배열)에 s의 숫자들 저장
        int[] arr = Stream.of(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        // arr 정렬
        Arrays.sort(arr);
        // answer에 arr의 첫 번째 값과 마지막 값을 저장하여 반환
        answer += arr[0] + " ";
        answer += arr[arr.length - 1];
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L015_12939 solution = new L015_12939();

        String s = "1 2 3 4";

        String result = solution.solution(s);

        System.out.println(result);
    }
}
