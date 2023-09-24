package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1854) K번째_최단경로_찾기
 */
public class D058_1854 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(노드 개수)
        int N = Integer.parseInt(st.nextToken());
        // M(엣지 개수)
        int M = Integer.parseInt(st.nextToken());
        // K(몇 번째 최단 경로를 구해야 하는지 나타내는 변수)
        int K = Integer.parseInt(st.nextToken());
        // W(그래프 정보 저장 인접 행렬)
        int[][] W = new int[1001][1001];
        // 최단 거리 큐 배열 초기화하기 (최단 경로를 표현하는 배열을 우선순위 큐 배열로 사용)
        PriorityQueue<Integer>[] distQueue = new PriorityQueue[N + 1];
        Comparator<Integer> cp = new Comparator<Integer>() {
            // 오름차순 정렬 기준 설정
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? 1 : -1;
            }
        };
        for (int i = 0; i < N + 1; i++) {
            distQueue[i] = new PriorityQueue<Integer>(K, cp);
        }
        // 인접 행렬에 엣지 정보 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            W[a][b] = c;
        }
        // 출발 노드는 우선순위 큐에 넣고 시작하기
        PriorityQueue<cNode> pq = new PriorityQueue<>();
        pq.add(new cNode(1, 0));
        distQueue[1].add(0);
        while (!pq.isEmpty()) {
            cNode now = pq.poll();
            for (int next = 1; next <= N; next++) {
                // 해당 노드와 현재 노드가 연결돼 있으면
                if (W[now.node][next] != 0) {
                    // 최단 거리 배열 큐에 해당 노드에 관해 저장된 경로가 K개보다 작으면
                    if (distQueue[next].size() < K) {
                        // 최단 거리 큐 배열에 거리 정보 삽입하고
                        distQueue[next].add(now.cost + W[now.node][next]);
                        // 큐에 선택 노드를 추가하기
                        pq.add(new cNode(next, now.cost + W[now.node][next]));
                    }
                    // 저장된 경로가 K개이고, 최단 거리 큐의 마지막 값 > 이전 노드의 값 + 두 노드 사이의 엣지 가중치
                    else if (distQueue[next].peek() > now.cost + W[now.node][next]) {
                        // 해당 노드의 최단 거리 큐에 마지막값(Max 값) 삭제하고
                        distQueue[next].poll();
                        // 신규값으로 업데이트하기
                        distQueue[next].add(now.cost + W[now.node][next]);
                        // 큐에 선택 노드를 추가하기
                        pq.add(new cNode(next, now.cost + W[now.node][next]));
                    }
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            // 우선순위 큐 크기가 K이면 큐의 값 출력
            if (distQueue[i].size() == K) {
                bw.write(distQueue[i].peek() + "\n");
            }
            // 아니면 -1 출력
            else {
                bw.write(-1 + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

class cNode implements Comparable<cNode> {
    // node(가리키는 노드)
    int node;
    // cost(엣지의 가중치)
    int cost;

    cNode(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    // 우선순위 큐 정렬 기준을 위해 compareTo 함수 구현하기
    @Override
    public int compareTo(cNode o) {
        return this.cost < o.cost ? -1 : 1;
    }
}
