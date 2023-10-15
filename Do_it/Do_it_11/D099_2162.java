package Do_it.Do_it_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2162) 선분_그룹
 */
public class D099_2162 {
    // parent[](선분들의 부모 선분 저장 노드 배열)
    static int parent[] = new int[3001];
    // L[][](선분 저장 배열)
    static int L[][] = new int[3001][4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(선분의 갯수)
        int N = Integer.parseInt(st.nextToken());
        // 부모 선분 저장 노드 배열 -1로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = -1;
        }
        // 신규 선분 저장하기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            L[i][0] = Integer.parseInt(st.nextToken());
            L[i][1] = Integer.parseInt(st.nextToken());
            L[i][2] = Integer.parseInt(st.nextToken());
            L[i][3] = Integer.parseInt(st.nextToken());
            // 신규 선분과 현재까지 저장된 선분이 교차되는지 확인
            for (int j = 1; j < i; j++) {
                // 선분이 교차될 때 두 선분은 1개의 그룹으로 저장하기
                if (isCross(L[i][0], L[i][1], L[i][2], L[i][3], L[j][0], L[j][1], L[j][2], L[j][3]) == true) {
                    union(i, j);
                }
            }
        }
        int answer = 0, result = 0;
        for (int i = 1; i <= N; i++) {
            // 음수인 parent[i]는 선분 그룹의 부모 노드
            if (parent[i] < 0) {
                // 선분 그룹의 수 증가
                answer++;
                // 가장 크기가 큰 그룹에 속한 선분의 개수 갱신
                // 음수의 절댓값이 선분 그룹의 선분 개수이므로 가장 작은 음수일수록 가장 크기가 큼
                result = Math.min(result, parent[i]);
            }
        }
        System.out.println(answer);
        System.out.println(-result);
    }

    // 선분 교차 여부 판별 함수
    private static boolean isCross(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        // 각 선분과 관련된 CCW 수행하기
        int abc = CCW(x1, y1, x2, y2, x3, y3); // a-b 선분을 기준으로 점 c의 CCW
        int abd = CCW(x1, y1, x2, y2, x4, y4); // a-b 선분을 기준으로 점 d의 CCW
        int cda = CCW(x3, y3, x4, y4, x1, y1); // c-d 선분을 기준으로 점 a의 CCW
        int cdb = CCW(x3, y3, x4, y4, x2, y2); // c-d 선분을 기준으로 점 b의 CCW
        // 각 선분과 관련된 CCW 결괏값의 곱이 모두 0일 때 (두 선분이 일직선상에 있음)
        if (abc * abd == 0 && cda * cdb == 0)
            // 선분 겹침 여부 판별 함수 호출
            return isOverlab(x1, y1, x2, y2, x3, y3, x4, y4);
        // 각 선분과 관련된 CCW 결괏값의 곱이 모두 양수가 아닐 때
        else if (abc * abd <= 0 && cda * cdb <= 0)
            // 선분 교차로 판별 1(true) 출력하기
            return true;
        // 이 외의 경우
        // 선분 미교차로 판별 0(false) 호출하기
        return false;
    }

    // CCW 알고리즘
    private static int CCW(long x1, long y1, long x2, long y2, long x3, long y3) {
        long temp = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        if (temp > 0)
            return 1;
        else if (temp < 0)
            return -1;
        return 0;
    }

    // 선분 겹침 여부 판별 함수
    private static boolean isOverlab(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        // 특정 선분과 좌표의 max값이 타 선분의 min값보다 항상 크거나 같으면 선분 교차
        if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2)
                && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2))
            return true;
        // 아닌 경우가 1개라도 발생하면 선분 미교차
        return false;
    }

    // 변형된 find
    private static int find(int i) {
        // 음수면 자기 자신이 부모 노드
        if (parent[i] < 0)
            return i;
        return parent[i] = find(parent[i]);
    }

    // 변형된 union
    private static void union(int i, int j) {
        int p = find(i);
        int q = find(j);
        // 이미 연결되어 있음
        if (p == q)
            return;
        // p의 부모 노드에 q가 속한 선분 그룹의 선분 개수를 더하기 (음수의 절댓값으로 개수 표현)
        parent[p] += parent[q];
        // p를 q의 부모 노드로 지정하기
        parent[q] = p;
    }
}
