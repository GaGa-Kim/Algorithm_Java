package Programmers.LV_2;

/**
 * 148652) 유사_칸토어_비트열
 */
public class L089_148652 {
    // n(알고싶은 유사 칸토어 비트열)
    // l, r(1의 개수가 몇 개인지 알고 싶은 구간)
    public int solution(int n, long l, long r) {
        // answer(구간 내의 1의 개수) = 1의 개수 세기 함수
        int answer = countOne(n, l, r, 1);
        // answer 반환
        return answer;
    }

    // 1의 개수 세기 함수
    private int countOne(int n, long l, long r, long index) {
        // n이 0이라면
        if (n == 0)
            // 1을 반환
            return 1;
        // number(1의 갯수)
        int number = 0;
        // part(이전 유사 칸토어 비트열의 길이) = 5의 n - 1의 제곱
        long part = (long) Math.pow(5, n - 1);
        for (int i = 0; i < 5; i++) {
            // 가 2이거나, index + part * i가 r보다 크거나, index + part * (i + 1) -1이 l보다 작다면
            if (i == 2 || r < index + part * i || index + part * (i + 1) - 1 < l)
                // 0이거나 범위를 벗어나므로 continue
                continue;
            // number += 1의 개수 세기 함수(n - 1, s, e, index + part * i)
            number += countOne(n - 1, l, r, index + part * i);
        }
        // number 반환
        return number;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L089_148652 solution = new L089_148652();

        int n = 2;
        long l = 4;
        long r = 17;

        int result = solution.solution(n, l, r);

        System.out.println(result);
    }
}
