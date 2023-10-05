package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2458 {
    private static int n,m;
    private static boolean[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new boolean[n+1][n+1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
        }
        floid();
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for(int j = 1; j <= n; j++){
                if(graph[i][j] || graph[j][i]) cnt ++;
            }
            if(cnt == n-1) ans ++;
        }
        System.out.println(ans);
    }
    private static void floid(){
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++){
                    if(i == k || j == k) continue;
                    if(graph[i][k] && graph[k][j]) graph[i][j] = true;
                }
            }
        }
    }
}
