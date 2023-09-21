package Do_it.Do_it_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 11689) GCD(n, k) = 1
 */
public class D041_11689 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n(소인수 표현)
        long n = Long.parseLong(br.readLine());
        // result(결괏값)
        long result = n;
        // 제곱근까지만 진행
        for (long p = 2; p <= Math.sqrt(n); p++) {
            // p가 소인수인지 확인
            if (n % p == 0) {
                // 결괏값 = 결괏값 - 결괏값 / 현재값 업데이트
                result = result - result / p;
                // n에서 현재 소인수 내역을 제거하기
                while (n % p == 0) {
                    n /= p;
                }
            }
        }
        // 아직 소인수 구성이 남아 있을 때
        if (n > 1)
            // 반복문에서 제곱근까지만 탐색했으므로 1개의 소인수가 누락되는 케이스
            result = result - result / n;
        // 결괏값 출력하기
        System.out.println(result);
    }
}
