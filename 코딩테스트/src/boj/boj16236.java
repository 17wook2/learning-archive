package boj;

import java.util.*;

public class boj16236 {
    static int n,sx,sy,size,eat;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                int x = sc.nextInt();
                map[i][j] = x;
                if(x == 9) {
                    sx = i; sy = j;
                    map[i][j] = 0;
                }
            }
        }
        size = 2; eat = 0;
        int res = go();
        System.out.println(res);
        sc.close();

    }
    private static int go(){
        int time = 0;
        while (true) {
            List<int[]> fishList = getFishList();
            if(fishList.isEmpty()) break;
            fishList.sort(Comparator
                    .comparingInt((int[] v) -> v[2])
                    .thenComparingInt(v -> v[0])
                    .thenComparingInt(v -> v[1]));
            int[] fish = fishList.get(0);
            int x = fish[0]; int y = fish[1]; int d = fish[2];
            map[x][y] = 0; // 물고기가 먹힘
            sx = x; sy = y; // 현재 위치 변경
            eat += 1;
            time += d;
            if(eat == size) {
                size += 1; eat = 0;
            }
        }
        return time;
    }
    private static List<int[]> getFishList(){
        ArrayList<int[]> list = new ArrayList<>(); // 물고기를 담을 리스트
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        q.add(new int[]{sx,sy,0});
        visited[sx][sy] = true;
        while (!q.isEmpty()) {
            int[] xyd = q.poll();
            int x = xyd[0]; int y = xyd[1]; int d = xyd[2];
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(visited[nx][ny]) continue;
                if(size < map[nx][ny]) continue; // 자신 보다 큰 곳은 지나갈 수 없음
                if (map[nx][ny] != 0 && size > map[nx][ny]) list.add(new int[] {nx,ny,d+1}); // 물고기가 있는 칸이라면 리스트에 추가
                visited[nx][ny] = true;
                q.add(new int[] {nx,ny,d+1});
            }
        }
        return list;
    }


}
