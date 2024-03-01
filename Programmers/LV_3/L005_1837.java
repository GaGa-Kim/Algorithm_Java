package Programmers.LV_3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1837) GPS
 */
public class L005_1837 {
    // D(start에서 목적지인 end를 갈 때 수정한 최소 오류 변경 횟수를 담은 DP 테이블)
    int[][] D;
    // A(그래프 데이터 저장 인접 리스트)
    ArrayList<Integer>[] A;
    // MAX(최대 오류 변경 횟수)
    int MAX = 201;

    // n(거점 개수)
    // m(도로의 개수)
    // edge_list(각 거점 간의 연결된 도로 정보)
    // k(택시가 시간대별로 보내오는 거점 정보의 총 개수)
    // gps_log(머물렀던 거점의 정보)
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        // answer(택시가 보내온 경로에서 이동 가능한 경로로 만드는 최소의 오류 수정 횟수)
        int answer = 0;
        // DP 테이블 및 A 인접 리스트 초기화하기
        init(n, edge_list, gps_log);
        // 마지막까지 가는 경로에 대한 최소의 오류 수정 횟수 DP 테이블 채우기
        answer = fillDP(0, gps_log[0], gps_log);
        // 마지막 지점으로 가는 경로가 없거나, 최대 오류 변경 횟수보다 크다면
        if (D[k - 1][gps_log[k - 1]] == -1 || D[k - 1][gps_log[k - 1]] >= MAX)
            // -1 반환
            return -1;
        // answer 반환
        return answer;
    }

    // DP 테이블 및 A 인접 리스트 초기화하기
    private void init(int n, int[][] edge_list, int[] gps_log) {
        // DP 테이블인 D를 -1로 초기화하기
        D = new int[101][201];
        for (int i = 0; i <= gps_log.length; i++) {
            Arrays.fill(D[i], -1);
        }
        // A 인접 리스트의 각 ArrayList 초기화하기
        A = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            A[i] = new ArrayList<Integer>();
        }
        // A 인접 리스트에 연결된 도로 정보 데이터 저장하기
        for (int i = 0; i < edge_list.length; i++) {
            int S = edge_list[i][0];
            int E = edge_list[i][1];
            A[S].add(E);
            A[E].add(S);
        }
    }

    // 마지막까지 가는 경로에 대한 최소의 오류 수정 횟수 DP 테이블 채우기
    private int fillDP(int start, int end, int[] gps_log) {
        // 시작 지점이 마지막 지점이라면
        if (start == gps_log.length - 1) {
            // 시작 지점과 마지막 지점이 같다면
            if (gps_log[start] == end)
                // 수정하지 않아도 되므로 최소의 오류 수정 횟수를 0으로 갱신 후 반환
                return D[start][end] = 0;
            // 시작 지점과 마지막 지점이 다르면 변경이 불가능하므로 MAX 값으로 갱신 후 반환
            return D[start][end] = MAX;
        }
        // 이미 구해진 최소의 오류 수정 횟수가 있다면
        if (D[start][end] != -1)
            // 이를 반환
            return D[start][end];
        // result(최소의 오류 수정 횟수를 저장하기 위한 변수)
        int result = MAX;
        // 현재 오류 수정 횟수 초기화
        D[start][end] = 0;
        // 시작 지점에서 이동 가능한 다음 목적지들에 대해 최소의 오류 수정 횟수 계산
        for (int i = 0; i < A[end].size(); i++) {
            // next(다음 목적지)
            int next = A[end].get(i);
            // 최소의 오류 수정 횟수 갱신
            result = Math.min(fillDP(start + 1, next, gps_log), result);
        }
        // 시작 지점과 마지막 지점이 같다면
        if (gps_log[start] == end)
            // 한 번 더 수정하지 않아도 되므로 최소의 오류 수정 횟수를 result로 갱신 후 반환
            return D[start][end] = result;
        // 시작 지점과 마지막 지점이 다르다면 한 번 더 수정해야 하므로 result + 1로 갱신 후 반환
        return D[start][end] = result + 1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L005_1837 solution = new L005_1837();

        int n = 7;
        int m = 10;
        int[][] edge_list = { { 1, 2 }, { 1, 3 }, { 2, 3 }, { 2, 4 }, { 3, 4 },
                { 3, 5 }, { 4, 6 }, { 5, 6 }, { 5, 7 }, { 6, 7 } };
        int k = 6;
        int[] gps_log = { 1, 2, 4, 4, 6, 7 };

        int result = solution.solution(n, m, edge_list, k, gps_log);

        System.out.println(result);
    }
}
