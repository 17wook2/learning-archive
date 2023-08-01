package boj;
import java.io.*;
import java.util.*;

public class boj2583 {

    private static int r,c,k;
    private static int[][] arr;
    private static boolean[][] visited;
    private static Queue<Node> q = new LinkedList<>();
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    private static class Node{
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[r][c];
        visited = new boolean[r][c];
        for (int ii = 0; ii < k; ii++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    arr[i][j] = 1;
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && arr[i][j] == 0) {
                    visited[i][j] = true;
                    ans.add(bfs(i,j));
                }
            }
        }
        sb.append(ans.size());
        sb.append('\n');
        Collections.sort(ans);
        for (Integer x: ans){
            sb.append(x);
            sb.append(' ');
        }
        System.out.println(sb);
    }
    private static int bfs(int x, int y){
        int cnt = 0;
        q.add(new Node(x,y));
        while (!q.isEmpty()) {
            Node node = q.poll();
            cnt += 1;
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if (0 <= nx && nx < r && 0 <= ny && ny < c && !visited[nx][ny] && arr[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx,ny));
                }
            }
        }
        return cnt;
    }
}