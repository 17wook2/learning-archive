package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution1861 {
    static int[][] arr;
    static boolean[][] visited;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            visited = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ArrayDeque<int[]> q = new ArrayDeque<>();
            int max_space = 0;
            int min_number = 1001;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cur = 0;
                    int start = arr[i][j];
                    if(!visited[i][j]) {
                        q.add(new int[]{i,j});
                        visited[i][j] = true;
                        while (!q.isEmpty()){
                            int[] node = q.poll();
                            start = Math.min(start, arr[node[0]][node[1]]);
                            cur += 1;
                            for(int k = 0; k < 4; k++){
                                int nx = node[0] + dx[k];
                                int ny = node[1] + dy[k];
                                if(nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) continue;
                                if(Math.abs(arr[nx][ny] - arr[node[0]][node[1]]) == 1){
                                    visited[nx][ny] = true;
                                    q.addLast(new int[]{nx,ny});
                                }
                            }
                        }
                        if(cur == 0) continue;
                        if(max_space < cur) {
                            max_space = cur;
                            min_number = start;
                        } else if (max_space == cur) {
                            min_number = Math.min(min_number, start);
                        }
                    }
                }
            }
            System.out.println("#" + tc + " " + min_number + " " + max_space);
        }
        br.close();
    }
}
