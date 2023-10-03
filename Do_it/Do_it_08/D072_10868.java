package Do_it.Do_it_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 10868) 최솟값
 */
public class D072_10868 {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(수의 개수)
        int N = Integer.parseInt(st.nextToken());
        // M(최솟값을 구하는 횟수)
        int M = Integer.parseInt(st.nextToken());
        // treeHeight(트리의 높이) 구하기
        int treeHeight = 0;
        int length = N;
        while (length != 0) {
            length /= 2;
            treeHeight++;
        }
        // treeSize(트리 배열의 크기) 구하기
        int treeSize = (int) Math.pow(2, treeHeight + 1);
        // leftNodeStartIndex(리프 노드 시작 인덱스) 구하기
        int leftNodeStartIndex = treeSize / 2 - 1;
        // tree(세그먼트 트리 배열)
        tree = new long[treeSize + 1];
        // 트리 초기화하기(모든 값을 Max값으로 초기화)
        for (int i = 0; i < tree.length; i++) {
            tree[i] = Integer.MAX_VALUE;
        }
        // tree 배열의 리프 노드 영역에 데이터 입력받기
        for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        // setTree(트리의 크기)
        setTree(treeSize - 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // a(시작 인덱스)
            int a = Integer.parseInt(st.nextToken());
            // b(종료 인덱스)
            int b = Integer.parseInt(st.nextToken());
            // getMin(tree에서 시작 인덱스, tree에서 종료 인덱스)
            a = a + leftNodeStartIndex;
            b = b + leftNodeStartIndex;
            System.out.println(getMin(a, b));
        }
        br.close();
    }

    // 초기 트리를 구성하는 함수
    public static void setTree(int i) {
        // 인덱스가 루트가 아닐 때까지 반복하기
        while (i != 1) {
            // 트리의 인덱스 / 2 부분(부모 노드)의 값과 현재 값을 비교해 현재의 값이 더 작을 때
            if (tree[i / 2] > tree[i])
                // 해당 값을 트리의 인덱스 / 2 부분(부모 노드)에 저장하기
                tree[i / 2] = tree[i];
            // index 1개 감소
            i--;
        }
    }

    // 범위의 최솟값을 구하는 함수
    public static long getMin(int s, int e) {
        // Min(범위의 최솟값을 나타내는 변수, MAX_VALUE로 초기화)
        long Min = Long.MAX_VALUE;
        // 시작 인덱스와 종료 인덱스가 교차될 때까지
        while (s <= e) {
            if (s % 2 == 1) {
                // Min과 현재 인덱스의 트리 값을 비교해 작은 값을 Min 변수에 저장
                Min = Math.min(Min, tree[s]);
                // 시작 인덱스 증가
                s++;
            }
            // 시작 인덱스 = 시작 인덱스 / 2
            s = s / 2;
            if (e % 2 == 0) {
                // Min과 현재 인덱스의 트리 값을 비교해 작은 값을 Min 변수에 저장
                Min = Math.min(Min, tree[e]);
                // 종료 인덱스 감소
                e--;
            }
            // 종료 인덱스 = 종료 인덱스 / 2
            e = e / 2;
        }
        // Min값 리턴하기
        return Min;
    }
}
