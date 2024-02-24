package Programmers.LV_2;

import java.util.Arrays;

/**
 * 258711) 도넛과_막대_그래프
 */
public class L108_258711 {
    // edges(그래프의 간선 정보를 담은 2차원 정수 배열)
    public int[] solution(int[][] edges) {
        // inDegree(각 정점에 대해 들어오는 간선의 개수를 담은 배열)
        int[] inDegree = new int[1000001];
        // outDegree(각 정점에 대해 나가는 간선의 개수를 담은 배열)
        int[] outDegree = new int[1000001];
        // maxNode(최대 정점 번호)
        int maxNode = 0;
        // edge(a번 정점에서 b번 정점으로 향하는 간선)
        for (int[] edge : edges) {
            // a(a번 정점)
            int a = edge[0];
            // b(b번 정점)
            int b = edge[1];
            // 들어오는 간선인 b에 대해 간선 개수 증가
            inDegree[b]++;
            // 나가는 간선인 a에 대해 간선 개수 증가
            outDegree[a]++;
            // maxNode를 갱신
            maxNode = Math.max(maxNode, Math.max(a, b));
        }
        // answer(생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수를 담은 1차원 정수 배열)
        int[] answer = new int[4];
        for (int node = 1; node <= maxNode; node++) {
            // 생성한 정점의 번호인지 확인
            if (isNewNode(node, inDegree, outDegree))
                answer[0] = node;
            // 막대 모양 그래프인지 확인
            else if (isBarGraph(node, outDegree))
                answer[2]++;
            // 8자 모양 그래프인지 확인
            else if (isEightGraph(node, inDegree, outDegree))
                answer[3]++;
        }
        // 도넛 모양 그래프의 수 갱신
        answer[1] = countDonutGraph(answer, outDegree);
        // answer 반환
        return answer;
    }

    // 생성한 정점 찾기
    private boolean isNewNode(int node, int[] inDegree, int[] outDegree) {
        // 들어오는 간선이 없으면서 나가는 간선이 2개 이상이라면
        return inDegree[node] == 0 && outDegree[node] >= 2;
    }

    // 막대 모양 그래프 찾기
    private boolean isBarGraph(int node, int[] outDegree) {
        // 나가는 간선이 0개라면
        return outDegree[node] == 0;
    }

    // 8자 모양 그래프 찾기
    private boolean isEightGraph(int node, int[] inDegree, int[] outDegree) {
        // 들어오는 간선이 2개 이상이며 나가는 간선이 2개라면
        return inDegree[node] >= 2 && outDegree[node] == 2;
    }

    // 도넛 모양 그래프 찾기
    private int countDonutGraph(int[] answer, int[] outDegree) {
        // 전체 그래프 갯수 - (막대 모양 그래프 개수 + 8자 모양 그래프 개수)
        return outDegree[answer[0]] - (answer[2] + answer[3]);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L108_258711 solution = new L108_258711();

        int[][] edges = { { 2, 3 }, { 4, 3 }, { 1, 1 }, { 2, 1 } };

        int[] result = solution.solution(edges);

        System.out.println(Arrays.toString(result));
    }
}
