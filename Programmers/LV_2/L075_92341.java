package Programmers.LV_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 92341) 주차_요금_계산
 */
public class L075_92341 {
    class Car {
        // inTime(입차 시간)
        String inTime;
        // outTime(출차 시간)
        String outTime;
        // prefixTime(누적 시간)
        int prefixTime;

        public Car(String inTime, String outTime, int prefixTime) {
            this.inTime = inTime;
            this.outTime = outTime;
            this.prefixTime = prefixTime;
        }
    }

    // fees(주차 요금)
    // records(자동차의 입/출차 내역)
    public int[] solution(int[] fees, String[] records) {
        // map(차량 번호와 입차 시간+누적 시간을 저장할 HashMap)
        Map<String, Car> map = new HashMap<>();
        for (int i = 0; i < records.length; i++) {
            // arr(records[i]를 공백으로 나눈 문자열 배열)
            String[] arr = records[i].split(" ");
            // arr[2]가 'IN'이라면
            if (arr[2].equals("IN")) {
                // map에 arr[1]이 존재한다면
                if (map.containsKey(arr[1])) {
                    // inTime을 arr[0]으로 변경, outTime을 0으로 갱신
                    map.get(arr[1]).inTime = arr[0];
                    map.get(arr[1]).outTime = "0";
                } else {
                    // map에 arr[1]과 Car 저장
                    map.put(arr[1], new Car(arr[0], "0", 0));
                }
            }
            // arr[2]가 'OUT'이라면
            else if (arr[2].equals("OUT")) {
                // map에서 arr[1]을 찾아 outTime을 갱신하고 prefixTime을 계산
                map.get(arr[1]).outTime = arr[0];
                map.get(arr[1]).prefixTime += calculateTime(map.get(arr[1]).inTime, map.get(arr[1]).outTime);
            }
        }
        // map을 돌면서
        for (String key : map.keySet()) {
            // outTime이 0이라면
            if (map.get(key).outTime.equals("0"))
                // outTime을 23:59로 하여 prefixTime을 계산
                map.get(key).prefixTime += calculateTime(map.get(key).inTime, "23:59");
        }
        // answer(차량 번호가 작은 자동차부터 청구할 주차 요금) 반환
        List<Integer> feesList = map.entrySet().stream()
                // 차량 번호가 작은 자동차부터 정렬
                .sorted(Comparator.comparing(Map.Entry::getKey))
                // prefixTime에 따른 요금을 계산하여 저장
                .map(entry -> calculateFee(entry.getValue().prefixTime, fees))
                .collect(Collectors.toList());
        return feesList.stream().mapToInt(Integer::intValue).toArray();
    }

    // 누적 시간 계산 함수
    private int calculateTime(String inTime, String outTime) {
        int inMinutes = Integer.parseInt(inTime.split(":")[0]) * 60 + Integer.parseInt(inTime.split(":")[1]);
        int outMinutes = Integer.parseInt(outTime.split(":")[0]) * 60 + Integer.parseInt(outTime.split(":")[1]);
        return outMinutes - inMinutes;
    }

    // 누적 시간에 따른 요금 계산 함수
    private int calculateFee(int prefixTime, int[] fees) {
        int fee = fees[1];
        if (prefixTime <= fees[0])
            return fee;
        else {
            int exceedingTime = prefixTime - fees[0];
            return fee + (exceedingTime / fees[2] + (exceedingTime % fees[2] == 0 ? 0 : 1)) * fees[3];
        }
    }

    // 테스트 케이스
    public static void main(String[] args) {
        L075_92341 solution = new L075_92341();

        int[] fees = { 180, 5000, 10, 600 };
        String[] records = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT" };

        int[] result = solution.solution(fees, records);

        System.out.println(Arrays.toString(result));
    }
}
