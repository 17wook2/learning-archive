package boj;

import java.util.*;

public class boj17472 {
    static int n,m,area_cnt;
    static int[][] arr;
    static int[][] map; // bfs한 배열
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static List<Edge> edgeList;
    static int[] p;
    static class Edge{
        int from, to, dist;

        public Edge(int[] edge) {
            this.from = edge[0];
            this.to = edge[1];
            this.dist = edge[2];
        }

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        map = new int[n][m];
        edgeList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) arr[i][j] = sc.nextInt();
        }
        area_cnt = bfs();
        edgeList = getEdgeList();
        edgeList.sort(Comparator.comparingInt(v -> v.dist));
        int res = kruskal();
        System.out.println(res);

    }
    private static int bfs(){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && arr[i][j] == 1){
                    q.add(new int[]{i,j});
                    visited[i][j] = true;
                    map[i][j] = ++cnt;
                    while (!q.isEmpty()) {
                        int[] xy = q.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = xy[0] + dx[k];
                            int ny = xy[1] + dy[k];
                            if(nx < 0 || nx >= n || ny < 0 || ny >= m || arr[nx][ny] == 0 || visited[nx][ny]) continue;
                            visited[nx][ny] = true;
                            map[nx][ny] = cnt;
                            q.add(new int[]{nx,ny});
                        }
                    }
                }
            }
        }
        return cnt;
    }
    private static List<Edge> getEdgeList(){
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0){ // 섬이라면 해당 위치에서 쭉 가보기
                    for(int k = 0; k < 4; k++) {
                        int[] int_edge = getEdge(i,j,k);
                        if (int_edge[1] == -1 && int_edge[2] == -1) continue; // 나갔거나 본인 영역
                        if(int_edge[2] < 2) continue; // 거리가 2 미만인 경우
                        Edge edge = new Edge(int_edge);
                        edgeList.add(edge);
                    }
                }
            }
        }
        return edgeList;
    }
    private static int[] getEdge(int x, int y, int d) {
        int cur = map[x][y];
        int res = 0;
        while (true) {
            x += dx[d]; y += dy[d];
            if (x < 0 || x >= n || y < 0 || y >= m || map[x][y] == cur) return new int[]{cur,-1,-1}; // from , to , dist
            if(map[x][y] != cur && map[x][y] >= 1) return new int[]{cur,map[x][y],res};
            res ++;
        }
    }
    private static int kruskal(){
        makeSet();
        int res = 0;
        int cnt = 0;
        for(Edge edge: edgeList){
            if(union(edge.from,edge.to)){
                res += edge.dist;
                if(++cnt == area_cnt-1) return res;
            }
        }
        return -1;
    }

    private static void makeSet(){
        p = new int[7];
        for(int i = 0; i <= 6; i++) p[i] = i;
    }

    private static int find(int a) {
        if(a == p[a]) return a;
        else return p[a] = find(p[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        p[bRoot] = aRoot;
        return true;
    }
}