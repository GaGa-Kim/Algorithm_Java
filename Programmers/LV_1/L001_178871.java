package Programmers.LV_1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 178871) 달리기_경주
 */
public class L001_178871 {

    // players(선수들의 이름과 현재 등수)
    // callings(해설진이 부른 이름)
    public String[] solution(String[] players, String[] callings) {

        // rank(선수들의 이름과 현재 등수를 담은 Hashmap)
        Map<String, Integer> rank = new HashMap<>();
        // rank에 players 저장하기
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i);
        }

        // 순위 변경하기
        for (String player : callings) {
            // 해설진이 부른 선수의 순위
            int callRank = rank.get(player);
            if (callRank > 0) {
                // 해설진이 부른 선수의 앞 순위 선수
                String temp = players[callRank - 1];
                // 해설진이 부른 이름을 가진 선수의 앞 순위 선수의 등수 -> 해설진이 부른 이름을 가진 선수로 변경
                players[callRank - 1] = players[callRank];
                // 해설진이 부른 이름을 가진 선수의 등수 -> 해설진이 부른 이름을 가진 선수의 앞 순서 선수로 변경
                players[callRank] = temp;

                // rank 갱신
                rank.put(players[callRank], callRank);
                rank.put(players[callRank - 1], callRank - 1);
            }
        }

        // players 반환
        return players;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L001_178871 solution = new L001_178871();

        String[] players = { "mumu", "soe", "poe", "kai", "mine" };
        String[] callings = { "kai", "kai", "mine", "mine" };

        String[] result = solution.solution(players, callings);

        System.out.println(Arrays.toString(result));
    }
}
