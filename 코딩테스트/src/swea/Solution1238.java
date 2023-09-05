package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution1238 {
    static int n, start;
    static List<Integer>[] g;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            g = new List[101];
            for(int i = 0; i <= 100; i++) g[i] = new ArrayList<>();
            for(int i = 0; i < n/2; i++){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                g[x].add(y);
            }
//            for(int i = 0; i <= 100; i++) System.out.println(g[i]);
            int ans = bfs();
            System.out.println("#" + tc + " " + ans);

        }
    }

    private static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[101];
        q.add(new int[]{start,0});
        visited[start] = true;
        int depth = 0;
        int ans = 0;
        while (!q.isEmpty()) {
            int[] xd = q.poll();
            if(depth < xd[1]){
                depth = xd[1];
                ans = xd[0];
            }
            else if(depth == xd[1]){
                ans = Math.max(ans, xd[0]);
            }
            for(int cur : g[xd[0]]){
                if (visited[cur]) continue;
                visited[cur] = true;
                q.add(new int[]{cur, xd[1]+1});
            }
        }

        return ans;
    }
}
