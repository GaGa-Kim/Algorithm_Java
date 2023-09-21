package Do_it.Do_it_06;

import java.util.Scanner;

/**
 * 1016) 제곱_ㄴㄴ_수
 */
public class D040_1016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Min(최솟값)
        long Min = sc.nextLong();
        // Max(최댓값)
        long Max = sc.nextLong();
        // Check(Min ~ Max 사이에 제곱수 판별 배열)
        boolean[] Check = new boolean[(int) (Max - Min + 1)];
        // 2의 제곱수인 4부터 Max보다 작거나 같은 값까지 반복하기
        for (long i = 2; i * i <= Max; i++) {
            // pow(제곱수)
            long pow = i * i;
            // start_index(최솟값/제곱수)
            long start_index = Min / pow;
            // 나머지가 있으면 1을 더해야 Min보다 큰 제곱수에서 시작됨
            if (Min % pow != 0)
                start_index++;
            // j * pow가 Max보다 작을 때
            for (long j = start_index; pow * j <= Max; j++) {
                // 최솟값, 최댓값 사이의 제곱수이므로 Check 배열에 저장하기
                Check[(int) ((j * pow) - Min)] = true;
            }
        }
        // count(제곱이 아닌 수 카운트)
        int count = 0;
        for (int i = 0; i <= Max - Min; i++) {
            // Check 배열에서 제곱이 아닌 수라면 count 증가
            if (!Check[i]) {
                count++;
            }
        }
        // count 출력하기
        System.out.println(count);
    }
}
