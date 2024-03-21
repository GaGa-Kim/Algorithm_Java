package Programmers.LV_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 43164) 여행경로
 */
public class L029_43164 {
    static ArrayList<String> allRoute;
    static boolean[] visited;

    // tickets(항공권 정보가 담긴 2차원 배열)
    public String[] solution(String[][] tickets) {
        // answer(방문하는 공항 경로 배열)
        String[] answer = {};
        // allRoute(방문하는 모든 공항 경로 리스트)
        allRoute = new ArrayList<>();
        // visited(항공권 사용 유무 배열)
        visited = new boolean[tickets.length];
        // 가능한 모든 경로 탐색
        dfs("ICN", "ICN", tickets, 0);
        // allRoute 정렬
        Collections.sort(allRoute);
        // " "으로 분리하여 저장
        answer = allRoute.get(0).split(" ");
        // answer 반환
        return answer;
    }

    private void dfs(String start, String route, String[][] tickets, int visitCount) {
        // 모든 항공권을 사용했다면
        if (visitCount == tickets.length) {
            // 현재 경로를 모든 공항 경로 리스트에 저장
            allRoute.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            // tickets[i][0]이 항공권 출발지와 같으며 항공권 i를 사용하지 않았다면
            if (start.equals(tickets[i][0]) && visited[i] != true) {
                // 현재 항공권 사용 처리 갱신
                visited[i] = true;
                // 현재 항공권의 도착지를 출발지로 변경한 후 경로 탐색
                dfs(tickets[i][1], route + " " + tickets[i][1], tickets, visitCount + 1);
                // 현재 항공권 사용 초기화
                visited[i] = false;
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L029_43164 solution = new L029_43164();

        String[][] tickets = { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" },
                { "ATL", "SFO" } };

        String[] result = solution.solution(tickets);

        System.out.println(Arrays.toString(result));
    }
}
