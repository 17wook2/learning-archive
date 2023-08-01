package swea;

import java.io.*;
import java.util.*;

public class Solution7465 {

    public static int t;
    public static ArrayList<Integer>[] graph;
    public static boolean[] visited;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ans = 0;
            int n = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n+1];
            for (int j = 1; j <= n; j++) {
                graph[j] = new ArrayList<>();
            }
            visited = new boolean[n+1];
            int m = Integer.parseInt(st.nextToken());
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                graph[y].add(x);
            }
            for (int j = 1; j < n+1; j++) {
                if (!visited[j]) {
                    ans += 1;
                    go(j);
                }
            }
            System.out.println("#"+(i+1)+" "+ans);
        }
    }

    public static void go(int n) {
        LinkedList<Integer> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            int x = q.poll();
            visited[x] = true;
            for (int node : graph[x]) {
                if (!visited[node]) {
                    visited[node] = true;
                    q.add(node);
                }
            }
        }
    }
}

