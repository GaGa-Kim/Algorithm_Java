package Programmers.LV_3;

/**
 * 68646) 풍선_터트리기
 */
public class L041_68646 {
    // a(일렬로 나열된 풍선들의 번호가 담긴 배열)
    public int solution(int[] a) {
        // 풍선이 총 1개일 경우
        if (a.length == 1) {
            // 모든 풍선이 살아남을 수 있으므로 1을 반환
            return 1;
        }
        // leftMin(임의의 풍선 i에 대한 왼쪽 그룹의 최솟값)
        int[] leftMin = new int[a.length];
        // l(왼쪽 그룹의 값 중 최솟값)
        int l = a[0];
        // 임의의 풍선 i일 때 왼쪽 그룹의 원소 최솟값 저장
        for (int i = 1; i < a.length - 1; i++) {
            l = Math.min(l, a[i]);
            leftMin[i] = l;
        }
        // rightMin(임의의 풍선 i에 대한 오른쪽 그룹의 최솟값)
        int[] rightMin = new int[a.length];
        // r(오른쪽 그룹의 값 중 최솟값)
        int r = a[a.length - 1];
        // 임의의 풍선 i일 때 오른쪽 그룹의 원소 최솟값 저장
        for (int i = a.length - 2; i > 0; i--) {
            r = Math.min(r, a[i]);
            rightMin[i] = r;
        }
        // answer(규칙대로 풍선들을 1개만 남을 때까지 터트렸을 때 최후까지 남기는 것이 가능한 풍선들의 개수)
        int answer = 2; // 풍선이 2개 이상일 경우 무조건 2개는 남으므로 2로 초기화
        // 임의의 풍선 i를 모두 탐색하면서
        for (int i = 1; i < a.length - 1; i++) {
            // 임의의 풍선 i의 양쪽 그룹의 최솟값이 모두 i보다 작다면
            if (a[i] > leftMin[i] && a[i] > rightMin[i]) {
                // 가운데 풍선인 i는 살아남을 수 없으므로 건너뜀
                continue;
            }
            // 양쪽보다 모두 작은 경우가 아니라면 살아남을 수 있으므로 answer 증가
            answer++;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L041_68646 solution = new L041_68646();

        int[] a = { 9, -1, -5 };

        int result = solution.solution(a);

        System.out.println(result);
    }
}
