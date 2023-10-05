package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj17136 {
    private static int[][] arr;
    private static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[10][10];
        for(int i = 0; i < 10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MAX_VALUE;
        int[] left = new int[5];
        Arrays.fill(left, 5);
        go(0,left, arr, 0);
        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }
    private static void go(int idx, int[] left, int[][] map, int use){
        if(use >= ans) return;
//        for(int[] row: map) System.out.println(Arrays.toString(row));
        if(idx == 100){
            for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if (map[i][j] == 1) return;
                }
            }
            int cnt = 0;
            for(int i = 0; i < 5; i++){
                cnt += 5 - left[i];
            }
            ans = Math.min(ans,cnt);
            return;
        }
        int x = idx / 10;
        int y = idx % 10;
        if(map[x][y] == 0) go(idx+1, left, map, use);
        else{
            for(int k = 0; k < 5; k++){
                int d = left[k];
                if(d > 0 && check(x,y,k+1,map)){
                    int[][] new_map = copy(map);
                    remove(x,y,k+1,new_map);
                    left[k] -= 1;
                    go(idx+1, left, new_map, use + 1);
                    left[k] += 1;
                }
            }
        }
    }
    private static int[][] copy(int[][] map){
        int[][] new_map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++){
                new_map[i][j] = map[i][j];
            }
        }
        return new_map;
    }

    private static boolean check(int x, int y, int d, int[][] map) {
        for (int i = 0; i < d; i++) {
            for(int j = 0; j < d; j++){
                if(x + i >= 10 || y + j >= 10 || map[x+i][y+j] == 0) return false;
            }
        }
        return true;
    }
    private static void remove(int x, int y, int d, int[][] map){
        for (int i = 0; i < d; i++) {
            for(int j = 0; j < d; j++){
                map[x+i][y+j] = 0;
            }
        }
    }
}
