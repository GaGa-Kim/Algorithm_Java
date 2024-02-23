package Programmers.LV_1;

import java.util.HashMap;
import java.util.Map;

/**
 * 258912) 가장_많이_받은_선물
 */
public class L081_258712 {
    // friends(친구들의 이름을 담은 1차원 문자열 배열)
    // gifts(이번 달까지 친구들이 주고받은 선물 기록을 담은 1차원 문자열 배열)
    public int solution(String[] friends, String[] gifts) {
        // giftRecords(친구들의 이름에 따라 주고받은 선물 기록 HashMap)
        Map<String, Map<String, Integer>> giftRecords = new HashMap<>();
        // giftScore(선물 지수 HashMap)
        Map<String, Integer> giftScore = new HashMap<>();
        // nextGifts(선물 지수에 따라 다음달 받을 선물의 수 HashMap)
        Map<String, Integer> nextGifts = new HashMap<>();
        // friends에 따라 HashMap들 초기화
        for (String friend : friends) {
            giftRecords.put(friend, new HashMap<>());
            giftScore.put(friend, 0);
            nextGifts.put(friend, 0);
        }
        // gifts에 따라 선물 기록 및 선물 지수 갱신
        for (String gift : gifts) {
            // AB(주고받은 선물 기록)
            String[] AB = gift.split(" ");
            // A(선물을 준 친구)
            String A = AB[0];
            // B(선물을 받은 친구)
            String B = AB[1];
            // A가 B에게 준 선물을 giftRecords에 기록
            giftRecords.get(A).put(B, giftRecords.get(A).getOrDefault(B, 0) + 1);
            // A가 B에게 선물을 주었으므로 선물 지수 증가
            giftScore.put(A, giftScore.get(A) + 1);
            // B가 A에게 선물을 받았으므로 선물 지수 감소
            giftScore.put(B, giftScore.get(B) - 1);
        }
        // 선물 지수에 따라 다음달에 받은 선물의 수 갱신
        for (String A : friends) {
            for (String B : friends) {
                // A와 B가 같다면
                if (A.equals(B))
                    // 같은 사람이므로 넘어감
                    continue;
                // giftBFromA(A가 B에게 준 선물의 수)
                int giftBFromA = giftRecords.get(A).getOrDefault(B, 0);
                // giftAFromB(B가 A에게 준 선물의 수)
                int giftAFromB = giftRecords.get(B).getOrDefault(A, 0);
                // B가 A에게 선물을 더 받았다면
                if (giftBFromA > giftAFromB)
                    // 다음 달에 A가 B로부터 선물을 하나 받게 되므로 다음달 받을 선물의 수 증가
                    nextGifts.put(A, nextGifts.get(A) + 1);
                // A와 B가 선물을 주고받은 기록이 하나도 없거나 주고받은 수가 같으며,
                // A의 선물 지수가 B의 선물 지수보다 크다면
                else if (giftBFromA == giftAFromB && giftScore.get(A) > giftScore.get(B))
                    // 선물 지수가 더 큰 A가 선물 지수가 더 작은 B에게 선물을 하나 받게 되므로 다음달 받을 선물의 수 증가
                    nextGifts.put(A, nextGifts.get(A) + 1);
            }
        }
        // answer(다음달에 가장 많은 선물을 받은 친구가 받을 선물의 수)
        int answer = 0;
        // 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수로 갱신
        for (int nextGift : nextGifts.values()) {
            answer = Math.max(answer, nextGift);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L081_258712 solution = new L081_258712();

        String[] friends = { "muzi", "ryan", "frodo", "neo" };
        String[] gifts = { "muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi",
                "frodo ryan", "neo muzi" };

        int result = solution.solution(friends, gifts);

        System.out.println(result);
    }
}