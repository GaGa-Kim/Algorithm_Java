package Programmers.LV_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 42578) 베스트앨범
 */
public class L018_42579 {
    // music
    class music {
        // idx(고유 번호)
        int idx;
        // play(재생 횟수)
        int play;

        music(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
    }

    static ArrayList<String> genres_order;
    static ArrayList<Integer> answer;

    // genres(노래의 장르를 나타내는 문자열 배열)
    // plays(노래별 재생 횟수를 나타내는 정수 배열)
    public int[] solution(String[] genres, int[] plays) {
        // answer(결과 list)
        answer = new ArrayList<>();
        // 장르 순서 선정 함수
        selectGenreOrder(genres, plays);
        // 장르별 노래 선정 함수
        selectSongByGenre(genres, plays);
        // answer를 배열로 변환하여 반환
        return answer.stream().mapToInt(i -> i).toArray();
    }

    // 장르 순서 선정 함수
    private void selectGenreOrder(String[] genres, int[] plays) {
        // map(노래 장르별 재생횟수를 저장하는 hashMap)
        Map<String, Integer> map = new HashMap<>();
        // map에 노래 장르와 재생횟수를 저장
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        // genres_oder(재생횟수가 많은 순서대로 장르를 저장하는 list)
        genres_order = new ArrayList<>(map.keySet());
        // 리스트 정렬을 커스텀하여 내림차순으로 genres_order에 map 저장
        genres_order.sort((o1, o2) -> map.get(o2) - map.get(o1));
    }

    // 장르별 노래 선정 함수
    private void selectSongByGenre(String[] genres, int[] plays) {
        for (String genre : genres_order) {
            // musics(장르별 고유 번호와 재생 횟수를 담은 music을 저장하는 list)
            ArrayList<music> musics = new ArrayList<>();
            for (int i = 0; i < genres.length; i++) {
                // 장르가 동일하다면
                if (genre.equals(genres[i])) {
                    // musics에 music 저장
                    musics.add(new music(i, plays[i]));
                }
            }
            // 리스트 정렬을 커스텀하여 내림차순으로 musics 정렬
            musics.sort(new Comparator<music>() {
                @Override
                public int compare(music o1, music o2) {
                    // 재생횟수가 같다면
                    if (o1.play == o2.play) {
                        // 고유번호가 작은 순서대로 오름차순
                        return o1.idx - o2.idx;
                    }
                    // 재생횟수가 많은 순서대로 내림차순
                    return o2.play - o1.play;
                }
            });
            // answer에 musics 리스트의 노래 중 첫 번째 고유번호 저장
            answer.add(musics.get(0).idx);
            // musics의 크기가 1보다 크다면
            if (musics.size() > 1) {
                // answer에 musics 리스트의 노래 중 두 번째 고유번호 저장 (장르 별 최대 두 개까지 수록 가능)
                answer.add(musics.get(1).idx);
            }
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L018_42579 solution = new L018_42579();

        String[] genres = { "classic", "pop", "classic", "classic", "pop" };
        int[] plays = { 500, 600, 150, 800, 2500 };

        int[] result = solution.solution(genres, plays);

        System.out.println(Arrays.toString(result));
    }
}