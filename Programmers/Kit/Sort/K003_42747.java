package Programmers.Kit.Sort;

import java.util.Arrays;

/**
 * 42747) H-Index
 */
public class K003_42747 {
    // citations(어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열)
    public int solution(int[] citations) {
        // citations 정렬
        Arrays.sort(citations);
        // answer(h의 최댓값) = h 찾기
        int answer = findH(citations);
        // answer 반환
        return answer;
    }

    // h 찾기
    private int findH(int[] citations) {
        for (int i = 0; i < citations.length; i++) {
            // count(h가 i일 때, 논문 수)
            int count = citations.length - i;
            // 각 논문의 인용 수가 논문 수보다 같거나 클 경우
            // citations[i]번 이상 인용된 논문이 count편인지 확인
            if (citations[i] >= count) {
                // 논문 수 리턴
                return count;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        K003_42747 solution = new K003_42747();

        int[] citations = { 3, 0, 6, 1, 5 };

        int result = solution.solution(citations);

        System.out.println(result);
    }
}
