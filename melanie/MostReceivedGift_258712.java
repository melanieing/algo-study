import java.util.*;
// 258712. 가장 많이 받은 선물
public class MostReceivedGift_258712 {

    public static void main(String[] args) {
        String[] friends = {"muzi", "ryan", "frodo", "neo"}; // 친구들의 이름
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"}; // 이번 달까지 친구들이 주고받은 선물 기록을

        MostReceivedGift_258712 q = new MostReceivedGift_258712();
        System.out.println(q.solution(friends, gifts)); // 2(선물을 가장 많이 받을 친구가 받을 선물의 수)
    }

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        // 이번달 선물통계(이름, 상대이름, 선물횟수)
        Map<String, Map<String, Integer>> thisMonthMap = new HashMap<>();
        for (String friend : friends) {
            thisMonthMap.put(friend, new HashMap<>());
        }

        // 준 기록과 받은 기록 (이름, 횟수)
        Map<String, Integer> sendMap = new HashMap<>();
        Map<String, Integer> receiveMap = new HashMap<>();
        for (String gift : gifts) {
            String[] tmp = gift.split(" ");
            String sender = tmp[0];
            String receiver = tmp[1];
            int cnt = thisMonthMap.get(sender).getOrDefault(receiver, 0);
            thisMonthMap.get(sender).put(receiver, cnt + 1);

            sendMap.put(sender, sendMap.getOrDefault(sender, 0) + 1);
            receiveMap.put(receiver, receiveMap.getOrDefault(receiver, 0) + 1);
        }

        Map<String, Integer> nextMonthMap = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            for (int j = 0; j < i; j++) {
                String first = friends[i];
                String second = friends[j];

                int firstToSecond = thisMonthMap.get(first).getOrDefault(second, 0);
                int secondToFirst = thisMonthMap.get(second).getOrDefault(first, 0);

                if (firstToSecond > secondToFirst) { // first가 더 많이 줬으면 내달에는 더 받아야 함
                    nextMonthMap.put(first, nextMonthMap.getOrDefault(first, 0) + 1);
                } else if (firstToSecond < secondToFirst) { // second가 더 많이 줬으면 내달에는 더 받아야 함
                    nextMonthMap.put(second, nextMonthMap.getOrDefault(second, 0) + 1);
                } else { // 동일하게 주고 받았다면 선물 지수로 판단
                    int firstGiftIdx = sendMap.getOrDefault(first, 0) - receiveMap.getOrDefault(first, 0);
                    int secondGiftIdx = sendMap.getOrDefault(second, 0) - receiveMap.getOrDefault(second, 0);
                    if (firstGiftIdx > secondGiftIdx) { // first가 선물지수가 더 높으면 더 받아야 함
                        nextMonthMap.put(first, nextMonthMap.getOrDefault(first, 0) + 1);
                    } else if (firstGiftIdx < secondGiftIdx) { // second가 선물지수가 더 높으면 더 받아야 함
                        nextMonthMap.put(second, nextMonthMap.getOrDefault(second, 0) + 1);
                    } // 같으면 아무도 더 받지 않음
                }
            }
        }

        for (int value : nextMonthMap.values()) {
            answer = Math.max(value, answer);
        }

        return answer;
    }

}
