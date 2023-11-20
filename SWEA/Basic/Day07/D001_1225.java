package SWEA.Basic.Day07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 1225) 암호생성기
 */
public class D001_1225 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        // T(테스트 케이스 수) = 10
        int T = 10;
        // T만큼
        for (int test_case = 1; test_case <= T; test_case++) {
            // t(테스트 케이스 번호)
            int t = sc.nextInt();
            // queue(암호 저장 큐)
            Queue<Integer> queue = new LinkedList<>();
            // queue에 암호 숫자 8개 저장
            for (int i = 0; i < 8; i++) {
                queue.add(sc.nextInt());
            }
            // queue = 암호 변환 함수
            queue = generate(queue);
            // sb에 암호와 공백 추가
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                sb.append(queue.poll() + " ");
            }
            // #T와 sb 반환
            System.out.println("#" + t + " " + sb);
        }
    }

    // 암호 변환 함수
    private static Queue<Integer> generate(Queue<Integer> queue) {
        // index(감소 숫자) = 1
        int index = 1;
        while (true) {
            // 큐에서 숫자를 하나 꺼냄
            int num = queue.poll();
            // 숫자에서 index를 뺀 숫자가 0보다 작다면
            if (num - index <= 0) {
                // 큐에 0을 저장
                queue.add(0);
                // 프로그램 종료
                break;
            } else {
                // 큐에 숫자에서 index를 뺀 숫자 저장
                queue.add(num - index);
                // index 증가
                index++;
            }
            // index가 6이라면
            if (index == 6) {
                // 한 사이클을 완료했으므로 index를 원래대로 초기화
                index = 1;
            }
        }
        return queue;
    }
}
