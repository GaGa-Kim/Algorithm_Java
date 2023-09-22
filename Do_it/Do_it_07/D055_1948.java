package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1948) 임계경로
 */
public class D055_1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(도시 수)
        int N = Integer.parseInt(br.readLine());
        // M(도로 수)
        int M = Integer.parseInt(br.readLine());
        // A(도시 인접 리스트)
        ArrayList<ArrayList<dNode>> A = new ArrayList<>();
        // reverseA(역방향 인접 리스트)
        ArrayList<ArrayList<dNode>> reverseA = new ArrayList<>();
        // 도시 수만큼 인접 리스트 초기화하기
        for (int i = 0; i <= N; i++) {
            A.add(new ArrayList<>());
            reverseA.add(new ArrayList<>());
        }
        // 진입 차수 배열 초기화하기
        int[] indegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            // 인접 리스트 데이터 저장하기
            A.get(S).add(new dNode(E, V));
            // 역방향 인접 리스트 데이터 저장하기
            reverseA.get(E).add(new dNode(S, V));
            // 진입 차수 배열 초기 데이터 저장하기
            indegree[E]++;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startDosi = Integer.parseInt(st.nextToken());
        int endDosi = Integer.parseInt(st.nextToken());
        // 큐 생성하기
        Queue<Integer> queue = new LinkedList<>();
        // 출발 도시를 큐에 삽입하기
        queue.offer(startDosi);
        // result(각 도시의 최대 걸리는 시간 저장 - 임계 경로값)
        int[] result = new int[N + 1];
        while (!queue.isEmpty()) {
            // 현재 노드 = 큐에서 데이터 poll
            int now = queue.poll();
            // 현재 노드에서 갈 수 있는 노드
            for (dNode next : A.get(now)) {
                // 타깃 노드 진입 차수 배열 --
                indegree[next.targetNode]--;
                // result = 타깃 노드의 현재 경로 값과 현재 노드의 경로 값 + 도로 시간 값 중 큰 값으로 저장하기
                result[next.targetNode] = Math.max(result[next.targetNode], result[now] + next.value);
                // 타깃 노드의 진입 차수가 0이면
                if (indegree[next.targetNode] == 0) {
                    // 큐에 타깃 노드 추가하기
                    queue.offer(next.targetNode);
                }
            }
        }
        // resultCount(1분도 쉬지 않고 달려야 하는 도로의 수)
        int resultCount = 0;
        // visited(각 도시의 방문 유무 저장하기)
        boolean visited[] = new boolean[N + 1];
        queue = new LinkedList<>();
        // 도착 도시를 큐에 삽입하기
        queue.offer(endDosi);
        // visited 배열에 도착 도시를 방문 도시로 표시하기
        visited[endDosi] = true;
        while (!queue.isEmpty()) {
            // 현재 노드 = 큐에서 데이터 poll
            int now = queue.poll();
            // 현재 노드에서 갈 수 있는 노드
            for (dNode next : reverseA.get(now)) {
                // 타깃 노드의 result 값 + 도로를 걸리는데 지나는 시간 == 현재 노드의 result 값
                if (result[next.targetNode] + next.value == result[now]) {
                    // 1분도 쉬지 않고 달려야 하는 도로 값 1 증가
                    resultCount++;
                    // 아직 방문하지 않은 도시이면
                    if (visited[next.targetNode] == false) {
                        // visited 배열에 방문 도시 표시하기
                        visited[next.targetNode] = true;
                        // 큐에 타깃 노드 추가하기
                        queue.offer(next.targetNode);
                    }
                }
            }
        }
        // 만나는 시간 출력
        System.out.println(result[endDosi]);
        // 1분도 쉬지 않고 달려야 하는 도로의 수 출력
        System.out.println(resultCount);
    }
}

class dNode {
    int targetNode;
    int value;

    dNode(int targetNode, int value) {
        this.targetNode = targetNode;
        this.value = value;
    }
}
