package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 72412) 순위_검색
 */
public class L063_72412 {
    // map(4가지 정보와 획득한 코딩 테스트 점수 리스트를 담은 HashMap)
    Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

    // info(지원자가 지원서에 입력한 4가지의 정보와 획득한 코딩테스트 점수)
    // query(개발팀이 궁금해하는 문의조건)
    public int[] solution(String[] info, String[] query) {
        for (int i = 0; i < info.length; i++) {
            // dfs("", info[i].split(" "), 0);
            dfs("", info[i].split(" "), 0);
        }
        // map에 존재하는 key를 모두 가져와서 key의 value 값 리스트를 정렬
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        // answer(각 문의조건에 해당하는 사람들의 숫자)
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            // inquiry(query[i]에서 and를 공백으로 변경한 후, 공백으로 구분하여 전달) 저장
            answer[i] = inquiry(query[i].replace(" and ", " ").split(" "));
        }
        // answer 반환
        return answer;
    }

    // dfs
    private void dfs(String combi, String[] dfsInfo, int depth) {
        // depth가 4라면 4가지 정보 조합 완성
        if (depth == 4) {
            // score(코딩 테스트 점수)
            int score = Integer.parseInt(dfsInfo[4]);
            // map에 이미 4가지 정보 조합이 존재한다면
            if (map.containsKey(combi)) {
                // 이미 존재하는 4가지 정보 조합에 score 추가
                map.get(combi).add(score);
            } else {
                // 리스트에 score를 저장하고 4가지 정보와 함께 map에 저장
                ArrayList<Integer> list = new ArrayList<>();
                list.add(score);
                map.put(combi, list);
            }
            return;
        }
        // dfs(combi + "-", dfsInfo, depth + 1)
        dfs(combi + "-", dfsInfo, depth + 1);
        // dfs(combi + dfsInfo[depth], dfsInfo, depth + 1)
        dfs(combi + dfsInfo[depth], dfsInfo, depth + 1);
    }

    // inquiry
    private int inquiry(String[] queryInfo) {
        // combi(4가지 정보 조건)
        String combi = queryInfo[0] + queryInfo[1] + queryInfo[2] + queryInfo[3];
        // score(코딩 테스트 점수 조건)
        int score = Integer.parseInt(queryInfo[4]);
        // map에 combi가 존재한다면
        if (map.containsKey(combi))
            // countScore(scoreList, score) 반환
            return countScore(map.get(combi), score);
        // 0 반환
        return 0;
    }

    // countScore
    private int countScore(ArrayList<Integer> scoreList, int score) {
        // start(시작 인덱스)
        int start = 0;
        // end(종료 인덱스)
        int end = scoreList.size() - 1;
        // 이진 탐색
        while (start <= end) {
            int mid = (start + end) / 2;
            if (scoreList.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }
        // list의 크기 - start 반환
        return scoreList.size() - start;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L063_72412 solution = new L063_72412();

        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50" };
        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150" };

        int[] result = solution.solution(info, query);

        System.out.println(Arrays.toString(result));
    }
}
