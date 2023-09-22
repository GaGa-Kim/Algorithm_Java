package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1516) 게임_개발
 */
public class D054_1516 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N(건물 종류 수)
        int N = Integer.parseInt(br.readLine());
        // A(데이터 저장 인접 리스트)
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        // 건물의 개수만큼 인접 리스트 초기화하기
        for (int i = 0; i <= N; i++) {
            A.add(new ArrayList<>());
        }
        // 진입 차수 배열 초기화하기
        int[] indegree = new int[N + 1];
        // 자기 자신을 짓는데 걸리는 시간 저장 배열 초기화하기
        int[] selfBuild = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 건물을 짓는데 걸리는 시간 저장하기
            selfBuild[i] = Integer.parseInt(st.nextToken());
            // 인접 리스트 데이터 저장하기
            while (true) {
                int preTemp = Integer.parseInt(st.nextToken());
                if (preTemp == -1)
                    break;
                A.get(preTemp).add(i);
                // 진입 차수 배열 초기 데이터 저장하기
                indegree[i]++;
            }
        }
        // 큐 생성하기
        Queue<Integer> queue = new LinkedList<>();
        // 진입 차수 배열의 값이 0인 건물을 큐에 삽입하기
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] result = new int[N + 1];
        while (!queue.isEmpty()) {
            // 현재 노드 = 큐에서 데이터 poll
            int now = queue.poll();
            // 현재 노드에서 갈 수 있는 노드
            for (int next : A.get(now)) {
                // 타깃 노드 진입 차수 배열 --
                indegree[next]--;
                // 결과 노드 업데이트 = Math.max(현재 저장된 값, 현재 출발 노드 + 비용)
                result[next] = Math.max(result[next], result[now] + selfBuild[now]);
                // 타깃 노드의 진입 차수가 0이면
                if (indegree[next] == 0) {
                    // 큐에 타깃 노드 추가하기
                    queue.offer(next);
                }
            }
        }
        // 위상 정렬 결과 출력하기
        for (int i = 1; i <= N; i++) {
            // 자기 건물을 짓는데 걸리는 시간을 더함
            System.out.println(result[i] + selfBuild[i]);
        }
    }
}
