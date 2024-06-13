package Baekjoon.B_16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 아기상어의 상하좌우를 확인하며 물고기를 먹으러 가도록 함
 *   이때 자신보다 작은 물고기가 있다면 이를 먹고, 같다면 지나가고, 크면 지나갈 수 없음
 *   이를 반복하며 아기상어가 더 이상 먹을 수 있는 물고기가 없다면 멈추도록 함
 *   
 */

/* 
 * 손으로 풀어보기
 * 1. 아기상어의 상하좌우를 확인하며 먹을 수 있는 물고기가 1마리 이상 있는지 확인
 *    먹을 수 있는 물고기는 자신보다 크기가 작은 물고기이며, 이동 시 자신과 같다면 지나갈 수 있고 자신보다 크다면 지나갈 수 없음
 *    이때 물고기가 1마리라면 이 물고기를 먹으러 가고, 
 *    그렇지 않다면 거리가 가장 가까운 물고기/거리가 가까운 물고기 많다면 가장 위쪽 물고기/그러한 물고기가 많다면 가장 왼쪽 순으로 선택하도록 함
 * 2. 자신의 크기와 같은 수의 물고기만큼 먹은 후 크기를 1만큼 증가
 * 3. 더 이상 먹을 수 있는 물고기가 없을 때까지 반복
 * 4. 아기 상어가 엄마 상어에게 도움을 요청하는 시간 출력
 */

/*
 * 16236) 아기상어 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int[][] board; // board(물고기와 아기 상어가 있는 좌표 배열)

    static int[] dr = { -1, 1, 0, 0 }; // dr, dc(아기상어가 이동할 상하좌우 좌표)
    static int[] dc = { 0, 0, -1, 1 };

    static int[] shark = new int[2]; // shark(아기 상어의 위치)
    static int shark_size = 2; // shark_size(아기 상어의 크기)
    static int count = 0; // count(아기 상어가 먹은 물고기 수)

    static int[] fish = new int[2]; // fish(아기 상어와 가장 가까이 있는 물고기 위치)
    static int[][] distance; // distance(아기 상어와 물고기 위치에 따른 거리 이동 횟수 배열)
    static int min_distance; // min_distance(아기 상어와 가장 가까이 있는 물고기까지 최소 거리 이동 횟수)

    static int time = 0; // time(아기 상어가 움직인 횟수)

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) { // 공간 정보와 아기 상어의 위치 저장
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                    board[i][j] = 0;
                }
            }
        }
    }

    /*
     * 아기 상어 물고기 찾기
     */
    static void find() {
        while (true) { // 엄마 상어에게 도움을 요청하지 않을 때까지
            fish[0] = Integer.MAX_VALUE;
            fish[1] = Integer.MAX_VALUE;
            distance = new int[N][N];
            min_distance = Integer.MAX_VALUE;

            move(); // 물고기를 찾아 이동

            if (fish[0] != Integer.MAX_VALUE && fish[1] != Integer.MAX_VALUE) { // 먹을 수 있는 물고기가 있다면
                count++; // 물고기 먹기
                board[fish[0]][fish[1]] = 0;
                shark[0] = fish[0]; // 아기 상어 위치 갱신
                shark[1] = fish[1];
                time += distance[fish[0]][fish[1]]; // 이동한 거리만큼 이동 거리 시간 추가

                if (count == shark_size) { // 자신의 크기와 같은 수의 물고기만큼 먹었다면
                    shark_size++;
                    count = 0;
                }
            } else { // 더 이상 먹을 수 있는 물고기가 없다면 종료
                break;
            }
        }
    }

    /*
     * 아기 상어 이동하기
     */
    static void move() {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[] { shark[0], shark[1] });

        while (!queue.isEmpty()) {
            int[] now = queue.poll(); // 현재 아기 상어의 위치
            int r = now[0];
            int c = now[1];

            for (int d = 0; d < 4; d++) { // 아기 상어의 상하좌우 방향 확인
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) { // 좌표를 벗어난다면 이동할 수 없음
                    continue;
                }
                if (board[nr][nc] > shark_size) { // 물고기의 크기가 아기 상어보다 크다면 지나갈 수 없음
                    continue;
                }
                if (distance[nr][nc] != 0) { // 이미 이 위치를 확인했다면 이동할 수 없음
                    continue;
                }

                distance[nr][nc] = distance[r][c] + 1; // 이 위치까지의 최소 이동 거리로 갱신

                if (board[nr][nc] != 0 && board[nr][nc] < shark_size) { // 아기 상어보다 크기가 작은 물고기라면 먹을 수 있음
                    if (min_distance > distance[nr][nc]) { // 거리가 가장 가까운 물고기보다 더 가까운 물고기라면, 거리가 가장 가까운 물고기가 1마리이므로 이 물고기로 갱신
                        min_distance = distance[nr][nc];
                        fish[0] = nr;
                        fish[1] = nc;
                    } else if (min_distance == distance[nr][nc]) { // 거리가 같은 물고기라면 거리가 가장 가까운 물고기가 많게 됨
                        if (fish[0] > nr) { // 가장 위에 있는 물고기보다 더 위에 있는 물고기라면, 가장 위에 있는 물고기가 1마리이므로 이 물고기로 갱신
                            fish[0] = nr;
                            fish[1] = nc;
                        } else if (fish[0] == nr) { // 가장 위에 있는 물고기와 같은 물고기라면 가장 위에 있는 물고기가 많게 됨
                            if (fish[1] > nc) { // 가장 왼쪽에 있는 물고기보다 더 왼쪽에 있는 물고기라면, 가장 왼쪽에 있는 물고기가 1마리이므로 이 물고기로 갱신
                                fish[0] = nr;
                                fish[1] = nc;
                            }
                        }
                    }
                }
                queue.add(new int[] { nr, nc });
            }
        }
    }

    /*
     * 아기 상어가 엄마 상어에게 도움을 요청하는 시간 출력하기
     */
    static void print() {
        System.out.println(time);
    }

    public static void main(String[] args) throws IOException {
        init();
        find();
        print();
    }
}