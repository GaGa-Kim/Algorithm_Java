package Programmers.LV_2;

import java.util.Arrays;
import java.util.Stack;

/**
 * 176962) 과제_진행하기
 */
public class L102_176962 {
    class Plan {
        // name(과제의 이름)
        String name;
        // start(과제의 시작 시간)
        int start;
        // playtime(과제를 마치는데 걸리는 시간)
        int playTime;

        public Plan(String name, String start, String playTime) {
            this.name = name;
            this.start = calculateTime(start);
            this.playTime = Integer.parseInt(playTime);
        }

        public int calculateTime(String start) {
            String[] time = start.split(":");
            return Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }
    }

    // plans(과제 계획을 담은 이차원 문자열 배열)
    public String[] solution(String[][] plans) {
        // answer(과제를 끝낸 순서대로의 이름)
        String[] answer = new String[plans.length];
        // 시작 시간으로 기준으로 plans을 정렬
        Arrays.sort(plans, (o1, o2) -> o1[1].compareTo(o2[1]));
        // stack(멈춰둔 과제를 저장할 스택)
        Stack<Plan> stack = new Stack<>();
        // index(answer 저장 인덱스)
        int index = 0;
        for (String[] plan : plans) {
            // nowPlan(현재 과제 Plan 클래스)
            Plan nowPlan = new Plan(plan[0], plan[1], plan[2]);
            // stack이 비어있지 않다면
            if (!stack.isEmpty()) {
                // recentlyPlan(가장 최근에 중지된 과제 Plan 클래스)
                Plan recentlyPlan = stack.pop();
                // recentlyPlan의 종료 시간이 nowPlan의 시작 시간보다 크다면
                if (recentlyPlan.start + recentlyPlan.playTime > nowPlan.start) {
                    // recentlyPlan의 과제를 모두 마칠 수 없으므로 걸리는 시간 갱신
                    recentlyPlan.playTime -= nowPlan.start - recentlyPlan.start;
                    // stack에 갱신된 recentlyPlan을 저장
                    stack.push(recentlyPlan);
                }
                // recentlyPlan의 종료 시간이 nowPlan의 시작 시간보다 작거나 같다면
                else {
                    // recentlyPlan의 과제를 모두 마칠 수 있으므로 answer에 recentlyPlan의 name 저장
                    answer[index++] = recentlyPlan.name;
                    // restTime(recentlyPlan과 nowPlan 사이의 남는 시간)
                    int restTime = nowPlan.start - recentlyPlan.start - recentlyPlan.playTime;
                    // restTime이 0보다 크며 stack이 비어있지 않을 동안 다른 중지된 과제를 처리
                    while (restTime > 0 && !stack.isEmpty()) {
                        // prePlan(중지된 이전 과제 Plan 클래스)
                        Plan prePlan = stack.pop();
                        // prePlan의 playtime이 restTime보다 크다면
                        if (prePlan.playTime > restTime) {
                            // prePlan의 과제를 모두 마칠 수 없으므로 걸리는 시간 갱신
                            prePlan.playTime -= restTime;
                            // restTime을 0으로 갱신
                            restTime = 0;
                            // stack에 갱신된 prePlan을 저장
                            stack.push(prePlan);
                        }
                        // prePlan의 playtime이 restTime보다 작거나 같다면
                        else {
                            // restTime을 prePlan의 걸리는 시간만큼 갱신
                            restTime -= prePlan.playTime;
                            // prePlan의 과제를 모두 마칠 수 있으므로 answer에 prePlan의 name 저장
                            answer[index++] = prePlan.name;
                        }
                    }
                }
            }
            // stack에 nowPlan 저장
            stack.push(nowPlan);
        }
        // stack이 비어있지 않는 동안
        while (!stack.isEmpty()) {
            // 순서대로 남은 과제를 완료할 수 있으므로 answer에 name 저장
            answer[index++] = stack.pop().name;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L102_176962 solution = new L102_176962();

        String[][] plans = {
                { "science", "12:40", "50" },
                { "music", "12:20", "40" },
                { "history", "14:00", "30" },
                { "computer", "12:30", "100" }
        };

        String[] result = solution.solution(plans);

        System.out.println(Arrays.toString(result));
    }
}
