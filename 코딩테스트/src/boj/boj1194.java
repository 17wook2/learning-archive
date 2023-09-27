package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj1194 {
    private static int n,m,sx,sy;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    private static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for(int i = 0; i < n; i++) {
            String[] split = br.readLine().split("");
            for(int j = 0; j < m; j++){
                char cur = split[j].charAt(0);
                if(cur == '0') {
                    sx = i; sy = j;
                }
                map[i][j] = cur;
            }
        }
        System.out.println(go());
    }

    private static int go() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx,sy,0,0});
        boolean[][][] visited = new boolean[n][m][1<<6];
        visited[sx][sy][0] = true;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int move = poll[2];
            int status = poll[3];
//            System.out.println(Arrays.toString(poll));
            if(map[poll[0]][poll[1]] == '1') return move;
            for(int k = 0; k < 4; k++){
                int nx = poll[0] + dx[k];
                int ny = poll[1] + dy[k];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny][status] || map[nx][ny] == '#') continue;
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f'){
                    int idx = map[nx][ny] - 'a';
                    int new_status = status | (1 << idx);
                    visited[nx][ny][new_status] = true;
                    q.add(new int[]{nx,ny,move+1,new_status});
                }else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F'){
                    int idx = map[nx][ny] - 'A';
                    if((status & 1<<idx) > 0){
                        visited[nx][ny][status] = true;
                        q.add(new int[]{nx,ny,move+1,status});
                    }
                } else{
                    visited[nx][ny][status] = true;
                    q.add(new int[]{nx,ny,move+1,status});
                }
            }
        }
        return -1;
    }
}
