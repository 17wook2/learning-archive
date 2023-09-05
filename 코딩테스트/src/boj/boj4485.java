package boj;
import java.io.*;
import java.util.*;

public class boj4485 {
    private static final int INF = 1000000000;
    private static int n;
    private static int[][] arr, dist;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    static class Node implements Comparable<Node>{
        int x,y,w;

        public Node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int cnt = 1;
        while(true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            arr = new int[n+1][n+1];
            dist = new int[n+1][n+1];

            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i],Integer.MAX_VALUE);
            }
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine()," ");
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int res = dijkstra();
            sb.append("Problem " + cnt++ + ": " + res);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int dijkstra(){
        dist[0][0] = arr[0][0];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,arr[0][0]));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            int x = poll.x;
            int y = poll.y;
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (dist[nx][ny] > dist[x][y] + arr[nx][ny]) {
                        dist[nx][ny] = dist[x][y] + arr[nx][ny];
                        pq.add(new Node(nx, ny, arr[nx][ny]));
                    }
                }
            }
        }
        return dist[n-1][n-1];
    }

}

