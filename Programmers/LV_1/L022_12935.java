package Programmers.LV_1;

import java.util.Arrays;

/**
 * 12935) 제일_작은_수_제거하기
 */
public class L022_12935 {
    // arr(정수를 저장한 배열)
    public int[] solution(int[] arr) {
        // arr의 크기가 1이라면
        if (arr.length == 1) {
            // 배열에 -1을 담아 반환
            return new int[] { -1 };
        }
        // arr의 크기가 1보다 크다면
        else {
            // min(제일 작은 수)
            int min = Integer.MAX_VALUE;
            // min을 더 작은 수로 갱신
            for (int i : arr) {
                min = Math.min(min, i);
            }
            // answer(가장 작은 수를 제거한 배열)
            int[] answer = new int[arr.length - 1];
            // index(숫자 저장 위치 인덱스)
            int index = 0;
            for (int i = 0; i < arr.length; i++) {
                // arr[i]가 min과 같다면
                if (arr[i] == min)
                    // 저장하지 않고 통과
                    continue;
                // answer[index]에 arr[i] 저장 후 index 증가
                answer[index++] = arr[i];
            }
            // answer 반환
            return answer;
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L022_12935 solution = new L022_12935();

        int[] arr = { 4, 3, 2, 1 };

        int[] result = solution.solution(arr);

        System.out.println(Arrays.toString(result));
    }
}
