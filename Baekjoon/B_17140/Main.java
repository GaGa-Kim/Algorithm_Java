package Baekjoon.B_17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 행의 개수와 열의 개수의 크기에 따라 R 연산과 C 연산을 반복하여 A[r][c]에 들어있는 값이 k가 되도록 함
 *   예) [3, 1, 1]
 *   3이 1번, 1이 2번 등장하므로 등장 횟수가 커지는 순으로 정렬해야 하므로 [3, 1, 1, 2]
 *   
 */

/* 
 * 손으로 풀어보기
 * 1. 행의 개수가 열의 개수보다 많거나 같다면 R 연산을 수행, 그렇지 않다면 C 연산을 수행
 * 2. 수의 등장 횟수가 커지는 순으로 정렬. 같은 것이 있을 경우, 수가 커지는 순으로 정렬
 *    이 정렬 결과(수, 등장 횟수)를 배열 A에 다시 넣도록 함
 *    이를 위해 한 행(열)에 해당하는 수와 그 수의 등장 횟수를 찾고 
 *    우선순위 큐에 저장하여 가장 적은 횟수를 가진 수부터 배열에 넣도록 함
 * 3. R 연산이 적용된 경우에는 가장 큰 행을 기준으로 모든 행의 크기를 변경
 *    C 연산이 적용된 경우에는 가장 큰 열을 기준으로 모든 열의 크기를 변경
 *    행 또는 열의 크기가 커진 곳에는 0이 채워짐
 * 4. 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버림
 * 5. 이를 반복하며 A[r][c]에 들어있는 값이 k가 되도록 할 때의 시간을 출력
 *    100초가 지나도 되지 않는다면 -1을 출력
 */

/*
 * 17140) 이차원_배열과_연산 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int r, c, k;
    static int[][] A = new int[101][101];
    static int rLength = 3, cLength = 3; // rLength(행의 개수), cLength(열의 개수)

    /*
     * Pair(수와 수의 등장 횟수를 담을 클래스)
     */
    static class Pair implements Comparable<Pair> {
        int number;
        int count;

        public Pair(int number, int count) {
            this.number = number;
            this.count = count;
        }

        public int compareTo(Pair other) {
            if (this.count == other.count) {
                return this.number - other.number;
            }
            return this.count - other.count;
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    /*
     * 행과 열의 개수에 따라 연산 수행
     */
    static void sorting() {
        if (rLength >= cLength) { // 행의 개수가 열의 개수보다 많거나 같다면 R 연산을 수행
            for (int i = 1; i <= rLength; i++) {
                R(i);
            }
        } else { // 그렇지 않다면 C 연산을 수행
            for (int i = 1; i <= cLength; i++) {
                C(i);
            }
        }
    }

    /*
     * R 연산
     */
    static void R(int row) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // 한 행에 해당하는 수와 그 수의 등장 횟수를 저장
        for (int i = 1; i <= rLength; i++) {
            if (A[row][i] == 0) {
                continue;
            }
            map.put(A[row][i], map.getOrDefault(A[row][i], 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(); // 우선순위 큐에 저장
        for (int number : map.keySet()) {
            pq.add(new Pair(number, map.get(number)));
        }

        int i = 1;
        while (!pq.isEmpty()) { // 가장 적은 횟수를 가진 수와 등장 횟수를 배열에 넣도록 함
            Pair pair = pq.poll();
            A[row][i++] = pair.number;
            A[row][i++] = pair.count;
        }

        cLength = Math.max(cLength, i); // R 연산에 의해 행이 커진 만큼 열의 개수 갱신

        while (i <= 99) { // 갱신한 수의 뒤에 있는 행들을 모두 다시 0으로 갱신
            A[row][i++] = 0;
            A[row][i++] = 0;
        }
    }

    /*
     * C 연산
     */
    static void C(int column) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // 한 열에 해당하는 수와 그 수의 등장 횟수를 저장
        for (int i = 1; i <= cLength; i++) {
            if (A[i][column] == 0) {
                continue;
            }
            map.put(A[i][column], map.getOrDefault(A[i][column], 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(); // 우선순위 큐에 저장
        for (int number : map.keySet()) {
            pq.add(new Pair(number, map.get(number)));
        }

        int i = 1;
        while (!pq.isEmpty()) { // 가장 적은 횟수를 가진 수와 등장 횟수를 배열에 넣도록 함
            Pair pair = pq.poll();
            A[i++][column] = pair.number;
            A[i++][column] = pair.count;
        }

        rLength = Math.max(rLength, i); // C 연산에 의해 열이 커진 만큼 행의 개수 갱신

        while (i <= 99) { // 갱신한 수의 뒤에 있는 열들을 모두 다시 0으로 갱신
            A[i++][column] = 0;
            A[i++][column] = 0;
        }
    }

    /*
     * A[r][c]에 들어있는 값이 k가 되도록 할 때의 시간을 출력
     */
    static void print(int time) {
        System.out.println(time);
    }

    public static void main(String[] args) throws IOException {
        init();
        for (int t = 0; t <= 100; t++) {
            if (A[r][c] == k) {
                print(t);
                return;
            }
            sorting();
        }
        print(-1);
    }
}