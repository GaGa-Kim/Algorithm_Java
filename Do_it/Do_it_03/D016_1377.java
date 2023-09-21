package Do_it.Do_it_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 버블_소트
 */
public class D016_1377 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(데이터 개수)
        int N = Integer.parseInt(br.readLine());
        // A(데이터 배열, 단 클래스를 데이터로 담는 배열)
        mData[] A = new mData[N];
        // A 배열 저장하기
        for (int i = 0; i < N; i++) {
            A[i] = new mData(Integer.parseInt(br.readLine()), i);
        }
        // A 배열 정렬하기
        Arrays.sort(A);
        // A[i]의 정렬 전 index - 정렬 후 index 계산의 최댓값을 찾아 저장하기
        int Max = 0;
        for (int i = 0; i < N; i++) {
            if (Max < A[i].index - i) {
                Max = A[i].index - i;
            }
        }
        // 최댓값 +1을 정답으로 출력하기
        System.out.println(Max + 1);
    }
}

// 별도 클래스 선언하기
class mData implements Comparable<mData> {
    int value;
    int index;

    public mData(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    // value 기준 오름차순 정렬
    @Override
    public int compareTo(mData o) {
        return this.value - o.value;
    }
}
