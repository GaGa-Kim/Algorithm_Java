package Programmers.LV_1;

/**
 * 12943) 콜라츠_추측
 */
public class L025_12943 {
    // num(주어진 수)
    public int solution(int num) {
        // n(num을 long형으로 변환한 수)
        long n = (long) num;
        // index(인덱스)
        int index = 0;
        // index가 500번보다 작을 동안
        while (index < 500) {
            // n이 1이라면
            if (n == 1)
                // index 반환
                return index;
            // n이 짝수라면
            else if (n % 2 == 0)
                n = n / 2;
            // n이 홀수라면
            else
                n = n * 3 + 1;
            // index 증가
            index++;
        }
        // 500번 반복할 때까지 1이 되지 않는다면 –1을 반환
        return -1;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L025_12943 solution = new L025_12943();

        int num = 626331;

        int result = solution.solution(num);

        System.out.println(result);
    }
}
