package Programmers.LV_2;

/**
 * 62048) 멀쩡한_사각형
 */
public class L056_62048 {
    // w, h(직사각형 종이의 가로 길이와 세로 길이)
    public long solution(int w, int h) {
        // wh = 최대공약수 함수(w, h)
        long wh = gcd(w, h);
        // w * h - (w/wh + h/wh - 1) * wh 반환
        return (long) w * h - (w / wh + h / wh - 1) * wh;
    }

    // 최대 공약수 함수
    private int gcd(int w, int h) {
        // a(w과 h 중 큰 수)
        int a = Math.max(w, h);
        // b(w과 h 중 작은 수)
        int b = Math.min(w, h);
        // b가 0이라면
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // 최대 공약수 함수(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L056_62048 solution = new L056_62048();

        int w = 8;
        int h = 12;

        long result = solution.solution(w, h);

        System.out.println(result);
    }
}
