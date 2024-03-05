package Programmers.LV_3;

/**
 * 12920) 선입_선출_스케줄링
 */
public class L009_12920 {
    // n(처리해야 될 작업의 수)
    // cores(각 코어의 처리시간)
    public int solution(int n, int[] cores) {
        // answer(마지막 작업을 처리하는 코어의 번호)
        int answer = 0;
        // minTime(코어들을 이용했을 때 걸리는 최소 작업 시간)
        int minTime = 1;
        // maxTime(코어들을 이용했을 때 걸리는 최대 작업 시간)
        int maxTime = 10000 * n;
        // time(코어들을 이용했을 때 걸리는 작업 시간)
        int time = 0;
        // 이진 탐색
        while (minTime <= maxTime) {
            // midTime(코어들을 이용했을 때 걸리는 중간값 작업 시간)
            int midTime = (minTime + maxTime) / 2;
            // jobCount(현재 midTime에 한 작업량)
            int jobCount = calculateJobCount(midTime, cores);
            // jobCount가 n보다 크거나 같을 경우
            if (jobCount >= n) {
                // midTime에 이미 n 이상 작업을 마칠 수 있으므로 midTime보다 더 짧은 시간으로 다시 탐색
                maxTime = midTime - 1;
                // time을 midTime로 갱신
                time = midTime;
            }
            // jobCount가 n보다 작을 경우
            else {
                // midTime에 n 작업을 마칠 수 없으므로 midTime보다 긴 시간으로 다시 탐색
                minTime = midTime + 1;
            }
        }
        // completedJobCount(time - 1 시간까지 처리한 작업량)
        int completedJobCount = calculateJobCount(time - 1, cores);
        // index(마지막 작업을 처리하는 코어의 번호 인덱스)
        int index = 1;
        for (int core : cores) {
            // time일 때 코어의 처리 시간으로 나누어 떨어진다면
            if (time % core == 0) {
                // 이 코어로 작업을 처리할 수 있으므로 completedJobCount 증가
                completedJobCount++;
            }
            // completedJobCount가 n과 같다면
            if (completedJobCount == n) {
                // 해당 코어가 마지막 작업을 수행하므로 answer을 index으로 갱신
                answer = index;
                break;
            }
            // 해당 코어가 마지막 작업을 수행하지 않으므로 다음 코어로 index 증가
            index++;
        }
        // answer 반환
        return answer;
    }

    // 시간에 따라 코어가 처리할 수 있는 작업량 계산
    private int calculateJobCount(int midTime, int[] cores) {
        // count(midTime까지 코어가 처리할 수 있는 작업량)
        // midTime까지 코어가 처리할 수 있는 작업량 합산
        int count = cores.length; // 0초에 모든 코어에 cores의 길이만큼의 작업이 수행
        for (int core : cores)
            count += midTime / core;
        return count;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L009_12920 solution = new L009_12920();

        int n = 6;
        int[] cores = { 1, 2, 3 };

        int result = solution.solution(n, cores);

        System.out.println(result);
    }
}
