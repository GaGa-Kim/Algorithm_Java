package Baekjoon.B_21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 좋아하는 학생에 따라 자리를 배치한 후, 학생의 만족도의 총 합을 출력함
 */

/* 
 * 손으로 풀어보기
 * 1. 학생 정보 저장
 * 2. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸을 선택
 * 3. 위를 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸을 선택
 * 4. 위를 만족하는 칸이 여러 개이면, 행의 번호가 가장 작은 칸을 선택
 * 5. 위를 만족하는 칸이 여러 개이면, 열의 번호가 가장 작은 칸을 선택
 * 6. 각 학생과 인접한 칸에 앉은 좋아하는 학생의 수를 구해 만족도를 구함
 * 7. 학생의 만족도의 총 합을 출력
 */

/*
 * 21608) 상어_초등학교  
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static Student[] students; // students(학생 정보 배열)
    static Student[][] board; // board(자리 배치 정보 배열)
    static int[] dr = { -1, 1, 0, 0 }; // dr, dc(상하좌우 좌표)
    static int[] dc = { 0, 0, -1, 1 };
    static int point; // point(만족도의 총 합)

    /*
     * Student(학생 정보를 담은 클래스)
     */
    static class Student {
        int id;
        ArrayList<Integer> likes;

        public Student(int id, ArrayList<Integer> likes) {
            this.id = id;
            this.likes = likes;
        }
    }

    /*
     * Seat(학생이 앉을 수 있는 자리 정보를 담은 클래스)
     */
    static class Seat implements Comparable<Seat> {
        int r, c;
        int friendCount;
        int emptyCount;

        public Seat(int r, int c, int friendCount, int emptyCount) {
            this.r = r;
            this.c = c;
            this.friendCount = friendCount;
            this.emptyCount = emptyCount;
        }

        @Override
        public int compareTo(Seat other) {
            if (this.friendCount != other.friendCount) {
                return Integer.compare(other.friendCount, this.friendCount);
            }
            if (this.emptyCount != other.emptyCount) {
                return Integer.compare(other.emptyCount, this.emptyCount);
            }
            if (this.r != other.r) {
                return Integer.compare(this.r, other.r);
            }
            return Integer.compare(this.c, other.c);
        }
    }

    /*
     * 자리 배치 준비하기
     */
    static void init(StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());

        students = new Student[N * N];
        for (int i = 0; i < N * N; i++) { // 학생 정보 저장
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            ArrayList<Integer> likes = new ArrayList<Integer>();
            for (int j = 0; j < 4; j++) {
                int like = Integer.parseInt(st.nextToken());
                likes.add(like);
            }
            students[i] = new Student(id, likes);
        }
    }

    /*
     * 자리 배치하기
     */
    static void select() {
        board = new Student[N][N];
        for (int i = 0; i < N * N; i++) {
            Student student = students[i];
            List<Seat> seats = new ArrayList<Seat>(); // seats(학생이 앉을 수 있는 자리들)
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int friendCount = 0;
                    int emptyCount = 0;
                    for (int d = 0; d < 4; d++) { // 인접한 칸에 있는 좋아하는 학생 칸 수와 비어있는 칸 수를 구함
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                            continue;
                        }
                        if (board[nr][nc] == null) {
                            emptyCount++;
                            continue;
                        }
                        for (int friend : student.likes) {
                            if (board[nr][nc].id == friend) {
                                friendCount++;
                            }
                        }
                    }
                    seats.add(new Seat(r, c, friendCount, emptyCount));
                }
            }
            Collections.sort(seats);
            for (Seat seat : seats) {
                if (board[seat.r][seat.c] == null) {
                    board[seat.r][seat.c] = student;
                    break;
                }
            }
        }
    }

    /*
     * 만족도 구하기
     */
    static void satisfied() {
        point = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int count = 0;
                Student student = board[r][c];
                for (int d = 0; d < 4; d++) { // 인접한 칸에 있는 좋아하는 학생 수를 구함
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }
                    for (int friend : student.likes) {
                        if (board[nr][nc].id == friend) {
                            count++;
                        }
                    }
                }
                if (count == 1) {
                    point += 1;
                } else if (count == 2) {
                    point += 10;
                } else if (count == 3) {
                    point += 100;
                } else if (count == 4) {
                    point += 1000;
                }
            }
        }
    }

    /*
     * 학생의 만족도의 총 합 출력하기
     */
    static void print() {
        System.out.println(point);
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init(st);
        select();
        satisfied();
        print();
    }
}