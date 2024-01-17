package Programmers.LV_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 49993) 스킬트리
 */
public class L052_49993 {
    // map(선행 스킬트리 북)
    Map<Character, Integer> map = new HashMap<>();

    // skill(선행 스킬 순서)
    // skill_trees(유저들이 만든 스킬트리)
    public int solution(String skill, String[] skill_trees) {
        // answer(가능한 스킬트리 개수)
        int answer = 0;
        // 선행 스킬트리 북 만들기 함수(skill)
        makeSkillBook(skill);
        for (String s : skill_trees) {
            // 스킬 순서 확인 함수(s)가 true라면
            if (isCorrect(s))
                // answer 증가
                answer++;
        }
        // answer 반환
        return answer;
    }

    // 선행 스킬트리 북 만들기 함수
    private void makeSkillBook(String skill) {
        // map에 스킬과 스킬 순서 인덱스를 저장
        for (int i = 0; i < skill.length(); i++) {
            map.put(skill.charAt(i), i + 1);
        }
    }

    // 스킬 순서 확인 함수
    private boolean isCorrect(String skill_tree) {
        // level(현재 스킬 레벨)
        int level = 0;
        for (int i = 0; i < skill_tree.length(); i++) {
            // c(현재 배울 스킬)
            char c = skill_tree.charAt(i);
            // c를 배우기 위해서 선행 스킬이 필요하다면
            if (map.containsKey(c)) {
                // level과 c의 선행 스킬 레벨(c의 스킬 레벨 - 1)이 같다면
                if (level == map.get(c) - 1)
                    // 배울 수 있으므로 level을 c의 스킬 레벨로 변경
                    level = map.get(c);
                // level과 c의 선행 스킬 레벨이 다르다면
                else
                    // 배울 수 없으므로 false 반환
                    return false;
            }
        }
        // true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L052_49993 solution = new L052_49993();

        String skill = "CBD";
        String[] skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };

        int result = solution.solution(skill, skill_trees);

        System.out.println(result);
    }
}
