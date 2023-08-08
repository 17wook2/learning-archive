package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution7733 {
    static int n, maxDay,ans;
    static int[][] a;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            n = Integer.parseInt(br.readLine());
            a = new int[n][n];
            maxDay = 0;
            ans = 0;
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    int cheese = Integer.parseInt(st.nextToken());
                    maxDay = Math.max(cheese, maxDay);
                    a[i][j] = cheese;
                }
            }
            for (int i = 0; i <= maxDay; i++) {
                int res = bfs(i);
                ans = Math.max(ans,res);
            }
            System.out.println("#"+tc+" "+ans);
        }

    }

    private static int bfs(int day) {
        visited = new boolean[n][n];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                if(!visited[i][j] && a[i][j] > day){
                    visited[i][j] = true;
                    q.add(new int[] {i,j});
                    cnt += 1;
                    while (!q.isEmpty()) {
                        int[] poll = q.pollFirst();
                        for(int k = 0; k < 4; k++){
                            int nx = poll[0] + dx[k];
                            int ny = poll[1] + dy[k];
                            if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || a[nx][ny] <= day) continue;
                            visited[nx][ny] = true;
                            q.addLast(new int[]{nx,ny});
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
