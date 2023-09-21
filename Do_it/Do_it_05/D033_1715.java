package Do_it.Do_it_05;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 1715) 카드_정렬하기
 */
public class D033_1715 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(카드 묶음 개수)
        int N = sc.nextInt();
        // pq(우선순위 큐)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        // 우선순위 큐에 데이터 저장하기
        for (int i = 0; i < N; i++) {
            int data = sc.nextInt();
            pq.add(data);
        }
        int data1 = 0;
        int data2 = 0;
        int sum = 0;
        while (pq.size() != 1) {
            // 2개 카드 묶음을 큐에서 뽑음
            data1 = pq.remove();
            data2 = pq.remove();
            // 2개 카드 묶음을 합치는 데 필요한 비교 횟수를 결괏값에 더함
            sum += data1 + data2;
            // 2개 카드 묶음의 합을 우선순위 큐에 다시 넣음
            pq.add(data1 + data2);
        }
        // 누적된 비교 횟수 출력하기
        System.out.println(sum);
    }
}
