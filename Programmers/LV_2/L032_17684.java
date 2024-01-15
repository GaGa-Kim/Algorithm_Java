package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17684) 압축
 */
public class L032_17684 {
    // msg(영문 대문자로만 이뤄진 문자열)
    public int[] solution(String msg) {
        // list(압축 색인 번호 리스트)
        List<Integer> list = new ArrayList<>();
        // map(영문 대문자를 순서대로 담은 사전 HashMap)
        Map<String, Integer> map = new HashMap<>();
        // map에 알파벳 대문자 담기 함수(map)
        init(map);
        // start, end(시작, 끝 투 포인터)
        int start = 0, end = 0;
        // index(사전 저장 인덱스)
        int index = 27;
        // end가 msg.length보다 작은 동안
        while (end <= msg.length()) {
            // end = start + 1
            end = start + 1;
            // w(msg의 현재 입력 글자)
            String w = "";
            // wc(msg의 현재 입력 글자 + 다음 입력 글자)
            String wc = msg.substring(start, end);
            // map에 wc가 존재하는 동안
            while (map.containsKey(wc)) {
                // w를 wc로 갱신
                w = wc;
                // end 증가
                end++;
                // end가 msg의 길이보다 크다면
                if (end > msg.length()) {
                    // 종료
                    break;
                }
                // wc를 msg를 start부터 end까지 자른 글자로 갱신
                wc = msg.substring(start, end);
            }
            // list에 w의 색인 저장
            list.add(map.get(w));
            // map에 wc와 index++ 저장
            map.put(wc, index++);
            // start = end - 1
            start = end - 1;
        }
        // answer(주어진 문자열을 압축한 후의 사전 색인 번호 배열)
        int[] answer = new int[list.size()];
        // list를 answer 배열에 저장해 반환
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // map에 알파벳 대문자 담기 함수
    private Map<String, Integer> init(Map<String, Integer> map) {
        // c(알파벳)
        char c = 'A';
        for (int i = 1; i <= 26; i++) {
            // map에 c++와 i을 저장
            map.put(Character.toString(c++), i);
        }
        // map 반환
        return map;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L032_17684 solution = new L032_17684();

        String msg = "KAKAO";

        int[] result = solution.solution(msg);

        System.out.println(Arrays.toString(result));
    }
}
