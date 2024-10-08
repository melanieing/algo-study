import java.util.*;

// 17684. [3차] 압축

class Compression_17684 {
    public int[] solution(String msg) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        // 사전 초기화
        HashMap<String, Integer> dictionary = new HashMap<>();
        for (int i = 1; i <= 26; i++) {
            dictionary.put(Character.toString((char)(i+64)), i);
        }

        int lt = 0;
        int rt = 0;
        int tmp = 0;
        while (rt < msg.length()) {
            String w = msg.substring(lt, rt+1);
            if (dictionary.containsKey(w)) { // 사전에 있으면
                tmp = dictionary.get(w);
                rt++;
            } else { // 사전에 없으면
                dictionary.put(w, dictionary.size()+1);
                lt = rt;
                answer.add(tmp);
            }
        }
        
        // 마지막 글자 출력
        answer.add(tmp);
        
          
        return answer.stream().mapToInt(i -> i).toArray();      
    }
}
