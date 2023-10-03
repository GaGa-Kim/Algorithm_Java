package Do_it.Do_it_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2042) 구간_합_구하기
 */
public class D071_2042 {
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(수의 개수)
        int N = Integer.parseInt(st.nextToken());
        // M(변경이 일어나는 개수)
        int M = Integer.parseInt(st.nextToken());
        // K(구간 합을 구하는 개수)
        int K = Integer.parseInt(st.nextToken());
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
        // tree 배열의 리프 노드 영역에 데이터 입력받기
        for (int i = leftNodeStartIndex + 1; i <= leftNodeStartIndex + N; i++) {
            tree[i] = Long.parseLong(br.readLine());
        }
        // setTree(트리의 크기)
        setTree(treeSize - 1);
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            // a(질의 유형)
            int a = Integer.parseInt(st.nextToken());
            // b(시작 인덱스)
            int b = Integer.parseInt(st.nextToken());
            // c(변경값 또는 종료 인덱스)
            long c = Long.parseLong(st.nextToken());
            // a가 1일 때
            if (a == 1) {
                // changeVal(tree에서 시작 인덱스, c(변경값))
                changeVal(leftNodeStartIndex + b, c);
            }
            // a가 2일 때
            else if (a == 2) {
                // getSum(tree에서 시작 인덱스, tree에서 종료 인덱스)
                b = b + leftNodeStartIndex;
                c = c + leftNodeStartIndex;
                System.out.println(getSum(b, (int) c));
            } else {
                return;
            }
        }
        br.close();
    }

    // 초기 트리를 구성하는 함수
    public static void setTree(int i) {
        // 인덱스가 루트가 아닐 때까지 반복하기
        while (i != 1) {
            // 트리의 인덱스 / 2 부분(부모 노드)에 현재 index의 트리 값 더하기
            tree[i / 2] += tree[i];
            // index 1개 감소
            i--;
        }
    }

    // 값을 변경하는 함수
    public static void changeVal(int index, long val) {
        // diff(현재 노드의 값과 변경된 값의 차이)
        long diff = val - tree[index];
        while (index > 0) {
            // 시작 인덱스의 트리 값에 diff값을 더함
            tree[index] = tree[index] + diff;
            // 시작 인덱스 = 시작 인덱스 / 2
            index = index / 2;
        }
    }

    // 구간 합을 구하는 함수
    public static long getSum(int s, int e) {
        long partSum = 0;
        // 시작 인덱스와 종료 인덱스가 교차될 때까지
        while (s <= e) {
            if (s % 2 == 1) {
                // 해당 노드의 값을 구간 합에 추가하거나 시작 인덱스 증가
                partSum = partSum + tree[s];
                s++;
            }
            if (e % 2 == 0) {
                // 해당 노드의 값을 구간 합에 추가하거나 종료 인덱스 감소
                partSum = partSum + tree[e];
                e--;
            }
            // 시작 인덱스 = 시작 인덱스 / 2
            s = s / 2;
            // 종료 인덱스 = 종료 인덱스 / 2
            e = e / 2;
        }
        // 구간 합 결과 리턴하기
        return partSum;
    }
}
