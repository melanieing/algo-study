import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

// 120956. 옹알이 (1)
public class Babble1_120956 {
    public int solution(String[] babbling) {
        int answer = 0;

        String[] possibleSounds = {"aya", "ye", "woo", "ma"};

        // 1개로 만들 수 있는 단어들 먼저 넣어놓기
        ArrayList<String> possibleWords = new ArrayList<>(List.of(possibleSounds));

        // 2개로 만들 수 있는 단어들 넣기
        for (int i = 0; i < possibleSounds.length; i++) {
            for (int j = 0; j < possibleSounds.length; j++) {
                if (i == j) continue;
                possibleWords.add(possibleSounds[i] + possibleSounds[j]);
            }
        }

        // 3개로 만들 수 있는 단어들 넣기
        for (int i = 0; i < possibleSounds.length; i++) {
            for (int j = 0; j < possibleSounds.length; j++) {
                for (int k = 0; k < possibleSounds.length; k++) {
                    if (i == j || i == k || j == k) continue;
                    possibleWords.add(possibleSounds[i] + possibleSounds[j] + possibleSounds[k]);
                }
            }
        }

        // 4개로 만들 수 있는 단어들 넣기
        for (int i = 0; i < possibleSounds.length; i++) {
            for (int j = 0; j < possibleSounds.length; j++) {
                for (int k = 0; k < possibleSounds.length; k++) {
                    for (int l = 0; l < possibleSounds.length; l++) {
                        if (i == j || i == k || i == l || j == k || j == l || k == l) continue;
                        possibleWords.add(possibleSounds[i] + possibleSounds[j] + possibleSounds[k] + possibleSounds[l]);
                    }
                }
            }
        }

        // 발음할 수 있는 단어라면 카운트
        for (String s : babbling) {
            if (possibleWords.contains(s)) answer++;
        }

        return answer;

    }

    @Test
    public void test() {
        String[] babbling = {"aya", "yee", "u", "maa", "wyeoo"};
        Assert.assertEquals(1, solution(babbling));
    }
}
