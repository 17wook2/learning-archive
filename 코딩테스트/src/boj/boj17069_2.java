package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj17069_2 {
    static int n;
    static int[][] map;
    static long[][][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n+1][n+1];
        dp = new long[n+1][n+1][3];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++) map[i][j] = sc.nextInt();
        }
        dp[1][2][0] = 1;
        //Bottom - up Approach
        // 가로 -> 가로, 대각선 - > 가로
        // 세로 -> 세로, 대각선 -> 세로
        // 가로 -> 대각선, 세로 -> 대각선, 대각선 -> 대각선
        for(int i = 1; i <= n; i++){
            for(int j = 3; j <= n; j++){
                if (map[i][j] == 1) continue;
                dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
                if(map[i][j-1] != 1 && map[i-1][j] != 1){
                    dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                }
            }
        }
        System.out.println(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);

    }

}
