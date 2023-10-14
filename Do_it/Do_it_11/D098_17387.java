package Do_it.Do_it_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 17387) 선분_교차_2
 */
public class D098_17387 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // x1, y1, x2, y2, x3, y3, x4, y4 (네 점의 x, y 좌표값을 저장하는 변수)
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());
        boolean cross = isCross(x1, y1, x2, y2, x3, y3, x4, y4);
        if (cross)
            System.out.println(1);
        else
            System.out.println(0);
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
}
