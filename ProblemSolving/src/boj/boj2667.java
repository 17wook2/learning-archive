package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj2667 {
    static boolean[][] visited;
    static int[][] arr;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    int cur = 0;
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                    while (!q.isEmpty()){
                        int[] node = q.poll();
                        cur += 1;
                        for(int k = 0; k < 4; k++){
                            int nx = node[0] + dx[k];
                            int ny = node[1] + dy[k];
                            if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || arr[nx][ny] == 0) continue;
                            visited[nx][ny] = true;
                            q.addLast(new int[]{nx,ny});
                        }
                    }
                    ans.add(cur);
                }
            }
        }
        Collections.sort(ans);
        System.out.println(ans.size());
        for(Integer x: ans) System.out.println(x);
    }
}
