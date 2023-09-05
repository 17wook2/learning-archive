package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj17069 {
    static int n;
    static int[][] map;
    static long[][][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        dp = new long[n][n][3];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) Arrays.fill(dp[i][j], -1);
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) map[i][j] = sc.nextInt();
        }
        //TOP - down Approach
        System.out.println(go(0,1,0));

    }
    private static long go(int x, int y, int d){
        if(x < 0 || x >= n || y < 0 || y >= n || map[x][y] == 1) return 0;
        if(d == 2 && (map[x][y-1] == 1 || map[x-1][y] == 1)) return 0;
        if (x == n-1 && y == n-1) return 1;
        if(dp[x][y][d] != -1) return dp[x][y][d];
        dp[x][y][d] = 0;
        if(d == 0) dp[x][y][d] += go(x,y+1,d) + go(x+1,y+1,2);
        if(d == 1) dp[x][y][d] += go(x+1,y,d) + go(x+1,y+1,2);
        if(d == 2) dp[x][y][d] += go(x,y+1,0) + go(x+1,y,1) + go(x+1,y+1,2);
        return dp[x][y][d];
    }
}
