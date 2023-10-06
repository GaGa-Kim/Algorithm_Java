package Do_it.Do_it_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1722) 순열의_순서
 */
public class D081_1722 {
    // F(각 자리별로 만들 수 있는 경우의 수)
    static long F[] = new long[21];
    // S(순열을 담는 배열)
    static int S[] = new int[21];
    // visited(숫자 사용 유무 저장 배열)
    static boolean visited[] = new boolean[21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(순열의 길이)
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // 각 자리별로 만들 수 있는 경우의 수를 팩토리얼 형태로 저장하기
        F[0] = 1;
        for (int i = 1; i <= N; i++) {
            F[i] = F[i - 1] * i;
        }
        // Q(문제의 종류)
        int Q = Integer.parseInt(st.nextToken());
        // Q가 1이면 순열 출력하기
        if (Q == 1) {
            // K(몇 번째 순열을 출력할지 입력받기)
            long K = Long.parseLong(st.nextToken());
            for (int i = 1; i <= N; i++) {
                int cnt = 1;
                for (int j = 1; j <= N; j++) {
                    // 이미 사용한 숫자는 계산하지 않음
                    if (visited[j])
                        continue;
                    // 현재 순서가 <= 해당 자리 순열 수 * cnt
                    if (K <= cnt * F[N - i]) {
                        // 현재 순서 = 현재 순서 - (해당 자리 - 1의 순열 수 * (cnt - 1))
                        K -= ((cnt - 1) * F[N - i]);
                        // 순열의 현재 자리에 j값 저장하기
                        S[i] = j;
                        // 숫자 j를 사용 숫자로 체크하기
                        visited[j] = true;
                        // 반복문 종료
                        break;
                    }
                    cnt++;
                }
            }
            // 배열 출력하기
            for (int i = 1; i <= N; i++) {
                System.out.print(S[i] + " ");
            }
        }
        // Q가 2이면 순서 출력하기
        else {
            // K(순열의 순서 저장 변수)
            long K = 1;
            // S(순열 배열 저장)
            for (int i = 1; i <= N; i++) {
                S[i] = Integer.parseInt(st.nextToken());
                long cnt = 0;
                for (int j = 1; j < S[i]; j++) {
                    if (visited[j] == false) {
                        cnt++;
                    }
                }
                // K = K + cnt * 현재 자릿수 - 1에서 만들 수 있는 경우의 수
                K += cnt * F[N - i];
                // S[i]번째 숫자를 사용 숫자로 변경하기
                visited[S[i]] = true;
            }
            // K 출력하기
            System.out.println(K);
        }
    }
}
