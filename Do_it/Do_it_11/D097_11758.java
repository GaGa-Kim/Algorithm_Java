package Do_it.Do_it_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 11758) CCW
 */
public class D097_11758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // x1, y1, x2, y2, x3, y3 (세 점의 x, y 좌표값을 저장하는 변수)
        // 세 점의 정보를 x1, y1, x2, y2, x3, y3에 입력받기
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        // CCW 수행
        int result = (x1 * y2 + x2 * y3 + x3 * y1) - (x2 * y1 + x3 * y2 + x1 * y3);
        // 결과가 양수이면 1, 음수이면 -1, 0이면 0을 출력하기
        int answer = 0;
        if (result > 0)
            answer = 1;
        else if (result < 0)
            answer = -1;
        else
            answer = 0;
        System.out.println(answer);
    }
}
