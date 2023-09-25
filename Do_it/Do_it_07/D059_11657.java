package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 11657) 타임머신
 */
public class D059_11657 {
    // 최단 거리 저장
    static long distance[];
    // 그래프 정보 저장
    static bEdge edges[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(노드 개수)
        int N = Integer.parseInt(st.nextToken());
        // M(엣지 개수)
        int M = Integer.parseInt(st.nextToken());
        // Edges(엣지 리스트 배열)
        edges = new bEdge[M + 1];
        // 거리 배열을 충분히 큰 수로 초기화하기
        distance = new long[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        // 엣지 리스트 배열에 엣지 정보를 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges[i] = new bEdge(start, end, time);
        }
        // 거리 배열에 출발 노드 0으로 초기화하기
        distance[1] = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 현재 엣지 데이터 가져오기
                bEdge edge = edges[j];
                // 출발 노드가 무한대가 아니며 종료 노드값 > 출발 노드값 + 엣지 가중치
                if (distance[edge.start] != Integer.MAX_VALUE
                        && distance[edge.end] > distance[edge.start] + edge.time) {
                    // 업데이트 수행 → 종료 노드값 = 출발 노드값 + 엣지 가중치
                    distance[edge.end] = distance[edge.start] + edge.time;
                }
            }
        }
        // 음수 사이클 확인하기
        boolean myCycle = false;
        for (int i = 0; i < M; i++) {
            // 현재 엣지 데이터 가져오기
            bEdge edge = edges[i];
            // 출발 노드가 무한대가 아니며 종료 노드값 > 출발 노드값 + 엣지 가중치
            if (distance[edge.start] != Integer.MAX_VALUE && distance[edge.end] > distance[edge.start] + edge.time) {
                // 업데이트 가능 → 음수 사이클 존재
                myCycle = true;
            }
        }
        // 음수 사이클 미존재 → 거리 배열 출력하기
        if (!myCycle) {
            for (int i = 2; i <= N; i++) {
                if (distance[i] == Integer.MAX_VALUE)
                    System.out.println("-1");
                else
                    System.out.println(distance[i]);
            }
        }
        // 음수 사이클 존재 → -1 출력하기
        else {
            System.out.println("-1");
        }
    }
}

class bEdge {
    // start(출발 노드)
    int start;
    // end(종료 노드)
    int end;
    // time(엣지의 가중치)
    int time;

    public bEdge(int start, int end, int time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }
}
