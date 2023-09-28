package Do_it.Do_it_07;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 1197) 최소_스패닝_트리
 */
public class D064_1197 {
    static int[] parent;
    static PriorityQueue<pEdge> queue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N(노드 수)
        int N = sc.nextInt();
        // M(엣지 수)
        int M = sc.nextInt();
        // parent(대표 노드 저장 배열)
        parent = new int[N + 1];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        // queue(엣지 정보를 저장할 우선순위 큐)
        queue = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            int v = sc.nextInt();
            // queue에 엣지 정보 저장하기
            queue.add(new pEdge(s, e, v));
        }
        int useEdge = 0;
        int result = 0;
        // 사용한 엣지 수가 노드 - 1이 될 때까지
        while (useEdge < N - 1) {
            // 큐에서 엣지 정보 가져오기
            pEdge now = queue.poll();
            // 엣지 시작점에서 끝점의 부모 노드가 다르면 (연결해도 사이클이 생기지 않으면)
            if (find(now.s) != find(now.e)) {
                // union 연산 수행하기
                union(now.s, now.e);
                // 엣지의 가중치를 정답 변수에 더하기
                result = result + now.v;
                useEdge++;
            }
        }
        // 정답 변수 출력하기
        System.out.println(result);
    }

    // union 연산
    public static void union(int a, int b) {
        // a와 b의 대표 노드 찾기
        a = find(a);
        b = find(b);
        // 두 원소의 대표 노드끼리 연결하기
        if (a != b) {
            parent[b] = a;
        }
    }

    // find 연산
    public static int find(int a) {
        // a가 대표 노드면 리턴하기
        if (a == parent[a])
            return a;
        // 아니면 a의 대표 노드값을 find(parent[a]) 값으로 저장 (재귀 함수 형태)
        else
            return parent[a] = find(parent[a]);
    }
}

class pEdge implements Comparable<pEdge> {
    // s(출발 노드)
    int s;
    // e(종료 노드)
    int e;
    // v(가중치)
    int v;

    pEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    // 가중치가 오름차순 정렬되도록 compareTo 함수 구현하기
    @Override
    public int compareTo(pEdge o) {
        return this.v - o.v;
    }
}