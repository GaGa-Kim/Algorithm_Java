package Baekjoon.B_21942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/*
 * 문제 분석하기
 * : 회원 닉네임과 부품 이름에 따른 대여 시간을 저장한 후, 
 *   같은 회원 닉네임과 부품 이름을 가진 정보가 또 다시 들어온다면 두 시각을 계산해 벌금을 계산하도록 함
 */

/* 
 * 손으로 풀어보기
 * 1. 회원 닉네임과 부품 이름에 따른 대여 시간을 저장
 * 2. 같은 회원 닉네임과 부품 이름을 가진 정보가 또 다시 들어온다면 두 시각을 계산해 벌금을 계산
 * 3. 벌금을 내야하는 사람들을 사전순으로 정렬하여 출력
 *    만약 벌금을 내야하는 사람들이 없을 경우는 -1을 출력
 */

/*
 * 21942) 부품_대여장 
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, F;
    static int limit; // limit(대여 기간)
    static Map<Rent, String> renter = new HashMap<Rent, String>(); // renter(닉네임, 부품 이름에 따른 대여 기간)
    static Map<String, Long> fine = new HashMap<String, Long>(); // fine(벌금을 낼 닉네임과 벌금)

    /*
     * Rent(대여할 닉네임과 부품 이름에 대한 정보를 담을 클래스)
     */
    static class Rent {
        String nickname;
        String part;

        public Rent(String nickname, String part) {
            this.nickname = nickname;
            this.part = part;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Rent other = (Rent) obj;
            return nickname.equals(other.nickname) && part.equals(other.part);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nickname, part);
        }
    }

    /*
     * 데이터 준비하기
     */
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        String L = st.nextToken();
        int DDD = Integer.parseInt(L.substring(0, 3));
        int hh = Integer.parseInt(L.substring(4, 6));
        int mm = Integer.parseInt(L.substring(7, 9));
        limit = DDD * 24 * 60 + hh * 60 + mm;

        F = Integer.parseInt(st.nextToken());
    }

    /*
     * 대여 기간 계산하기
     */
    static void renter_period() throws IOException, ParseException {
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String date = st.nextToken();
            String time = st.nextToken();
            String part = st.nextToken();
            String nickname = st.nextToken();

            Rent rent = new Rent(nickname, part);
            if (!renter.containsKey(rent)) { // 대여라면
                renter.put(rent, date + " " + time);
            } else { // 반납이라면
                String start = renter.get(rent);
                String end = date + " " + time;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                long s = format.parse(start).getTime();
                long e = format.parse(end).getTime();
                long diff = ((e - s) / (1000 * 60));

                if (diff > limit) { // 대여기간을 넘었다면 벌금 부과
                    fine.put(nickname, fine.getOrDefault(nickname, 0L) + (diff - limit) * F);
                }
                renter.remove(rent);
            }
        }
    }

    /*
     * 벌금을 내야하는 사람들 출력하기
     */
    static void print() {
        if (fine.size() == 0) {
            System.out.println(-1);
        } else {
            List<String> keys = new ArrayList<String>(fine.keySet()); // 벌금을 내야하는 사람들을 사전순으로 정렬
            Collections.sort(keys);
            StringBuilder answer = new StringBuilder();
            for (String key : keys) {
                answer.append(key).append(" ").append(fine.get(key)).append("\n");
            }
            System.out.println(answer.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        renter_period();
        print();
    }
}