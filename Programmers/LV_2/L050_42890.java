package Programmers.LV_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 428900) 후보키
 */
public class L050_42890 {
    // answer(가능한 후보키 리스트)
    List<String> answer = new ArrayList<>();
    // visited(방문 유무 배열)
    boolean[] visited;

    // relation(관계 데이터베이스의 릴레이션)
    public int solution(String[][] relation) {
        // 컬럼끼리의 조합으로 후보키릁 생성
        for (int i = 0; i < relation[0].length; i++) {
            // visited 초기화
            visited = new boolean[relation[0].length];
            // dfs(0, 0, i + 1, relation) 함수
            dfs(0, 0, i + 1, relation);
        }
        // answer의 크기를 반환
        return answer.size();
    }

    // dfs (시작 컬럼, dfs 깊이, 원하는 후보키 개수, relation) 함수
    public void dfs(int start, int depth, int count, String[][] relation) {
        // dfs의 깊이가 count와 동일하다면
        if (depth == count) {
            // key(후보키)
            String key = "";
            for (int i = 0; i < visited.length; i++) {
                // visited[i]가 true라면
                if (visited[i]) {
                    // key에 컬럼의 인덱스인 i 추가 (컬럼명이 없으므로 0번부터 인덱스 번호로 후보키를 저장)
                    key += String.valueOf(i);
                }
            }
            // 유일성 판단하기 함수가 true라면
            if (isUnique(key, relation))
                // 최소성 판단하기 함수가 true라면
                if (isMinimum(key))
                    // answer에 key 추가
                    answer.add(key);
            return;
        }
        for (int i = start; i < visited.length; i++) {
            // visited[i]가 true라면
            if (visited[i])
                // 이미 사용한 컬럼이므로 넘어감
                continue;
            // visited[i]를 true로 갱신
            visited[i] = true;
            // dfs(i, depth + 1, count, relation) 함수
            dfs(i, depth + 1, count, relation);
            // visited[i]를 false로 초기화
            visited[i] = false;
        }
    }

    // 유일성 판단하기 함수
    private boolean isUnique(String key, String[][] relation) {
        // map(후보키에 따른 튜플 값들을 저장할 HashMap)
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < relation.length; i++) {
            // s(후보키에 따른 튜플 값 조합)
            String s = "";
            for (int j = 0; j < key.length(); j++) {
                // k(key의 i번째 후보키의 컬럼 위치 인덱스)
                int k = Character.getNumericValue(key.charAt(j));
                // s에 현재 컬럼의 튜플 값 추가
                s += relation[i][k];
            }
            // map에 s가 존재한다면
            if (map.containsKey(s)) {
                // 중복되므로 유일성을 만족하지 못하므로 false 반환
                return false;
            }
            // map에 s가 존재하지 않는다면
            else {
                // map에 s 추가
                map.put(s, 0);
            }
        }
        // 모두 중복되지 않는다면 유일성을 만족하므로 true 반환
        return true;
    }

    // 최소성 판단하기 함수
    private boolean isMinimum(String key) {
        for (String s : answer) {
            // count(후보키 key와 후보키 s의 동일 후보키 갯수)
            int count = 0;
            for (int i = 0; i < key.length(); i++) {
                // subKey(key의 부분 후보키)
                String subKey = String.valueOf(key.charAt(i));
                // s가 subKey를 포함하고 있다면
                if (s.contains(subKey))
                    // count 증가
                    count++;
            }
            // count의 길이가 s의 길이와 같다면)
            if (count == s.length())
                // 유일성을 만족하지 못하므로 false 반환
                return false;
        }
        // 유일성을 만족하므로 true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L050_42890 solution = new L050_42890();

        String[][] relation = {
                { "100", "ryan", "music", "2" },
                { "200", "apeach", "math", "2" },
                { "300", "tube", "computer", "3" },
                { "400", "con", "computer", "4" },
                { "500", "muzi", "music", "3" },
                { "600", "apeach", "music", "2" }
        };

        int result = solution.solution(relation);

        System.out.println(result);
    }
}
