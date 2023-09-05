package boj;

import java.util.Scanner;

public class boj17070 {
    static int n, ans;
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) map[i][j] = sc.nextInt();
        }
        go(0,1,0,0);
        System.out.println(ans);
    }

    private static void go(int x, int y, int a, int b) {
        if((x == n-1 && y == n-1) || (a == n-1 && b == n-1)){
            ans += 1;
            return;
        }
        if(x == a && y - b == 1) {
            int nx = x, ny = y + 1, na = a, nb = b + 1;
            if (check(nx, ny) && check(na, nb) && map[nx][ny] != 1 && map[na][nb] != 1) go(nx, ny, na, nb);
            nx = x + 1;ny = y + 1;na = a;nb = b + 1;
            if (check(nx, ny) && check(na, nb) && map[nx][ny] != 1 && map[na][nb] != 1 && map[nx-1][ny] != 1 && map[nx][ny-1] != 1) go(nx, ny, na, nb);
        }
        else if(x - a == 1 && y == b){
            int nx = x+1, ny = y, na = a+1, nb = b;
            if(check(nx,ny) && check(na,nb) && map[nx][ny] != 1 && map[na][nb] != 1) go(nx,ny,na,nb);
            nx = x + 1; ny = y +1; na = a+1; nb = b;
            if(check(nx,ny) && check(na,nb) && map[nx][ny] != 1 && map[na][nb] != 1 && map[nx-1][ny] != 1 && map[nx][ny-1] != 1) go(nx,ny,na,nb);
        }
        else{
            int nx = x, ny = y+1, na = a+1, nb = b+1;
            if(check(nx,ny) && check(na,nb) && map[nx][ny] != 1 && map[na][nb] != 1) go(nx,ny,na,nb);
            nx = x + 1; ny = y; na = a+1; nb = b+1;
            if(check(nx,ny) && check(na,nb) && map[nx][ny] != 1 && map[na][nb] != 1) go(nx,ny,na,nb);
            nx = x + 1; ny = y+1; na = a+1; nb = b+1;
            if(check(nx,ny) && check(na,nb) && map[nx][ny] != 1 && map[na][nb] != 1 && map[nx-1][ny] != 1 && map[nx][ny-1] != 1) go(nx,ny,na,nb);
        }
    }
    private static boolean check(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= n) return false;
        return true;
    }
}
