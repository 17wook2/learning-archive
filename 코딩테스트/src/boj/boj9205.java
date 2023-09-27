package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj9205 {
    private static int n;
    private static int[][] dist, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; tc++){
            n = Integer.parseInt(br.readLine());
            arr = new int[n+2][2];
            dist = new int[n+2][n+2];
            for (int i = 0; i < n + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[i] = new int[]{x,y};
            }
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    dist[i][j] = getdist(i,j);
                }
            }
            if (go()) System.out.println("happy");
            else System.out.println("sad");
        }
    }

    private static boolean go() {
        // 시작점은 0번 도착점은 n+1 각 지점 들어가며 거리 1000이하인가
        boolean[] visited = new boolean[n+2];
        visited[0] = true;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        while (!q.isEmpty()) {
            int x = q.poll();
            if(x == n+1) return true;
            for(int i = 0; i < n+2; i++){
                if(i == x || visited[i]) continue;
                if(dist[x][i] <= 1000){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
        return false;
    }
    private static int getdist(int i, int j){
        return Math.abs(arr[i][0] - arr[j][0]) + Math.abs(arr[i][1] - arr[j][1]);
    }
}
