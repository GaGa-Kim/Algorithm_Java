package Do_it;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 1744) 수_묶기
 */
public class D034_1744 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(카드 묶음 개수)
        int N = sc.nextInt();
        // plusPq(양수 우선순위 큐)
        PriorityQueue<Integer> plusPq = new PriorityQueue<>(Collections.reverseOrder());
        // minusPq(음수 우선순위 큐)
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();
        // one(1의 개수 카운트)
        int one = 0;
        // zero(0의 개수 카운트)
        int zero = 0;
        // 데이터를 4개의 그룹에 분리 저장하기
        for (int i = 0; i < N; i++) {
            int data = sc.nextInt();
            if (data > 1) {
                plusPq.add(data);
            } else if (data == 1) {
                one++;
            } else if (data == 0) {
                zero++;
            } else {
                minusPq.add(data);
            }
        }
        int sum = 0;
        // 양수 처리하기
        while (plusPq.size() > 1) {
            // 수 2개를 큐에서 뽑음
            int first = plusPq.remove();
            int second = plusPq.remove();
            // 2개의 수를 곱한 값을 결괏값에 더함
            sum = sum + first * second;
        }
        // 양수 우선순위 큐에 데이터가 남아 있으면
        if (!plusPq.isEmpty()) {
            // 이 데이터를 결괏값에 더함
            sum = sum + plusPq.remove();
        }
        // 음수 처리하기
        while (minusPq.size() > 1) {
            // 수 2개를 큐에서 뽑음
            int first = minusPq.remove();
            int second = minusPq.remove();
            // 2개의 수를 곱한 값을 결괏값에 더함
            sum = sum + first * second;
        }
        // 음수 우선순위 큐에 데이터가 남아 있고
        if (!minusPq.isEmpty()) {
            // 데이터 0이 1개도 없으면
            if (zero == 0) {
                // 이 데이터를 결괏값에 더함
                sum = sum + minusPq.remove();
            }
        }
        // 1 처리하기
        sum = sum + one;
        // 결괏값 출력
        System.out.println(sum);
    }
}
