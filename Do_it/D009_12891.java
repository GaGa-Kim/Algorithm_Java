package Do_it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 12891) DNA_비밀번호
 */
public class D009_12891 {
    static int checkArr[];
    static int myArr[];
    static int checkSecret;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        // S(문자열 크기) P(부분 문자열의 크기)
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Result = 0;
        // A(문자열 데이터)
        char A[] = new char[S];
        // charArr(비밀번호 체크 배열) - ACGT
        checkArr = new int[4];
        // myArr(현재 상태 배열) - ACGT
        myArr = new int[4];
        // checkSecret(몇 개의 문자와 관련된 개수를 충족했는지 판단하는 변수)
        checkSecret = 0;
        A = bf.readLine().toCharArray();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            // 0이면 이미 그 문자열에 대해서는 충족하므로 +1
            if (checkArr[i] == 0)
                checkSecret++;
        }
        // P 범위(0 ~ P - 1)만큼 S배열에 적용하고, 유효한 비밀번호인지 판단하기
        // 초기 P 부분 문자열 처리 부분
        for (int i = 0; i < P; i++) {
            Add(A[i]);
        }
        if (checkSecret == 4)
            Result++;
        // 슬라이딩 윈도우 처리 부분
        for (int i = P; i < S; i++) {
            // 한 칸씩 이동하면서 제거되는 문자열과 새로 들어오는 문자열을 처리하기
            // i는 부분 문자열의 끝 (추가되어야 함), j는 부분 문자열의 처음 (삭제되어야 함)
            int j = i - P;
            Add(A[i]);
            Remove(A[j]);
            // 4자릿수와 관련된 크기가 모두 충족될 때 유효한 비밀번호
            if (checkSecret == 4)
                Result++;
        }
        System.out.println(Result);
        bf.close();
    }

    // 문자 더하기 함수
    private static void Add(char c) {
        // 새로 들어온 문자를 myArr에 업데이트하거나 checkSecret 값 변경하기
        switch (c) {
            case 'A':
                myArr[0]++;
                if (myArr[0] == checkArr[0]) // 추가됨으로써 조건 충족 O
                    checkSecret++;
                break;
            case 'C':
                myArr[1]++;
                if (myArr[1] == checkArr[1])
                    checkSecret++;
                break;
            case 'G':
                myArr[2]++;
                if (myArr[2] == checkArr[2])
                    checkSecret++;
                break;
            case 'T':
                myArr[3]++;
                if (myArr[3] == checkArr[3])
                    checkSecret++;
                break;
        }
    }

    // 문자 빼기 함수
    private static void Remove(char c) {
        // 제거되는 문자를 myArr에 업데이트하거 checkSecret 값 변경하기
        switch (c) {
            case 'A':
                if (myArr[0] == checkArr[0]) // 제거됨으로써 조건 충족 X
                    checkSecret--;
                myArr[0]--;
                break;
            case 'C':
                if (myArr[1] == checkArr[1])
                    checkSecret--;
                myArr[1]--;
                break;
            case 'G':
                if (myArr[2] == checkArr[2])
                    checkSecret--;
                myArr[2]--;
                break;
            case 'T':
                if (myArr[3] == checkArr[3])
                    checkSecret--;
                myArr[3]--;
                break;
        }
    }
}