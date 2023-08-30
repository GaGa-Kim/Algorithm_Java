package Do_it;

import java.util.Scanner;

/**
 * 11720) 숫자의_합
 */
public class D001_11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N값 입력받기
        int N = sc.nextInt();
        // 길이 N의 숫자를 입력받아 String형 변수 sNum에 저장하기
        String sNum = sc.next();
        // sNum을 다시 char[]형 변수 cNum에 변환하여 저장하기
        char[] cNum = sNum.toCharArray();
        // int형 변수 sum 선언하기
        int sum = 0;
        // cNum 길이만큼 반복하기
        for (int i = 0; i < cNum.length; i++) {
            // 배열의 각 자릿값을 아스키코드를 이용하여 정수형으로 변환하며 sum에 더하여 누적하기
            sum += cNum[i] - '0';
        }
        // sum 출력하기
        System.out.print(sum);
    }
}