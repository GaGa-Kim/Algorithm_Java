package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 42888) 오픈채팅방
 */
public class L049_42888 {
    // record(채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열)
    public String[] solution(String[] record) {
        // list(유저아이디와 채팅 내용을 저장할 리스트)
        List<String> list = new ArrayList<>();
        // map(유저아이디에 따른 닉네임을 저장할 HashMap)
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < record.length; i++) {
            // arr(record[i]를 공백에 따라 나눈 문자열 배열)
            String[] arr = record[i].split(" ");
            // arr[0]이 Enter라면
            if (arr[0].equals("Enter")) {
                // list에 'arr[1]님이 들어왔습니다.' 저장
                list.add(arr[1] + "님이 들어왔습니다.");
                // map에 유저아이디인 arr[1]과 닉네임인 arr[2]를 저장
                map.put(arr[1], arr[2]);
            }
            // arr[0]이 Change라면
            else if (arr[0].equals("Change")) {
                // // map에 유저아이디인 arr[1]과 닉네임인 arr[2]를 저장
                map.put(arr[1], arr[2]);
            }
            // arr[0]이 Leave라면
            else {
                // list에 'arr[1]님이 나갔습니다.' 저장
                list.add(arr[1] + "님이 나갔습니다.");
            }
        }
        for (int i = 0; i < list.size(); i++) {
            // id(유저아이디) = list.get(i)를 '님'으로 분리하여 앞의 글자를 가져옴
            String id = list.get(i).split("님")[0];
            // 맵에서 유저아이디에 해당하는 닉네임을 가져와 변경
            list.set(i, list.get(i).replace(id, map.get(id)));
        }
        // answer(최종적으로 방을 개설한 사람이 보게 되는 메시지의 문자열 배열)
        String[] answer = new String[list.size()];
        // list를 answer로 변환하여 반환
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L049_42888 solution = new L049_42888();

        String[] record = {
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan" };

        String[] result = solution.solution(record);

        System.out.println(Arrays.toString(result));
    }
}
