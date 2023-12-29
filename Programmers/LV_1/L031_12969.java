package Programmers.LV_1;

import java.util.Scanner;

/**
 * 12969) 직사각형_별찍기
 */
public class L031_12969 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // a, b(두 정수)
        int a = sc.nextInt();
        int b = sc.nextInt();

        // b(세로 줄)만큼 반복
        for (int i = 0; i < b; i++) {
            // s(가로 줄)
            String s = "*";
            // s를 a만큼 반복
            s = s.repeat(a);
            // s 출력
            System.out.println(s);
        }
    }
}
