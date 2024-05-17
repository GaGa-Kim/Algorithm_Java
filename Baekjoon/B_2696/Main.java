package Baekjoon.B_2696;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 중앙값과 중앙값보다 큰 값을 저장할 우선순위 큐와 작은 값을 저장할 우선순위 큐를 생성한 후
 *   값을 저장하고 교환하며 중앙값을 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 중앙값과 중앙값보다 큰 값을 저장할 우선순위 큐와 작은 값을 저장할 우선순위 큐를 생성
 * 2. 두 우선순위 큐의 크기가 같다면 큰 값을 저장하는 우선순위 큐에 걊을 저장
 * 3. 두 우선순위 큐의 크기가 다르다면 작은 값을 저장하는 우선순위 큐에 값을 저장
 * 4. 큰 값을 저장하는 우선순위 큐의 최소값과 작은 값을 저장하는 우선순위 큐의 최대값을 비교
 * 5. 작은 값을 저장하는 우선순위 큐의 최대값이 더 클 경우, 이 값으로 중앙값이 갱신되므로 두 수를 교환
 * 6. 이를 반복하며 홀수 번째 수를 읽을 때마다 중앙값이 큰 값을 저장하는 우선순위 큐의 최소값을 저장
 */

/*
 * 2696) 중앙값_구하기 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int M;
    static int[] sequence; // sequence(수열의 값들)
    static PriorityQueue<Integer> smallPq; // smallPq(중앙값보다 작은 수를 담을 최대값 우선순위 큐)
    static PriorityQueue<Integer> bigPq; // bigPq(중앙값과 중앙값보다 큰 수를 담을 최소값 우선순위 큐)
    static List<Integer> result; // result(홀수 번째 수를 읽을 때마다 구한 중앙값들)

    /*
     * 수열 읽기
     */
    static void init() throws IOException {
        M = Integer.parseInt(br.readLine());

        sequence = new int[M];
        StringTokenizer st = null;
        for (int i = 0; i < M; i++) { // 한 줄에 10개씩 수열의 원소 저장
            if (i % 10 == 0) {
                st = new StringTokenizer(br.readLine());
            }
            sequence[i] = Integer.parseInt(st.nextToken());
        }
    }

    /*
     * 홀수 번째 수를 읽을 때마다 중앙값 찾기
     */
    static void find() {
        smallPq = new PriorityQueue<Integer>(Comparator.reverseOrder());
        bigPq = new PriorityQueue<Integer>();
        result = new ArrayList<Integer>();

        for (int i = 0; i < M; i++) {
            if (bigPq.size() == smallPq.size()) { // 두 우선순위 큐의 크기가 같다면
                bigPq.add(sequence[i]); // 큰 값을 저장하는 우선순위 큐에 걊을 저장
            } else { // 두 우선순위 큐의 크기가 다르다면
                smallPq.add(sequence[i]); // 작은 값을 저장하는 우선순위 큐에 값을 저장
            }

            if (!smallPq.isEmpty() && !bigPq.isEmpty()) {
                if (smallPq.peek() > bigPq.peek()) { // 큰 값을 저장하는 우선순위 큐의 최소값과 작은 값을 저장하는 우선순위 큐의 최대값을 비교
                    int tmp = bigPq.poll(); // 작은 값을 저장하는 우선순위 큐의 최대값이 더 클 경우, 이 값으로 중앙값이 갱신되므로 두 수를 교환
                    bigPq.add(smallPq.poll());
                    smallPq.add(tmp);
                }
            }

            if (i % 2 == 0) { // 홀수 번째 수를 읽을 때마다
                result.add(bigPq.peek()); // 중앙값과 중앙값보다 큰 값을 저장하는 우선순위 큐의 최소값인 중앙값을 저장
            }
        }
    }

    /*
     * 홀수 번째 수를 읽을 때마다 구한 중앙값 출력하기
     */
    static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(result.size());
        for (int i = 0; i < result.size(); i++) { // 중앙값들을 10개씩 출력
            if (i % 10 == 0) {
                sb.append("\n");
            }
            sb.append(result.get(i));
            sb.append(" ");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            find();
            print();
        }
    }
}