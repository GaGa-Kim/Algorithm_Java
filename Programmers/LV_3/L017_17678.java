package Programmers.LV_3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 17678) 셔틀버스
 */
public class L017_17678 {
    // n(셔틀 운행 횟수)
    // t(셔틀 운행 간격)
    // m(한 셔틀에 탈 수 있는 최대 크루 수)
    // timetable(크루가 대기열에 도착하는 시각을 모은 배열)
    public String solution(int n, int t, int m, String[] timetable) {
        // answer(콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각)
        int answer = 0;
        // shuttles(셔틀 간격당 탈 수 있는 크루들의 도착 시간들을 담은 리스트)
        List<List<Integer>> shuttles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            shuttles.add(new ArrayList<>());
        }
        // crews(크루들의 도착 시간을 담은 최소값 우선순위 큐)
        PriorityQueue<Integer> crews = new PriorityQueue<>();
        for (String table : timetable) {
            // time(도착 시간을 분으로 변환한 값)
            int time = Integer.parseInt(table.substring(0, 2)) * 60;
            time += Integer.parseInt(table.substring(3));
            // crews에 time 저장
            crews.add(time);
        }
        // lastTime(사무실로 갈 수 있는 도착 시각)
        int lastTime = 9 * 60;
        for (int i = 0; i < n; i++) {
            // crews가 비어있지 않는 동안
            while (!crews.isEmpty()) {
                // crew(한 크루의 도착 시간)
                int crew = crews.poll();
                // crew가 lastTime보다 작거나 같으며 shuttles.get(i)의 크기가 m보다 작다면
                if (crew <= lastTime && shuttles.get(i).size() < m) {
                    // 현재 셔틀에 crew를 태울 수 있으므로 shuttles.get(i).add(crew)
                    shuttles.get(i).add(crew);
                    // 셔틀에 탈 수 있는 다른 크루보다 먼저 도착해야하므로 현재 셔틀에 탈 수 있는 마지막 시간 - 1로 answer 갱신
                    answer = crew - 1;
                }
                // crew가 lastTime보다 크거나 shuttles.get(i)의 크기가 m보다 크다면
                else {
                    // 현재 셔틀에 crew를 태울 수 없으므로 다음 셔틀에 태우기 위해 crews에 crew 다시 저장
                    crews.add(crew);
                    // 현재 셔틀 종료
                    break;
                }
            }
            // lastTime을 다음 셔틀 운행 간격인 t만큼 증가
            lastTime += t;
        }
        // 마지막 셔틀 버스에 탄 인원이 m보다 작다면
        if (shuttles.get(n - 1).size() < m) {
            // 무조건 탑승 가능해 버스 도착 시간과 동시에 와도 되므로 answer을 lastTime - t로 갱신
            answer = lastTime - t;
        }
        // answer을 HH:MM 형태로 변환하여 반환
        String HH = String.format("%02d", answer / 60);
        String MM = String.format("%02d", answer % 60);
        return HH + ":" + MM;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L017_17678 solution = new L017_17678();

        int n = 1;
        int t = 1;
        int m = 5;
        String[] timetable = { "08:00", "08:01", "08:02", "08:03" };

        String result = solution.solution(n, t, m, timetable);

        System.out.println(result);
    }
}
