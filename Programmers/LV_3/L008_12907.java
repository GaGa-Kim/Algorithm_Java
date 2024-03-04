package Programmers.LV_3;

/**
 * 12907) 거스름돈
 */
public class L008_12907 {
    // n(거슬러 줘야 하는 금액)
    // money(현재 보유하고 있는 돈의 종류)
    public int solution(int n, int[] money) {
        // D(i개의 동전으로 j원을 만들 수 있는 경우의 수)
        int[][] D = new int[money.length + 1][n + 1];
        // 1개의 동전부터 money의 길이의 동전까지
        for (int i = 1; i <= money.length; i++) {
            // 0원부터 n + 1원까지
            for (int j = 0; j < n + 1; j++) {
                // j가 0이라면
                if (j == 0)
                    // 0원을 만들 수 있는 경우의 수는 1개
                    D[i][j] = 1;
                else {
                    // j가 money[i - 1]보다 작다면
                    if (j < money[i - 1])
                        // 현재 동전을 사용할 수 없으므로 현재 동전을 사용하지 않고 j원을 만드는 경우의 수만 구하도록 함
                        D[i][j] = D[i - 1][j] % 1000000007;
                    else
                        // 현재 동전을 사용하지 않고 j원을 만드는 경우의 수와 현재 동전을 사용하여 만드는 경우의 수의 합을 구하도록 함
                        D[i][j] = (D[i - 1][j] + D[i][j - money[i - 1]]) % 1000000007;
                }
            }
        }
        // D[money의 길이][n]을 반환
        return D[money.length][n];
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L008_12907 solution = new L008_12907();

        int n = 5;
        int[] money = { 1, 2, 5 };

        int result = solution.solution(n, money);

        System.out.println(result);
    }
}
