package Programmers.LV_2;

/**
 * 140107) 점_찍기
 */
public class L086_140107 {
    // k(정수)
    // d(원점과의 거리)
    public long solution(int k, int d) {
        // answer(점의 총 갯수)
        long answer = 0;
        for (int x = 0; x <= d; x += k) {
            // maxY(x일 때의 최대 y 값)
            // x² + y² = d² → y² = d² - x²
            long maxY = (long) Math.sqrt(Math.pow(d, 2) - Math.pow(x, 2));
            // (maxY / k) + 1만큼 answer 증가
            answer += (maxY / k) + 1;
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L086_140107 solution = new L086_140107();

        int k = 1;
        int d = 5;

        long result = solution.solution(k, d);

        System.out.println(result);
    }
}
