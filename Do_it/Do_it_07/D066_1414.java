package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1414) 불우이웃돕기
 */
public class D066_1414 {
    static int[] parent;
    static PriorityQueue<lEdge> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(컴퓨터의 개수)
        int N = Integer.parseInt(st.nextToken());
        // sum(모든 랜선의 합 저장하기)
        int sum = 0;
        // queue(엣지 정보를 저장할 우선순위 큐)
        queue = new PriorityQueue<>();
        // 랜선의 총합 저장하기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] tempc = st.nextToken().toCharArray();
            for (int j = 0; j < N; j++) {
                int temp = 0;
                // 소문자일 때
                if (tempc[j] >= 'a' && tempc[j] <= 'z')
                    temp = tempc[j] - 'a' + 1;
                // 대문자일 때
                else if (tempc[j] >= 'A' && tempc[j] <= 'Z')
                    temp = tempc[j] - 'A' + 27;
                sum = sum + temp;
                // i와 j가 다르면 랜선 정보를 큐에 저장하기
                if (i != j && temp != 0)
                    queue.add(new lEdge(i, j, temp));
            }
        }
        // parent(대표 노드 저장 배열)
        parent = new int[N];
        // 대표 노드 저장 배열의 값을 자신의 index로 초기화하기
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int useEdge = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            // 큐에서 엣지 정보 가져오기
            lEdge now = queue.poll();
            // 엣지 시작점에서 끝점의 부모 노드가 다르면 (연결해도 사이클이 생기지 않으면)
            if (find(now.s) != find(now.e)) {
                // union 연산 수행하기
                union(now.s, now.e);
                // 엣지의 가중치를 정답 변수에 더하기
                result = result + now.v;
                useEdge++;
            }
        }
        // 사용한 엣지가 노드 개수 - 1만큼이면 모든 랜선의 합에서 최소 신장 트리의 결괏값을 뺀 값을 출력
        if (useEdge == N - 1)
            System.out.println(sum - result);
        // 아니면 -1 출력하기
        else
            System.out.println(-1);
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

class lEdge implements Comparable<lEdge> {
    // s(출발 노드)
    int s;
    // e(종료 노드)
    int e;
    // v(가중치)
    int v;

    lEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    // 가중치가 오름차순 정렬되도록 compareTo 함수 구현하기
    @Override
    public int compareTo(lEdge o) {
        return this.v - o.v;
    }
}
