import java.util.Scanner;

/**
 * 2018) 수들의_합_5
 */
public class 수들의_합_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N 변수 저장
        int N = sc.nextInt();
        // 사용 변수 초기화
        int count = 1;
        int start_index = 1;
        int end_index = 1;
        int sum = 1;
        while (end_index != N) {
            // 현재 연속 합이 N과 같은 경우
            if (sum == N) {
                // count 증가, end_index 증가, sum값 변경
                count++;
                end_index++;
                sum = sum + end_index;
                // 현재 연속 합이 N보다 더 큰 경우
            } else if (sum > N) {
                // sum값 변경, start_index 증가
                sum = sum - start_index;
                start_index++;
                // 현재 연속 합이 N보다 작은 경우
            } else {
                // end_index 증가, sum값 변경
                end_index++;
                sum = sum + end_index;
            }
        }
        // count 출력하기
        System.out.println(count);
    }
}