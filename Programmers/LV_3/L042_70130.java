package Programmers.LV_3;

/**
 * 70130) 스타_수열
 */
public class L042_70130 {
    // a(1차원 정수 배열)
    public int solution(int[] a) {
        // count(각 원소의 개수를 담은 배열)
        int[] count = new int[a.length];
        // a 배열을 돌면서 각 원소에 따른 개수를 증가시킴
        for (int num : a) {
            count[num]++;
        }
        // answer(a의 모든 부분 수열 중에서 가장 길이가 긴 스타 수열의 집합 개수)
        int answer = 0;
        for (int i = 0; i < count.length; i++) {
            // count[i]의 개수가 answer보다 작다면
            if (count[i] <= answer) {
                // 더 긴 스타 수열이 될 수 없으므로 넘어감
                continue;
            }
            // n(현재 스타 수열의 집합 개수)
            int n = 0;
            for (int j = 0; j < a.length - 1; j++) {
                // 현재 집합이 i를 포함하지 않는다면
                if (a[j] != i && a[j + 1] != i) {
                    // 교집합의 원소의 개수가 1 이상이 될 수 없어 스타 수열이 될 부분 수열에 포함되지 않으므로 넘어감
                    continue;
                }
                // 집합의 두 원소가 같다면
                if (a[j] == a[j + 1]) {
                    // 스타 수열이 될 부분 수열에 포함되지 않으므로 넘어감
                    continue;
                }
                // 두 조건을 만족한다면 스타 수열이 될 부분 수열에 포함될 수 있으므로 n 증가
                n++;
                // 두 원소씩 살펴봐야 하므로 j 하나 더 증가
                j++;
            }
            // n이 answer보다 더 길다면 이로 갱신
            answer = Math.max(answer, n);
        }
        // 스타 수열의 길이의 집합 개수 * 2를 반환
        return answer * 2;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L042_70130 solution = new L042_70130();

        int[] a = { 5, 2, 3, 3, 5, 3 };

        int result = solution.solution(a);

        System.out.println(result);
    }
}
