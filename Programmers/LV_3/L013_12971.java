package Programmers.LV_3;

/**
 * 12971) 스티커_모으기(2)
 */
public class L013_12971 {
    // sticker(원형으로 연결된 스티커의 각 칸에 적힌 숫자가 순서대로 들어있는 배열)
    public int solution(int sticker[]) {
        // answer(스티커를 뜯어내어 얻을 수 있는 숫자의 합의 최댓값)
        int answer = 0;
        // sticker의 길이가 1일 경우
        if (sticker.length == 1)
            // 무조건 첫 번째 스티커만 사용 가능하므로 sticker의 첫 번째 값 반환
            return sticker[0];
        // D1(첫 번째 스티커부터 사용하여 마지막 스티커 - 1까지 i번째 스티커를 뜯어내어 얻을 수 있는 숫자의 합을 담은 DP 테이블)
        int[] D1 = new int[sticker.length];
        // 첫 번째 스티커를 사용했으므로 D1[0]은 sticker[0]
        D1[0] = sticker[0];
        // 첫 번째 스티커를 사용했으므로 두 번째 스티커는 사용하지 못하므로 D1[1]은 D1[0]
        D1[1] = D1[0];
        // 이전 위치의 스티커를 사용하는 것과 이전 위치의 스티커를 사용하지 않는 것 중에서 더 큰 값으로 갱신
        for (int i = 2; i < sticker.length - 1; i++) {
            D1[i] = Math.max(D1[i - 1], sticker[i] + D1[i - 2]);
        }
        // answer을 D1[sticker의 길이 - 2]로 갱신
        answer = D1[sticker.length - 2];
        // D2(두 번째 스티커부터 사용하여 마지막 스티커까지 i번째 스티커를 뜯어내어 얻을 수 있는 숫자의 합을 담은 DP 테이블)
        int[] D2 = new int[sticker.length];
        // 첫 번째 스티커는 사용하지 못하므로 D2[0]은 0
        D2[0] = 0;
        // 두 번째 스티커를 사용했으므로 D2[1]은 sticker[1]
        D2[1] = sticker[1];
        // 이전 위치의 스티커를 사용하는 것과 이전 위치의 스티커를 사용하지 않는 것 중에서 더 큰 값으로 갱신
        for (int i = 2; i < sticker.length; i++) {
            D2[i] = Math.max(D2[i - 1], sticker[i] + D2[i - 2]);
        }
        // answer을 answer과 D2[sticker의 길이 - 1] 중 더 큰 값으로 갱신
        answer = Math.max(answer, D2[sticker.length - 1]);
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L013_12971 solution = new L013_12971();

        int sticker[] = { 14, 6, 5, 11, 3, 9, 2, 10 };

        int result = solution.solution(sticker);

        System.out.println(result);
    }
}
