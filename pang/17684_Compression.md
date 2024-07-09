## 문제

https://school.programmers.co.kr/learn/courses/30/lessons/17684?language=java



## 코드

```java
import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int len = msg.length();

        int lastAlphaNumber = 26;
        List<Integer> resultList = new ArrayList<>();
        List<String> diary = new ArrayList<>();

        int lt = 0, rt=0;
        String splitMsg = "";
        int result = 0;
        while (rt < len) {
          splitMsg = msg.substring(lt, rt+1);

          //1단어
          if (lt == rt) {
            result = splitMsg.charAt(0) - 'A' + 1;
            rt++;
            continue;
          }

          int indexOfDiary = diary.indexOf(splitMsg);
          if (indexOfDiary == -1) {
            //없는 단어입니다.
            diary.add(splitMsg);
            resultList.add(result);
            lt = rt;
          } else {
            //있는 단어입니다.
            result = lastAlphaNumber + indexOfDiary + 1;
            rt++;
          }
        }

        resultList.add(result);

        return resultList.stream().mapToInt(i -> i).toArray();
    }
}
```



## 해설

* two pointer 알고리즘