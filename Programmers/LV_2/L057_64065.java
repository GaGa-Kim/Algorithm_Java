package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 64065) 튜플
 */
public class L057_64065 {
    // s(특정 튜플을 표현하는 집합이 담긴 문자열)
    public int[] solution(String s) {
        // list(특정 튜플을 표현하는 집합을 담을 리스트)
        List<List<Integer>> list = new ArrayList<>();
        // arr(s를 },를 기준으로 분할한 문자열 배열)
        String[] arr = s.split("},");
        // {}를 삭제하여 저장한 정수 리스트를 list에 저장
        for (String a : arr) {
            List<Integer> parseA = parseString(a);
            list.add(parseA);
        }
        // list를 원소 리스트의 길이 순으로 커스텀 정렬
        list.sort((a, b) -> Integer.compare(a.size(), b.size()));
        // answer(표현하는 튜플 LinkedHashSet)
        Set<Integer> answer = new LinkedHashSet<>();
        // answer에 중복을 제거하여 없는 값을 저장
        for (List<Integer> l : list) {
            answer.addAll(l);
        }
        // answer을 배열로 변환하여 반환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 문자열에서 {}를 삭제하고 정수 리스트로 변환 함수
    private List<Integer> parseString(String a) {
        String parseA = a.replaceAll("[{}]", "");
        return List.of(parseA.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L057_64065 solution = new L057_64065();

        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";

        int[] result = solution.solution(s);

        System.out.println(Arrays.toString(result));
    }
}
