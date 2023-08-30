package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj10971_dp {
    static int n,ans, w[][], dp[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = new int[n][n];
        dp = new int[n][1<<n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) w[i][j] = sc.nextInt();
        }
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        ans = Integer.MAX_VALUE;


        System.out.println(ans);
        // 1) dfs
        // 2) next per
        // 3) dp
    }

    private static int go(int start, int visited) {
        if(visited == (1 << n) - 1){
            if(w[start][0] != 0) return w[start][0];
            return Integer.MAX_VALUE;
        }
        if(dp[start][visited] != Integer.MAX_VALUE) return dp[start][visited];
        for(int i = 0; i < n; i++){
            if(w[start][i] == 0 || (visited & (1 << i)) != 0) continue; // i로의 길이 없거나 방문하지 않았다면
            int next = visited | (1<<i);
            dp[start][visited] = Math.min(dp[start][visited], go(i,next) + dp[start][i]);
        }
        return dp[start][visited];
    }
}
