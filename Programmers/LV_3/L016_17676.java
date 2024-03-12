package Programmers.LV_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 17676) 추석_트래픽
 */
public class L016_17676 {
    // lines(로그 데이터)
    public int solution(String[] lines) {
        // answer(초당 최대 처리량)
        int answer = 0;
        // lineList(로그 데이터의 시작하는 시점과 끝나는 시점을 담은 리스트)
        List<int[]> lineList = new ArrayList<>();
        for (String line : lines) {
            // end(끝나는 시점) = (hh * 3600 + mm * 60) * 1000 + ss.sss * 1000
            int end = (int) ((Integer.parseInt(line.substring(11, 13)) * 3600
                    + Integer.parseInt(line.substring(14, 16)) * 60) * 1000
                    + Double.parseDouble(line.substring(17, 23)) * 1000);
            // processTime(처리 시간) = 최대 소수점 셋째 자리까지 기록된 처리 시간 * 1000
            int processTime = (int) (Double.parseDouble(line.substring(24, line.length() - 1)) * 1000);
            // start(시작하는 시점) = 처리시간은 시작시간과 끝시간을 포함하므로 end - processTime + 1
            int start = end - processTime + 1;
            // lineList에 start와 end 저장
            lineList.add(new int[] { start, end });
        }
        for (int i = 0; i < lineList.size(); i++) {
            // count(처리량)
            int count = 1;
            for (int j = i + 1; j < lineList.size(); j++) {
                // lineList의 i번째 end + 1000이 lineList의 j번째 start보다 크다면
                if (lineList.get(i)[1] + 1000 > lineList.get(j)[0])
                    // 이전 로그의 끝나는 시점보다 빨리 시작하는 로그이므로 함께 처리할 수 있어 count 증가
                    count++;
            }
            // answer을 Math.max(answer, count)로 갱신
            answer = Math.max(answer, count);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L016_17676 solution = new L016_17676();

        String[] lines = {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s" };

        int result = solution.solution(lines);

        System.out.println(result);
    }
}
