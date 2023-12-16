package Programmers.LV_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 92334) 신고_결과_받기
 */
public class L023_92334 {
    // id_list(이용자의 ID)
    // report(각 이용자가 신고한 이용자의 ID 정보)
    // k(정지 기준이 되는 신고 횟수)
    public int[] solution(String[] id_list, String[] report, int k) {
        // answer(각 유저별로 처리 결과 메일을 받은 횟수)
        int[] answer = new int[id_list.length];
        // map(신고 당한 이용자의 ID와 신고한 이용자의 ID 집합을 가지는 HashMap)
        Map<String, Set<String>> map = new HashMap<>();
        for (String r : report) {
            // sender(신고한 이용자)
            String sender = r.split(" ")[0];
            // receiver(신고 당한 이용자)
            String receiver = r.split(" ")[1];
            // 처음 신고 당한 것이 아니라면
            if (map.containsKey(receiver)) {
                // receiver의 map에 sender 추가
                map.get(receiver).add(sender);
            }
            // 처음 신고 당한 것이라면
            else {
                // map에 receiver와 sender를 담은 집합 저장
                Set<String> set = new HashSet<>();
                set.add(sender);
                map.put(receiver, set);
            }
        }
        for (Map.Entry<String, Set<String>> result : map.entrySet()) {
            // result의 집합의 크기가 k보다 크거나 같다면 (k번 이상 신고 당했다면)
            if (result.getValue().size() >= k) {
                for (String reporter : result.getValue()) {
                    // index(answer에서 reporter의 인덱스)
                    int index = Arrays.asList(id_list).indexOf(reporter);
                    // 신고한 이용자의 메일 횟수인 answer[index] 증가
                    answer[index] += 1;
                }
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L023_92334 solution = new L023_92334();

        String[] id_list = { "muzi", "frodo", "apeach", "neo" };
        String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
        int k = 2;

        int[] result = solution.solution(id_list, report, k);

        System.out.println(Arrays.toString(result));
    }
}
