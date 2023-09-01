package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 11286) 절댓값_힙
 */
public class D014_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(질의 요청 개수)
        int N = Integer.parseInt(br.readLine());
        // 우선순위 큐 선언
        PriorityQueue<Integer> myQueue = new PriorityQueue<>((o1, o2) -> {
            // 절댓값 기준으로 정렬되도록 설정하기
            int first_abs = Math.abs(o1);
            int second_abs = Math.abs(o2);
            // 단, 절댓값이 같으면 음수 우선 정렬하기
            if (first_abs == second_abs)
                return o1 > o2 ? 1 : -1;
            else
                return first_abs - second_abs;
        });
        for (int i = 0; i < N; i++) {
            int request = Integer.parseInt(br.readLine());
            // 요청이 0일 때: 큐가 비어 있으면 0, 비어 있지 않으면 큐의 front 값 출력하기
            if (request == 0) {
                if (myQueue.isEmpty())
                    System.out.println(0);
                else
                    System.out.println(myQueue.poll());
            }
            // 요청이 0이 아닐 때: 새로운 데이터를 우선순위 큐에 더하기
            else {
                myQueue.add(request);
            }
        }
    }
}
