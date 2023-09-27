package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj2636 {
    private static int r,c,ans;
    private static int map[][];
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        int time = 0;
        while (true){
            boolean check = go();
            time += 1;
            if(!check) break;
        }
        System.out.println(time-1);
        System.out.println(ans);

    }

    private static boolean go() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        boolean[][] visited = new boolean[r][c];
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = poll[0] + dx[k];
                int ny = poll[1] + dy[k];
                if(nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if(map[nx][ny] == 0) q.add(new int[]{nx,ny});
            }
        }
        int cur = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(map[i][j] == 1 && visited[i][j]){
                    map[i][j] = 0; cur ++;
                }
            }
        }
        if(cur == 0) return false;
        else{
            ans = cur;
            return true;
        }
    }
}
