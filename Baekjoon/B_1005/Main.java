package Baekjoon.B_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 위상 정렬 알고리즘을 이용해 각 건물까지의 건설 시간을 갱신한 후 특정 건물의 건설완료 최소 시간을 출력
 */

/* 
 * 손으로 풀어보기
 * 1. 위상 정렬 알고리즘을 이용해 각 건물까지의 건설 시간을 갱신
 * 2. 특정 건물까지의 건설완료 최소 시간 출력
 */

/*
 * 1005) ACM_Craft
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] time; // time(건물당 건설에 걸리는 시간 배열)
    static ArrayList<Integer>[] arr; // arr(건설 순서 정보)
    static int[] indegree; // indegree(진입차수 배열)
    static int W;
    static int[] result; // result(각 건물까지를 건설 완료하는데 드는 최소 시간)

    /*
     * 건물 건설 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        time = new int[N + 1]; // 건물당 건설에 걸리는 시간 저장하기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        arr = new ArrayList[N + 1]; // 건물 건설 순서 정보를 담기 위한 연결 리스트 초기화
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        indegree = new int[N + 1]; // 건물 건설 순서 저장하기
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            arr[X].add(Y);
            indegree[Y]++;
        }

        W = Integer.parseInt(br.readLine());
    }

    /*
     * 위상 정렬로 각 건물까지의 건설 시간 찾기
     */
    static void topological_sort() {
        result = new int[N + 1];

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i <= N; i++) {
            result[i] = time[i];
            if (indegree[i] == 0) { // 진입차수가 0인 건물을 큐에 삽입
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : arr[now]) { // 해당 건물과 연결된 다음 건물 살펴보기
                result[next] = Math.max(result[now] + time[next], result[next]); // 이전까지의 모든 건물과 현재 건물을 지을 수 있는 소요 시간으로 갱신
                indegree[next]--;
                if (indegree[next] == 0) { // 새롭게 진입차수가 0이 되는 건물을 큐에 삽입
                    queue.add(next);
                }
            }
        }
    }

    /*
     * 특정 건물까지의 건설완료 최소 시간 출력하기
     */
    static void print() {
        System.out.println(result[W]);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            topological_sort();
            print();
        }
    }
}