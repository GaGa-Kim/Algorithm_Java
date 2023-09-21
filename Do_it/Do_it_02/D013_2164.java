package Do_it.Do_it_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 2164) 카드_2
 */
public class D013_2164 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(카드의 개수)
        int N = sc.nextInt();
        // myQueue(카드 저장 자료구조)
        Queue<Integer> myQueue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            // 큐에 카드 저장하기
            myQueue.add(i);
        }
        // 카드가 1장 남을 때까지
        while (myQueue.size() > 1) {
            // 맨 위의 카드를 버림 : poll()
            myQueue.poll();
            // 맨 위의 카드를 가장 아래의 카드 밑으로 이동 : poll() → add()
            myQueue.add(myQueue.poll());
        }
        // 마지막으로 남은 카드 출력
        System.out.println(myQueue.poll());
    }
}
