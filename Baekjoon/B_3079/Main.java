package Baekjoon.B_3079;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 전체 인원 입국에 걸리는 시간의 최소와 최대를 이진 탐색의 인덱스로 설정한 후 
 *   이진 탐색을 하며 시간 안에 모든 인원을 처리할 수 있는지 계산함
 */

/* 
 * 손으로 풀어보기
 * 1. 이진탐색을 위해 데이터 정렬
 * 2. 걸리는 시간의 최소와 최대 시간을 찾기
 * 3. 이진 탐색을 하며 최소 시간을 찾기
 * 4. 심사를 마치는데 걸리는 시간의 최솟값 출력하기
 */

/*
 * 3079) 입국심사 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static long M;
    static long[] times; // times(각 심사대에서 심사를 하는데 걸리는 시간)
    static long T = Long.MAX_VALUE; // T(심사를 마치는데 걸리는 시간의 최솟값)

    /*
     * 입국심사 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        times = new long[N];
        for (int i = 0; i < N; i++) { // 각 심사대에서 심사를 하는데 걸리는 시간
            st = new StringTokenizer(br.readLine());
            times[i] = Long.parseLong(st.nextToken());
        }
    }

    /*
     * 입국심사에 걸리는 최소 시간 이진 탐색
     */
    static void entry() {
        Arrays.sort(times); // 이진탐색을 위해 데이터 정렬

        long start = 0; // start, end(이진 탐색 시작, 종료 인덱스)
        long end = times[times.length - 1] * M;

        while (start <= end) {
            long mid = (start + end) / 2;
            if (ableEntryInTime(mid)) { // 중간 인덱스 값의 시간으로 모든 인원을 심사할 수 있다면, 더 적은 시간으로 심사할 수 있는지 탐색
                T = Math.min(T, mid);
                end = mid - 1;
            } else { // 중간 인덱스 값의 시간으로 모든 인원을 심사할 수 없다면, 더 긴 시간으로 심사할 수 있는지 탐색
                start = mid + 1;
            }
        }
    }

    /*
     * 주어진 시간을 가지고 이진 탐색이 가능한지 여부
     */
    static boolean ableEntryInTime(long time) {
        long count = 0; // count(입국 가능한 인원 수)
        for (int i = 0; i < N; i++) {
            count += time / times[i];
            if (count >= M) {
                return true;
            }
        }
        return false;
    }

    /*
     * 심사를 마치는데 걸리는 시간의 최솟값 출력하기
     */
    static void print() {
        System.out.println(T);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        entry();
        print();
    }
}