import java.util.*;
// 42888. 오픈채팅방
public class OpenChatRoom_42888 {
    public String[] solution(String[] record) {

        LinkedHashMap<String, String> userMap = getUserMap(record);

        ArrayList<String> answer = new ArrayList<>();

        for (String rec : record) {
            // 문자열을 분리하기
            String[] tmp = rec.split(" ");
            String action = tmp[0]; // Enter, Leave, Change
            String id = tmp[1]; // 유저 아이디

            // 닉네임 변경은 넘어가기
            if (action.equals("Change")) continue;

            // 출력할 메시지 만들기
            StringBuilder sb = new StringBuilder();
            sb.append(userMap.get(id));
            if (action.equals("Enter")) { // 입장했을 때
                sb.append("님이 들어왔습니다.");
            } else if (action.equals("Leave")) { // 퇴장했을 때
                sb.append("님이 나갔습니다.");
            }
            answer.add(sb.toString());
        }

        return answer.toArray(String[]::new);

    }

    public LinkedHashMap<String, String> getUserMap(String[] record) {
        LinkedHashMap<String, String> userMap = new LinkedHashMap<>();

        for (String rec : record) {
            String[] tmp = rec.split(" ");
            String action = tmp[0];
            String id = tmp[1];
            String nickName = action.equals("Leave") ? "" : tmp[2];

            if (action.equals("Enter")) { // 입장했을 때
                userMap.put(id, nickName);
            } else if (action.equals("Leave")) { // 퇴장했을 때
                // userMap.remove(id);
            } else if (action.equals("Change")) { // 닉네임 변경했을 때 덮어씌우기
                userMap.replace(id, nickName);
            }
        }
        return userMap;
    }
}
