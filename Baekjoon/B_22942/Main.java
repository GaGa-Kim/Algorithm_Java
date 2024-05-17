package Baekjoon.B_22942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 모든 원의 중심 좌표는 x축 위에 존재하므로 중심 좌표 기준으로 반지름의 길이를 활용해 맨 앞의 점과 맨 뒤의 점을 인덱스와 함께 저장
 *   이후 좌표 순으로 정렬하여 스택에 넣으며 같은 원의 점 끼리 모두 짝이 맞아 스택이 비었다면 YES
 *   아니라면 원 사이에 다른 점이 있는 것이므로 NO를 출력
 */

/* 
 * 손으로 풀어보기
 * 1. 중심 좌표 기준으로 반지름의 길이를 활용해 맨 앞의 점과 맨 뒤의 점을 인덱스와 함께 저장
 * 2. 좌표 순으로 정렬하여 스택에 넣으며 같은 원의 점 끼리 모두 짝이 맞는지 확인
 * 3. 모두 짝이 맞아 스택이 비었다면 YES, 아니라면 원 사이에 다른 점이 있는 것이므로 NO를 출력
 */

/*
 * 22942) 데이터_체커 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static List<Point> points; // points(원의 맨 앞, 뒤 좌표들)
    static Stack<Point> stack; // stack(원의 맨 앞, 뒤 좌표들을 저장하며 확인할 스택)

    /*
     * Point(좌표 정보를 담을 클래스)
     */
    static class Point {
        int x; // x(원의 x 좌표)
        int index; // index(원의 인덱스)

        public Point(int x, int index) {
            this.x = x;
            this.index = index;
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        points = new ArrayList<Point>(); // 원의 중심 좌표와 반지름에 따라 맨 앞의 점과 맨 뒤의 점을 인덱스와 함께 저장하기
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            points.add(new Point(x - r, i));
            points.add(new Point(x + r, i));
        }
    }

    /*
     * 데이터 조건 확인하기
     */
    static void valid() {
        points.sort(new Comparator<Point>() { // 좌표 순으로 정렬
            @Override
            public int compare(Point p1, Point p2) {
                return p1.x - p2.x;
            }
        });

        stack = new Stack<Point>(); // 좌표를 스택에 넣으며 같은 원의 점 끼리 모두 짝이 맞는지 확인
        for (int i = 0; i < points.size(); i++) {
            if (stack.isEmpty()) {
                stack.push(points.get(i));
            } else {
                if (stack.peek().index == points.get(i).index) {
                    stack.pop();
                } else {
                    stack.push(points.get(i));
                }
            }
        }
    }

    /*
     * 스택에 남아 있는 데이터에 따라 결과 출력하기
     */
    static void print() {
        if (stack.isEmpty()) { // 모두 짝이 맞아 스택이 비었다면 YES 출력
            System.out.println("YES");
            return;
        }
        System.out.println("NO"); // 아니라면 원 사이에 겹치는 다른 점이 있는 것이므로 NO를 출력
    }

    public static void main(String[] args) throws IOException {
        init();
        valid();
        print();
    }
}