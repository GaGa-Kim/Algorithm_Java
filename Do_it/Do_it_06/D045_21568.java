package Do_it.Do_it_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 21568) Ax+By=C
 */
public class D045_21568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // a(1번째 수)
        int a = Integer.parseInt(st.nextToken());
        // b(2번째 수)
        int b = Integer.parseInt(st.nextToken());
        // c(3번째 수)
        int c = Integer.parseInt(st.nextToken());
        // a와 b의 최대 공약수
        long gcd = gcd(a, b);
        // c가 최대 공약수의 배수가 아니라면
        if (c % gcd != 0) {
            // -1 출력하기
            System.out.println(-1);
        } else {
            int mok = (int) (c / gcd);
            // 나머지(b)가 0이 될 때까지 재귀 함수를 호출하는 유클리드 호제법 함수 호출하기
            long[] ret = Excute(a, b);
            // 결괏값에 c/최대 공약수의 값을 곱한 후 해당 값을 출력하기
            System.out.println(ret[0] * mok + " " + ret[1] * mok);
        }
    }

    public static long[] Excute(long a, long b) {
        long[] ret = new long[2];
        // if(b == 0) 재귀 함수를 중단하고 return
        if (b == 0) {
            ret[0] = 1;
            ret[1] = 0;
            return ret;
        }
        // 현재 보고 있는 몫
        long q = a / b;
        // 재귀를 빠져나오는 영역에서 자연스럽게 역순이 저장됨
        long[] v = Excute(b, a % b);
        // x = y' 계산
        ret[0] = v[1];
        // y = x' - y' * 몫 계산
        ret[1] = v[0] - v[1] * q;
        return ret;
    }

    // 최대 공약수 함수 구현하기
    public static int gcd(int a, int b) {
        // if(b가 0이면)
        if (b == 0)
            // a가 최대 공약수
            return a;
        else
            // gcd(작은 수, 큰 수 % 작은 수)
            return gcd(b, a % b);
    }
}
