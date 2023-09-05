package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj17144 {
    static int r,c,t;
    static int[][] arr;
    static int[][] difuse;
    static int[][] robot;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        int r_cnt = 0;
        robot = new int[2][2];
        arr = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1) robot[r_cnt++] = new int[]{i,j};
                arr[i][j] = x;
            }
        }
        for(int i = 0; i < t; i++){
            go1(); go2();
        }
        int ans = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(arr[i][j] != -1) ans += arr[i][j];
            }
        }
        System.out.println(ans);


    }

    private static void go1() {
        difuse = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(arr[i][j] >= 5){
                    int diff = arr[i][j] / 5;
                    for(int k = 0; k < 4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(!check(nx,ny)) continue;
                        if(arr[nx][ny] == -1) continue;
                        difuse[nx][ny] += diff;
                        difuse[i][j] -= diff;
                    }
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(arr[i][j] != -1) arr[i][j] += difuse[i][j];
            }
        }
    }

    private static void go2() {
        int sx = robot[0][0];
        int sy = robot[0][1];
        int cx = sx, cy = sy+1;
        int temp = arr[cx][cy];
        arr[cx][cy] = 0;
        int d = 1;
        while(true){
            if(cx == sx && cy == sy) break;
            int nx = cx + dx[d];
            int ny = cy + dy[d];
            if(!check(nx,ny)) {
                d = ((d + 4) - 1) % 4;
                continue;
            }
            int temp2 = arr[nx][ny];
            arr[nx][ny] = temp;
            temp = temp2;
            cx = nx; cy = ny;
        }
        arr[sx][sy] = -1;

        sx = robot[1][0]; sy = robot[1][1];
        cx = sx; cy = sy+1;
        temp = arr[cx][cy];
        arr[cx][cy] = 0;
        d = 1;
        while(true){
            if(cx == sx && cy == sy) break;
            int nx = cx + dx[d];
            int ny = cy + dy[d];
            if(!check(nx,ny)) {
                d = (d + 1) % 4;
                continue;
            }
            int temp2 = arr[nx][ny];
            arr[nx][ny] = temp;
            temp = temp2;
            cx = nx; cy = ny;
        }
        arr[sx][sy] = -1;
    }

    private static boolean check(int x, int y) {
        if(x < 0 || x >= r || y < 0 || y >= c) return false;
        return true;
    }
}
