package Baekjoon.B_1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 우수 마을을 선정할 때, 인접한 마을에 우수 마을이 있다면 이번 마을을 우수 마을로 선정할 수 없으므로
 *   선정 조건에 맞춰 인접한 마을을 탐색하면서 우수 마을의 조합에 따른 마을의 주민 수의 총 합을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 인접한 마을 정보를 저장
 * 2. 인접한 마을 정보에 따라 첫 번째 마을이 우수 마을일 때와 우수 마을이 아닐 때로 시작하여 우수 마을의 조합을 탐색
 * 3. 이전 마을이 우수 마을일 경우, 이번 마을은 우수 마을이 될 수 없음   
 *    이전 마을이 우수 마을이 아닐 경우에는, 이번 마을은 우수 마을이 될 수도 있고 안 될 수도 있음
 *    이때 우수 마을로 선정되지 못한 마을은 적어도 하나의 우수 마을과 인접해 있어야 하는데
 *    이 문제의 경우, 최대의 마을 주민 수의 총 합을 구해야 하므로 최대가 될 때는 최대한의 우수 마을을 선정해야 하므로 자동으로 충족되게 됨
 * 4. 이를 통해 구한 조합 중 가장 마을의 주민 수의 총 합이 큰 수를 출력
 */

/*
 * 1949) 우수_마을 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] people; // people(마을 주민 수)
    static List<Integer>[] town; // town(인접한 마을 정보)
    static boolean[] visited; // visited(마을 방문 유무 배열)
    static int[][] D; // D(우수 마을 조합에 따른 마을의 주민 수의 총 합을 담은 DP 테이블)
    static int answer = Integer.MIN_VALUE; // answer(우수 마을의 주민 수의 총 합)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        people = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        town = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            town[i] = new ArrayList<Integer>();
        }

        visited = new boolean[N + 1];

        D = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(D[i], -1);
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            town[A].add(B);
            town[B].add(A);
        }

        answer = Math.max(answer, find(1, 0)); // 첫 번째 마을이 우수 마을이 아닐 때
        answer = Math.max(answer, find(1, 1) + people[1]); // 첫 번째 마을이 우수 마을일 때
    }

    /*
     * 우수 마을들의 주민 수 찾기
     */
    static int find(int start, int flag) {
        if (town[start].size() == 0) { // 시작 마을과 인접한 마을이 없다면
            return 0;
        }
        if (D[start][flag] != -1) { // 이미 시작 마을을 시작으로 하여 우수 마을의 주민 수를 구했다면 다시 계산할 필요가 없음
            return D[start][flag];
        }

        visited[start] = true;
        D[start][flag] = 0;
        for (int next : town[start]) { // 시작 마을과 인접한 마을을 모두 탐색
            if (visited[next]) { // 이미 방문한 마을이라면 넘어감
                continue;
            }
            if (flag == 1) { // 이전 마을이 우수 마을이었을 경우, 이번 마을은 우수 마을로 선정할 수 없음
                D[start][flag] += find(next, 0);
            } else if (flag == 0) { // 이전 마을이 우수 마을이 아니었을 경우, 이번 마을은 우수 마을로 선정하거나 선정하지 않을 수 있음
                D[start][flag] += Math.max(find(next, 0), find(next, 1) + people[next]);
            }
        }
        visited[start] = false;
        return D[start][flag];
    }

    /*
     * 우수 마을의 주민 수의 총 합 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        print();
    }
}