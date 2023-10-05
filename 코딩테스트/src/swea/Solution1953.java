package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1953 {

    private static int n,m,r,c,l;
    private static int[][] arr;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static int[][] check = {{1,2,4,7}, {1,3,4,5},{1,2,5,6},{1,3,6,7}};

    private static int[] up = {1,2,4,7};
    private static int[] right = {1,3,4,5};
    private static int[] down = {1,2,5,6};
    private static int[] left = {1,3,6,7};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int res = go();
            System.out.println("#" + tc + " " + res);
        }

    }
    private static int go(){
        boolean[][] visited = new boolean[n][m];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{r,c,1});
        visited[r][c] = true;
        while (!q.isEmpty()) {
//            for(int[] ele : q) System.out.print(Arrays.toString(ele));
//            System.out.println();
            int[] cur = q.poll();
            int time = cur[2];
            if(time >= l) continue;
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || arr[nx][ny] == 0) continue;
                for(int pipe: check[i]) {
                    if (arr[cur[0]][cur[1]] == pipe) {
                        int[] pipes = check[(i+2) % 4];
                        for(int next_pipe : pipes){
                            if (arr[nx][ny] == next_pipe){
                                visited[nx][ny] = true;
                                q.add(new int[]{nx,ny,time + 1});
                            }
                        }
                    }
                }

            }
        }
//        for(boolean[] row : visited) System.out.println(Arrays.toString(row));
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) ans ++;
            }
        }
        return ans;
    }
}
