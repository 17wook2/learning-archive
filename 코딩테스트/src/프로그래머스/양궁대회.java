import java.util.*;
class Solution {
    static int max_diff;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        max_diff = -1;
        answer = new int[11];
        int[] right = new int[11];
        go(0,n,info,right);
        if(max_diff == -1) return new int[]{-1};
        else return answer;

    }
    private void go(int idx, int n, int[] left, int[] right){
        if(n < 0) return; // 화살 남지 않은 경우
        if(idx == 11){
            int lscore = 0;
            int rscore = 0;
            for(int i = 0; i<= 10; i++){
                if(left[i] >= right[i] && left[i] != 0) lscore += (10-i);
                else if(left[i] < right[i]) rscore += (10-i);
            }
            if(lscore >= rscore) return;
            int cur_diff = rscore - lscore;
            if(max_diff < cur_diff){
                max_diff = cur_diff;
                for(int i = 0; i <= 10; i++) answer[i] = right[i];
            }
            else if(max_diff == cur_diff){
                boolean check = false;
                // System.out.println(Arrays.toString(answer));
                // System.out.println(Arrays.toString(right));
                // System.out.println();
                for(int i = 10; i >= 0; i--){
                    if(answer[i] == right[i]) continue;
                    else if(answer[i] < right[i]) check = true;
                    else break;
                }
                if(check){
                    for(int i = 0; i <= 10; i++) answer[i] = right[i];
                }
            }
            return;
        }

        // right가 안쏘는 경우
        go(idx+1, n, left, right);

        // right가 쏘는 경우
        int need = left[idx] + 1;
        if(n >= need){
            right[idx] = need;
            go(idx+1, n-need, left, right);
            right[idx] = 0;
        }else{
            if(idx == 10){
                right[10] += n;
                go(idx+1, 0, left, right);
                right[10] -= n;
            }
        }

    }
}