// 340202. [PCCE 기출문제] 6번 / 물 부족
public class WaterShortage_340202 {
    public static void main(String[] args) {
        int storage = 1000, usage = 2000;
        int[] change = {-10, 25, -33};

        WaterShortage_340202 q = new WaterShortage_340202();
        System.out.println(q.solution(storage, usage, change));
    }

    public int solution(int storage, int usage, int[] change) {
        int total_usage = 0;
        for(int i=0; i<change.length; i++){
            usage += usage * change[i] / 100;
            total_usage += usage;
            if(total_usage > storage){
                return i;
            }
        }
        return -1;
    }
}
