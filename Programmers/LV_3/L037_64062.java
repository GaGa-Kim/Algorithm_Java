package Programmers.LV_3;

/**
 * 64062) 징검다리_건너기
 */
public class L037_64062 {
    // stones(디딤돌에 적힌 숫자가 순서대로 담긴 배열)
    // k(한 번에 건너뛸 수 있는 디딤돌의 최대 칸수)
    public int solution(int[] stones, int k) {
        // answer(징검다리를 건널 수 있는 최대 니니즈 친구들의 수)
        int answer = 0;
        // min(징검다리를 건널 수 있는 최소 니니즈 친구들의 수)
        int min = 0;
        // max(징검다리를 건널 수 있는 최대 니니즈 친구들의 수)
        int max = Integer.MAX_VALUE;
        // 이진 탐색
        while (min <= max) {
            // mid(징검다리를 건널 수 있는 니니즈 친구들 수의 중간값)
            int mid = (min + max) / 2;
            // 징검다리를 mid명 만큼 건널 수 있다면
            if (possiblePass(mid, stones, k)) {
                // min을 mid + 1로 갱신하여 더 큰 오른쪽 데이터 값에서 친구들의 수를 찾음
                min = mid + 1;
                answer = mid;
            }
            // 징검다리를 mid만큼 건널 수 없다면
            else {
                // max을 mid - 1로 갱신하여 더 작은 왼쪽 데이터 값에서 친구들의 수를 찾음
                max = mid - 1;
            }
        }
        // answer 반환
        return answer;
    }

    // 징검다리를 mid명 만큼 건널 수 있는지 확인
    private boolean possiblePass(int mid, int[] stones, int k) {
        // count(돌과 돌 사이의 건너 뛰어야 하는 돌의 개수)
        int count = 0;
        for (int stone : stones) {
            // stone이 mid보다 작다면
            if (stone < mid) {
                // 바로 건널 수 없어 건너 뛰어야 하므로 count 증가
                count++;
                // count가 k보다 크거나 같다면
                if (count >= k) {
                    // 건너 뛰어도 갈 수 없으므로 false 반환
                    return false;
                }
            }
            // 다음 stone이 mid보다 크다면
            else {
                // 바로 건널 수 있으므로 count를 0으로 초기화
                count = 0;
            }
        }
        // true 반환
        return true;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L037_64062 solution = new L037_64062();

        int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
        int k = 3;

        int result = solution.solution(stones, k);

        System.out.println(result);
    }
}