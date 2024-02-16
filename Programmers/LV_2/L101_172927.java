package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 172927) 광물_캐기
 */
public class L101_172927 {
    class Mineral {
        // diamond(광물 5개를 다이아몬드 곡괭이로 캘 경우의 피로도)
        int diamond;
        // iron(광물 5개를 철 곡괭이로 캘 경우의 피로도)
        int iron;
        // stone(광물 5개를 돌 곡괭이로 캘 경우의 피로도)
        int stone;

        public Mineral(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
    }

    // answer(최소한의 피로도)
    static int answer;
    // arr(곡괭이와 광물에 따른 피로도 배열)
    static int[][] arr = new int[][] { { 1, 1, 1 }, { 5, 1, 1 }, { 25, 5, 1 } };
    // list(곡괭이에 따른 광물들의 피로도를 담은 리스트)
    static List<Mineral> list;

    // picks(갖고 있는 곡괭이의 개수)
    // minerals(광물들의 순서)
    public int solution(int[] picks, String[] minerals) {
        answer = 0;
        list = new ArrayList<>();
        // 각 곡괭이에 따른 피로도 계산하기 함수
        calculateFatigue(picks, minerals);
        // 곡괭이 선택하기 함수
        selectPicks(picks);
        // answer 반환
        return answer;
    }

    // 각 곡괭이에 따른 피로도 계산하기 함수
    private void calculateFatigue(int[] picks, String[] minerals) {
        // picks_size(곡괭이의 개수)
        int picks_size = Arrays.stream(picks).sum();
        // 광물을 5개씩 계산
        for (int i = 0; i < minerals.length; i += 5) {
            // picks_size가 0이라면
            if (picks_size == 0)
                // 모든 곡괭이를 사용했으므로 종료
                break;
            // diamond(다이아몬드 곡괭이로 캘 경우의 피로도)
            int diamond = 0;
            // iron(철 곡괭이로 캘 경우의 피로도)
            int iron = 0;
            // stone(돌 곡괭이로 캘 경우의 피로도)
            int stone = 0;
            // 광물 5개에 따른 각 곡괭이에 대한 피로도 계산
            for (int j = i; j < i + 5; j++) {
                // j가 minerals의 길이와 같다면
                if (j == minerals.length)
                    // 마지막 광물이므로 종료
                    break;
                // mineral(광물의 인덱스)
                int mineral = 0; // 처음은 다이아몬드인 0으로 초기화
                // mineral[j]가 iron이라면
                if (minerals[j].equals("iron"))
                    // mineral을 철인 1로 초기화
                    mineral = 1;
                // mineral[j] stone이라면
                else if (minerals[j].equals("stone"))
                    // mineral을 돌인 2로 초기화
                    mineral = 2;
                // 광물에 따른 피로도 계산
                diamond += arr[0][mineral];
                iron += arr[1][mineral];
                stone += arr[2][mineral];
            }
            // list에 Mineral(diamond, iron, stone)을 저장
            list.add(new Mineral(diamond, iron, stone));
            // 곡괭이를 하나 사용했으므로 picks_size 감소
            picks_size--;
        }
    }

    // 곡괭이 선택하기 함수
    private void selectPicks(int[] picks) {
        // 피로도가 많이 증가하는 stone의 경우, 다이아몬드 곡괭이를 사용하기 위해
        // list를 stone을 사용했을 때의 피로도를 기준으로 내림차순 정렬
        list.sort(((o1, o2) -> (o2.stone - o1.stone)));
        for (Mineral m : list) {
            // 다이아몬드 곡괭이 남아있을 경우
            if (picks[0] > 0) {
                // answer을 다이아몬드 곡괭이로 캘 경우의 피로도만큼 증가
                answer += m.diamond;
                // 다이아몬드 곡괭이의 개수를 감소
                picks[0]--;
                // 남은 곡괭이로 계속 진행
                continue;
            }
            // 철 곡괭이 남아있을 경우
            if (picks[1] > 0) {
                // answer을 철 곡괭이로 캘 경우의 피로도만큼 증가
                answer += m.iron;
                // 철 곡괭이의 개수를 감소
                picks[1]--;
                // 남은 곡괭이로 계속 진행
                continue;
            }
            // 돌 곡괭이 남아있을 경우
            if (picks[2] > 0) {
                // answer을 돌 곡괭이로 캘 경우의 피로도만큼 증가
                answer += m.stone;
                // 돌 곡괭이의 개수를 감소
                picks[2]--;
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L101_172927 solution = new L101_172927();

        int[] picks = { 1, 3, 2 };
        String[] minerals = { "diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone" };

        int result = solution.solution(picks, minerals);

        System.out.println(result);
    }
}
