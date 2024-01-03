package Programmers.LV_2;

/**
 * 12911) 다음_큰_숫자
 */
public class L001_12911 {
    // n(자연수)
    public int solution(int n) {
        // answer(조건을 만족하는 수 중 가장 작은 수)
        int answer = 0;
        // one(n을 2진수로 변환했을 때 1의 개수)
        int one = convertAndCount(n);
        for (int i = n + 1; i < 1000000; i++) {
            // 이진수 변환 및 1의 개수 세기 함수(i)가 one과 동일하다면
            if (convertAndCount(i) == one) {
                // answer을 i로 갱신한 후 종료
                answer = i;
                break;
            }
        }
        // answer 반환
        return answer;
    }

    // 이진수 변환 및 1의 개수 세기 함수
    private int convertAndCount(int n) {
        // s(이진수로 변환한 문자열)
        String s = Integer.toBinaryString(n);
        // count(1의 개수)
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            // s.charAt(i)가 1이라면
            if (s.charAt(i) == '1')
                // count 증가
                count++;
        }
        // count 반환
        return count;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L001_12911 solution = new L001_12911();

        int n = 78;

        int result = solution.solution(n);

        System.out.println(result);
    }
}
