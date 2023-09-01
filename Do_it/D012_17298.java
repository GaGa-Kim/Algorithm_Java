package Do_it;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/**
 * 17298) 오큰수
 */
public class D012_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(수열 개수)
        int N = Integer.parseInt(br.readLine());
        // A[](수열 배열)
        int[] A = new int[N];
        // ans[](정답 배열)
        int[] ans = new int[N];
        // 수열 배열 채우기
        String[] str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(str[i]);
        }
        // 최초 스택 초기화하기
        Stack<Integer> myStack = new Stack<>();
        myStack.push(0);
        for (int i = 1; i < N; i++) {
            // 스택이 비어 있지 않고, 현재 수열 값이 top에 해당하는 수열보다 클 때까지
            while (!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
                // 정답 배열에 오큰수를 현재 수열로 저장하기
                ans[myStack.pop()] = A[i];
            }
            // 현재 수열을 스택에 push (다음 인덱스의 오큰수 구하기)
            myStack.push(i);
        }
        // 스택이 빌 때까지
        while (!myStack.isEmpty()) {
            // 오큰수가 없어 스택에 남아 있는 인덱스의 정답 배열에 -1 저장하기
            ans[myStack.pop()] = -1;
        }
        // 정답 배열 출력하기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < N; i++) {
            bw.write(ans[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
