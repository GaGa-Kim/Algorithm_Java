package Programmers.LV_1;

/**
 * 86051) 없는_숫자_더하기
 */
public class L026_86051 {
    // numbers(0부터 9까지의 숫자 중 일부가 들어있는 정수 배열)
    public int solution(int[] numbers) {
        // answer(찾을 수 없는 0부터 9까지의 숫자의 합)
        int answer = 0;
        // arr(0부터 9까지의 정수의 개수를 담는 정수 배열)
        int[] arr = new int[10];
        // 정수 배열에 숫자 저장
        for (int i = 0; i < numbers.length; i++) {
            arr[numbers[i]]++;
        }
        // 정수 배열이 0인 인덱스끼리 합산
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0)
                answer += i;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L026_86051 solution = new L026_86051();

        int[] numbers = { 1, 2, 3, 4, 6, 7, 8, 0 };

        int result = solution.solution(numbers);

        System.out.println(result);
    }
}
