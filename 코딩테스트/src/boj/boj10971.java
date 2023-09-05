package boj;

import java.util.Scanner;

public class boj10971 {
    static int n,ans, w[][];
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) w[i][j] = sc.nextInt();
        }
        ans = Integer.MAX_VALUE;
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            visited[i] = true;
            ans = Math.min(ans,go(0,i,i,Integer.MAX_VALUE));
            visited[i] = false;
        }
        System.out.println(ans);
        // 1) dfs
        // 2) next per
        // 3) dp
    }

    private static int go(int cnt, int cur, int start, int cost) {
        if(cnt == n-1){
            if(w[cur][start]!= 0) return w[cur][start];
            return Integer.MAX_VALUE;
        }
        for(int i = 0; i < n; i++){
            if(visited[i] || w[cur][i] == 0) continue;
            visited[i] = true;
            cost = Math.min(cost, go(cnt+1, i,start, cost + w[cur][i]));
            visited[i] = false;
        }
        return cost;
    }
}
