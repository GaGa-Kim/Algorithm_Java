package Baekjoon.B_1548;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 세 수 중, 두 수를 더한 값이 나머지 하나의 수보다 클 경우 삼각관계를 만족하므로 
 *   이를 비교해보며 삼각관계가 될 수 있는 수들의 길이를 구함
 */

/* 
 * 손으로 풀어보기
 * 1. N이 2 이하이면 항상 삼각 수열이므로 N을 출력
 * 2. N이 3 이상이라면 수를 정렬
 * 3. 작은 수 + 그 다음 작은 수 > 가장 큰 수의 관계를 만족하도록 찾아가며 가장 긴 삼각수열의 길이를 구함
 */

/*
 * 1548) 부분_삼각_수열
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[] A; // A(수열 A에 들어있는 수)
    static int length; // length(가장 긴 부분 삼각 수열의 길이)

    /*
     * 수열 저장하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        if (N < 3) { // N이 2 이하이면, 항상 삼각 수열이므로 N을 출력
            length = N;
            print();
        } else { // N이 3 이상이라면 가장 긴 삼각수열의 길이를 구함
            length = -1;
            find();
            if (length == -1) { // 가장 긴 삼각수열의 길이가 없을 경우, 두 개를 가진 수열일 경우 항상 삼각 수열이므로 2를 출력
                length = 2;
            }
            print();
        }
    }

    /*
     * 부분 삼각 수열 찾기
     */
    static void find() {
        Arrays.sort(A); // 오름차순 정렬
        for (int first = 0; first < N - 1; first++) { // 작은 수 + 그 다음 작은 수 > 가장 큰 수
            for (int third = N - 1; third >= 0; third--) {
                if (first + 1 == third) { // 두 번째 작은 수와 가장 큰 수가 같을 경우 종료
                    break;
                }
                if (A[first] + A[first + 1] > A[third]) {
                    length = Math.max(length, third - first + 1);
                    break;
                }
            }
        }
    }

    /*
     * 가장 긴 부분 삼각 수열의 길이 출력하기
     */
    static void print() {
        System.out.println(length);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        find();
    }
}