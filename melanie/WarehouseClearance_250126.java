import org.junit.Assert;
import org.junit.Test;

// 250126. 창고 정리
public class WarehouseClearance_250126 {
    public String solution(String[] storage, int[] num) {
        int num_item = 0;
        String[] clean_storage = new String[storage.length]; // pencil, book
        int[] clean_num = new int[num.length]; // 9, 1

        for (int i = 0; i < storage.length; i++){
            int clean_idx = -1;
            for (int j = 0; j < num_item; j++){
                if(storage[i].equals(clean_storage[j])){ // 같은 종류인지 확인
                    clean_idx = j;
                    break;
                }
            }

            if (clean_idx == -1){ // 저장되어 있지 않다고 판단하고, 새로 저장
                clean_storage[num_item] = storage[i];
                clean_num[num_item] = num[i];
                num_item += 1;
            }
            else { // 이미 저장되어 있으므로 더해주기
                clean_num[clean_idx] += num[i];
            }
        }

        // 아래 코드에는 틀린 부분이 없습니다.

        int num_max = -1;
        String answer = "";
        for (int i = 0; i < num_item; i++) {
            if(clean_num[i] > num_max){
                num_max = clean_num[i];
                answer = clean_storage[i];
            }
        }
        return answer;
    }

    @Test
    public void test() {
        String[] storage = {"pencil", "pencil", "pencil", "book"};
        int[] num = {2, 4, 3, 1};

        Assert.assertEquals("pencil", solution(storage, num));
    }
}
