package swea;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution1251 {
    static int n,tc;
    static double e;
    static int[][] islands;
    static double[][] g;
    static boolean[] visited;
    static double[] minEdge;
    static class Vertex{
        int v;
        double dist;

        public Vertex(int v, double dist) {
            this.v = v;
            this.dist = dist;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(tc = 1; tc <= t; tc++){
            n = sc.nextInt();
            g = new double[n][n];
            islands = new int[n][2];
            visited = new boolean[n];
            minEdge = new double[n];
            for(int i = 0; i < n; i++){
                int x = sc.nextInt(); islands[i][0] = x;
            }
            for (int i = 0; i < n; i++) {
                int y = sc.nextInt(); islands[i][1] = y;
            }
            e = sc.nextDouble();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] = getDist(i,j);
                }
            }

            PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> v.dist));
            double res = 0;
            pq.add(new Vertex(0,0.0));
            Arrays.fill(minEdge,Integer.MAX_VALUE);
            minEdge[0] = 0;
            int cnt = 0; double minDist;
            int minVertex;
            while (!pq.isEmpty()) {
                Vertex cur = pq.poll();
                minVertex = cur.v;
                minDist = cur.dist;
                if(visited[minVertex]) continue;
                visited[minVertex] = true;
                res += minDist*minDist;
                if(cnt++ == n-1) break;
                for(int j = 0; j < n; j++){
                    if(!visited[j] && g[minVertex][j] != 0 && minEdge[j] > g[minVertex][j]){
                        minEdge[j] = g[minVertex][j];
                        pq.add(new Vertex(j,minEdge[j]));
                    }
                }
            }
            System.out.printf("#" + tc + " " + "%.0f", e * res);
            System.out.println();
        }
    }

    private static double getDist(int i, int j) {
        int ix = islands[i][0];
        int iy = islands[i][1];

        int jx = islands[j][0];
        int jy = islands[j][1];
        int a = Math.abs(ix - jx);
        int b = Math.abs(iy - jy);
        double dist = Math.sqrt((Math.pow(a,2) + Math.pow(b,2)));
        return dist;
    }
}
