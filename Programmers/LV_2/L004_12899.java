package Programmers.LV_2;

/**
 * 12899) 124_나라의_숫자
 */
public class L004_12899 {
    // n(자연수)
    public String solution(int n) {
        // answer(n을 124 나라에서 사용하는 숫자로 바꾼 값 StringBuilder)
        StringBuilder answer = new StringBuilder();
        // numbers(4, 1, 2를 저장한 배열)
        String[] numbers = { "4", "1", "2" };
        // n이 0보다 클 동안
        while (n > 0) {
            // remain(나머지 값) 계산
            int remain = n % 3;
            // n을 3으로 나눈 값의 나머지를 answer의 앞에 저장
            answer.insert(0, numbers[remain]);
            // (n - 1) / 3으로 n을 갱신
            n = (n - 1) / 3;
        }
        // answer를 문자열로 변환하여 반환
        return answer.toString();
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L004_12899 solution = new L004_12899();

        int n = 1;

        String result = solution.solution(n);

        System.out.println(result);
    }
}
