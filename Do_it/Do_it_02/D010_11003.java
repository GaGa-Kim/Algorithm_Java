package Do_it.Do_it_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 11003) 최솟값_찾기
 */
public class D010_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력을 버퍼에 넣고 한 번에 출력하기
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(데이터 개수)
        int N = Integer.parseInt(st.nextToken());
        // L(최솟값을 구하는 범위)
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Deque<Node> mydeque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            // now(현재 데이터 값)
            int now = Integer.parseInt(st.nextToken());
            // 덱의 마지막 위치로부터 now보다 큰 값은 덱에서 제거하기
            while (!mydeque.isEmpty() && mydeque.getLast().value > now) {
                mydeque.removeLast();
            }
            // 덱의 마지막 위치에 now값 저장하기
            mydeque.addLast(new Node(now, i));
            // 덱의 1번째 위치에서부터 L의 범위를 벗어난 값(index <= now index - L)을 덱에서 제거하기
            if (mydeque.getFirst().index <= i - L) {
                mydeque.removeFirst();
            }
            // 덱의 1번째 데이터 출력하기
            bw.write(mydeque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    // 덱에 저장할 노드 클래스 별도 생성하기
    static class Node {
        // index(자신의 위치), value(자신의 값) 담기
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
