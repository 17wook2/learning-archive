package boj;

import java.util.Scanner;

public class boj1987 {
    static int r, c, ans;
    static boolean[] visited;
    static char[][] arr;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt(); c= sc.nextInt();
        visited = new boolean[26];
        arr = new char[r][c];
        for(int i = 0; i < r; i++){
            arr[i] = sc.next().toCharArray();
        }
        ans = 0;
        go(0,0,1);
        System.out.println(ans);
    }
    private static void go(int x , int y, int cnt){
        visited[(int)arr[x][y] - 65] = true;
        int cur = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!check(nx,ny)) {
                cur += 1; continue;
            }
            int idx = (int) arr[nx][ny] - 65;
            if(visited[idx]) {
                cur += 1; continue;
            }
            visited[idx] = true;
            go(nx,ny,cnt+1);
            visited[idx] =false;

        }
        if (cur == 4) ans = Math.max(ans, cnt);
    }

    private static boolean check(int x, int y) {
        if (x < 0 || x>= r || y < 0 ||y >= c) return false;
        return true;
    }
}
