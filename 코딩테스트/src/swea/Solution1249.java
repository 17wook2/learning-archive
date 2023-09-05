package swea;

import java.io.*;
import java.util.*;

public class Solution1249 {
    private static final int INF = 1000000000;
    private static int t,n;
    private static int[][] arr;
    private static int[][] dist;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    static class Node {
        int x,y,w;
        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1][n + 1];
            dist = new int[n + 1][n + 1];
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[j],INF);
            }
            dist[0][0] = 0;
            for (int j = 0; j < n; j++) {
                String[] row = br.readLine().split("");
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(row[k]);
                }
            }
            go();
            System.out.println("#"+(i+1)+" "+dist[n-1][n-1]);
        }
    }

    private static void go() {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (dist[node.x][node.y] + arr[nx][ny] < dist[nx][ny]) {
                    dist[nx][ny] = dist[node.x][node.y] + arr[nx][ny];
                    q.add(new Node(nx,ny,dist[nx][ny]));
                }
            }
        }
    }
}
