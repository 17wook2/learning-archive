package boj;

import java.util.Arrays;
import java.util.Scanner;

public class boj10971 {
    static int n,ans, w[][];
    static int[] b;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = new int[n][n];
        b = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) w[i][j] = sc.nextInt();
        }
        Arrays.fill(b, -1);
        ans = Integer.MAX_VALUE;
        // 1) dfs
        // 2) next per
        // 3) dp
    }

    private static void go(int cnt, int cur, int start, int cost) {
        if(cnt == n){
            // b[cnt-1]에 마지막 지점
            int new_cost = cost + w[cnt - 1][start];
            ans = Math.min(ans, new_cost);
            return;
        }
        for(int i = 0; i < n; i++){ // cnt개 방문
            b[cnt] = i; // cnt 번째에 i번 도시 방문
            go(cnt+1, i,start, cost + w[cur][i]);

        }

    }
}
