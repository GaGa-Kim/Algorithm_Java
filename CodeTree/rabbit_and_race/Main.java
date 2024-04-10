package CodeTree.rabbit_and_race;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 토끼와_경주
 */
public class Main {
    /*
     * Rabbit(토끼 정보를 담을 클래스)
     */
    static class Rabbit {
        int r, c; // r, c(토끼의 위치)
        int id; // id(고유번호)
        int dist; // dist(이동해야 하는 거리)
        int count; // count(점프 횟수)
        long score; // score(점수)
        boolean isSelected; // isSelected(K번의 턴 동안 한번이라도 뽑혔던 적이 있는지 여부)

        public Rabbit(int id, int dist) {
            r = 0;
            c = 0;
            this.id = id;
            this.dist = dist;
            count = 0;
            score = 0;
            isSelected = false;
        }
    }

    /*
     * 가장 우선순위가 높은 토끼를 뽑아 멀리 보내주기 위한 토끼 커스텀 정렬
     */
    static class RabbitComparatorForSelect implements Comparator<Rabbit> {
        @Override
        public int compare(Rabbit r1, Rabbit r2) {
            if (r1.count != r2.count)
                return Integer.compare(r1.count, r2.count); // 현재까지의 총 점프 횟수가 적은 토끼
            if (r1.r + r1.c != r2.r + r2.c)
                return Integer.compare(r1.r + r1.c, r2.r + r2.c); // 행 번호 + 열 번호가 작은 토끼
            if (r1.r != r2.r)
                return Integer.compare(r1.r, r2.r); // 행 번호가 작은 토끼
            if (r1.c != r2.c)
                return Integer.compare(r1.c, r2.c); // 열 번호가 작은 토끼
            return Integer.compare(r1.id, r2.id); // 고유번호가 작은 토끼
        }
    }

    /*
     * 가장 우선순위가 높은 토끼를 골라 점수 S를 더해주기 위한 토끼 커스텀 정렬
     */
    static class RabbitComparatorForIncrease implements Comparator<Rabbit> {
        @Override
        public int compare(Rabbit r1, Rabbit r2) {
            if (r1.r + r1.c != r2.r + r2.c)
                return Integer.compare(r2.r + r2.c, r1.r + r1.c); // 행 번호 + 열 번호가 큰 토끼
            if (r1.r != r2.r)
                return Integer.compare(r2.r, r1.r); // 행 번호가 큰 토끼
            if (r1.c != r2.c)
                return Integer.compare(r2.c, r1.c); // 열 번호가 큰 토끼
            return Integer.compare(r2.id, r1.id); // 고유번호가 큰 토끼
        }
    }

    /*
     * Point(토끼의 이동 위치를 담을 클래스)
     */
    static class Point {
        int r, c; // r, c(행, 열)

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    /*
     * 선택된 토끼를 상하좌우 네 방향으로 이동했을 때의 위치를 커스텀 정렬
     */
    static class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point point1, Point point2) {
            if (point1.r + point1.c != point2.r + point2.c) // 행 번호 + 열 번호가 큰 칸
                return Integer.compare(point2.r + point2.c, point1.r + point1.c);
            if (point1.r != point2.r) // 행 번호가 큰 칸
                return Integer.compare(point2.r, point1.r);
            return Integer.compare(point2.c, point1.c); // 열 번호가 큰 칸
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, P;
    static Rabbit[] rabbits = new Rabbit[10000001]; // rabbits(토끼들을 담을 배열)
    static PriorityQueue<Rabbit> rabbitSelectPq; // rabbitSelectPq(가장 우선순위가 높은 토끼를 뽑아 멀리 보내주기 위한 우선순위 큐)
    static PriorityQueue<Rabbit> rabbitIncreasePq; // rabbitIncreasePq(가장 우선순위가 높은 토끼를 골라 점수 S를 더해주기 위한 우선순위 큐)
    static int dr[] = new int[] { -1, 0, 1, 0 }; // dr, dc(상하좌우)
    static int dc[] = new int[] { 0, 1, 0, -1 };

    /*
     * 경주 시작 준비
     */
    static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken()); // N, M(격자 크기)
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()); // P(토끼의 수)
        rabbitSelectPq = new PriorityQueue<>(new RabbitComparatorForSelect());
        for (int i = 0; i < P; i++) {
            int pid = Integer.parseInt(st.nextToken()); // pid(고유 번호)
            int d = Integer.parseInt(st.nextToken()); // d(이동해야 하는 거리)
            Rabbit rabbit = new Rabbit(pid, d); // 배열과 우선순위 큐에 토끼들을 저장
            rabbits[pid] = rabbit;
            rabbitSelectPq.add(rabbit);
        }
    }

    /*
     * 경주 진행
     */
    static void playRace(StringTokenizer st) {
        int K = Integer.parseInt(st.nextToken()); // K(경주 진행 횟수)
        int S = Integer.parseInt(st.nextToken()); // S(K번의 턴이 모두 진행된 직후에 가장 우선순위가 높은 토끼에게 줄 점수)
        for (int i = 0; i < K; i++) {
            selectRabbit();
        }
        increaseBestRabbitScore(S);
    }

    /*
     * 토끼들을 담은 우선순위 큐를 커스텀 정렬하여 가장 우선순위가 높은 토끼 선택
     */
    static void selectRabbit() {
        Rabbit rabbit = rabbitSelectPq.poll();
        rabbit.isSelected = true; // 턴 동안 뽑혔으므로 갱신
        rabbit.count++; // 점프 횟수 증가
        selectPoint(rabbit);
    }

    /*
     * 선택된 토끼를 상하좌우 네 방향으로 이동했을 때의 위치를 커스텀 정렬하여 가장 우선순위가 높은 위치 선택
     */
    static void selectPoint(Rabbit rabbit) {
        PriorityQueue<Point> pointPq = new PriorityQueue<>(new PointComparator());
        for (int d = 0; d < 4; d++) { // 상하좌우 이동 후 위치 저장
            pointPq.add(move(rabbit, d));
        }
        Point nRC = pointPq.peek(); // 가장 우선순위가 높은 위치로 갱신
        rabbit.r = nRC.r;
        rabbit.c = nRC.c;
        int plusScore = rabbit.r + rabbit.c + 2; // plusScore(나머지 토끼들이 받을 점수)
        increaseRabbitsScore(plusScore);
        rabbitSelectPq.add(rabbit);
    }

    /*
     * 상하좌우로 이동
     */
    static Point move(Rabbit rabbit, int direction) {
        int r = rabbit.r;
        int c = rabbit.c;
        int dist = rabbit.dist;
        if (direction == 0 || direction == 2) { // 상하 이동일 경우
            dist %= N * 2 - 2;
        } else { // 좌우 이동일 경우
            dist %= M * 2 - 2;
        }
        while (dist != 0) {
            int distance = getMaxDistance(r, c, direction); // distance(최대 이동 거리)
            if (distance >= dist) { // 토끼가 이동할 거리가 최대 이동 거리보다 작거나 같을 경우
                r += dr[direction] * dist; // 방향 전환 없이 모두 이동 가능
                c += dc[direction] * dist;
                dist = 0;
            } else { // 토끼가 이동할 거리가 최대 이동 거리보다 클 경우
                r += dr[direction] * distance; // 이동 가능한 거리만 이동한 후
                c += dc[direction] * distance;
                dist -= distance;
                direction = (direction + 2) % 4; // 방향 전환
            }
        }
        return new Point(r, c);
    }

    /*
     * 이동 방향에 따른 최대 이동 거리 계산
     */
    static int getMaxDistance(int r, int c, int direction) {
        if (direction == 0) { // 위쪽 이동일 경우
            return r;
        } else if (direction == 1) { // 아래쪽 이동일 경우
            return M - c - 1;
        } else if (direction == 2) { // 왼쪽 이동일 경우
            return N - r - 1;
        } else { // 오른쪽 이동일 경우
            return c;
        }
    }

    /*
     * 선택된 토끼 외에 나머지 토끼들의 점수를 이동한 토끼의 위치인 행 번호 + 열 번호의 크기만큼 증가
     */
    static void increaseRabbitsScore(int plusScore) {
        for (Rabbit rabbit : rabbitSelectPq) {
            rabbit.score += plusScore;
        }
    }

    /*
     * 한 번이라도 뽑혔던 적 있던 토끼 중 가장 우선순위가 높은 토끼를 골라 S만큼 점수 증가
     */
    static void increaseBestRabbitScore(int S) {
        rabbitIncreasePq = new PriorityQueue<>(new RabbitComparatorForIncrease());
        for (Rabbit rabbit : rabbitSelectPq) {
            if (rabbit.isSelected) {
                rabbitIncreasePq.add(rabbit);
                rabbit.isSelected = false;
            }
        }
        Rabbit bestRabbit = rabbitIncreasePq.peek();
        bestRabbit.score += S;
    }

    /*
     * 이동거리 변경
     */
    static void updateDist(StringTokenizer st) {
        int pid_t = Integer.parseInt(st.nextToken()); // pid_t(고유번호)
        int L = Integer.parseInt(st.nextToken()); // L(증가시킬 이동거리의 배수)
        rabbits[pid_t].dist *= L; // 이동거리를 배수만큼 곱하여 증가
    }

    /**
     * 최고의 토끼 선정
     */
    static void selectBestRabbit() {
        long maxScore = Integer.MIN_VALUE;
        for (Rabbit rabbit : rabbitSelectPq) {
            if (maxScore < rabbit.score) { // 더 큰 점수가 있다면 갱신
                maxScore = rabbit.score;
            }
        }
        System.out.println(maxScore);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken()); // Q(명령의 수)
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken()); // command(명령)
            if (command == 100)
                init(st); // 경주 시작 준비
            else if (command == 200)
                playRace(st); // 경주 진행
            else if (command == 300)
                updateDist(st); // 이동거리 변경
            else if (command == 400)
                selectBestRabbit(); // 최고의 토끼 선정
        }
        br.close();
    }
}