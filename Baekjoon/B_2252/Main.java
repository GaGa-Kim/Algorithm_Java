package Baekjoon.B_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 각 키를 비교한 학생들을 저장한 후, 진입 차수의 개수가 0개인 학생부터 위상정렬하도록 함 
 */

/* 
 * 손으로 풀어보기
 * 1. 인접 리스트에 학생 데이터와 진입 차수를 저장
 * 2. 진입 차수의 개수가 0인 학생을 큐에 저장
 * 3. 위상 정렬 알고리즘을 수행
 * 4. 위상 정렬한 결과를 출력
 */

/*
 * 2252) 줄_세우기 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>(); // arr(학생 데이터 저장 인접 리스트)
    static int[] indegree; // indegree(진입 차수 배열)
    static StringBuilder answer = new StringBuilder(); // answer(키 순서대로 줄을 세운 결과)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            arr.add(new ArrayList<>());
        }

        indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr.get(A).add(B);
            indegree[B]++; // 진입 차수 증가
        }
    }

    /*
     * 위상 정렬하기
     */
    static void topological_sorting() {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 1; i <= N; i++) { // 진입 차수의 개수가 0인 학생 정점을 큐에 저장
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            answer.append(now).append(" ");
            for (int next : arr.get(now)) { // 현재 학생 정점에서 갈 수 있는 다른 학생 정점으로 이동
                indegree[next]--; // 진입 차수 감소
                if (indegree[next] == 0) { // 진입 차수가 0이라면 학생 정점을 큐에 저장
                    queue.add(next);
                }
            }
        }
    }

    /*
     * 위상 정렬 결과 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        topological_sorting();
        print();
    }
}