package Programmers.LV_2;

/**
 * 150369) 택배_배달과_수거하기
 */
public class L092_150369 {
    // cap(트럭에 실을 수 있는 재활용 택배 상자의 최대 개수)
    // n(배달할 집의 개수)
    // deliveries(각 집에 배달할 재활용 택배 상자의 개수)
    // pickups(각 집에서 수거할 빈 재활용 택배 상자의 개수)
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // answer(트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리)
        long answer = 0;
        // deliver(배달할 택배 상자 개수)
        int deliver = 0;
        // pickup(수거할 재활용 택배 상자 개수)
        int pickup = 0;
        // 가장 먼 집부터
        for (int i = n - 1; i >= 0; i--) {
            // count(현재 집 방문 횟수)
            int count = 0;
            // 현재 집에서 배달할 택배 상자와 수거할 재활용 택배 상자 개수만큼 증가
            deliver += deliveries[i];
            pickup += pickups[i];
            // 한 집에 대해서 택배를 모두 배달하고 재활용을 모두 수거할 동안
            while (deliver > 0 || pickup > 0) {
                // count 증가
                count++;
                // cap만큼 deliver, pickup 감소
                deliver -= cap;
                pickup -= cap;
            }
            // (i + 1) * count * 2의 거리만큼 answer 증가
            answer += (i + 1) * count * 2;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L092_150369 solution = new L092_150369();

        int cap = 4;
        int n = 5;
        int[] deliveries = { 1, 0, 3, 1, 2 };
        int[] pickups = { 0, 3, 0, 4, 0 };

        long result = solution.solution(cap, n, deliveries, pickups);

        System.out.println(result);
    }
}
