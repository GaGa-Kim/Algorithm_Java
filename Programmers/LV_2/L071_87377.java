package Programmers.LV_2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 87377) 교점에_별_만들기
 */
public class L071_87377 {
    // Point(교점)
    class Point {
        // x, y(좌표)
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    // line(직선 A, B, C에 대한 정보가 담긴 배열)
    public String[] solution(int[][] line) {
        // set(모든 교점을 담을 set)
        Set<Point> set = new HashSet<>();
        // x, y(두 직선의 교점 좌표)
        long x = 0, y = 0;
        // minX, maxX, minY, maxY(최소 직사각형의 상하좌우 좌표)
        long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;
        for (int i = 0; i < line.length - 1; i++) {
            // a, b, e(line[i]의 x, y, 절편 계수)
            long a = line[i][0];
            long b = line[i][1];
            long e = line[i][2];
            for (int j = i + 1; j < line.length; j++) {
                // c, d, f(line[j]의 x, y, 절편 계수)
                long c = line[j][0];
                long d = line[j][1];
                long f = line[j][2];
                // adbc(a * d - b * c)
                long abdc = a * d - b * c;
                // adbc가 0이라면
                if (abdc == 0)
                    // 두 직선은 평행 또는 일치하므로 교점이 존재하지 않으므로 continue
                    continue;
                // bfed(b * f - e * d)
                long bfed = b * f - e * d;
                // bfed를 adbc로 나누었을 때의 나머지가 0이 아니라면
                if (bfed % abdc != 0)
                    // x가 정수가 아니므로 continue
                    continue;
                // ecaf(e * c - a * f)
                long ecaf = e * c - a * f;
                // ecaf를 adbc로 나누었을 때의 나머지가 0이 아니라면
                if (ecaf % abdc != 0)
                    // y가 정수가 아니므로 continue
                    continue;
                // x, y 갱신
                x = bfed / abdc;
                y = ecaf / abdc;
                // set에 교점(x, y) 저장
                set.add(new Point(x, y));
                // minX, maxX, minY, maxY 갱신
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }
        // h(최소 직사각형의 높이)
        long h = maxY - minY + 1;
        // w(최소 직사각형의 넓이)
        long w = maxX - minX + 1;
        // answer(모든 별을 포함하는 최소 사각형)
        String[] answer = new String[(int) h];
        // answer의 모든 부분을 .으로 저장
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w; i++) {
            sb.append(".");
        }
        Arrays.fill(answer, sb.toString());
        // sx, sy(별의 좌표)
        long sx = 0, sy = 0;
        for (Point s : set) {
            // h와 w에 맞춰 sx, sy 갱신
            sx = s.x - minX;
            sy = maxY - s.y;
            // answer의 sx, sy 위치를 .에서 *로 변경
            answer[(int) sy] = answer[(int) sy].substring(0, (int) sx) + "*" + answer[(int) sy].substring((int) sx + 1);
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L071_87377 solution = new L071_87377();

        int[][] line = {
                { 2, -1, 4 },
                { -2, -1, 4 },
                { 0, -1, 1 },
                { 5, -8, -12 },
                { 5, 8, 12 }
        };

        String[] result = solution.solution(line);

        System.out.println(Arrays.toString(result));
    }
}
