package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1219) 오민식의_고민
 */
public class D060_1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(노드 개수)
        int N = Integer.parseInt(st.nextToken());
        // sCity(시작 도시)
        int sCity = Integer.parseInt(st.nextToken());
        // eCity(종료 도시)
        int eCity = Integer.parseInt(st.nextToken());
        // M(엣지 개수)
        int M = Integer.parseInt(st.nextToken());
        // Edges(엣지 리스트 배열)
        dEdge[] edges = new dEdge[M];
        // cityMoney(각 도시에서 버는 수입 배열)
        long[] cityMoney = new long[N];
        // 거리 배열
        long[] distance = new long[N];
        // 거리 배열을 충분히 큰 작은 수로 초기화하기
        Arrays.fill(distance, Long.MIN_VALUE);
        // 엣지 리스트 배열에 엣지 정보를 저장하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            edges[i] = new dEdge(start, end, price);
        }
        // 수입 배열에 각 도시에서 버는 수입 정보를 저장하기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cityMoney[i] = Long.parseLong(st.nextToken());
        }
        // 거리 배열에 출발 노드 cityMoney[출발 노드]로 초기화하기
        distance[sCity] = cityMoney[sCity];
        for (int i = 0; i <= N + 100; i++) {
            for (int j = 0; j < M; j++) {
                // 현재 엣지 데이터 가져오기
                int start = edges[j].start;
                int end = edges[j].end;
                int price = edges[j].price;
                // 출발 노드가 방문하지 않은 노드 → 값이 Long.MIN_VALUE
                if (distance[start] == Long.MIN_VALUE)
                    // Skip
                    continue;
                // 출발 노드가 양수 사이클에 연결된 노드 → 값이 Long.MAX_VALUE
                else if (distance[start] == Long.MAX_VALUE)
                    // 종료 노드를 양수 사이클에 연결된 노드로 업데이트 → 값 = Long.MAX_VALUE
                    distance[end] = Long.MAX_VALUE;
                // 종료 노드의 값 < 출발 노드의 값 + 도착 도시에서의 수입 - 엣지의 가중치
                else if (distance[end] < distance[start] + cityMoney[end] - price) {
                    // 종료 노드의 값을 출발 노드의 값 + 도착 도시에서의 수입 - 엣지의 가중치로 업데이트
                    distance[end] = distance[start] + cityMoney[end] - price;
                    // N - 1 반복 이후 업데이트될 때
                    if (i >= N - 1)
                        // 이 종료 노드를 양수 사이클 연결 노드로 업데이트
                        distance[end] = Long.MAX_VALUE;
                }
            }
        }
        // 도착 도시가 Long.MIN_VALUE → 도착 불가 → gg
        if (distance[eCity] == Long.MIN_VALUE)
            System.out.println("gg");
        // 도착 도시가 Long.MAX_VALUE → 돈을 무한대로 벌 수 있음 → Gee
        else if (distance[eCity] == Long.MAX_VALUE)
            System.out.println("Gee");
        // 이외의 경우 → 도착 도시의 값 출력하기
        else
            System.out.println(distance[eCity]);
    }
}

class dEdge {
    // start(출발 노드)
    int start;
    // end(종료 노드)
    int end;
    // price(엣지의 가중치)
    int price;

    public dEdge(int start, int end, int price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }
}
