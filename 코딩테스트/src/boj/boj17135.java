package boj;

import java.util.*;

public class boj17135 {
    static int n, m,d,ans;
    static int[] archers;
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt(); d = sc.nextInt();
        archers = new int[3];
        map = new int[n+1][m];
        ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++) map[i][j] = sc.nextInt();
        }
        // 1. 궁수 위치 뽑기 fight 함수
        batch(0,0);
        System.out.println(ans);
    }

    private static int fight() {
        int res = 0;
        int[][] kill = new int[3][2];
        for(int i = 0; i < 3; i++){
            int[] xy = find(i);
            kill[i][0] = xy[0]; kill[i][1] = xy[1];
        }
        for (int i = 0; i < 3; i++) {
            if (kill[i][0] == -1 && kill[i][1] == -1) continue;
            if(map[kill[i][0]][kill[i][1]] == 1){
                map[kill[i][0]][kill[i][1]] = 0;
                res += 1;
            }
        }
        move();
        return res;
    }
    private static int[] find(int idx){
        int x = n; int y = archers[idx];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> list = new ArrayList<>();
        boolean[][] visited = new boolean[n+1][m];
        q.add(new int[]{x,y,0});
        visited[x][y] = true;
        while (!q.isEmpty()){
            int[] xy = q.poll();
            for(int k = 0; k < 4; k++){
                int nx = xy[0] + dx[k];
                int ny = xy[1] + dy[k];
                if (nx < 0 || nx >= n+1 || ny < 0 || ny >= m) continue;
                if (visited[nx][ny]) continue;
                int dist = getDistance(x,y,nx,ny);
                if(dist <= d){
                    if (map[nx][ny] == 1) list.add(new int[]{nx,ny,dist});
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }
        }
        if(list.size() == 0) return new int[] {-1,-1};
        list.sort(Comparator.comparingInt((int[] v) -> v[2]).thenComparing(v -> v[1]));
        return list.get(0);
    }
    private static int getDistance(int x, int y, int a, int b){
        return Math.abs(x - a) + Math.abs(y - b);
    }

    private static void move() {
        for(int i = n-1; i >= 0; i--){
            for(int j = 0; j < m; j++){
                if (i == n-1 && map[i][j] == 1) map[i][j] = 0;
                if (i != n-1 && map[i][j] == 1){
                    map[i][j] = 0; map[i+1][j] = 1;
                }
            }
        }
    }
    private static void batch(int cnt, int start) {
        if(cnt == 3){
            int[][] new_map = new int[n+1][m];
            for(int i = 0; i <= n; i++){
                for(int j = 0; j < m; j++) new_map[i][j] = map[i][j];
            }
            int cur = 0;
            for(int i = 0; i < n; i++) cur += fight();
            ans = Math.max(ans,cur);
            map = new_map;
            return;
        }
        for(int i = start; i < m; i++){
            archers[cnt] = i;
            batch(cnt+1,i+1);
        }
    }
}