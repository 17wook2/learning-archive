package boj;

import java.util.ArrayDeque;
import java.util.Scanner;

public class boj14502 {
    static int n,m,ans;
    static int[] b = new int[3];
    static int[][] arr;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        arr = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) arr[i][j] = sc.nextInt();
        }
        ans = 0;
        comb(0,0);
        System.out.println(ans);
    }
    private static int bfs(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && arr[i][j] == 2){
                    q.add(new int[] {i,j});
                    while (!q.isEmpty()) {
                        int[] xy = q.poll();
                        int x = xy[0], y = xy[1];
                        for(int k = 0; k < 4; k++){
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                            if(visited[nx][ny]) continue;
                            if(arr[nx][ny] == 1) continue;
                            visited[nx][ny] = true;
                            arr[nx][ny] = 2;
                            q.add(new int[]{nx,ny});
                        }
                    }
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
               if(arr[i][j] == 0) cnt += 1;
            }
        }
        return cnt;
    }
    private static void comb(int cnt, int number){
        if(cnt == 3){
            int[][] new_map = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    new_map[i][j] = arr[i][j];
                }
            }
//            System.out.println(Arrays.toString(b));
            for (int i = 0; i < 3; i++) {
                int row = b[i] / m;
                int col = b[i] % m;
                arr[row][col] = 1;
            }
//            for(int[] row:arr) System.out.println(Arrays.toString(row));
            int res = bfs();
            ans = Math.max(ans, res);
            arr = new_map;
            return;
        }
        for(int x = number; x < n*m; x++){
            int row = x / m;
            int col = x % m;
            if(arr[row][col] == 1 || arr[row][col] == 2) continue;
            b[cnt] = x;
            comb(cnt+1,x+1);
        }
    }
}
