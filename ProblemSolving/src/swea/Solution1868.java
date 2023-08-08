package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Solution1868 {
    static int n,ans;
    static char[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1,-1,-1,0,1,1,1,0};
    static int[] dy = {-1,0,1,1,1,0,-1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc += 1) {
            n = Integer.parseInt(br.readLine());
            arr = new char[n][n];
            visited = new boolean[n][n];
            ans = 0;
            for (int i = 0; i < n; i++) arr[i] = br.readLine().toCharArray();
            go();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(arr[i][j] == '.' && !visited[i][j]) ans += 1;
                }
            }
            System.out.println("#"+tc + " " +ans);


        }

    }

    private static void go() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == '.' && !visited[i][j]){
                    if(isNaive(i,j)){
                        ans += 1;
                        visited[i][j] = true;
                        q.addLast(new int[]{i,j});
                        while (!q.isEmpty()){
                            int[] node = q.poll();
                            for(int k = 0; k < 8; k++){
                                int nx = node[0] + dx[k];
                                int ny = node[1] + dy[k];
                                if(check(nx,ny) && arr[nx][ny] == '.' && !visited[nx][ny]){
                                    visited[nx][ny] = true;
                                    if(isNaive(nx,ny)) q.addLast(new int[]{nx,ny});
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean check(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }
    private static boolean isNaive(int x, int y){
        int cnt = 0;
        for(int k = 0; k < 8; k++){
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(!check(nx,ny)) cnt += 1;
            if(check(nx,ny) && arr[nx][ny] == '.') cnt += 1;
        }
        if(cnt == 8) return true;
        return false;
    }
}
