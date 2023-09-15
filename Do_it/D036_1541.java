package Do_it;

import java.util.Scanner;

/**
 * 1541) 잃어버린_괄호
 */
public class D036_1541 {
    // answer(정답 변수)
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 들어온 데이터를 "-" 기호를 기준으로 split 수행하기
        String example = sc.nextLine();
        String[] str = example.split("-");
        for (int i = 0; i < str.length; i++) {
            // 결괏값 = mySum() 함수 수행하기
            int temp = mySum(str[i]);
            // 가장 앞 데이터일 때
            if (i == 0)
                // answer에 결괏값 더하기
                answer = answer + temp;
            else
                // answer에 결괏값 빼기
                answer = answer - temp;
        }
        // answer 출력하기
        System.out.println(answer);
    }

    // 나뉜 그룹의 더하기 연산 수행 함수
    public static int mySum(String a) {
        int sum = 0;
        // 현재 들어온 String 값을 "+" 기호 기준으로 split 수행하기
        String[] temp = a.split("[+]");
        for (int i = 0; i < temp.length; i++) {
            // String 값을 Integer형으로 변환해 리턴값에 더하기
            sum += Integer.parseInt(temp[i]);
        }
        // 전체 합 리턴하기
        return sum;
    }
}
