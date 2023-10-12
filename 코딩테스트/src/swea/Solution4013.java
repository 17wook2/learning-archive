package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution4013 {
    private static int k;
    private static LinkedList<Integer>[] map;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            k = Integer.parseInt(br.readLine());
            // 2번과 6번
            map = new LinkedList[4];
            for(int i = 0; i < 4; i++){
                map[i] = new LinkedList<>();
            }
            for(int i = 0; i < 4; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 8; j++){
                    map[i].add(Integer.parseInt(st.nextToken()));
                }
            }
            for(int i = 0; i < k; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                visited = new boolean[4];
                go(idx-1, direction);
            }
            int ans = 0;
            for(int i = 0; i < 4; i++){
                if(map[i].peek() == 1) ans += (1<<i);
            }
            System.out.println("#" + tc + " " + ans);
        }

    }

    // 회전시키는 바퀴, 방향
    private static void go(int cur, int direction) {
        visited[cur] = true;
        if(cur-1 >= 0 && !visited[cur-1]){
            if(map[cur].get(6).intValue() != map[cur-1].get(2).intValue()){
                go(cur-1, -direction);
            }
        }
        if(cur + 1 < 4 && !visited[cur+1]){
            if(map[cur].get(2).intValue() != map[cur+1].get(6).intValue()){
                go(cur+1, -direction);
            }
        }
        goRotate(cur, direction);
    }
    private static void goRotate(int cur, int direction){
        if(direction == 1){
            map[cur].addFirst(map[cur].pollLast());
        }else{
            map[cur].addLast(map[cur].pollFirst());
        }
    }
}
