package Baekjoon.B_2212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 각 센서들을 K개의 그룹으로 나눌 때 수신 가능 영역의 길이의 합을 최소화하도록 함
 *   이를 위해 각 센서들의 좌표를 정렬한 후, N - K개의 거리 차이를 무시하여 제외한 후 나머지의 합을 구해줌
 *   
 *   예) N = 6 (1, 6, 9, 3, 6, 7), K = 2일 때
 *   각 센서들의 위치는 1, 3, 6, 6, 7, 9이므로 센서들끼리의 거리 차이는 2, 3, 0, 1, 2가 되므로
 *   1, 3 / 6, 6, 7, 9로 그룹을 나누면 집중국의 수신 가능 영역의 길이의 합은 2 + 3이므로 5가 됨
 */

/* 
 * 손으로 풀어보기
 * 1. 각 센서들의 좌표를 정렬
 * 2. 각 센서들의 거리 차이 구하기
 * 3. N - K개의 가장 큰 수신 가능 영역의 길이 차이를 무시하기
 * 4. 나머지 수신 가능 영역의 차이의 합을 출력하기
 */

/*
 * 2212) 센서 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, K;
    static int[] sensor; // sensor(센서의 좌표들)
    static int[] diff; // diff(각 센서들의 거리 차이)
    static int answer = 0; // answer(수신 가능 영역의 차이의 합)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        sensor = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensor[i] = Integer.parseInt(st.nextToken());
        }
    }

    /*
     * 각 센서들의 거리 차이 구하기
     */
    static void diff_sensor() {
        Arrays.sort(sensor);

        diff = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = sensor[i + 1] - sensor[i];
        }
    }

    /*
     * 나머지 수신 가능 영역의 차이의 합 구하기
     */
    static void calculate() {
        Arrays.sort(diff);

        for (int i = 0; i < N - K; i++) { // N - K개의 가장 큰 수신 가능 영역의 차이를 제외하고 합산
            answer += diff[i];
        }
    }

    /*
     * 나머지 수신 가능 영역의 차이의 합을 출력하기
     */
    static void print() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        init();
        diff_sensor();
        calculate();
        print();
    }
}