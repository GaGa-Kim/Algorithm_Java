package Programmers.LV_1;

/**
 * 250137) 붕대_감기
 */
public class L080_250137 {
    // bandage(시전 시간, 1초당 회복량, 추가 회복량을 담은 1차원 정수 배열)
    // health(최대 체력)
    // attacks(몬스터의 공격 시간과 피해량을 담은 2차원 정수 배열)
    public int solution(int[] bandage, int health, int[][] attacks) {
        // answer(현재 체력) = health
        int answer = health;
        // bandage_time(붕대 감기 소요 시간)
        int bandage_time = 0;
        // now_time(게임 실행 시간)
        int now_time = 0;
        // index(attacks의 위치 인덱스)
        int index = 0;
        // attacks의 마지막 공격의 시간까지
        for (int i = 0; i <= attacks[attacks.length - 1][0]; i++) {
            // now_time이 attacks[index][0]의 시간과 동일하다면
            if (now_time == attacks[index][0]) {
                // answer에서 현재 피해량만큼 체력 소비
                answer -= attacks[index][1];
                // bandage_time을 0으로 초기화
                bandage_time = 0;
                // index 증가
                index++;
            }
            // now_time이 attacks[index][0]의 시간과 다르다면
            else {
                // answer에 초당 회복량만큼 증가
                answer += bandage[1];
                // bandage_time 증가
                bandage_time++;
                // bandage_time이 시전 시간과 동일하다면
                if (bandage_time == bandage[0]) {
                    // answer에 추가 회복량만큼 증가
                    answer += bandage[2];
                    // bandage_time을 0으로 초기화
                    bandage_time = 0;
                }
                // answer가 health보다 크다면
                if (answer > health)
                    // answer을 health로 초기화
                    answer = health;
            }
            // now_time 증가
            now_time++;
            // answer이 0보다 작다면
            if (answer <= 0)
                // 게임 종료 전에 체력이 0이므로 -1 반환
                return -1;
        }
        // 게임 종료 전까지 체력이 남아 있다면 answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L080_250137 solution = new L080_250137();

        int[] bandage = { 5, 1, 5 };
        int health = 30;
        int[][] attacks = { { 2, 10 }, { 9, 15 }, { 10, 5 }, { 11, 5 } };

        int result = solution.solution(bandage, health, attacks);

        System.out.println(result);
    }
}
