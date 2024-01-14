package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 17683) 방금그곡
 */
public class L031_17683 {
    // 음악
    class Music implements Comparable<Music> {
        // name(음악 제목)
        String name;
        // melody(음악 악보)
        String melody;

        Music(String name, String melody) {
            this.name = name;
            this.melody = melody;
        }

        // time이 같을 경우 인덱스로 비교하는 커스텀 정렬 코드 작성
        @Override
        public int compareTo(Music m) {
            // melody의 길이(재생된 시간)가 같다면
            if (this.melody.length() == m.melody.length()) {
                // 먼저 입력된 음악 제목이 우선
                return Integer.compare(list.indexOf(this), list.indexOf(m));
            }
            // melody의 길이(재생된 시간)이 긴 음악 제목이 우선
            return Integer.compare(m.melody.length(), this.melody.length());
        }
    }

    // list(일치하는 음악 정보를 저장할 리스트)
    static List<Music> list;

    // m(네오가 기억하는 멜로디를 담은 문자열)
    // musicinfos(방송된 곡의 정보를 담고 있는 배열)
    public String solution(String m, String[] musicinfos) {
        list = new ArrayList<>();
        // m의 멜로디를 변환
        m = changeMelody(m);
        // 일치하는 음악 저장하기 함수(m, musicinfos)
        saveCorrectMusic(m, musicinfos);
        // list가 비어있다면
        if (list.isEmpty())
            // (None) 반환
            return "(None)";
        // list 정렬
        Collections.sort(list);
        // list.get(0)의 name을 반환
        return list.get(0).name;
    }

    // 일치하는 음악 저장하기 함수
    private void saveCorrectMusic(String m, String[] infos) {
        for (String info : infos) {
            // arr(info의 정보를 담은 문자열 배열)
            String[] arr = info.split(",");
            // time(음악 재생 시간) = 음악 재생 시간 구하기 함수(음악이 시작한 시각, 끝난 시각)
            int time = calculateTime(arr[0], arr[1]);
            // name(음악 제목)
            String name = arr[2];
            // melody(음악 악보)
            String melody = arr[3];
            // melody의 멜로디를 변환
            melody = changeMelody(melody);
            // sb(StringBuilder)에 time만큼 melody를 반복하여 저장
            StringBuilder sb = new StringBuilder();
            for (int i = 0, j = 0; i < time; i++, j = (j + 1) % melody.length()) {
                sb.append(melody.charAt(j));
            }
            melody = sb.toString();
            // melody에 m이 포함된다면
            if (melody.contains(m))
                // 리스트에 일치하는 음악 저장
                list.add(new Music(name, melody));
        }
    }

    // 멜로디 변환하기 함수
    private String changeMelody(String melody) {
        // #이 붙은 음계를 다른 음으로 변환
        melody = melody.replaceAll("C#", "H");
        melody = melody.replaceAll("D#", "I");
        melody = melody.replaceAll("F#", "J");
        melody = melody.replaceAll("G#", "K");
        melody = melody.replaceAll("A#", "L");
        // 변환된 멜로디를 반환
        return melody;
    }

    // 음악 재생 시간 구하기 함수
    private int calculateTime(String time1, String time2) {
        // start(음악이 시작한 시간 초)
        int start = 60 * Integer.parseInt(time1.substring(0, 2)) + Integer.parseInt(time1.substring(3));
        // end(끝난 시간 초)
        int end = 60 * Integer.parseInt(time2.substring(0, 2)) + Integer.parseInt(time2.substring(3));
        // end - start 반환
        return end - start;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L031_17683 solution = new L031_17683();

        String m = "ABC";
        String[] musicinfos = {
                "12:00,12:14,HELLO,C#DEFGAB",
                "13:00,13:05,WORLD,ABCDEF" };

        String result = solution.solution(m, musicinfos);

        System.out.println(result);
    }
}
