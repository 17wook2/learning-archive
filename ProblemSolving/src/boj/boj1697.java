package boj;

import java.util.ArrayDeque;
import java.util.Scanner;

public class boj1697 {
    static int n,k;
    static int[] dx;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        dx = new int[3];
        dx[0] = -1; dx[1] = 1;
        go(n);
    }
    private static void go(int start){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[200001];
        q.add(new int[]{start,0});
        visited[start] = true;
        while (!q.isEmpty()){
            int[] xc = q.poll();
            int x = xc[0];
            dx[2] = x;
            if(x == k) {
                System.out.println(xc[1]);
                return;
            }
            for(int i = 0; i < 3; i++){
                int nx = x + dx[i];
                if(nx < 0 || nx >= 200000) continue;
                if (visited[nx]) continue;
                visited[nx] = true;
                q.add(new int[]{nx,xc[1] + 1});
            }
        }
    }
}
