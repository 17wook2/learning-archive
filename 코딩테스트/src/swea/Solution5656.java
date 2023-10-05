package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution5656 {
    private static int n,w,h, ans;
    private static int[][] arr;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            arr = new int[h][w];
            ans = Integer.MAX_VALUE;
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            go(0, arr);
            System.out.println("#" + tc + " " + ans);

        }
    }

    private static void go(int idx, int[][] map) {
        if (idx == n){
            int cnt = 0;
            for(int i = 0; i < h; i++){
                for(int j = 0; j < w; j++){
                    if(map[i][j] > 0) cnt ++;
                }
            }
            ans = Math.min(ans,cnt);
            return;
        }

        // 구슬 떨어틀이기
        for (int j = 0; j < w; j++) {
            int i = 0;
            int[][] before = copy(map);
            while(i < h && before[i][j] == 0) i++;
            if(i == h) continue;
            int[][] after = bfs(before,i,j);
            fall(after);
            go(idx+1, after);
        }
        int cnt = 0;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(map[i][j] > 0) cnt ++;
            }
        }
        ans = Math.min(ans,cnt);
    }
    private static int[][] copy(int[][] map){
        int[][] new_map = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                new_map[i][j] = map[i][j];
            }
        }
        return new_map;
    }

    private static int[][] bfs(int[][] map,int x, int y) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];
        q.add(new int[]{x, y});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int move = map[cur[0]][cur[1]];
            for (int i = 0; i < 4; i++) {
                for(int k = 0; k < move; k++){
                    int nx = cur[0] + dx[i] * k;
                    int ny = cur[1] + dy[i] * k;
                    if(nx < 0 || nx >= h || ny < 0 || ny >= w || map[nx][ny] == 0 || visited[nx][ny]) continue;
                    q.add(new int[]{nx,ny});
                    visited[nx][ny] = true;
                }
            }
        }
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if (visited[i][j]) map[i][j] = 0;
            }
        }
        return map;
    }
    private static int[][] fall(int[][] map){
        for (int j = 0; j < w; j++) {
            for (int i = h - 2; i >= 0; i--) {
                if(map[i][j] != 0){
                    int idx = i + 1;
                    while(idx <= h-1 && map[idx][j] == 0) idx ++;
                    if(idx-1 == i) continue;
                    map[idx-1][j] = map[i][j];
                    map[i][j] = 0;
                }
            }
        }
    return map;
    }

}
