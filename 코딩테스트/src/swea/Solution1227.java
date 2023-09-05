package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution1227 {
    static int ans;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int tc = 1; tc <= 10; tc++){
            br.readLine();
            arr = new int[101][101];
            visited = new boolean[101][101];
            int sx = 0, sy = 0, ex = 0, ey = 0;
            for(int i = 0; i < 100; i++){
                String[] s = br.readLine().split("");
                for(int j = 0; j < 100; j++) {
                    if (s[j].equals("2")) {
                        sx = i; sy = j;
                    }
                    if(s[j].equals("3")){
                        ex = i; ey = j;
                    }
                    arr[i][j] = Integer.parseInt(s[j]);
                }
            }
            ArrayDeque<int[]> q = new ArrayDeque<>();
            q.add(new int[]{sx,sy});
            visited[sx][sy] = true;
            ans = 0;
            while (!q.isEmpty()) {
                int[] node = q.pollFirst();
                if (node[0] == ex && node[1] == ey) {
                    ans = 1;
                    break;
                }
                for(int i = 0; i < 4; i++){
                    int nx = node[0] + dx[i];
                    int ny = node[1] + dy[i];
                    if (nx < 0 || nx > 100 || ny < 0 || ny > 100 || visited[nx][ny] || arr[nx][ny] == 1) continue;
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }
            System.out.println("#"+tc+" "+ans);
        }
    }
}
