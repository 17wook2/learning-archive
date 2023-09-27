package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj3055 {
    private static int r,c,sx,sy;
    private static char[][] map;
    private static List<int[]> wlist;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        wlist = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < c; j++) {
                char cur = split[j].charAt(0);
                if(cur == 'S'){
                    sx = i; sy = j;
                }else if(cur == '*'){
                    wlist.add(new int[]{i, j});
                }
                map[i][j] = cur;
            }
        }
        int res = go();
        if(res == -1) System.out.println("KAKTUS");
        else System.out.println(res);
    }
    private static int go(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[r][c];
        for(int[] w: wlist){
            int x = w[0]; int y = w[1];
            q.add(new int[]{x,y,1,0});
            visited[x][y] = 2;
        }
        q.add(new int[]{sx,sy,0,0});
        visited[sx][sy] = 1;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
//            System.out.println(Arrays.toString(poll));
            int move = poll[3];
            if(map[poll[0]][poll[1]] == 'D') return move;
            for (int k = 0; k < 4; k++) {
                int nx = poll[0] + dx[k];
                int ny = poll[1] + dy[k];
                if(nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 'X') continue;
                if(poll[2] == 1){ // 물이라면
                    if(visited[nx][ny] <= 1 && map[nx][ny] != 'D'){
                        visited[nx][ny] = 2;
                        q.add(new int[]{nx,ny,1,move+1});
                    }
                } else if(poll[2] == 0){
                    if (visited[nx][ny] == 0){
                        visited[nx][ny] = 1;
                        q.add(new int[]{nx,ny,0,move+1});
                    }
                }
            }
        }
        return -1;
    }
}
