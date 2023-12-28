package Programmers.LV_1;

/**
 * 12944) 평균_구하기
 */
public class L026_12944 {
    // arr(정수를 담고 있는 배열)
    public double solution(int[] arr) {
        // answer(정수를 담고 있는 배열 arr의 평균값)
        double answer = 0;
        // answer에 각 정수를 더하기
        for (int i = 0; i < arr.length; i++) {
            answer += arr[i];
        }
        // answer = answer / arr의 크기
        answer = answer / arr.length;
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L026_12944 solution = new L026_12944();

        int[] arr = { 1, 2, 3, 4 };

        double result = solution.solution(arr);

        System.out.println(result);
    }
}
