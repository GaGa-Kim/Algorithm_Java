package Baekjoon.B_7662;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * 문제 분석하기
 * : 우선순위 큐를 사용할 경우, 삭제할 때 O(N)의 시간이 걸리므로 시간초과가 발생
 *   그러므로 TreeMap을 이용해 최댓값과 최솟값을 삭제
 */

/* 
 * 손으로 풀어보기
 * 1. TreeMap 생성
 * 2. I일 경우, TreeMap에 삽입
 * 3. D 1 또는 D - 1일 경우에 TreeMap이 비어 있다면 연산을 무시하도록 함
 * 4. D 1일 경우, TreeMap에서 가장 큰 값을 삭제. 이때 여러 개라면 삭제 대신 숫자를 하나 줄여주도록 함
 * 5. D -1일 경우, TreeMap에서 가장 작은 값을 삭제. 이때 여러 개라면 삭제 대신 숫자를 하나 줄여주도록 함
 * 6. 모든 연산이 끝난 후, 남아 있는 값 중 최댓값과 최솟값을 출력. 만약 TreeMap이 비어있다면 EMPTY를 출력
 */

/*
 * 7662) 이중_우선순위_큐 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int T; // T(입력 데이터의 수)
    static TreeMap<Integer, Integer> map; // map(데이터와 데이터의 개수를 담을 트리 맵)

    /*
     * 테스트 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            calculate(st);
        }
    }

    /*
     * 테스트에 따른 연산하기
     */
    static void calculate(StringTokenizer st) throws IOException {
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        map = new TreeMap<Integer, Integer>();

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            String operation = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (operation.equals("I")) { // I일 경우, 트리 맵에 삽입
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else if (operation.equals("D")) { // D일 경우, 트리 맵에서 삭제
                if (map.isEmpty()) {
                    continue; // 트리 맵이 비어 있다면 연산을 무시
                } else if (num == 1) {
                    int max = map.lastKey();
                    if (map.get(max) == 1) {
                        map.remove(max); // 트리 맵에서 가장 큰 값을 삭제
                    } else {
                        map.put(max, map.get(max) - 1); // 이때 여러 개라면 삭제 대신 숫자를 하나 줄여주도록 함
                    }
                } else if (num == -1) {
                    int min = map.firstKey();
                    if (map.get(min) == 1) {
                        map.remove(min); // 트리 맵에서 가장 작은 값을 삭제
                    } else {
                        map.put(min, map.get(min) - 1); // 이때 여러 개라면 삭제 대신 숫자를 하나 줄여주도록 함
                    }
                }
            }
        }

        print();
    }

    /*
     * 연산 결과 출력하기
     */
    static void print() {
        if (map.isEmpty()) {
            System.out.println("EMPTY"); // 만약 트리 맵이 비어잇다면 EMPTY를 출력
        } else {
            int max = map.lastKey();
            int min = map.firstKey();
            System.out.println(max + " " + min); // 모든 연산이 끝난 후, 남아 있는 값 중 최댓값과 최솟값을 출력
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
    }
}