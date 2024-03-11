package Programmers.LV_3;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 12987) 숫자_게임
 */
public class L015_12987 {
    // A(A 팀원들이 부여받은 수)
    // B(B 팀원들이 부여받은 수)
    public int solution(int[] A, int[] B) {
        // answer(B 팀원들이 얻을 수 있는 최대 승점)
        int answer = 0;
        // apq(A를 저장할 최대값 우선순위 큐)
        PriorityQueue<Integer> apq = new PriorityQueue<>(Collections.reverseOrder());
        // bpq(B를 저장할 최대값 우선순위 큐)
        PriorityQueue<Integer> bpq = new PriorityQueue<>(Collections.reverseOrder());
        // apq와 bpq에 A, B 배열 값 저장
        for (int i = 0; i < B.length; i++) {
            apq.add(A[i]);
            bpq.add(B[i]);
        }
        for (int i = 0; i < B.length; i++) {
            // a(apq의 최대값)
            int a = apq.peek();
            // b(bpq의 최대값)
            int b = bpq.peek();
            // b가 a보다 크다면
            if (a < b) {
                // answer 증가
                answer++;
                // bpq에서 b 제거
                bpq.remove();
            }
            // apq에서 a 제거
            apq.remove();
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L015_12987 solution = new L015_12987();

        int[] A = { 5, 1, 3, 7 };
        int[] B = { 2, 2, 6, 8 };

        int result = solution.solution(A, B);

        System.out.println(result);
    }
}