package Programmers.LV_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 12936) 줄_서는_방법
 */
public class L006_12936 {
    // n(사람의 수)
    // k(자연수)
    public int[] solution(int n, long k) {
        // answer(사람을 나열 하는 방법을 사전 순으로 나열 했을 때, k번째 방법)
        int[] answer = new int[n];
        // list(1번부터 n번 번호)
        List<Integer> list = new ArrayList<>();
        // count(줄을 서는 방법의 수)
        long count = 1;
        // list와 count 계산
        for (int i = 1; i <= n; i++) {
            list.add(i);
            count *= i;
        }
        // 배열의 시작이 0이므로 이로 인한 자릿수를 맞추기 위해 k 감소
        k--;
        // index(번호 위치)
        int index = 0;
        // index가 n보다 작을 동안
        while (index < n) {
            // 단위(전체 개수 / 요소 개수) 구하기
            count = count / (n - index);
            // value(index번째 숫자)
            int value = (int) (k / count);
            // answer에 index번째 숫자 저장 후 삭제
            answer[index++] = list.get(value);
            list.remove(value);
            // k를 빠진 단위 만큼 변경
            k %= count;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L006_12936 solution = new L006_12936();

        int n = 3;
        long k = 5;

        int[] result = solution.solution(n, k);

        System.out.println(Arrays.toString(result));
    }
}
