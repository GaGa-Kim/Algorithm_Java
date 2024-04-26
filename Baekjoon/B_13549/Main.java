package Baekjoon.B_13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 순간이동, 걷기를 반복하며 최단거리를 찾도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 순간이동, 걷기를 반복하며 최단거리를 찾도록 함
 */

/*
 * 13549) 숨바꼭질_3
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static Queue<Move> queue; // queue(이동 정보를 담을 큐)
    static boolean[] visited; // visited(방문 유무 배열)
    static int answer = Integer.MAX_VALUE; // answer(수빈이가 동생을 찾을 수 있는 가장 빠른 시간)

    /*
     * Move(이동 정보를 담은 클래스)
     */
    static class Move {
        int location;
        int time;

        public Move(int location, int time) {
            this.location = location;
            this.time = time;
        }
    }

    /*
     * 숨바꼭질 준비하기
     */
    static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    /*
     * 최단거리 찾기
     */
    static void dikstra() {
        queue = new LinkedList<Move>();
        queue.add(new Move(N, 0));
        visited = new boolean[100001];
        visited[N] = true;

        while (!queue.isEmpty()) {
            Move now = queue.poll();

            if (now.location == K) { // 수빈이 동생이 있는 위치로 이동했다면 최단거리 시간으로 갱신
                answer = Math.min(answer, now.time);
            }

            if (now.location * 2 <= 100000 && !visited[now.location * 2]) { // 순간이동이 가능하다면
                queue.add(new Move(now.location * 2, now.time));
                visited[now.location * 2] = true;
            }

            if (now.location - 1 >= 0 && !visited[now.location - 1]) { // 뒤로 걷기가 가능하다면
                queue.add(new Move(now.location - 1, now.time + 1));
                visited[now.location - 1] = true;
            }

            if (now.location + 1 <= 100000 && !visited[now.location + 1]) { // 앞으로 걷기가 가능하다면
                queue.add(new Move(now.location + 1, now.time + 1));
                visited[now.location + 1] = true;
            }

        }
    }

    /*
     * 가장 빠른 시간 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        dikstra();
        print();
    }
}