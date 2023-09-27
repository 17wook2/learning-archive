package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj12865 {
    private static int n,k;
    private static int[][] arr;
    private static int[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }
        dp = new int[n][k+1];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        System.out.println(go(0,0));
    }
    private static int go(int idx, int weight){
        if(weight > k) return -Integer.MAX_VALUE;
        if(idx == n) return 0;
        if(dp[idx][weight] != -1) return dp[idx][weight];
        dp[idx][weight] = Math.max(go(idx+1,weight + arr[idx][0]) + arr[idx][1], go(idx+1, weight));
        return dp[idx][weight];
    }
}
