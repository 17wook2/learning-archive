package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj1149 {
    static int n;
    static int[][] map, dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][3];
        dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < 3; j++) map[i][j] = sc.nextInt();
        }
        for(int j = 0; j < 3; j++) dp[0][j] = map[0][j];
        go();
        Arrays.sort(dp[n-1]);
        System.out.println(dp[n-1][0]);
//        for(int[] row : dp) System.out.println(Arrays.toString(row));
    }

    private static void go() {
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + map[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + map[i][2];
        }
    }
}
