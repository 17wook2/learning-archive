package swea;

import java.util.*;
import java.io.*;
public class Solution4193 {

    private static int t,n, sx,sy,ex,ey;
    private static int[][] arr;
    private static boolean[][] visited;
    private static StringTokenizer st;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    static class Node {
        int x,y,time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());
        for (int i = 1; i < t+1; i++) {
            n = Integer.parseInt(br.readLine());
            visited = new boolean[n+1][n+1];
            arr = new int[n+1][n+1];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            st = new StringTokenizer(br.readLine());
            sx = Integer.parseInt(st.nextToken());
            sy = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            ex = Integer.parseInt(st.nextToken());
            ey = Integer.parseInt(st.nextToken());
            int ans = go();
            System.out.println("#"+i+" "+ans);
        }
    }
    private static int go() {
        LinkedList<Node> q = new LinkedList<>();
        Node start = new Node(sx, sy,0);
        q.add(start);
        visited[sx][sy] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int time = node.time;
            if (x == ex && y == ey) {
                return time;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visited[nx][ny] || arr[nx][ny] == 1) continue;
                if (arr[nx][ny] == 2) {
                    if (time % 3 != 2) {
                        visited[x][y] = true;
                        q.add(new Node(x,y,time+1));
                    }else{
                        visited[nx][ny] = true;
                        q.add(new Node(nx,ny,time+1));
                    }
                } else {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, time + 1));
                }
            }
        }
    return -1;
    }

}
