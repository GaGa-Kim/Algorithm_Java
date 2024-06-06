package Baekjoon.B_15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 폐업 시키지 않을 치킨 집을 M개 구하는 모든 경우의 수에 따라, 도시의 치킨 거리를 구해 가장 작은 값을 구하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 집과 치킨집의 좌표 정보를 저장하기 
 * 2. 치킨 집을 M개 선택한 후, 그 치킨 집에 따른 도시의 치킨 거리 구하기
 * 3. 이를 반복하여 가장 작은 도시의 치킨 거리 값을 구하여 출력하기
 */

/*
 * 15686) 치킨_배달 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M;
    static List<Point> home = new ArrayList<Point>(); // home(집 좌표 정보들)
    static List<Point> chicken = new ArrayList<Point>(); // chicken(치킨 집 좌표 정보들)
    static boolean[] open_chicken; // open_chicken(폐업하지 않은 치킨 집 유무 배열)
    static int answer = Integer.MAX_VALUE; // answer(도시의 치킨 거리의 최솟값)

    /*
     * Point(좌표 정보를 담을 클래스)
     */
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) {
                    home.add(new Point(i, j));
                } else if (info == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        open_chicken = new boolean[chicken.size()];
    }

    /*
     * 치킨 집을 M개 선택한 후, 그 치킨 집에 따른 도시의 치킨 거리 구하기
     */
    static void backtracking(int start, int count) {
        if (count == M) { // 치킨 집 M개를 선택했다면
            int all_distance = 0;

            for (int i = 0; i < home.size(); i++) { // 집마다 가장 작은 치킨 거리를 갖는 치킨 집 찾기
                int distance = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (open_chicken[j]) {
                        int temp = calculate(home.get(i), chicken.get(j));
                        distance = Math.min(distance, temp);
                    }
                }
                all_distance += distance;
            }
            answer = Math.min(answer, all_distance);
            return;
        }

        for (int i = start; i < chicken.size(); i++) { // 치킨 집 선택하기
            open_chicken[i] = true;
            backtracking(i + 1, count + 1);
            open_chicken[i] = false;
        }
    }

    /*
     * 치킨 거리 구하기
     */
    static int calculate(Point home, Point chicken) {
        return Math.abs(home.r - chicken.r) + Math.abs(home.c - chicken.c);
    }

    /*
     * 가장 작은 도시의 치킨 거리 값 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        backtracking(0, 0);
        print();
    }
}