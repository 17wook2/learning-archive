package swea;

import java.util.Scanner;

public class Soluiton1247 {
    static int n,ans;
    static int[][] nodes;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            nodes = new int[n+2][2];
            visited = new boolean[n+2];
            ans = Integer.MAX_VALUE;
            nodes[0][0] = sc.nextInt(); nodes[0][1] = sc.nextInt();
            nodes[n+1][0] = sc.nextInt(); nodes[n+1][1] = sc.nextInt();
            for(int i = 1; i <= n; i++) {
                nodes[i][0] = sc.nextInt(); nodes[i][1] = sc.nextInt();
            }
            go(0,0,0);
            System.out.println("#" + tc + " " + ans);

        }
    }

    private static void go(int cnt, int start, int dist) {
        if(cnt == n){
            ans = Math.min(ans,dist + getDist(start,n+1));
            return;
        }
        for(int from = 1; from <= n; from++){
            if (visited[from]) continue;
            visited[from] = true;
            go(cnt+1, from,dist + getDist(start , from));
            visited[from] = false;
        }
    }
    private static int getDist(int start, int from){
        return Math.abs(nodes[start][0] - nodes[from][0]) + Math.abs(nodes[start][1] - nodes[from][1]);
    }
}
