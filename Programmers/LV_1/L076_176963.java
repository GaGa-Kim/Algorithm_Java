package Programmers.LV_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 176963) 추억_점수
 */
public class L076_176963 {

    // name(그리워하는 사람의 이름을 담은 문자열 배열)
    // yearning(각 사람별 그리움 점수를 담은 정수 배열)
    // photo(각 사진에 찍힌 인물의 이름을 담은 이차원 문자열 배열)
    public int[] solution(String[] name, int[] yearning, String[][] photo) {

        // missing(그리워하는 사람의 이름과 그리움 점수를 담은 hashMap)
        Map<String, Integer> missing = new HashMap<>();
        // missing에 name, yearning 저장하기
        for (int i = 0; i < name.length; i++) {
            missing.put(name[i], yearning[i]);
        }

        // 사진 속 이름의 그리움 점수 합산하기
        int[] answer = new int[photo.length];
        for (int i = 0; i < photo.length; i++) {
            int sum = 0;
            for (int j = 0; j < photo[i].length; j++) {
                sum += missing.getOrDefault(photo[i][j], 0);
            }
            answer[i] = sum;
        }
        // 합산된 그리움 점수들 반환하기
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L076_176963 solution = new L076_176963();

        String[] name = { "may", "kein", "kain", "radi" };
        int[] yearning = { 5, 10, 1, 3 };
        String[][] photo = { { "may", "kein", "kain", "radi" },
                { "may", "kein", "brin", "deny" },
                { "kon", "kain", "may", "coni" } };

        int[] result = solution.solution(name, yearning, photo);

        System.out.println(Arrays.toString(result));
    }
}
