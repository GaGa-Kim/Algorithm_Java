import java.util.Scanner;
import java.util.Stack;

/**
 * 1874) 스택_수열
 */
public class 스택_수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(수열 개수)
        int N = sc.nextInt();
        // A[](수열 배열)
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();
        // 오름차순 수
        int num = 1;
        boolean result = true;
        for (int i = 0; i < A.length; i++) {
            // 현재 수열의 수
            int su = A[i];
            // 현재 수열 값 >= 오름차순 자연수 : 값이 같아질 때까지 push() 수행
            if (su >= num) {
                while (su >= num) {
                    stack.push(num++);
                    // (+) 저장
                    bf.append("+\n");
                }
                stack.pop();
                // (-) 저장
                bf.append("-\n");
                // 현재 수열 값 < 오름차순 자연수 : pop()을 수행해서 수열 원소를 꺼냄
            } else {
                int n = stack.pop();
                // 스택의 가장 위의 수가 만들어야 하는 수열의 수보다 크면 수열을 출력할 수 없음
                if (n > su) {
                    System.out.println("NO");
                    result = false;
                    break;
                } else {
                    // (-) 저장
                    bf.append("-\n");
                }
            }
        }
        // if(NO 값을 출력한 적이 없으면) 저장한 값 출력
        if (result)
            System.out.println(bf.toString());
    }
}