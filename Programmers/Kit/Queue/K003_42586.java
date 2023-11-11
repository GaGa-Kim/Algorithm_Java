package Programmers.Kit.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 42586) 기능개발
 */
public class K003_42586 {
    // progresses(배포되어야 하는 순서대로 작업의 진도)
    // speeds(각 작업의 개발 속도)
    public int[] solution(int[] progresses, int[] speeds) {
        // publish(각 배포마다 몇 개의 기능이 배포되는지)
        ArrayList<Integer> publish = new ArrayList<>();
        // queue(각 작업의 배포 가능한 일자를 담을 queue)
        Queue<Integer> queue = new LinkedList<>();
        // queue에 작업 시간 저장
        for (int i = 0; i < progresses.length; i++) {
            // 남은 작업 시간이 각 작업의 개발 속도로 나누어 진다면
            if ((100 - progresses[i]) % speeds[i] == 0)
                queue.add((100 - progresses[i]) / speeds[i]);
            else
                queue.add((100 - progresses[i]) / speeds[i] + 1);
        }
        // 앞의 작업
        int previous = queue.poll();
        // count(함께 배포될 수 있는 기능의 수)
        int count = 1;
        while (!queue.isEmpty()) {
            // 함께 배포될 수 있는 기능이 있다면
            // 앞의 작업이 뒤의 작업보다 늦게 끝날 경우 같이 배포 가능
            if (previous >= queue.peek()) {
                count++;
                queue.poll();
            }
            // 함께 배포될 수 있는 기능이 없다면
            else {
                publish.add(count);
                count = 1;
                previous = queue.poll();
            }
        }
        publish.add(count);
        // publish를 int[]형으로 변환하여 반환
        int[] answer = new int[publish.size()];
        for (int i = 0; i < publish.size(); i++) {
            answer[i] = publish.get(i);
        }
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        K003_42586 solution = new K003_42586();

        int[] progresses = { 93, 30, 55 };
        int[] speeds = { 1, 30, 5 };
        int[] result = solution.solution(progresses, speeds);

        System.out.println(Arrays.toString(result));
    }
}
