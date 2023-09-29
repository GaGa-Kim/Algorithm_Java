package Do_it.Do_it_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 17472) 다리_만들기_2
 */
public class D065_17472 {
    // dr, dc(네 방향 탐색을 위한 상수)
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int[] parent;
    static int[][] map;
    static int N, M, sNum;
    static boolean[][] visited;
    static ArrayList<ArrayList<int[]>> sumlist;
    static ArrayList<int[]> mlist;
    static PriorityQueue<bbEdge> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N(행렬의 세로 크기)
        N = Integer.parseInt(st.nextToken());
        // M(행렬의 가로 크기)
        M = Integer.parseInt(st.nextToken());
        // map(맵 정보 저장 배열)
        map = new int[N][M];
        // 입력 데이터를 map 변수에 저장하기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // visited(BFS를 할 때 방문 여부 저장 배열)
        visited = new boolean[N][M];
        // sNum(섬 번호)
        sNum = 1;
        // sumlist(모든 섬 정보 저장하기)
        sumlist = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && visited[i][j] != true) {
                    // BFS(i, j) 모든 위치에서 BFS를 실행해 섬을 분리하기
                    BFS(i, j);
                    sNum++;
                    // 결과(1개의 섬 정보)를 sumlist 변수에 넣기
                    sumlist.add(mlist);
                }
            }
        }
        // queue(다리 정보를 저장할 우선순위 큐)
        queue = new PriorityQueue<>();
        for (int i = 0; i < sumlist.size(); i++) {
            // now -> sumlist에서 추출
            ArrayList<int[]> now = sumlist.get(i);
            // 1개의 섬의 모든 위치에서 만들 수 있는 다리 정보 저장하기
            for (int j = 0; j < now.size(); j++) {
                int r = now.get(j)[0];
                int c = now.get(j)[1];
                int now_S = map[r][c];
                // 네 방향 검색하기
                for (int d = 0; d < 4; d++) {
                    int tempR = dr[d];
                    int tempC = dc[d];
                    int blength = 0;
                    while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                        // 같은 섬이면 엣지를 만들 수 없음
                        if (map[r + tempR][c + tempC] == now_S)
                            break;
                        // 같은 섬도 아니고 바다도 아니면
                        else if (map[r + tempR][c + tempC] != 0) {
                            // 다리의 길이가 2 이상일 때 엣지로 더하기
                            if (blength > 1)
                                queue.add(new bbEdge(now_S, map[r + tempR][c + tempC], blength));
                            break;
                        }
                        // 바다이면 다리의 길이 연장하기
                        else {
                            blength++;
                        }
                        if (tempR < 0)
                            tempR--;
                        else if (tempR > 0)
                            tempR++;
                        else if (tempC < 0)
                            tempC--;
                        else if (tempC > 0)
                            tempC++;
                    }
                }
            }
        }
        // parent(대표 노드 저장 배열)
        parent = new int[sNum];
        // 대표 노드 저장 배열의 값을 자신의 index로 초기화하기
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        int useEdge = 0;
        int result = 0;
        while (!queue.isEmpty()) {
            // 큐에서 엣지 정보 가져오기
            bbEdge now = queue.poll();
            // 엣지 시작점에서 끝점의 부모 노드가 다르면 (연결해도 사이클이 생기지 않으면)
            if (find(now.s) != find(now.e)) {
                // union 연산 수행하기
                union(now.s, now.e);
                // 엣지의 가중치를 정답 변수에 더하기
                result = result + now.v;
                useEdge++;
            }
        }
        // 사용한 엣지가 노드 개수 - 1만큼이면 가중치의 합을 결과로 출력하기
        if (useEdge == sNum - 2)
            System.out.println(result);
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

    // BFS 연산 (섬 구분)
    public static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        // mlist(1개의 섬 정보 저장하기)
        mlist = new ArrayList<>();
        int[] start = { i, j };
        queue.add(start);
        mlist.add(start);
        visited[i][j] = true;
        map[i][j] = sNum;
        // i, j 위치에서 네 방향을 탐색해 1개의 섬의 영역을 저장하기
        while (!queue.isEmpty()) {
            int now[] = queue.poll();
            int r = now[0];
            int c = now[1];
            for (int d = 0; d < 4; d++) {
                int tempR = dr[d];
                int tempC = dc[d];
                while (r + tempR >= 0 && r + tempR < N && c + tempC >= 0 && c + tempC < M) {
                    // 방문한 적이 없고 바다가 아니면 같은 섬으로 취급하기
                    if (visited[r + tempR][c + tempC] == false && map[r + tempR][c + tempC] != 0) {
                        addNode(r + tempR, c + tempC, queue);
                    } else
                        break;
                    if (tempR < 0)
                        tempR--;
                    else if (tempR > 0)
                        tempR++;
                    else if (tempC < 0)
                        tempC--;
                    else if (tempC > 0)
                        tempC++;
                }
            }
        }
    }

    // 특정 위치를 섬의 정보로 넣어 주는 함수
    public static void addNode(int i, int j, Queue<int[]> queue) {
        map[i][j] = sNum;
        visited[i][j] = true;
        int[] temp = { i, j };
        mlist.add(temp);
        queue.add(temp);
    }
}

class bbEdge implements Comparable<bbEdge> {
    // s(출발 노드)
    int s;
    // e(종료 노드)
    int e;
    // v(가중치)
    int v;

    bbEdge(int s, int e, int v) {
        this.s = s;
        this.e = e;
        this.v = v;
    }

    // 가중치가 오름차순 정렬되도록 compareTo 함수 구현하기
    @Override
    public int compareTo(bbEdge o) {
        return this.v - o.v;
    }
}