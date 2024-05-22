package Baekjoon.B_17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : T초 동안 미세먼지를 상하좌우 탐색을 통해 확산시키고, 공기청정기를 작동하여 미세먼지를 이동시키도록 함
 *   이때 공기청정기 위치로 이동하는 미세먼지는 정화되어 사라지도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 미세먼지를 상하좌우 탐색을 통해 확산
 * 2. 공기청정기를 작동하여 공기청정기의 위쪽은 반시계 방향, 아래쪽은 시계방향으로 순환해 미세먼지를 이동
 * 3. 공기청정기 위치로 이동하는 미세먼지는 사라지게 됨
 * 4. 이를 T번 반복한 후 남아있는 미세먼지의 양을 출력

/*
 * 17144) 미세먼지_안녕! 
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int R, C, T;
	static int[][] room; // room(방 정보)
	static int air = -1; // air(공기청정기의 위 위치 행)
	static int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 이동 좌표)
	static int[] dc = { 0, 0, -1, 1 };

	/*
	 * 데이터 저장하기
	 */
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		room = new int[R][C]; // 방 정보 저장하기
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == -1 && air == -1) { // 공기청정기 위치 저장하기
					air = i;
				}
			}
		}

		for (int t = 0; t < T; t++) { // T초 동안 미세먼지 확산 및 공기청정기 작동하기
			room = spread();
			activate();
		}
	}

	/*
	 * 미세먼지 확산하기
	 */
	static int[][] spread() {
		int[][] newRoom = new int[R][C]; // newRoom(미세먼지 확산에 따른 새로운 방 정보)

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (room[r][c] == -1) { // 공기청정기 위치 저장
					newRoom[r][c] = -1;
					continue;
				}

				newRoom[r][c] += room[r][c]; // 공기청정기 이외의 위치들
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C) { // 칸이 없으면 그 방향으로는 확산이 일어나지 않음
						continue;
					}
					if (room[nr][nc] == -1) { // 인접한 방향에 공기청정기가 있다면 확산이 일어나지 않음
						continue;
					}

					newRoom[nr][nc] += (room[r][c] / 5);
					newRoom[r][c] -= (room[r][c] / 5);
				}
			}
		}
		return newRoom;
	}

	/*
	 * 공기청정기 작동하기
	 */
	static void activate() {
		int top = air; // 위쪽 공기청정기의 바람은 반시계방향으로 순환
		for (int r = top - 1; r > 0; r--) { // 아래로 순환
			room[r][0] = room[r - 1][0];
		}
		for (int c = 0; c < C - 1; c++) { // 왼쪽으로 순환
			room[0][c] = room[0][c + 1];
		}
		for (int r = 0; r < top; r++) { // 위쪽으로 순환
			room[r][C - 1] = room[r + 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) { // 오른쪽으로 순환
			room[top][c] = room[top][c - 1];
		}
		room[top][1] = 0; // 공기청정기를 거쳐 나온 바람은 미세먼지가 없는 바람

		int bottom = air + 1; // 아래쪽 공기청정기의 바람은 시계방향으로 순환
		for (int r = bottom + 1; r < R - 1; r++) { // 위쪽으로 순환
			room[r][0] = room[r + 1][0];
		}
		for (int c = 0; c < C - 1; c++) { // 왼쪽으로 순환
			room[R - 1][c] = room[R - 1][c + 1];
		}
		for (int r = R - 1; r > bottom; r--) { // 아래쪽으로 순환
			room[r][C - 1] = room[r - 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) { // 오른쪽으로 순환
			room[bottom][c] = room[bottom][c - 1];
		}
		room[bottom][1] = 0; // 공기청정기를 거쳐 나온 바람은 미세먼지가 없는 바람
	}

	/*
	 * 방에 남아있는 미세먼지의 양 출력하기
	 */
	static void print() {
		int sum = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += room[i][j];
			}
		}
		System.out.println(sum);
	}

	public static void main(String[] args) throws IOException {
		init();
		print();
	}
}