package Do_it;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * 1850) 최대공약수
 */
public class D043_1850 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // a(1번째 수)
        long a = sc.nextLong();
        // b(2번째 수)
        long b = sc.nextLong();
        // 결괏값 = gcd(a, b)
        long result = gcd(a, b);
        // 결괏값만큼 1을 반복해 출력하기
        while (result > 0) {
            bw.write("1");
            result--;
        }
        bw.flush();
        bw.close();
    }

    // 최대 공약수 함수 구현하기
    public static long gcd(long a, long b) {
        // if(b가 0이면)
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // gcd(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }
}
