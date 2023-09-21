package Do_it.Do_it_04;

import java.util.Scanner;

/**
 * 2023) 신기한_소수
 */
public class D024_2023 {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(자릿수)
        N = sc.nextInt();
        // 일의 자리 소수는 2, 3, 5, 7이므로 4개 수에서만 시작
        DFS(2, 1);
        DFS(3, 1);
        DFS(5, 1);
        DFS(7, 1);
    }

    // DFS
    public static void DFS(int number, int jarisu) {
        if (jarisu == N) {
            if (isPrime(number)) {
                System.out.println(number);
            }
            return;
        }
        for (int i = 1; i < 10; i++) {
            // 뒤에 붙는 수가 홀수이면서 소수일 때
            if (i % 2 == 0) {
                continue;
            }
            if (isPrime(number * 10 + i)) {
                // 자릿수를 1씩 늘리면서 DFS 실행
                DFS(number * 10 + i, jarisu + 1);
            }
        }
    }

    // 소수 구하기
    public static boolean isPrime(int num) {
        // 약수가 1과 자기 자신일 경우에만 소수
        for (int i = 2; i <= num / 2; i++)
            if (num % i == 0)
                return false;
        return true;
    }
}
