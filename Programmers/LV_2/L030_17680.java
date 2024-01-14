package Programmers.LV_2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 17680) 캐시
 */
public class L030_17680 {
    // cacheSize(캐시크기)
    // cities(도시이름)
    public int solution(int cacheSize, String[] cities) {
        // answer(실행시간)
        int answer = 0;
        // queue(도시이름을 저장할 큐)
        Queue<String> queue = new LinkedList<>();
        for (String city : cities) {
            // upperCity(city를 대문자로 변환한 문자)
            String upperCity = city.toUpperCase();
            // cacheSize가 0이라면 (cache miss)
            if (cacheSize == 0) {
                // 모두 캐시를 사용하지 못하므로 answer += 5;
                answer += 5;
            }
            // queue에 upperCity가 존재한다면 (cache hit)
            else if (queue.contains(upperCity)) {
                // queue에서 꺼내 다시 queue에 저장
                Iterator<String> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    String element = iterator.next();
                    if (element.equals(upperCity)) {
                        iterator.remove();
                        break;
                    }
                }
                queue.offer(upperCity);
                // answer += 1;
                answer += 1;
            }
            // queue에 upperCity가 존재하지 않는다면 (cache miss)
            else {
                // queue의 크기가 cachedSize와 같다면
                if (queue.size() == cacheSize)
                    // queue의 가장 앞의 도시 꺼내기
                    queue.poll();
                // queue에 upperCity 저장
                queue.offer(upperCity);
                // answer += 5;
                answer += 5;
            }
        }
        // answer 반환
        return answer;
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L030_17680 solution = new L030_17680();

        int cacheSize = 0;
        String[] cities = { "LA", "LA" };

        int result = solution.solution(cacheSize, cities);

        System.out.println(result);
    }
}
