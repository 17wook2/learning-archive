package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class boj10026 {
    static int n;
    static char[][] arr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];
        for(int i = 0; i < n; i++){
            arr[i] = br.readLine().toCharArray();
        }
        System.out.print(bfs(false) + " ");
        System.out.println(bfs(true));

    }
    private static int bfs(boolean check){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    q.add(new int[]{i, j});
                    cnt += 1;
                    while (!q.isEmpty()) {
                        int[] xy = q.poll();
                        int x = xy[0]; int y = xy[1];
                        for (int k = 0; k < 4; k++) {
                            int nx = xy[0] + dx[k];
                            int ny = xy[1] + dy[k];
                            if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                            if(visited[nx][ny]) continue;
                            if(check){ // 적록색약이면
                                if (arr[x][y] != 'B' && arr[nx][ny] != 'B') {
                                    visited[nx][ny] = true;
                                    q.add(new int[] {nx,ny});
                                }
                                else if(arr[x][y] == arr[nx][ny]){
                                    visited[nx][ny] = true;
                                    q.add(new int[]{nx,ny});
                                }
                            }else{
                                if(arr[x][y] == arr[nx][ny]){
                                    visited[nx][ny] = true;
                                    q.add(new int[]{nx,ny});
                                }

                            }
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
